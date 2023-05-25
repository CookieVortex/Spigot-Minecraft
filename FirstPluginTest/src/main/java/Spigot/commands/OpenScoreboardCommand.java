package Spigot.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Biome;
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
            Objective objective = scoreboard.registerNewObjective("test", "dummy", ChatColor.YELLOW + "Help plugin");
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);

            String getName = p.getName();
            Score getNameString = objective.getScore("Your name: " + getName);
            getNameString.setScore(1);

            Biome biome = p.getLocation().getBlock().getBiome();
            String getBiome = biome.name();
            Score getBiomeString = objective.getScore("Your Biom: " + getBiome);
            getBiomeString.setScore(2);

            int onlinePlayers = Bukkit.getOnlinePlayers().size();
            int maxPlayers = Bukkit.getMaxPlayers();
            Score score = objective.getScore("Players: " + onlinePlayers + "/" + maxPlayers);
            score.setScore(3);


            p.setScoreboard(scoreboard);
        }
        return true;
    }
}