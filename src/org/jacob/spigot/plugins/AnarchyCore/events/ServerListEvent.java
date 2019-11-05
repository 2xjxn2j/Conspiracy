package org.jacob.spigot.plugins.AnarchyCore.events;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import org.jacob.spigot.plugins.AnarchyCore.Main;

public class ServerListEvent implements Listener {

    @EventHandler
    public void onPing(ServerListPingEvent event) {
        event.setMotd(ChatColor.translateAlternateColorCodes('&', Main.getInstance().getConfig().getString("server-motd")));
    }
}
