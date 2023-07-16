package ru.sabzip.anarchycore.modules.illegal;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import ru.sabzip.anarchycore.Main;

import java.util.Arrays;
import java.util.List;

public class PreventPlace implements Listener {
    private final List<Material> illegalItems = Arrays.asList(
            Material.BEDROCK,
            Material.END_PORTAL_FRAME,
            Material.BARRIER,
            Material.STRUCTURE_BLOCK,
            Material.COMMAND_BLOCK,
            Material.CHAIN_COMMAND_BLOCK,
            Material.REPEATING_COMMAND_BLOCK
    );
    private final Main plugin;

    public PreventPlace(Main plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        if (!plugin.getConfig().getBoolean("PreventPlace")) {
            return;
        }
        if (illegalItems.contains(e.getBlockPlaced().getType())) {
            e.setCancelled(true);
            e.getPlayer().getInventory().remove(e.getItemInHand());
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        ItemStack item = e.getItem();
        if (!plugin.getConfig().getBoolean("PreventPlace")) {
            return;
        }
        if (item != null && illegalItems.contains(item.getType())) {
            e.setCancelled(true);
            e.getPlayer().getInventory().remove(item);
        }
    }

    @EventHandler
    private void onDrop(PlayerDropItemEvent e) {
        ItemStack item = e.getItemDrop().getItemStack();
        if (!plugin.getConfig().getBoolean("PreventPlace")) {
            return;
        }
        if (illegalItems.contains(item.getType())) {
            e.setCancelled(true);
            e.getPlayer().getInventory().remove(item);
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        ItemStack clickedItem = e.getCurrentItem();
        if (!plugin.getConfig().getBoolean("PreventPlace")) {
            return;
        }
        if (clickedItem != null && illegalItems.contains(clickedItem.getType())) {
            e.getWhoClicked().getInventory().remove(clickedItem);
        }
    }
}
