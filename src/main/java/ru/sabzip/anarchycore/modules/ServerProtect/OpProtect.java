package ru.sabzip.anarchycore.modules.ServerProtect;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import ru.sabzip.anarchycore.Main;

import java.util.List;

public class OpProtect implements Listener {
    private final List<String> whitelist;
    private final Main plugin;

    public OpProtect(Main plugin) {
        this.plugin = plugin;
        whitelist = plugin.getConfig().getStringList("OpProtect.whitelist");
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        checkAndChangeOp(e.getPlayer());
    }

    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent e) {
        checkAndChangeOp(e.getPlayer());
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        checkAndChangeOp(e.getPlayer());
    }

    private void checkAndChangeOp(Player p) {
        String kick = plugin.getConfig().getString("OpProtect.kick-message");
        if (!plugin.getConfig().getBoolean("OpProtect.enable")) {
            return;
        }
        if (p.isOp() && !whitelist.contains(p.getName())) {
            p.setOp(false);
            p.kickPlayer(ChatColor.translateAlternateColorCodes('&' , kick));
        }
    }
}
