package Spigot;

import Spigot.commands.*;
import Spigot.entity.SpawnSkeleton;
import Spigot.entity.SpawnZombie;
import Spigot.entity.onPlayerJoin;
import Spigot.permission.PermissionTag;
import Spigot.world.Weather;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class FirstPluginTest extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Objects.requireNonNull(getCommand("spawnZombie")).setExecutor(new SpawnZombie());
        Objects.requireNonNull(getCommand("spawnSkeleton")).setExecutor(new SpawnSkeleton());
        Objects.requireNonNull(getCommand("Weather")).setExecutor(new Weather());
        getServer().getPluginManager().registerEvents(new PermissionTag(this), this);
        getServer().getPluginManager().registerEvents(new onPlayerJoin(), this);
        Objects.requireNonNull(getCommand("hologram")).setExecutor(new HologramCommand());
        Objects.requireNonNull(getCommand("hologram2")).setExecutor(new HologramCommandTwo());
        Objects.requireNonNull(getCommand("removeholograms")).setExecutor(new RemoveHologramsCommand());
        Objects.requireNonNull(getCommand("rtp")).setExecutor(new RandomTPCommand());
        Objects.requireNonNull(getCommand("findarmorstands")).setExecutor(new FindArmorStandsCommand());
        Objects.requireNonNull(getCommand("spawn")).setExecutor(new SpawnCommand());
    }

    public static class FindArmorStandsCommand implements CommandExecutor {
        @Override
        public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
            if (sender instanceof Player) {
                Player player = (Player) sender;

                List<ArmorStand> armorStands = findArmorStandsInWorld(player.getWorld());

                player.sendMessage("Найдено голограмм: " + armorStands.size());
                for (ArmorStand armorStand : armorStands) {
                    player.sendMessage("Координаты голограммы: " + armorStand.getLocation());
                }
            }

            return true;
        }
        private List<ArmorStand> findArmorStandsInWorld(World world) {
            List<ArmorStand> armorStands = new ArrayList<>();

            for (Entity entity : world.getEntities()) {
                if (entity instanceof ArmorStand) {
                    armorStands.add((ArmorStand) entity);
                }
            }

            return armorStands;
        }
    }
}
