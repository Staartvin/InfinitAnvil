package me.staartvin.plugins.infinitanvil;

import me.staartvin.plugins.infinitanvil.commands.CommandsManager;
import me.staartvin.plugins.infinitanvil.configurations.ConfigurationManager;
import me.staartvin.plugins.infinitanvil.listeners.*;
import me.staartvin.plugins.infinitanvil.storage.AnvilStorage;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class InfinitAnvil extends JavaPlugin {
    private static InfinitAnvil instance;

    private AnvilStorage anvilStorage;
    private ConfigurationManager configurationManager;
    private CommandsManager commandHandler;

    public static InfinitAnvil getInstance() {
        return InfinitAnvil.instance;
    }

    public void onEnable() {
        InfinitAnvil.instance = this;
        this.anvilStorage = new AnvilStorage();
        this.configurationManager = new ConfigurationManager();
        this.commandHandler = new CommandsManager();

        this.getServer().getPluginManager().registerEvents(new BreakAnvil(), this);
        this.getServer().getPluginManager().registerEvents(new UseAnvil(), this);

        this.getCommand("anvil").setExecutor(commandHandler);
        this.getCommand("anvil").setTabCompleter(commandHandler);

        this.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + this.getDescription().getFullName() + " " +
                "is enabled");
    }

    public AnvilStorage getAnvilStorage() {
        return this.anvilStorage;
    }

    public ConfigurationManager getConfigurationManager() {
        return this.configurationManager;
    }

    public CommandsManager getCommandHandler() {
        return this.commandHandler;
    }
}
