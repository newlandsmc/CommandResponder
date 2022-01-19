package com.semivanilla.commandresponder.config;

import com.semivanilla.commandresponder.CommandResponder;
import com.semivanilla.commandresponder.Commands;
import org.bukkit.configuration.file.FileConfiguration;

public class Configuration {

    private final CommandResponder plugin;
    private FileConfiguration configuration;

    public Configuration(CommandResponder plugin) {
        this.plugin = plugin;
    }

    public void initConfig(){
        this.plugin.saveDefaultConfig();
        this.configuration = this.plugin.getConfig();
    }

    public void loadConfiguration(){
        configuration.getKeys(false).forEach((commandName) -> {
            Commands.fromConfigSection(configuration.getConfigurationSection(commandName)).register();
        });
    }


}
