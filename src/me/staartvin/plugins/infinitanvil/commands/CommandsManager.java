package me.staartvin.plugins.infinitanvil.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.List;

public class CommandsManager implements CommandExecutor, TabCompleter {

    public CommandsManager() {
    }

    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        return true;
//        final Player p = (Player) sender;
//        final int length = args.length;
//        if (length <= 0) {
//            this.help(label, sender);
//            return true;
//        }
//        if (args[0].equalsIgnoreCase("set")) {
//            if (!sender.hasPermission("infiniteanvil.set")) {
//                this.configurationManager.noPermCmdMessage(sender);
//                return true;
//            }
//            if (length > 1) {
//                try {
//                    this.radius = Integer.parseInt(args[1]);
//                } catch (NumberFormatException e) {
//                    this.configurationManager.invalidNumberMessage(sender, args[1]);
//                    return true;
//                }
//                if (this.radius == 0) {
//                    this.configurationManager.numberZeroMessage(sender);
//                    return true;
//                }
//                if (this.radius < 0) {
//                    this.configurationManager.numberNegativeMessage(sender);
//                    return true;
//                }
//                if (this.radius > this.configurationManager.maxRadius()) {
//                    this.configurationManager.numberAboveMaxMessage(sender);
//                    return true;
//                }
//                this.total = 0;
//                final Block b = p.getLocation().getBlock();
//                new BukkitRunnable() {
//                    public void run() {
//                        for (int x = -CommandsManager.this.radius; x <= CommandsManager.this.radius; ++x) {
//                            for (int y = -CommandsManager.this.radius; y <= CommandsManager.this.radius; ++y) {
//                                for (int z = -CommandsManager.this.radius; z <= CommandsManager.this.radius; ++z) {
//                                    if (b.getRelative(x, y, z).getType() == Material.ANVIL) {
//                                        final Block foundAnvil = b.getRelative(x, y, z);
//                                        if (!CommandsManager.this.anvilStorage.checkData(foundAnvil)) {
//                                            final Location l = foundAnvil.getLocation();
//                                            final int anvilX = l.getBlockX();
//                                            final int anvilY = l.getBlockY();
//                                            final int anvilZ = l.getBlockZ();
//                                            CommandsManager.this.anvilStorage.getConfiguration().set(CommandsManager.this.uuid.generateUUID().toString(), (Object) (foundAnvil.getWorld().getName() + ", " + anvilX + ", " + anvilY + ", " + anvilZ + ", " + foundAnvil.getData()));
//                                            CommandsManager.this.total++;
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                        if (CommandsManager.this.total == 0) {
//                            CommandsManager.this.configurationManager.noneRadiusSetMessage(sender);
//                        } else {
//                            CommandsManager.this.anvilStorage.saveData();
//                            CommandsManager.this.configurationManager.radiusSetMessage(sender, CommandsManager.this.total,
//                                    CommandsManager.this.radius);
//                        }
//                    }
//                }.runTaskAsynchronously((Plugin) InfinitAnvil.getInstance());
//                return true;
//            } else {
//                final Block b = p.getTargetBlock((Set) null, 25);
//                if (b.getType() != Material.ANVIL) {
//                    this.configurationManager.invalidBlockMessage(sender);
//                    return true;
//                }
//                final Location l = b.getLocation();
//                final int x = l.getBlockX();
//                final int y = l.getBlockY();
//                final int z = l.getBlockZ();
//                final Byte blockData = b.getData();
//                if (this.anvilStorage.checkData(b)) {
//                    this.configurationManager.alreadySetMessage((CommandSender) p);
//                    return true;
//                }
//                this.anvilStorage.getConfiguration().set(this.uuid.generateUUID().toString(), (Object) (b.getWorld().getName() +
//                        ", " + x + ", " + y + ", " + z + ", " + blockData));
//                this.anvilStorage.saveData();
//                this.configurationManager.anvilSetMessage(p, x, y, z);
//                return true;
//            }
//        } else if (args[0].equalsIgnoreCase("remove") || args[0].equalsIgnoreCase("unset")) {
//            if (!sender.hasPermission("infiniteanvil.remove")) {
//                this.configurationManager.noPermCmdMessage(sender);
//                return true;
//            }
//            if (length > 1) {
//                try {
//                    this.radius = Integer.parseInt(args[1]);
//                } catch (NumberFormatException e) {
//                    this.configurationManager.invalidNumberMessage(sender, args[1]);
//                    return true;
//                }
//                if (this.radius == 0) {
//                    this.configurationManager.numberZeroMessage(sender);
//                    return true;
//                }
//                if (this.radius < 0) {
//                    this.configurationManager.numberNegativeMessage(sender);
//                    return true;
//                }
//                if (this.radius > this.configurationManager.maxRadius()) {
//                    this.configurationManager.numberAboveMaxMessage(sender);
//                    return true;
//                }
//                this.total = 0;
//                final Block b = p.getLocation().getBlock();
//                new BukkitRunnable() {
//                    public void run() {
//                        for (int x = -CommandsManager.this.radius; x <= CommandsManager.this.radius; ++x) {
//                            for (int y = -CommandsManager.this.radius; y <= CommandsManager.this.radius; ++y) {
//                                for (int z = -CommandsManager.this.radius; z <= CommandsManager.this.radius; ++z) {
//                                    if (b.getRelative(x, y, z).getType() == Material.ANVIL) {
//                                        final Block foundAnvil = b.getRelative(x, y, z);
//                                        if (CommandsManager.this.anvilStorage.checkData(foundAnvil)) {
//                                            CommandsManager.this.anvilStorage.getConfiguration().set(CommandsManager.this.anvilStorage.getUUID(), (Object) null);
//                                            CommandsManager.this.total++;
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                        if (CommandsManager.this.total == 0) {
//                            CommandsManager.this.configurationManager.noneRadiusRemoveMessage(sender);
//                        } else {
//                            CommandsManager.this.anvilStorage.saveData();
//                            CommandsManager.this.configurationManager.radiusRemoveMessage(sender, CommandsManager.this.total,
//                                    CommandsManager.this.radius);
//                        }
//                    }
//                }.runTaskAsynchronously((Plugin) InfinitAnvil.getInstance());
//                return true;
//            } else {
//                final Block b = p.getTargetBlock((Set) null, 25);
//                if (b.getType() != Material.ANVIL) {
//                    this.configurationManager.invalidBlockMessage(sender);
//                    return true;
//                }
//                if (this.anvilStorage.checkData(b)) {
//                    final Location l = b.getLocation();
//                    final int x = l.getBlockX();
//                    final int y = l.getBlockY();
//                    final int z = l.getBlockZ();
//                    this.anvilStorage.getConfiguration().set(this.anvilStorage.getUUID(), (Object) null);
//                    this.anvilStorage.saveData();
//                    this.configurationManager.anvilRemoveMessage(p, x, y, z);
//                    return true;
//                }
//                this.configurationManager.alreadyRemovedMessage((CommandSender) p);
//                return true;
//            }
//        } else if (args[0].equalsIgnoreCase("toggle")) {
//            if (!sender.hasPermission("infiniteanvil.toggle")) {
//                this.configurationManager.noPermCmdMessage(sender);
//                return true;
//            }
//            if (length > 1) {
//                if (args[1].equalsIgnoreCase("off")) {
//                    if (!CommandsManager.togglePlayer.contains(p.getUniqueId())) {
//                        this.configurationManager.alreadyToggledOffMessage(sender);
//                        return true;
//                    }
//                    this.configurationManager.toggleOffMessage(sender);
//                    CommandsManager.togglePlayer.remove(p.getUniqueId());
//                    return true;
//                } else {
//                    if (!args[1].equalsIgnoreCase("on")) {
//                        this.configurationManager.invalidSyntaxMessage(sender, "/" + label + " toggle [on/off]");
//                        return true;
//                    }
//                    if (CommandsManager.togglePlayer.contains(p.getUniqueId())) {
//                        this.configurationManager.alreadyToggledOnMessage(sender);
//                        return true;
//                    }
//                    this.configurationManager.toggleOnMessage(sender);
//                    CommandsManager.togglePlayer.add(p.getUniqueId());
//                    return true;
//                }
//            } else {
//                if (CommandsManager.togglePlayer.contains(p.getUniqueId())) {
//                    this.configurationManager.toggleOffMessage(sender);
//                    CommandsManager.togglePlayer.remove(p.getUniqueId());
//                    return true;
//                }
//                this.configurationManager.toggleOnMessage(sender);
//                CommandsManager.togglePlayer.add(p.getUniqueId());
//                return true;
//            }
//        } else {
//            if (args[0].equalsIgnoreCase("purge") && sender.hasPermission("infiniteanvil.purge")) {
//                new BukkitRunnable() {
//                    public void run() {
//                        CommandsManager.this.anvilStorage.clearData();
//                        if (CommandsManager.this.anvilStorage.getAnvils() == 0) {
//                            CommandsManager.this.configurationManager.alreadyEmptyMessage(sender);
//                        } else {
//                            CommandsManager.this.configurationManager.dataPurgeMessage(sender,
//                                    CommandsManager.this.anvilStorage.getAnvils());
//                        }
//                    }
//                }.runTaskAsynchronously((Plugin) InfinitAnvil.getInstance());
//                return true;
//            }
//            if (args[0].equalsIgnoreCase("reload")) {
//                if (sender.hasPermission("infiniteanvil.reload")) {
//                    InfinitAnvil.getInstance().reloadConfig();
//                    this.configurationManager.reloadMessage(sender);
//                    return true;
//                }
//                this.configurationManager.noPermCmdMessage(sender);
//                return true;
//            } else {
//                if (args[0].equalsIgnoreCase("version")) {
//                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
//                            "&aRunning Infinite Anvil v" + InfinitAnvil.getInstance().getDescription().getVersion() + " by derive!"));
//                    final String version =
//                            InfinitAnvil.getInstance().getServer().getBukkitVersion().substring(0, 6).replace("-", "");
//                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Server Version: " + version));
//                    return true;
//                }
//                if (args[0].equalsIgnoreCase("help")) {
//                    this.help(label, sender);
//                    return true;
//                }
//                return true;
//            }
//        }
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
        return null;
    }
}
