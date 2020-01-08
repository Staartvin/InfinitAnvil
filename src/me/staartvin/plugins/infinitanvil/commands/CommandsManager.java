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

    private void help(final String label, final CommandSender sender) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e ------ &6Infinite Anvil Help &e------"));
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a/" + label + " help &7- Displays this page"));
        if (sender.hasPermission("infiniteanvil.set")) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a/" + label + " set [radius] &7- Set " +
                    "anvil(s) to be infinite"));
        }
        if (sender.hasPermission("infiniteanvil.remove")) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a/" + label + " remove [radius] &7- " +
                    "Remove anvil(s) from infinite"));
        }
        if (sender.hasPermission("infiniteanvil.toggle")) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a/" + label + " toggle [on/off] &7- " +
                    "Toggle infinite anvil on place on/off"));
        }
        if (sender.hasPermission("infiniteanvil.purge")) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a/" + label + " purge &7- Clear all " +
                    "infinite anvils from the database"));
        }
        if (sender.hasPermission("infiniteanvil.reload")) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a/" + label + " reload &7- Reloads the " +
                    "configuration"));
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        return Collections.singletonList("reload");
    }
}
