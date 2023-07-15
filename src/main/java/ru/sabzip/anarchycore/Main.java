package ru.sabzip.anarchycore;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        loadConfig();

        Logger logger = getLogger();
        logger.info(ChatColor.translateAlternateColorCodes('&' , "&aPlugin enabled!"));

        // Misc
        getServer().getPluginManager().registerEvents(new ru.sabzip.anarchycore.modules.Prevent.Test(this), this);

    }

    @Override
    public void onDisable() {
    }
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("ac") && args.length > 0 && args[0].equalsIgnoreCase("reload")) {
            reloadConfig();
            loadConfig();
            Permission permission = new Permission("anarchycore.reload");
            String message = getConfig().getString("reload-message");
            assert message != null;
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
            return true;
        }
        return false;
    }
    public void loadConfig() {
        saveDefaultConfig();
    }
}
