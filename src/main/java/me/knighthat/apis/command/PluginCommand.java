package me.knighthat.apis.command;

import lombok.NonNull;
import org.bukkit.command.CommandSender;

public abstract class PluginCommand {

    public abstract @NonNull String getName();

    public @NonNull String getPermission() {
        return getName();
    }

    public boolean isPlayerRequired() {
        return false;
    }

    public @NonNull String getUsage() {
        return "";
    }

    public abstract void execute(@NonNull CommandSender var1, @NonNull String[] var2);
}
