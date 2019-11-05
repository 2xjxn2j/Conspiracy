package org.jacob.spigot.plugins.AnarchyCore.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerFirstJoinEvent implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if(!e.getPlayer().hasPlayedBefore()) {
            e.getPlayer().getLocation().setX(-21);
            e.getPlayer().getLocation().setY(252);
            e.getPlayer().getLocation().setZ(-326);
        }
    }
}
