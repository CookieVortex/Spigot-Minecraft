# Added:

## - ScoreBoard

Now you do not need to update the table every time to get updated data, now everything happens automatically and is updated every tick (1 second divided by 20).

Updated statistics show the number of blocks destroyed (all types)

Added a rank system. Upon reaching a certain level, you get buffs (infinite duration), the higher the level of the player, the more powerful buffs you get

Removed unnecessary counters, left the most necessary.
Added a rank system. Upon reaching a certain level, you get buffs (infinite duration), the higher the level of the player, the more powerful buffs you get

Removed unnecessary counters, left the most necessary.
Improved code readability, now everything is in its own method

The *** plugin has been moved to a separate file (folder) to make it more convenient to work, so every day there is more and more code and I myself start to get lost.

Now **** creates an external config.yml file that stores all the information about the player who joined the server, for example:

players:
marg:
playerName: Marg
totalExperience: 49
level: 49
mobKills: 13
totalBrokenBlocks: 25

Unfortunately, the data is updated only after the server is restarted, everything except totalBrokenBlocks, so I'm looking for the causes and solutions to this problem.


## - Permissions (Creative, Survival)
Both classes are separated from each other and have different tags in different colors.

Reduced the amount of code and now everything is in one file instead of two.
Update permission file.

## - Entity
Added ability to spawn entity: Zombie and Skeleton. Both classes have buffs relative to vanilla.

## - World
I don't like night maps so I made it always day.

Changed the vanilla way of sending messages to the chat:
<Nickname: Message>  (Looks terrible)

On the:

[Permission Tag] Nickname: Message


## - Hologram

Hologram plugin that adds a hologram to the server by installing it at the player's location, as well as the ability to remove all holograms from the server
