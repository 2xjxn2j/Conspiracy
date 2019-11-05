package org.jacob.spigot.plugins.AnarchyCore.events;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.jacob.spigot.plugins.AnarchyCore.Main;

public class PlayerPasswordDenyMoveEvent implements Listener {

    FileConfiguration data = Main.getInstance().getPlayerData();

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();

        if(!data.getBoolean("players." + p.getUniqueId().toString() + ".verified", true)) {
            e.setCancelled(true);
        }
    }
}
