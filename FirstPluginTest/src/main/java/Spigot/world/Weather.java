package Spigot.world;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class Weather implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.isOnline()) {
                Objects.requireNonNull(player.getLocation().getWorld()).setTime(1000);
                Objects.requireNonNull(player.getLocation().getWorld()).setClearWeatherDuration(5000);
            }

        }
        return true;
    }
}
