package me.staartvin.plugins.infinitanvil.listeners;

import me.staartvin.plugins.infinitanvil.InfinitAnvil;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BreakAnvil implements Listener {

    @EventHandler
    public void breakAnvil(final BlockBreakEvent event) {

        Material blockType = event.getBlock().getType();

        // Ignore any blocks that are not anvils.
        if (blockType != Material.ANVIL && blockType != Material.CHIPPED_ANVIL && blockType != Material.DAMAGED_ANVIL) {
            return;
        }

        Location blockLocation = event.getBlock().getLocation();

        // We don't care about anvils that are not protected.
        if (!InfinitAnvil.getInstance().getAnvilStorage().isAnvilStored(blockLocation)) {
            return;
        }

        Player player = event.getPlayer();

        // Check if the player can break this anvil.
        if (InfinitAnvil.getInstance().getConfigurationManager().shouldProtectAnvils() && !player.hasPermission(
                "infinitanvil.break.protect")) {
            event.setCancelled(true);
            player.sendMessage(ChatColor.RED + "You cannot break this anvil as it's protected by some strange magic.." +
                    ".");
            return;
        }


        // Player broke the anvil, so remove it from the database.
        InfinitAnvil.getInstance().getAnvilStorage().removeStoredAnvil(blockLocation);
        player.sendMessage(ChatColor.GOLD + "This infinite anvil is no longer registered as one.");

    }
}
