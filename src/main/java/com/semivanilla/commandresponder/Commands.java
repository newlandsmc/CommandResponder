package com.semivanilla.commandresponder;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;

public final class Commands extends Command {

    private final String commandName;
    private final String commandResponse;
    private final boolean enabled;

    public Commands(String commandName, String commandResponse, boolean enabled) {
        super(commandName);
        this.commandName = commandName;
        this.commandResponse = commandResponse;
        this.enabled = enabled;
    }

    public String getCommandName() {
        return commandName;
    }

    public void sendResponseToPlayer(final Player player){
        Messages.sendMessage(player,commandResponse);
    }

    public static Commands fromConfigSection(ConfigurationSection section){
        return new Commands(section.getName(),section.getString("message"),section.getBoolean("enabled"));
    }

    public Commands register(){
        Field commandField = null;
        try {
            commandField = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            commandField.setAccessible(true);
            CommandMap commandMap = null;
            commandMap = (CommandMap) commandField.get(Bukkit.getServer());
            commandMap.register(commandName, this);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        return this;
    }


    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        if(!enabled)
            return true;

        if(sender instanceof Player)
            this.sendResponseToPlayer((Player) sender);
        return true;
    }
}
