package me.staartvin.plugins.infinitanvil.storage;

import me.staartvin.plugins.infinitanvil.InfinitAnvil;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class AnvilStorage {
    private FileConfiguration configuration;
    private File file;

    // Store the anvils (and their ids) in a map so we can easily retrieve it.
    private Map<Location, UUID> loadedAnvils = new HashMap<Location, UUID>();

    public AnvilStorage() {
        this.setup();
    }

    private void setup() {
        this.file = new File(InfinitAnvil.getInstance().getDataFolder(), "data.yml");
        this.configuration = YamlConfiguration.loadConfiguration(this.file);

        // Load anvil ids into map
        for (String anvilId : this.configuration.getKeys(false)) {
            Location location = getLocationOfAnvil(UUID.fromString(anvilId)).orElse(null);

            // Ignore anvils that couldn't be loaded properly.
            if (location == null) continue;

            loadedAnvils.put(location, UUID.fromString(anvilId));
        }
    }

    private void saveData() {
        try {
            this.configuration.save(this.file);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Check whether an anvil in the database for the given location.
     *
     * @param anvilLocation Location of the anvil
     * @return true if there is an anvil stored for the given location, false otherwise.
     */
    public boolean isAnvilStored(Location anvilLocation) {
        return loadedAnvils.containsKey(anvilLocation);
    }

    /**
     * Add an anvil to the storage, given a location.
     *
     * @param anvilLocation Location of the anvil
     */
    public void addStoredAnvil(Location anvilLocation) {

        // Check if we have already stored this anvil
        if (isAnvilStored(anvilLocation)) return;

        UUID anvilId = getNewAnvilId();

        configuration.set(anvilId.toString() + ".x", anvilLocation.getBlockX());
        configuration.set(anvilId.toString() + ".y", anvilLocation.getBlockY());
        configuration.set(anvilId.toString() + ".z", anvilLocation.getBlockZ());
        configuration.set(anvilId.toString() + ".world", anvilLocation.getWorld().getName());
        configuration.set(anvilId.toString() + ".time", System.currentTimeMillis());

        this.saveData();

        loadedAnvils.put(anvilLocation, anvilId);
    }

    /**
     * Remove a stored anvil from the database (if it exists).
     * @param anvilLocation Location of the anvil.
     */
    public void removeStoredAnvil(Location anvilLocation) {

        // Keep track of anvil id to remove.
        String anvilIdToRemove = null;

        // Search for the anvil id to remove from the database.
        for (String anvilId : this.configuration.getKeys(false)) {

            Location location = getLocationOfAnvil(UUID.fromString(anvilId)).orElse(null);

            if (location == null) continue;

            // Match the location of the anvil with the location of the anvil to remove.
            if (anvilLocation.equals(location)) {
                anvilIdToRemove = anvilId;
            }
        }

        // Don't remove if we can't remove
        if (anvilIdToRemove == null) return;

        configuration.set(anvilIdToRemove, null);

        this.saveData();

        // Remove anvil from loaded anvils.
        loadedAnvils.remove(anvilLocation);
    }

    /**
     * Toggle whether an anvil is stored or not in the database. If you call this method when there is no anvil
     * stored with the given location, it will be stored. If it was already stored, it will be removed from the
     * database.
     * @param anvilLocation Location of the anvil
     * @return true if the anvil is now stored. False if the anvil has been removed.
     */
    public boolean toggleAnvilStatus(Location anvilLocation) {

        if (this.isAnvilStored(anvilLocation)) {
            this.removeStoredAnvil(anvilLocation);
            return false;
        } else {
            this.addStoredAnvil(anvilLocation);
            return true;
        }
    }

    private Optional<Location> getLocationOfAnvil(UUID anvilId) {
        String worldName = configuration.getString(anvilId + ".world");

        if (worldName == null) return Optional.empty();

        World world = InfinitAnvil.getInstance().getServer().getWorld(worldName);

        if (world == null) Optional.empty();

        Location location = new Location(world,
                configuration.getDouble(anvilId + ".x"),
                configuration.getDouble(anvilId + ".y"),
                configuration.getDouble(anvilId + ".z")
        );

        return Optional.of(location);
    }

    private UUID getNewAnvilId() {
        return UUID.randomUUID();
    }

}
