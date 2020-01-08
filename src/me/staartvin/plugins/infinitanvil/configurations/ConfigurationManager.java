package me.staartvin.plugins.infinitanvil.configurations;

import me.staartvin.plugins.infinitanvil.InfinitAnvil;

public class ConfigurationManager {

    public ConfigurationManager() {
        InfinitAnvil.getInstance().saveDefaultConfig();
    }

    public boolean shouldProtectAnvils() {
        return InfinitAnvil.getInstance().getConfig().getBoolean("protect-infinite-anvils", true);
    }
}
