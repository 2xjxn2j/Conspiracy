package org.jacob.spigot.plugins.AnarchyCore.events;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jacob.spigot.plugins.AnarchyCore.Main;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PlayerDataQuitListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        FileConfiguration data = Main.getInstance().getPlayerData();

        Player p = (Player) e.getPlayer();

        data.set("players." + p.getUniqueId().toString() + ".location.x", p.getLocation().getBlockX());
        data.set("players." + p.getUniqueId().toString() + ".location.y", p.getLocation().getBlockY());
        data.set("players." + p.getUniqueId().toString() + ".location.z", p.getLocation().getBlockZ());
        data.set("players." + p.getUniqueId().toString() + ".verified", false);

        data.set("players." + p.getUniqueId().toString() + ".Name", p.getName());

        Main.getInstance().savePlayerData();

        if(!data.contains("players." + e.getPlayer().getUniqueId().toString() + ".first-joined")) {

            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            data.createSection("players." + e.getPlayer().getUniqueId().toString() + ".first-joined");
            data.set("players." + e.getPlayer().getUniqueId().toString() + ".first-joined", format.format(date));
            Main.getInstance().savePlayerData();
        }
    }
}
