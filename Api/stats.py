from riot.riot_responses import *


def win_loss_percent(wins, losses) -> int:
    return round(wins / (wins + losses) * 100)


def summary(entry: LeagueEntryDTO):
    wr = win_loss_percent(entry.wins, entry.losses)
    return (
        f"{str(entry.tier).capitalize()} {entry.rank}"
        f" (W:{entry.wins}/L:{entry.losses})  (WR:{wr}%)"
        f" ({entry.leaguePoints} LP)"
    )