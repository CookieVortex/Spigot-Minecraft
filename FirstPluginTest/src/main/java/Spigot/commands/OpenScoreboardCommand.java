package Spigot.commands;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.Objects;

public class OpenScoreboardCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;

            ScoreboardManager manager = Bukkit.getScoreboardManager();
            Scoreboard scoreboard = Objects.requireNonNull(manager).getNewScoreboard();
            Objective objective = scoreboard.registerNewObjective("test", "dummy", ChatColor.YELLOW + "✎ INFO: ");
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);

            Score line = objective.getScore("-------------------");
            GameMode getGameMode = p.getGameMode();
            int getLevel = p.getLevel();
            int getPing = p.getPing();

            Score getLevelInt = objective.getScore("▶ §2Your level: " + "§e" + getLevel);
            Score getPingInt = objective.getScore("▶ §2Your ping: " + "§e" + getPing);
            Score getGameModeString = objective.getScore("▶ §2Gamemode: " + "§e" + getGameMode);
            Score mobKills = objective.getScore("▶ §2Mob kills: " + "§e" + p.getStatistic(Statistic.MOB_KILLS));
            Score playerDeaths = objective.getScore("▶ §2Player deaths: " + "§e" + p.getStatistic(Statistic.DEATHS));
            Score damageDealt = objective.getScore("▶ §2Damage dealt: " + "§e" + p.getStatistic(Statistic.DAMAGE_DEALT));


            long ticks = p.getPlayerTime();
            int minutes = (int) (ticks / (20 * 60));
            int ticksPerHour = 20 * 60 * 60;
            double hours = (double) (ticks / ticksPerHour);

            if (minutes < 60) {
                Score minResult = objective.getScore("▶ §2Time played (min): " + "§e" + minutes);
                minResult.setScore(0);
            } else {
                Score hourResult = objective.getScore("▶ §2Time played (hours): " + "§e" + hours);
                hourResult.setScore(0);
            }

            int totalBrokenBlocks = 0;
            for (Material blockType : Material.values()) {
                if (blockType.isBlock()) {
                    totalBrokenBlocks += p.getStatistic(Statistic.MINE_BLOCK, blockType);
                }
            }

            Score brokenBlocks = objective.getScore("▶ §2Broken Blocks: " + "§e" + totalBrokenBlocks);

            line.setScore(1);
            getLevelInt.setScore(0);
            getPingInt.setScore(0);
            getGameModeString.setScore(0);
            mobKills.setScore(0);
            playerDeaths.setScore(0);
            damageDealt.setScore(0);
            brokenBlocks.setScore(0);

            p.setScoreboard(scoreboard);
        }
        return true;
    }
}