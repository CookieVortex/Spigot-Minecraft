package Spigot;

import Spigot.entity.SpawnCommand;
import Spigot.entity.SpawnCommandSecond;
import Spigot.entity.onPlayerJoin;
import Spigot.permission.PermissionTag;
import Spigot.world.Weather;
import org.bukkit.World;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.Objects;

public final class FirstPluginTest extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Objects.requireNonNull(getCommand("spawnZombie")).setExecutor(new SpawnCommand());
        Objects.requireNonNull(getCommand("spawnSkeleton")).setExecutor(new SpawnCommandSecond());
        Objects.requireNonNull(getCommand("Weather")).setExecutor(new Weather());
        getServer().getPluginManager().registerEvents(new PermissionTag(this), this);
        getServer().getPluginManager().registerEvents(new onPlayerJoin(), this);

        List<World> worlds = getServer().getWorlds();
        if (!worlds.isEmpty()) {
            World world = worlds.get(0);
            world.setTime(1000);
            world.setClearWeatherDuration(5000);
            world.isClearWeather();
        } else {
            getLogger().warning("No worlds found.");
        }
    }
}