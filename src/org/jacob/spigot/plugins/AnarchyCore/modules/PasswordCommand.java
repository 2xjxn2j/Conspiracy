package org.jacob.spigot.plugins.AnarchyCore.modules;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jacob.spigot.plugins.AnarchyCore.Main;

public class PasswordCommand implements CommandExecutor {

    FileConfiguration data = Main.getInstance().getPlayerData();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(s.equalsIgnoreCase("password")) {

            if(!(commandSender instanceof Player)) {
                commandSender.sendMessage(ChatColor.RED + "You must be a player!");
                return true;
            }

            Player p = (Player) commandSender;

            if(strings.length == 0) {

                if(data.contains("passwords." + p.getUniqueId().toString())) {
                    p.sendMessage(ChatColor.RED + "You've already created a password!");
                    return true;
                } else {
                    p.sendMessage(ChatColor.RED + "Incorrect Usage!" + ChatColor.GOLD + " /password <password>");
                    return true;
                }
            }

            if(strings.length > 1) {

                if(data.contains("passwords." + p.getUniqueId().toString())) {
                    p.sendMessage(ChatColor.RED + "You've already created a password!");
                    return true;
                } else {
                    p.sendMessage(ChatColor.RED + "Your password cannot contain any spaces!");
                    return true;
                }
            }

            if(!data.contains("passwords." + p.getUniqueId().toString())) {

                p.sendMessage(ChatColor.GOLD + "Your password is now " + ChatColor.WHITE + strings[0]);
                data.set("passwords." + p.getUniqueId().toString() + ".password", strings[0]);
                data.set("players." + p.getUniqueId().toString() + ".verified", true);
                Main.getInstance().savePlayerData();

                p.kickPlayer(ChatColor.GOLD + "Please relog!");
                return true;
            } else {
                p.sendMessage(ChatColor.RED + "You've already created a password!");
                return true;
            }

        }
        return true;
    }
}
