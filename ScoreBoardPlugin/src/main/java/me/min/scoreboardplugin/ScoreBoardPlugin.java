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
import java.util.UUID;
import java.util.logging.Logger;


public final class ScoreBoardPlugin extends JavaPlugin implements Listener {
    private File dataFile;
    private FileConfiguration data;

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
        UUID uuid = player.getUniqueId();
        int level = player.getLevel();
        int mobKills = player.getStatistic(Statistic.MOB_KILLS);
        int playerKills = player.getStatistic(Statistic.PLAYER_KILLS);
        int deathsPlayer = player.getStatistic(Statistic.DEATHS);
        int totalBrokenBlocks = countBrokenBlocks(player);

        data.set("players." + playerName + ".PlayerName", playerName);
        data.set("players." + playerName + ".UUID", uuid.toString());
        data.set("players." + playerName + ".Level", level);
        data.set("players." + playerName + ".MobKills", mobKills);
        data.set("players." + playerName + ".PlayerKills", playerKills);
        data.set("players." + playerName + ".DeathsPlayer", deathsPlayer);
        data.set("players." + playerName + ".TotalBrokenBlocks", totalBrokenBlocks);

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




