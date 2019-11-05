package org.jacob.spigot.plugins.AnarchyCore.modules;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class MuteChat implements Listener, CommandExecutor {

    public static boolean isMuted = false;
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(s.equalsIgnoreCase("mutechat")) {

            if(!commandSender.hasPermission("conspiracy.mutechat")) {
                commandSender.sendMessage(ChatColor.RED + "No permission");
                return true;

            } else {

                if(isMuted) {
                    isMuted = false;
                    Bukkit.getServer().broadcastMessage(ChatColor.GREEN + "Global chat has been enabled");
                    return true;

                } else if (!isMuted) {
                    isMuted = true;
                    Bukkit.getServer().broadcastMessage(ChatColor.RED + "Global chat has been disabled");
                    return true;
                }
            }
        }

        return false;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = (Player) e.getPlayer();

        if(!p.hasPermission("conspiracy.mutechat")) {
            if(isMuted) { e.setCancelled(true); }
        }
    }
}
