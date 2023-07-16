package ru.sabzip.anarchycore.modules.PreventLag;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import ru.sabzip.anarchycore.Main;

public class MiniSuppressor implements Listener {
    private final Main plugin;

    public MiniSuppressor(Main plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        Block placedBlock = e.getBlockPlaced();
        Material blockType = placedBlock.getType();
        String message = plugin.getConfig().getString("MiniSuppressor.message");

        if (!plugin.getConfig().getBoolean("MiniSuppressor.enable")) {
            return;
        }
        if (blockType == Material.REDSTONE_WIRE && isBlockOnTrapdoor(placedBlock)) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&' , message));
        }
    }

    private boolean isBlockOnTrapdoor(Block block) {
        Block blockBelow = block.getRelative(BlockFace.DOWN);
        return blockBelow.getType().toString().contains("TRAPDOOR");
    }
}
