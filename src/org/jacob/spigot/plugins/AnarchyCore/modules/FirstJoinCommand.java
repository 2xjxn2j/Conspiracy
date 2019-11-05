package org.jacob.spigot.plugins.AnarchyCore.modules;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jacob.spigot.plugins.AnarchyCore.Main;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FirstJoinCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(s.equalsIgnoreCase("firstjoin")) {

            if(!(commandSender instanceof Player)) {
                commandSender.sendMessage(ChatColor.RED + "You must be a player!");
                return true;
            }

            Player p = (Player) commandSender;


            if(strings.length == 0) {

                if(!Main.getInstance().getPlayerData().contains("players." + p.getUniqueId().toString() + ".first-joined")) {
                    p.sendMessage(ChatColor.RED + "You seem to not have a join date. Please relog and try again!");
                    return true;
                }

                p.sendMessage(ChatColor.GOLD + "You first joined on " +
                        ChatColor.WHITE + Main.getInstance().getPlayerData().get("players." +
                        p.getUniqueId().toString() + ".first-joined"));
                return true;

            }


            Player t = Bukkit.getPlayer(strings[0]);

            if(strings.length == 1) {

                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date date = new Date();

                p.sendMessage(ChatColor.WHITE + t.getName() + ChatColor.GOLD + " first joined on " +
                        ChatColor.WHITE + Main.getInstance().getPlayerData().get("players." +
                        t.getUniqueId().toString() + ".first-joined"));

                Main.getInstance().getPlayerData().set("players." + t.getUniqueId().toString() + ".first-joined", format.format(date));
                Main.getInstance().savePlayerData();
                return true;

            }

            if(t == null) {
                p.sendMessage(ChatColor.RED + "That player isn't online!");
                return true;
            }

        }
        return true;
    }
}
