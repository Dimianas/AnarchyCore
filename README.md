# AnarchyCore

___
## Features
* Prevent use OP and Gamemode
* Auto Deop on leave
* Auto Gamemode Survival on leave
* Fix Mini Suppressor
* BedCheck (Check respawn player)
___
## Config
<details>
  <summary>AnarchyCore Config.yml</summary>

```yml
##########
# Config #
##########
reload-message: '&f[AnarchyCore] &7- config reloaded!'
########
# Misc #
########
BedCheck:
  have: '&aYou have respawn'
  havent: "&cYou haven't respawn"
##############
# PreventLag #
##############
MiniSuppressor:
  enable: true
  message: "&cYou can't place redstone on the trapdoor"
##################
# Server Protect #
##################
GameModeLeft: true
GameModeProtect:
  enable: true
  kick-message: '&6You have disconnected from the server'
  whitelist:
    - 'sabzip'
OpLeft: true
OpProtect:
  enable: true
  kick-message: '&6You have disconnected from the server'
  whitelist:
    - 'sabzip'
```
</details>

___
