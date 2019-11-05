package org.jacob.spigot.plugins.AnarchyCore.modules;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SuicideCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(label.equalsIgnoreCase("suicide") || label.equalsIgnoreCase("kill")) {
            if(!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "You must be a player!");
                return true;
            }

            Player p = (Player) sender;
            p.setHealth(0.0);
        }
        return true;
    }
}
