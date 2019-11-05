package org.jacob.spigot.plugins.AnarchyCore.events;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jacob.spigot.plugins.AnarchyCore.Main;

public class PlayerInventorySaveEvent implements Listener {

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        FileConfiguration i = Main.getInstance().getPlayerInventory();

        i.createSection(e.getPlayer().getUniqueId().toString() + ".inventory-contents");
        i.set(e.getPlayer().getUniqueId().toString() + ".inventory-contents", e.getPlayer().getInventory().getContents());
        i.set(e.getPlayer().getUniqueId().toString() + ".Name", e.getPlayer().getName());
        i.createSection( "-----------------INVENTORY ENDS HERE----------------------");

        Main.getInstance().savePlayerInventory();
    }
}
