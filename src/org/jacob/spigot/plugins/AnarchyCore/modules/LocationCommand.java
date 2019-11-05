package org.jacob.spigot.plugins.AnarchyCore.modules;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LocationCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(s.equalsIgnoreCase("location")) {
            if(commandSender.hasPermission("constantiam.location")) {
                if(strings.length == 0) {
                    commandSender.sendMessage(ChatColor.RED + "Incorrect Usage! /location <player>");
                    return true;
                }

                if(strings.length > 1) {
                    commandSender.sendMessage(ChatColor.RED + "Incorrect Usage! /location <player>");
                    return true;
                }

                Player t = Bukkit.getPlayer(strings[0]);

                if(t == null) {
                    commandSender.sendMessage(ChatColor.RED + "That player doesn't exist!");
                    return true;
                }

                commandSender.sendMessage(ChatColor.GOLD + t.getName() + "'s location is:");
                commandSender.sendMessage(ChatColor.GOLD + "X: " + ChatColor.RESET + t.getLocation().getBlockX());
                commandSender.sendMessage(ChatColor.GOLD + "Y: " + ChatColor.RESET + t.getLocation().getBlockY());
                commandSender.sendMessage(ChatColor.GOLD + "Z: " + ChatColor.RESET + t.getLocation().getBlockZ());
                return true;
            }
        }
        return false;
    }
}
