package Spigot.entity;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;


public class onPlayerJoin implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        World world = player.getWorld();
        world.setStorm(false);
        world.setThundering(false);
        world.setClearWeatherDuration(10000000);
        Player player1 = event.getPlayer();
        String online = player1.getPlayerListName();


        String message = ChatColor.YELLOW + "Welcome, " + player.getName() + "! ";
        String currentonline = "Currently online: " + ChatColor.DARK_GREEN + online;
        String help = "Type" + ChatColor.RED + " /help" + ChatColor.WHITE + " for a list of commands";

        player.sendMessage(message);
        player1.sendMessage(currentonline);
        player1.sendMessage(help);
    }
}