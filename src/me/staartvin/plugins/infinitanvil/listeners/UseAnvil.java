package me.staartvin.plugins.infinitanvil.listeners;

import me.staartvin.plugins.infinitanvil.InfinitAnvil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class UseAnvil implements Listener {

    @EventHandler
    public void useAnvil(final PlayerInteractEvent event) {

        Block clickedBlock = event.getClickedBlock();

        if (clickedBlock == null) {
            return;
        }

        // Ignore blocks that are not anvils.
        if (clickedBlock.getType() != Material.ANVIL && clickedBlock.getType() != Material.CHIPPED_ANVIL && clickedBlock.getType() != Material.DAMAGED_ANVIL) {
            return;
        }

        boolean toggleClick = event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && event.getPlayer().isSneaking();

        // Check if user was just clicking the anvil to use it.
        if (!toggleClick) {
            // Ignore anvils that are not stored in the database.
            if (!InfinitAnvil.getInstance().getAnvilStorage().isAnvilStored(clickedBlock.getLocation())) {
                return;
            }

            // User is not allowed to use this infinite anvil
            if (!event.getPlayer().hasPermission("infinitanvil.use")) {
                event.setCancelled(true);

                event.getPlayer().sendMessage(ChatColor.RED + "A strange magic appears to block your use of this " +
                        "anvil...");
                return;
            }

            // Fix anvil up.
            clickedBlock.setType(Material.ANVIL);
            clickedBlock.setBlockData(clickedBlock.getBlockData());

            // Do nothing after that.
            return;
        }

        // User is toggling the status of the anvil.

        // Notify user that he doesn't have the proper permission to toggle.
        if (!event.getPlayer().hasPermission("infinitanvil.toggle")) {
            event.getPlayer().sendMessage(ChatColor.RED + "You are not allowed to adjust whether this anvil can break" +
                    " or not.");
            return;
        }

        // Toggle the status of the anvil
        boolean stored = InfinitAnvil.getInstance().getAnvilStorage().toggleAnvilStatus(clickedBlock.getLocation());

        if (stored) {
            event.getPlayer().sendMessage(ChatColor.GOLD + "This anvil now has " + ChatColor.GREEN + "infinite" + ChatColor.GOLD + " uses.");
        } else {
            event.getPlayer().sendMessage(ChatColor.GOLD + "This anvil" + ChatColor.RED + " no longer has infinite " +
                    "uses.");
        }

        event.setCancelled(true);
    }

}
