package ru.sabzip.anarchycore.modules.ServerProtect;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import ru.sabzip.anarchycore.Main;

import java.util.List;

public class GameModeProtect implements Listener {
    private final List<String> whitelist;
    private final Main plugin;

    public GameModeProtect(Main plugin) {
        this.plugin = plugin;
        whitelist = plugin.getConfig().getStringList("GameModeProtect.whitelist");
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        checkAndChangeGameMode(event.getPlayer());
    }

    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
        checkAndChangeGameMode(event.getPlayer());
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        checkAndChangeGameMode(event.getPlayer());
    }

    @EventHandler
    public void onPlayerInventoryClick(InventoryClickEvent event) {
        checkAndChangeGameMode((Player) event.getWhoClicked());
    }

    private void checkAndChangeGameMode(Player p) {
        GameMode gm = p.getGameMode();
        String kick = plugin.getConfig().getString("GameModeProtect.kick-message");
        if (!plugin.getConfig().getBoolean("GameModeProtect.enable")) {
            return;
        }
        if (gm == GameMode.CREATIVE || gm == GameMode.SPECTATOR || gm == GameMode.ADVENTURE) {
            if (!whitelist.contains(p.getName())) {
                p.setGameMode(GameMode.SURVIVAL);
                p.kickPlayer(ChatColor.translateAlternateColorCodes('&', kick));
            }
        }
    }
}