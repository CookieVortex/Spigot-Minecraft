package Spigot.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.*;

import java.util.Objects;

public class OpenScoreboardCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;
        setupScoreboard(player);
        return true;
    }

    private void setupScoreboard(Player player) {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = Objects.requireNonNull(manager).getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("test", "dummy", ChatColor.YELLOW + "✎ INFO: ");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        Score lineEmpty = objective.getScore(" ");
        lineEmpty.setScore(99);

        Score lineInformation2 = objective.getScore(ChatColor.GOLD + "➢ Server Information");
        lineInformation2.setScore(98);

        Score lineInformation = objective.getScore(ChatColor.GOLD + "➢ Other Information");
        lineInformation.setScore(94);

        String userName = player.getName();
        Score getUserName = objective.getScore(" ◆ §2Your Name: " + "§e" + userName);
        getUserName.setScore(97);

        int level = player.getLevel();
        Score getLevelInt = objective.getScore(" ◆ §2Your Level: " + "§e" + level);
        getLevelInt.setScore(95);

        int totalExperience = player.getLevel();
        setRankScore(objective, totalExperience, player);

        Score mobKills = objective.getScore(" ◆ §2Mob Kills: " + "§e" + player.getStatistic(Statistic.MOB_KILLS));
        mobKills.setScore(93);

        int totalBrokenBlocks = countBrokenBlocks(player);
        Score brokenBlocks = objective.getScore(" ◆ §2Broken Blocks: " + "§e" + totalBrokenBlocks);
        brokenBlocks.setScore(92);

        player.setScoreboard(scoreboard);
    }

    private void setRankScore(Objective objective, int totalExperience, Player player) {
        if (totalExperience <= 1) {
            objective.getScore(" ◆ §2Rank: " + "§7[" + "Beginner" + "]").setScore(96);
            player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 0));
        } else if (totalExperience <= 40) {
            objective.getScore(" ◆ §2Rank: " + "§3[" + "Intermediate" + "]").setScore(96);
            player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, Integer.MAX_VALUE, 0));
        } else if (totalExperience <= 60) {
            objective.getScore(" ◆ §2Rank: " + "§d[" + "Advanced" + "]").setScore(96);
            player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 1));
        } else if (totalExperience <= 80) {
            objective.getScore(" ◆ §2Rank: " + "§5[" + "Expert" + "]").setScore(96);
            player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 1));
        } else if (totalExperience < 99) {
            objective.getScore(" ◆ §2Rank: " + "§9[" + "GrandMaster" + "]").setScore(96);
            player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 2));
            player.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, Integer.MAX_VALUE, 1));
        } else if (totalExperience >= 100) {
            objective.getScore(" ◆ §2Rank: " + "§4[" + "Master" + "]").setScore(96);
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 2));
            player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 1));
        }
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
