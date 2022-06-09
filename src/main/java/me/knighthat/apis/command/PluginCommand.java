package me.knighthat.apis.command;

import lombok.NonNull;
import org.bukkit.command.CommandSender;

public abstract class PluginCommand {

    public abstract @NonNull String getName();

    public abstract @NonNull String getPermission();

    public abstract boolean isPlayerRequired();

    public @NonNull String getUsage() {
        return "";
    }

    public abstract void execute(@NonNull CommandSender sender, @NonNull String[] args);
}
