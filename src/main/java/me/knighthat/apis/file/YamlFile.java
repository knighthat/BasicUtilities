package me.knighthat.apis.file;

import lombok.NonNull;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;

public final class YamlFile {

    private @NonNull
    final JavaPlugin plugin;
    private @NonNull
    final String fileName;
    private File file;
    private FileConfiguration yaml;

    public YamlFile(@NonNull JavaPlugin plugin, @NonNull String fileName) {
        this.plugin = plugin;
        this.fileName = fileName.concat(".yml");
        startup();
    }

    private void startup() {

        file = new File(plugin.getDataFolder(), fileName);
        createIfNotExists();

        reload();
    }

    public void reload() {

        if (file == null) startup();
        createIfNotExists();

        yaml = YamlConfiguration.loadConfiguration(file);
    }

    public boolean save() {

        if (file == null || yaml == null)
            reload();

        try {

            yaml.save(file);
            return true;
        } catch (IOException e) {

            String msg = MessageFormat.format("Failed to save {0} due to: {1}", fileName, e.getCause());
            plugin.getSLF4JLogger().error(msg);

            return false;
        }
    }

    public @NonNull FileConfiguration get() {

        if (yaml == null) reload();

        return yaml;
    }

    private void createIfNotExists() {
        if (!file.exists())
            plugin.saveResource(fileName, false);
    }
}
