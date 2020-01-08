package me.staartvin.plugins.infinitanvil.commands;

import me.staartvin.plugins.infinitanvil.InfinitAnvil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.Collections;
import java.util.List;

public class CommandsManager implements CommandExecutor, TabCompleter {

    public CommandsManager() {
    }

    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {

        if (args.length > 0) {

            if (args[0].equalsIgnoreCase("reload")) {

                if (!sender.hasPermission("infinitanvil.reload")) {
                    sender.sendMessage(ChatColor.RED + "You are not allowed to reload the configuration file.");
                    return true;
                }

                InfinitAnvil.getInstance().reloadConfig();
                sender.sendMessage(ChatColor.GREEN + "Configuration file has been reloaded.");

                return true;
            }

        }

        sender.sendMessage(ChatColor.RED + "Unknown command!");
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        return Collections.singletonList("reload");
    }
}
