package com.semivanilla.commandresponder;

import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.transformation.TransformationRegistry;
import net.kyori.adventure.text.minimessage.transformation.TransformationType;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Messages {

    private static BukkitAudiences audiences;
    private static MiniMessage miniMessages;

    public Messages(CommandResponder plugin) {
        audiences = BukkitAudiences.create(plugin);
        miniMessages = MiniMessage.builder()
                .transformations(TransformationRegistry.builder()
                        .add(TransformationType.COLOR)
                        .add(TransformationType.DECORATION)
                        .add(TransformationType.HOVER_EVENT)
                        .add(TransformationType.CLICK_EVENT)
                        .add(TransformationType.KEYBIND)
                        .add(TransformationType.TRANSLATABLE)
                        .add(TransformationType.INSERTION)
                        .add(TransformationType.FONT)
                        .add(TransformationType.GRADIENT)
                        .add(TransformationType.RAINBOW)
                        .build())
                .strict(false)
                .build();
    }

    private static Component transform(@NotNull String s){
        return miniMessages.deserialize(s);
    }

    public static void sendMessage(@NotNull Player player, @NotNull String message){
        audiences.player(player).sendMessage(transform(message));
    }
}
