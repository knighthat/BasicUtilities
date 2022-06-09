package me.knighthat.apis.file;

import lombok.NonNull;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;

public final class YamlFile {

    private final @NonNull JavaPlugin plugin;
    private final @NonNull String fileName;
    private File file;
    private FileConfiguration yaml;

    public YamlFile(@NonNull JavaPlugin plugin, @NonNull String fileName) {
        this.plugin = plugin;
        this.fileName = fileName.concat(".yml");
        this.startup();
    }

    private void startup() {
        this.file = new File(this.plugin.getDataFolder(), this.fileName);
        this.createIfNotExists();
        this.reload();
    }

    public void reload() {
        if (this.file == null) {
            this.startup();
        }

        this.createIfNotExists();
        this.yaml = YamlConfiguration.loadConfiguration(this.file);
    }

    public boolean save() {
        if (this.file == null || this.yaml == null) {
            this.reload();
        }

        try {
            this.yaml.save(this.file);
            return true;
        } catch (IOException var3) {
            String msg = MessageFormat.format("Failed to save {0} due to: {1}", this.fileName, var3.getCause());
            this.plugin.getSLF4JLogger().error(msg);
            return false;
        }
    }

    public @NonNull FileConfiguration get() {
        if (this.yaml == null) {
            this.reload();
        }

        return this.yaml;
    }

    private void createIfNotExists() {
        if (!this.file.exists()) {
            this.plugin.saveResource(this.fileName, false);
        }
    }
}
