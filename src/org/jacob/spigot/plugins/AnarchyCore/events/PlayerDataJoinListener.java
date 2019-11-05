package org.jacob.spigot.plugins.AnarchyCore.events;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jacob.spigot.plugins.AnarchyCore.Main;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PlayerDataJoinListener implements Listener {


    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        FileConfiguration data = Main.getInstance().getPlayerData();

        if(!data.contains("players." + e.getPlayer().getUniqueId().toString())) {
            data.createSection("players." + e.getPlayer().getUniqueId().toString());
            Main.getInstance().savePlayerData();
        } else {
            return;
        }
    }
}
