package org.jacob.spigot.plugins.AnarchyCore.modules;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.jacob.spigot.plugins.AnarchyCore.Main;
import org.jacob.spigot.plugins.AnarchyCore.utils.IgnoreManager;

import java.util.ArrayList;
import java.util.HashMap;

public class IgnoreCommand implements CommandExecutor, Listener {

    HashMap<Player, ArrayList<Player>> i = IgnoreManager.getInstance().getIgnore();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(s.equalsIgnoreCase("ignore")) {

            if(!(commandSender instanceof Player)) {
                commandSender.sendMessage(ChatColor.RED + "You must be a player!");
                return true;
            }

            Player p = (Player) commandSender;

            if(!commandSender.hasPermission("conspiracy.ignore")) {
                commandSender.sendMessage(ChatColor.RED + "No permission");
                return true;
            }

            FileConfiguration data = Main.getInstance().getPlayerData();


            if(strings.length == 0) {
                p.sendMessage(ChatColor.RED + "Incorrect Usage! " + ChatColor.GOLD + "/ignore <player>");
                return true;
            }

            if(strings.length != 1) {
                p.sendMessage(ChatColor.RED + "Incorrect Usage! " + ChatColor.GOLD + "/ignore <player>");
                return true;
            }

            Player ignored = Bukkit.getPlayer(strings[0]);

            if(ignored == null) {
                p.sendMessage(ChatColor.RED + "That player isn't online!");
                return true;

            }

            if(p == ignored) {
                p.sendMessage(ChatColor.RED + "You can't ignore yourself!");
                return true;
            }

            if(i.get(p) == null) {
                ArrayList<Player> al = new ArrayList<Player>();
                al.add(ignored);
                i.put(p, al);
                p.sendMessage(ChatColor.GOLD + "You are now ignoring " + ChatColor.WHITE + ignored.getName());

                data.set("ignore." + p.getUniqueId().toString() + ".name", p.getName());
                data.set("ignore." + p.getUniqueId().toString() + ".list", i);
                Main.getInstance().savePlayerData();

                return true;
            }
            if(i.get(p).contains(ignored)) {
                ArrayList<Player> al = i.get(p);
                al.remove(ignored);
                i.put(p, al);
                p.sendMessage(ChatColor.GOLD + "You are no longer ignoring " + ChatColor.WHITE + ignored.getName());
                return true;
            }
            if(!i.get(p).contains(ignored)) {
                ArrayList<Player> al = i.get(p);
                al.add(ignored);
                i.put(p, al);
                p.sendMessage(ChatColor.GOLD + "You are now ignoring " + ChatColor.WHITE + ignored.getName());

                data.set("ignore." + p.getUniqueId().toString() + ".name", p.getName());
                data.set("ignore." + p.getUniqueId().toString() + ".list", i);
                Main.getInstance().savePlayerData();

                return true;
            }

        }
        return true;
    }

}
