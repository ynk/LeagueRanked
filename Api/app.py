import datetime
import concurrent.futures
import config
import stats
from flask import Flask, json, jsonify, redirect, url_for
from riot import RiotRequest
from riot.riot_responses import *
from api_responses import User


app = Flask(__name__)
usernames = [("Hide on bush", "kr"), ("Nightblue3", "na1")]


def fetch_user(server, name) -> User:
    global app
    riot_req = RiotRequest(config.api_key)
    summoner = riot_req.summoner_by_name(server, name)
    app.logger.info(summoner)

    if summoner:
        app.logger.info(f"Fetched summoner {server}:{name}")

        ranked, current_game = None, None
        with concurrent.futures.ThreadPoolExecutor(max_workers=2) as executor:
            # Attempt to make it faster -- no luck with asyncio due to number of sub function calls
            one = executor.submit(riot_req.entries_by_summoner, server, summoner.id)
            two = executor.submit(riot_req.activate_game, server, summoner.id)

            ranked = one.result()
            current_game = two.result()

        if ranked:
            app.logger.info(f"Fetched summoner ranked {server}:{name}")
            solo = list(filter(lambda row: row.queueType == 'RANKED_SOLO_5x5', ranked))
            flex = list(filter(lambda row: row.queueType == 'RANKED_FLEX_SR', ranked))
            in_game = current_game.gameId > 0 if current_game else False # may or may not be correct

            solo = stats.summary(solo[0]) if solo else "Unranked"
            flex = stats.summary(flex[0]) if flex else "Unranked"

            return User(
                username=summoner.name,
                level=summoner.summonerLevel,
                revisionDate=summoner.revisionDate,
                id=summoner.id,
                solo=solo,
                flex=flex,
                profileIconId=f"https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/profile-icons/{summoner.profileIconId}.jpg",
                server=server,
                utc=datetime.datetime.utcfromtimestamp(summoner.revisionDate / 1000),
                ingame=in_game
            )

    status_code = riot_req.last_failure.status_code if riot_req.last_failure else "unknown"
    app.logger.error(f"Failed {status_code} fetching summoner/ranked {server}:{name}")

    
def fetchData():
    global usernames, app
    return list(map(lambda us: fetch_user(us[1], us[0]), usernames))


@app.route('/')
@app.route('/list')  # w/e endpoint u want
def forcedlist():
    results = fetchData()
    return jsonify(results)


if __name__ == '__main__':
    app.run(port=2888)
