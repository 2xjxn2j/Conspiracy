package org.jacob.spigot.plugins.AnarchyCore.modules;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class TeleportCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(s.equalsIgnoreCase("tp") || s.equalsIgnoreCase("teleport")) {

            if(!(commandSender instanceof Player)) {
                commandSender.sendMessage(ChatColor.RED + "You must be a player!");
                return true;
            }

            Player p = (Player) commandSender;

            if(!p.hasPermission("conspiracy.teleport")) {
                p.sendMessage(ChatColor.RED + "No permission");
                return true;
            }

            if(strings.length == 0) {
                p.sendMessage(ChatColor.RED + "Incorrect Usage! " + ChatColor.GOLD + "/<tp:teleport> <player>");
                return true;
            }

            if(strings.length != 1) {
                p.sendMessage(ChatColor.RED + "Incorrect Usage! " + ChatColor.GOLD + "/<tp:teleport> <player>");
                return true;
            }

            Player t = Bukkit.getPlayer(strings[0]);

            if(t == null) {
                p.sendMessage(ChatColor.RED + "That player is offline!");
                return true;
            }

           Location loc = t.getLocation();

           ((Entity)  commandSender).teleport(loc);
            return true;

        }
        return true;
    }
}
