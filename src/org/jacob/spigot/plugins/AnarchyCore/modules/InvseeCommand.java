package org.jacob.spigot.plugins.AnarchyCore.modules;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class InvseeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(s.equalsIgnoreCase("invsee")) {
            if(!(commandSender instanceof Player)) {
                commandSender.sendMessage(ChatColor.RED + "You must be a player!");
                return true;
            }

            Player p = (Player) commandSender;

            if(!p.hasPermission("conspiracy.invsee")) {
                p.sendMessage(ChatColor.RED + "No permission");
                return true;
            }

            if(strings.length == 0) {
                p.sendMessage(ChatColor.RED + "Incorrect Usage! " + ChatColor.GOLD + "/invsee <player>");
                return true;
            }

            if(strings.length != 1) {
                p.sendMessage(ChatColor.RED + "Incorrect Usage! " + ChatColor.GOLD + "/invsee <player>");
                return true;
            }

            Player t = (Player) Bukkit.getPlayer(strings[0]);

            if(t == null) {
                p.sendMessage(ChatColor.RED + "That player isn't online!");
                return true;
            }

            Inventory tInventory = t.getInventory();

            p.openInventory(tInventory);
            return true;

        }
        return true;
    }
}
