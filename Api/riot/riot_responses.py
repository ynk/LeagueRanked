from dataclasses import dataclass
from typing import List


"""
    Mapping from Riot API responses

    Not using pip8 to make mapping more simple
"""


@dataclass
class SummonerDTO:
    accountId: str = None
    profileIconId: int = None
    revisionDate: int = None
    name: str = None
    id: str = None
    puuid: str = None
    summonerLevel: int = None


@dataclass
class MiniSeriesDTO:
    losses: int = None
    progress: str = None
    target: int = None
    wins: int = None


@dataclass
class LeagueEntryDTO:
    leagueId: str = None
    summonerId: str = None
    summonerName: str = None
    queueType: str = None
    tier: str = None
    rank: str = None
    leaguePoints: int = None
    wins: int = None
    losses: int = None
    hotStreak: bool = None
    veteran: bool = None
    freshBlood: bool = None
    inactive: bool = None
    # miniSeries: MiniSeriesDTO = MiniSeriesDTO() # ignored for now as wasn't working

@dataclass
class CurrentGameInfo:
    gameId: int = None
    gameType: str = None
    gameStartTime: int = None
    mapId: int = None
    gameLength: int = None
    platformId: str = None
    gameMode: str = None
    # bannedChampions: List[BannedChampion]
    gameQueueConfigId: int = None
    # observers: Observer
    # participants: List[CurrentGameParticipant]