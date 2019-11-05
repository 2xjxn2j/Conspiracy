package org.jacob.spigot.plugins.AnarchyCore.events;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jacob.spigot.plugins.AnarchyCore.Main;

public class PlayerVanishJoinEvent implements Listener {

    FileConfiguration data = Main.getInstance().getPlayerData();

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if(!data.contains("players." + e.getPlayer().getUniqueId().toString() + ".vanished")) {
            e.getPlayer().sendMessage(ChatColor.RED + "Error.. ");
            return;

        } else {
            if(data.getBoolean("players." + e.getPlayer().getUniqueId().toString() + ".vanished", true)) {
                data.getBoolean("players." + e.getPlayer().getUniqueId().toString() + ".vanished", false);
                Main.getInstance().savePlayerData();

                e.getPlayer().sendMessage(ChatColor.GOLD + "You've been taken out of vanish.");
                return;
            }
        }
    }
}
