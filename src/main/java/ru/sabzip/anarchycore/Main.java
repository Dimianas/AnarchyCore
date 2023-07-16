package ru.sabzip.anarchycore;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        loadConfig();

        Logger logger = getLogger();
        logger.info(ChatColor.translateAlternateColorCodes('&' , "&aPlugin enabled!"));

        // illegal
        getServer().getPluginManager().registerEvents(new ru.sabzip.anarchycore.modules.illegal.PreventPlace(this), this);

        // Misc
        getCommand("bed").setExecutor(new ru.sabzip.anarchycore.modules.Misc.BedCheck(this));

        // PreventLag
        getServer().getPluginManager().registerEvents(new ru.sabzip.anarchycore.modules.PreventLag.MiniSuppressor(this), this);

        // Server Protect
        getServer().getPluginManager().registerEvents(new ru.sabzip.anarchycore.modules.ServerProtect.GameModeLeft(this),this);
        getServer().getPluginManager().registerEvents(new ru.sabzip.anarchycore.modules.ServerProtect.GameModeProtect(this), this);
        getServer().getPluginManager().registerEvents(new ru.sabzip.anarchycore.modules.ServerProtect.OpLeft(this), this);
        getServer().getPluginManager().registerEvents(new ru.sabzip.anarchycore.modules.ServerProtect.OpProtect(this), this);

    }

    @Override
    public void onDisable() {
        Logger logger = getLogger();
        logger.info(ChatColor.translateAlternateColorCodes('&' , "&cPlugin disabled!"));
    }
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("ac") && args.length > 0 && args[0].equalsIgnoreCase("reload")) {
            reloadConfig();
            loadConfig();
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
