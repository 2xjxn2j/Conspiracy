package org.jacob.spigot.plugins.AnarchyCore.modules;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HelpCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(label.equalsIgnoreCase("help")) {
            sender.sendMessage(ChatColor.GOLD + "/help " + ChatColor.RED + "- Display this menu");
            sender.sendMessage(ChatColor.GOLD + "/suicide or /kill " + ChatColor.RED + "- Kill yourself!");
            sender.sendMessage(ChatColor.GOLD + "/tps " + ChatColor.RED + "- Check the server's performance");
            sender.sendMessage(ChatColor.GOLD + "/msg <player> <message> " + ChatColor.RED + "- Message a player");
            sender.sendMessage(ChatColor.GOLD + "/ignore <player> " + ChatColor.RED + "- Ignore a player");
            sender.sendMessage(ChatColor.GOLD + "/password <password> " + ChatColor.RED + "- Create a password");
            sender.sendMessage(ChatColor.GOLD + "/worldsize " + ChatColor.RED + "- Still in progress!");

        }
        return true;
    }
}
