package org.jacob.spigot.plugins.AnarchyCore.modules;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jacob.spigot.plugins.AnarchyCore.Main;

public class ReloadCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(label.equalsIgnoreCase("anarchyreload")) {

            if(!(sender instanceof Player)) {
                Main.getInstance().reloadConfig();
                Main.getInstance().saveConfig();
                sender.sendMessage(ChatColor.GREEN + "AnarchyMC reloaded!");
                return true;
            }
            Player p = (Player) sender;
            if(!p.hasPermission("anarchy.reloadconfig")) {
                p.sendMessage(ChatColor.RED + "No permission");
                return true;
            } else {
                Main.getInstance().reloadConfig();
                Main.getInstance().saveConfig();
                p.sendMessage(ChatColor.GREEN + "AnarchyMC reloaded!");
                return true;
            }
        }
        return true;
    }
}
