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
        world.setClearWeatherDuration(1000000);
        /*String ipAddress = Objects.requireNonNull(player.getAddress()).getAddress().getHostAddress();*/
        Player player1 = event.getPlayer();
        String online = player1.getPlayerListName();


        String message = ChatColor.YELLOW + "Welcome, " + player.getName() + "! ";
        /*message += ChatColor.GRAY + "Your IP address is: " + ipAddress;*/
        String currentonline = "Currently online: " + ChatColor.RED + online;
        String help = "Type" + ChatColor.RED + " /help" + ChatColor.WHITE + " for a list of commands";
        String scoreboard = "Type" + ChatColor.RED + " /sb" + ChatColor.GREEN + " for a help board";

        player.sendMessage(message);
        player1.sendMessage(currentonline);
        player1.sendMessage(help);
        player1.sendMessage(scoreboard);
    }
}