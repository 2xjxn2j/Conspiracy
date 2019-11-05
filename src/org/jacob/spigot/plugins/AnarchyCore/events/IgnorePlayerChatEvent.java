package org.jacob.spigot.plugins.AnarchyCore.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.jacob.spigot.plugins.AnarchyCore.utils.IgnoreManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class IgnorePlayerChatEvent implements Listener {

    HashMap<Player, ArrayList<Player>> i = IgnoreManager.getInstance().getIgnore();

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player sender = e.getPlayer();
        Set<Player> r = e.getRecipients();
        for(Player pls : Bukkit.getServer().getOnlinePlayers()) {

            if(!i.containsKey(pls)) {
                return;
            }

            if(i.get(pls).contains(sender)) {
                r.remove(pls);
            }
        }
        e.setCancelled(true);
        for(Player fin : r) {
            fin.sendMessage(e.getFormat() + e.getMessage());
        }
    }
}
