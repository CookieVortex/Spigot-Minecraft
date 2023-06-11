package me.min.scoreboardplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;

public class OpenScoreboardCommand implements CommandExecutor {
    private final Plugin plugin;
    private BukkitRunnable timerTask;
    private int counter = 0;

    public OpenScoreboardCommand(Plugin plugin) {
        this.plugin = plugin;
        this.timerTask = null;
    }

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
        Scoreboard scoreboard = manager.getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("test", "dummy", ChatColor.YELLOW + "✎ INFO: ");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        Score lineEmpty = objective.getScore(" ");
        lineEmpty.setScore(99);

        Score lineInformation2 = objective.getScore(ChatColor.GOLD + "➢ Server Information");
        lineInformation2.setScore(98);

        Score lineInformation = objective.getScore(ChatColor.GOLD + "➢ Other Information");
        lineInformation.setScore(94);

        player.setScoreboard(scoreboard);
        startTimer(player, objective);
    }

    private void startTimer(Player player, Objective objective) {
        timerTask = new BukkitRunnable() {
            private int counter = 0; // Переменная для отслеживания текущего состояния анимации

            @Override
            public void run() {
                switch (counter) {
                    case 0:
                        // Выполнить первую ветвь
                        String userName = player.getName();
                        Score getUserName = objective.getScore(" ◆ §2Your Name: " + "§e" + userName);
                        getUserName.setScore(97);
                        Bukkit.getLogger().info("Counter value after case 1: " + counter);
                        break;
                    case 1:
                        // Выполнить вторую ветвь
                        int level = player.getLevel();
                        Score getLevelInt = objective.getScore(" ◆ §2Your Level: " + "§e" + level);
                        getLevelInt.setScore(95);
                        Bukkit.getLogger().info("Counter value after case 2: " + counter);
                        break;
                    case 2:
                        // Выполнить третью ветвь
                        int totalExperience = player.getLevel();
                        setRankScore(objective, totalExperience, player);
                        Bukkit.getLogger().info("Counter value after case 3: " + counter);
                        break;
                    case 3:
                        // Выполнить четвертую ветвь
                        Score mobKills = objective.getScore(" ◆ §2Mob Kills: " + "§e" + player.getStatistic(Statistic.MOB_KILLS));
                        mobKills.setScore(93);
                        Bukkit.getLogger().info("Counter value after case 4: " + counter);
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + counter);
                }

                counter++; // Увеличить значение counter после каждого выполнения ветви

                if (counter >= 4) {
                    counter = 0; // Сбросить counter, если он достиг предела
                }
            }
        };
        timerTask.runTaskTimer(plugin, 0, 20); // Запускаем задачу с интервалом 1 секунда (20 тиков)
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