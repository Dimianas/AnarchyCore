package ru.sabzip.anarchycore.modules.Misc;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import ru.sabzip.anarchycore.Main;

public class BedCheck implements Listener, CommandExecutor {
    private final Main plugin;

    public BedCheck(Main plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            Location bedLocation = player.getBedSpawnLocation();
            String have = plugin.getConfig().getString("BedCheck.have");
            String havent = plugin.getConfig().getString("BedCheck.havent");

            if (bedLocation != null) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&' , have));
            } else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&' , havent));
            }
        } else {
            sender.sendMessage(ChatColor.RED + "This command can only be used by players.");
        }

        return true;
    }
}
