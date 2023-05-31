package Spigot;

import Spigot.commands.HologramCommand;
import Spigot.commands.OpenScoreboardCommand;
import Spigot.commands.RandomTPCommand;
import Spigot.commands.RemoveHologramsCommand;
import Spigot.entity.SpawnSkeleton;
import Spigot.entity.SpawnZombie;
import Spigot.entity.onPlayerJoin;
import Spigot.permission.PermissionTag;
import Spigot.world.Weather;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.Objects;

public final class FirstPluginTest extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Objects.requireNonNull(getCommand("spawnZombie")).setExecutor(new SpawnZombie());
        Objects.requireNonNull(getCommand("spawnSkeleton")).setExecutor(new SpawnSkeleton());
        Objects.requireNonNull(getCommand("Weather")).setExecutor(new Weather());
        getServer().getPluginManager().registerEvents(new PermissionTag(this), this);
        getServer().getPluginManager().registerEvents(new onPlayerJoin(), this);
        Objects.requireNonNull(getCommand("sb")).setExecutor(new OpenScoreboardCommand());
        Objects.requireNonNull(getCommand("hologram")).setExecutor(new HologramCommand());

        Objects.requireNonNull(getCommand("removeholograms")).setExecutor(new RemoveHologramsCommand());
        Objects.requireNonNull(getCommand("rtp")).setExecutor(new RandomTPCommand());


        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        scheduler.runTaskTimer(this, () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                ScoreboardUpdater scoreboardUpdater = new ScoreboardUpdater(player);
                scoreboardUpdater.run();
            }
        }, 0, 20);
    }

    private static class ScoreboardUpdater implements Runnable {
        private final Player player;

        public ScoreboardUpdater(Player player) {
            this.player = player;
        }

        @Override
        public void run() {
            OpenScoreboardCommand scoreboardCommand = new OpenScoreboardCommand();
            scoreboardCommand.onCommand(player, null, null, null);
        }
    }
}
