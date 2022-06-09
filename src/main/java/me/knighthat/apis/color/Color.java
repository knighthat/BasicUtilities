package me.knighthat.apis.color;

import lombok.NonNull;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.ChatColor;

public interface Color {

    default @NonNull String colorString(@NonNull String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    default @NonNull Component color(@NonNull String s) {
        return Component.text(this.colorString(s));
    }

    default @NonNull Component stripColor(@NonNull Component c) {
        String stripped = ChatColor.stripColor(this.colorString(this.toString(c)));
        return Component.text(stripped);
    }

    default @NonNull String toString(@NonNull Component c) {
        return PlainTextComponentSerializer.plainText().serialize(c);
    }
}
