from dataclasses import dataclass
from datetime import datetime


@dataclass
class User:
    username: str
    level: int
    revisionDate: int
    id: str
    solo: str
    flex: str
    profileIconId: str
    server: str
    utc: datetime
    ingame: bool