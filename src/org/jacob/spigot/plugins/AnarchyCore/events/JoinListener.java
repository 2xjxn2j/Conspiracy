package org.jacob.spigot.plugins.AnarchyCore.events;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        e.getPlayer().sendMessage(ChatColor.RED + "Welcome to Conspiracy! " + ChatColor.GOLD + "/help " + ChatColor.RED + "to get started.");
        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage(ChatColor.GOLD + p.getName() + " has joined.");
        e.setJoinMessage("");

        if(!p.hasPlayedBefore()) {

            ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
            Bukkit.getServer().dispatchCommand(console, "lp user " + p.getName() + " group set default");
        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        Bukkit.broadcastMessage("");
        e.setQuitMessage(ChatColor.GOLD + p.getName() + " has left.");
    }
}
