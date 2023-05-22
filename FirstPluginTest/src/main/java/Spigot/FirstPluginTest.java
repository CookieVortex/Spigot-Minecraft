package Spigot;

import Spigot.entity.SpawnCommand;
import Spigot.entity.SpawnCommandSecond;
import Spigot.permission.Creative;
import Spigot.permission.Survival;
import Spigot.world.Weather;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class FirstPluginTest extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Objects.requireNonNull(getCommand("spawnZombie")).setExecutor(new SpawnCommand());
        Objects.requireNonNull(getCommand("spawnSkeleton")).setExecutor(new SpawnCommandSecond());
        Objects.requireNonNull(getCommand("Survival")).setExecutor(new Survival());
        Objects.requireNonNull(getCommand("Creative")).setExecutor(new Creative());
        Objects.requireNonNull(getCommand("Weather")).setExecutor(new Weather());

    }

    @EventHandler
    public void handeJoin(PlayerJoinEvent event) {

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}