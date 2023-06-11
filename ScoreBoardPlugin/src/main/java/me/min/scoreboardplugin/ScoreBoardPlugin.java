package me.min.scoreboardplugin;

import me.min.scoreboardplugin.commands.OpenScoreboardCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.logging.Logger;

import static org.bukkit.Bukkit.getOnlinePlayers;

public final class ScoreBoardPlugin extends JavaPlugin implements Listener {
    private File dataFile;
    private FileConfiguration data;

    public File getDataFile() {
        return dataFile;
    }

    public void setDataFile(File dataFile) {
        this.dataFile = dataFile;
    }

    public FileConfiguration getData() {
        return data;
    }

    public void setData(FileConfiguration data) {
        this.data = data;
    }


    @Override
    public void onEnable() {
        Logger logger = getLogger();
        saveDefaultConfig();
        startScoreboardUpdateScheduler();
        dataFile = new File(getDataFolder(), "config.yml");
        data = YamlConfiguration.loadConfiguration(dataFile);

        getServer().getPluginManager().registerEvents(this, this);

        try {
            data.save(dataFile);
        } catch (IOException e) {
            logger.warning(ChatColor.RED + "Error saving data file: " + e.getMessage());
        }
    }

    private void updateScoreboard(Player player) {
        OpenScoreboardCommand scoreboardCommand = new OpenScoreboardCommand(this);
        scoreboardCommand.onCommand(player, null, null, null);
    }

    private void savePlayerData(Player player) {

        String playerName = player.getName();
        UUID uuid = player.getUniqueId();

        Location location = player.getLocation();
        double x = location.getX();
        double y = location.getY();
        double z = location.getZ();

        data.set("players." + playerName + ".UUID", uuid.toString());
        data.set("players." + playerName + ".X", x);
        data.set("players." + playerName + ".Y", y);
        data.set("players." + playerName + ".Z", z);

        try {
            data.save(dataFile);
        } catch (IOException e) {
            getLogger().warning(ChatColor.RED + "Error saving data file: " + e.getMessage());
        }
    }

    private void startScoreboardUpdateScheduler() {
        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        scheduler.runTaskTimer(this, () -> {
            for (Player player : getOnlinePlayers()) {
                updateScoreboard(player);
                savePlayerData(player);
            }
        }, 0, 20);
    }

    @Override
    public void onDisable() {
        getLogger().info(ChatColor.AQUA + "Plugin shut down: ");
    }
}




