package Spigot;

import Spigot.commands.OpenScoreboardCommand;
import Spigot.entity.SpawnSkeleton;
import Spigot.entity.SpawnZombie;
import Spigot.entity.onPlayerJoin;
import Spigot.permission.PermissionTag;
import Spigot.world.Weather;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

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
    }
}