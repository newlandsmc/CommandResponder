package com.semivanilla.commandresponder;

import com.semivanilla.commandresponder.config.Configuration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class CommandResponder extends JavaPlugin {

    private Configuration configuration;

    @Override
    public void onEnable() {
        new Messages(this);
        configuration = new Configuration(this);

        configuration.initConfig();
        configuration.loadConfiguration();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public Configuration getConfiguration() {
        return configuration;
    }

}
