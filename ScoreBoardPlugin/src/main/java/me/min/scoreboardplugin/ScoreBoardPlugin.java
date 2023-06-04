package me.min.scoreboardplugin;

import me.min.scoreboardplugin.commands.OpenScoreboardCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;


public final class ScoreBoardPlugin extends JavaPlugin implements Listener {

    private FileConfiguration data;
    private File dataFile;

    @Override
    public void onEnable() {
        Logger logger = getLogger();
        saveDefaultConfig();
        dataFile = new File(getDataFolder(), "config.yml");

        try {
            if (!dataFile.exists()) {
                dataFile.createNewFile();
            }
        } catch (IOException e) {
            logger.warning("Error creating data file: " + e.getMessage());
        }

        data = YamlConfiguration.loadConfiguration(dataFile);

        for (Player player : Bukkit.getOnlinePlayers()) {
            String playerName = player.getName();
            int totalExperience = player.getLevel();
            int level = player.getLevel();
            int mobKills = player.getStatistic(Statistic.MOB_KILLS);
            int totalBrokenBlocks = countBrokenBlocks(player);

            data.set("players." + playerName + ".playerName", playerName);
            data.set("players." + playerName + ".totalExperience", totalExperience);
            data.set("players." + playerName + ".level", level);
            data.set("players." + playerName + ".mobKills", mobKills);
            data.set("players." + playerName + ".totalBrokenBlocks", totalBrokenBlocks);
        }
        getServer().getPluginManager().registerEvents(this, this);

        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        scheduler.runTaskTimer(this, () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                updateScoreboard(player);
                savePlayerData(player);
            }
        }, 0, 20);

        try {
            data.save(dataFile);
        } catch (IOException e) {
            logger.warning("Error saving data file: " + e.getMessage());
        }
    }

    private void updateScoreboard(Player player) {
        OpenScoreboardCommand scoreboardCommand = new OpenScoreboardCommand();
        scoreboardCommand.onCommand(player, null, null, null);
    }

    private void savePlayerData(Player player) {
        String playerName = player.getName();
        int totalBrokenBlocks = countBrokenBlocks(player);

        data.set("players." + playerName + ".totalBrokenBlocks", totalBrokenBlocks);

        try {
            data.save(dataFile);
        } catch (IOException e) {
            getLogger().warning("Error saving data file: " + e.getMessage());
        }
    }

    @Override
    public void onDisable() {
        getLogger().info(ChatColor.AQUA + "Plugin shut down: ");
    }

    private int countBrokenBlocks(Player player) {
        int totalBrokenBlocks = 0;
        for (Material blockType : Material.values()) {
            if (blockType.isBlock()) {
                totalBrokenBlocks += player.getStatistic(Statistic.MINE_BLOCK, blockType);
            }
        }
        return totalBrokenBlocks;
    }
}




