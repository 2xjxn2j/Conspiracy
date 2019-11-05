package org.jacob.spigot.plugins.AnarchyCore.events;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jacob.spigot.plugins.AnarchyCore.Main;

public class PlayerPasswordCheckerListener implements Listener {

    FileConfiguration data = Main.getInstance().getPlayerData();

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {

        Player p = (Player) e.getPlayer();

        data.set("players." + p.getUniqueId().toString() + ".verified", false);
        Main.getInstance().savePlayerData();

        if (!data.contains("passwords." + p.getUniqueId().toString())) {

            p.sendMessage(ChatColor.RED + "Please create a password with /password <password>");
            p.sendMessage(ChatColor.RED + "Note that you CANNOT change your password!");
            return;

        } else {
            p.sendMessage(ChatColor.RED + "Please enter your password!");
        }
    }
}
