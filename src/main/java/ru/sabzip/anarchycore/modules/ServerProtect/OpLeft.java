package ru.sabzip.anarchycore.modules.ServerProtect;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import ru.sabzip.anarchycore.Main;

public class OpLeft implements Listener {
    private final Main plugin;

    public OpLeft(Main plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    private void onPlayerLeft(PlayerQuitEvent e) {
        if (!plugin.getConfig().getBoolean("OpLeft")) {
            return;
        }
        e.getPlayer().setOp(false);
    }
}
