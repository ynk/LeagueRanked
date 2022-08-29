import datetime
from flask import Flask, json, jsonify, redirect, url_for
app = Flask(__name__)
import requests
import json
import config
usernames = [["Hide on bush", "kr"], ["Nightblue3", "na1"]]

@app.route('/list')  # w/e endpoint u want
def forcedlist():
    result = fetchData()
    lol = {"result": result}

    return jsonify(lol)


def fetchData():
    result = []
    for player, server in usernames:
        sumreq = requests.get(
            "https://{0}.api.riotgames.com/lol/summoner/v4/summoners/by-name/{1}?api_key={2}".format(server, player,
                                                                                                     config.api_key))
        if sumreq.status_code == 200:
            summoner = json.loads(sumreq.text)
            ranked = requests.get(
                "https://{0}.api.riotgames.com/lol/league/v4/entries/by-summoner/{1}?api_key={2}".format(server,
                                                                                                         summoner["id"],
                                                                                                         config.api_key))
            if ranked.status_code == 200:
                try:

                    y = ranked.json()
                    # filter solo queue and flex queue
                    solo = [i for i in y if i['queueType'] == 'RANKED_SOLO_5x5']
                    flex = [i for i in y if i['queueType'] == 'RANKED_FLEX_SR']
                    # conver revisionDate to datetime object
                    # x['revisionDate'] = datetime.datetime.utcfromtimestamp(x['revisionDate'] / 1000)
                    # if solo exists else none

                    #todo this is dupe code, fixing it later
                    if solo:
                        wr = round(solo[0]['wins'] / (solo[0]['wins'] + solo[0]['losses']) * 100)
                        solo = f"{str(solo[0]['tier']).capitalize()} {solo[0]['rank']} (W:{solo[0]['wins']}/L:{solo[0]['losses']})  (WR:{wr}%)  ({solo[0]['leaguePoints']} LP)"
                    else:
                        solo = "Unranked"

                    if flex:
                        wr = round(flex[0]['wins'] / (flex[0]['wins'] + flex[0]['losses']) * 100)
                        flex = f"{str(flex[0]['tier']).capitalize()} {flex[0]['rank']}  (W:{flex[0]['wins']}/L:{flex[0]['losses']}) (WR:{wr}%) ({flex[0]['leaguePoints']} LP)"
                    else:
                        flex = "Unranked"

                    # ingame = requests.get(f"http://MYAPIURL/api/spectate/{server}/{player}")
                    ingame = False

                    current_player = {"username": summoner['name'], "level": summoner["summonerLevel"],
                                      "revisionDate": summoner["revisionDate"],
                                      "id": summoner["id"], "solo": solo, "flex": flex,
                                      "profileIconId": str(
                                          "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/profile-icons/{0}.jpg".format(
                                              summoner["profileIconId"])), "server": server,
                                      "utc": datetime.datetime.utcfromtimestamp(summoner['revisionDate'] / 1000),
                                      "ingame": ingame}

                    result.append(current_player)
                except Exception as e:
                    print(e)
    return result

if __name__ == '__main__':
    print(config.champion_names.get(147))
    app.run(port=2888)
