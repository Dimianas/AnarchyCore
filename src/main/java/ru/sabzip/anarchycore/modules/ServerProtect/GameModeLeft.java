package ru.sabzip.anarchycore.modules.ServerProtect;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import ru.sabzip.anarchycore.Main;

public class GameModeLeft implements Listener {
    private final Main plugin;

    public GameModeLeft(Main plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    private void onPlayerLeft (PlayerQuitEvent e) {
        if (!plugin.getConfig().getBoolean("GameModeLeft")) {
            return;
        }
        e.getPlayer().setGameMode(GameMode.SURVIVAL);
    }
}
