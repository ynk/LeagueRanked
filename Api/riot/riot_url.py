import urllib


class RiotUrl:
    API_DOMAIN = "api.riotgames.com"


    def __init__(self, api_key):
        self.api_key = api_key


    def summoner_by_name(self, server, name):
        escaped_name = urllib.parse.quote(name)
        base = self._base_url(server)
        endpoint = f"{base}/lol/summoner/v4/summoners/by-name/{escaped_name}"
        return self._add_api_key(endpoint)


    def entries_by_summoner(self, server, summoner_id):
        endpoint = f"{self._base_url(server)}/lol/league/v4/entries/by-summoner/{summoner_id}"
        return self._add_api_key(endpoint)

    
    def activate_game(self, server, summoner_id):
        endpoint = f"{self._base_url(server)}/lol/spectator/v4/active-games/by-summoner/{summoner_id}"
        return self._add_api_key(endpoint)


    def _base_url(self, server):
        return f"https://{server}.{self.API_DOMAIN}"


    def _add_api_key(self, url):
        return f"{url}?api_key={self.api_key}"