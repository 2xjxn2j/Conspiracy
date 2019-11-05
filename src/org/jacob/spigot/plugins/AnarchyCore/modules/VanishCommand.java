package org.jacob.spigot.plugins.AnarchyCore.modules;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jacob.spigot.plugins.AnarchyCore.Main;

public class VanishCommand implements CommandExecutor
{

    FileConfiguration data = Main.getInstance().getPlayerData();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(s.equalsIgnoreCase("vanish") || s.equalsIgnoreCase("v")) {

            if(!(commandSender instanceof Player)) {
                commandSender.sendMessage(ChatColor.RED + "You must be a player!");
                return true;
            }
            Player p = (Player) commandSender;

            if(!p.hasPermission("conspiracy.vanish")) {
                p.sendMessage(ChatColor.RED + "No permission");
                return true;
            }

            if(strings.length > 0) {
                p.sendMessage(ChatColor.RED + "Too many args!");
                return true;
            }

            if(!data.contains("players." + p.getUniqueId().toString() + ".vanished")) {
                data.createSection("players." + p.getUniqueId().toString() + ".vanished");
                Main.getInstance().savePlayerData();
                return true;

            }

            if(data.getBoolean("players." + p.getUniqueId().toString() + ".vanished", true)) {

                p.sendMessage(ChatColor.GOLD + "Successfully un-vanished.");

                data.set("players." + p.getUniqueId().toString() + ".vanished", false);
                Main.getInstance().savePlayerData();
                return true;

            } else {

                p.sendMessage(ChatColor.GOLD + "Successfully vanished.");

                data.set("players." + p.getUniqueId().toString() + ".vanished", true);
                Main.getInstance().savePlayerData();
                return true;

            }
        }
        return true;
    }
}
