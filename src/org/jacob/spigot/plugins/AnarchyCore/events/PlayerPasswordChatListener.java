package org.jacob.spigot.plugins.AnarchyCore.events;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.jacob.spigot.plugins.AnarchyCore.Main;

public class PlayerPasswordChatListener implements Listener {

    FileConfiguration data = Main.getInstance().getPlayerData();

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();

        if(!data.contains("passwords." + p.getUniqueId().toString() + ".password")) {
            p.sendMessage(ChatColor.RED + "Please create a password!");
            e.setCancelled(true);
            return;
        }

        if (data.getBoolean("players." + p.getUniqueId().toString() + ".verified", true)) {
            return;

        }

        if (data.getBoolean("players." + p.getUniqueId().toString() + ".verified", false)) {

            if (e.getMessage().equals(data.get("passwords." + p.getUniqueId().toString() + ".password"))) {
                e.setCancelled(true);
                p.sendMessage(ChatColor.GREEN + "Login Successfull.");
                data.set("players." + p.getUniqueId().toString() + ".verified", true);
                Main.getInstance().savePlayerData();
                return;
            }
            e.setCancelled(true);
            p.sendMessage(ChatColor.RED + "Incorrect password!");
            return;
        }

        if (e.getMessage().equals(data.get("passwords." + p.getUniqueId().toString() + ".password"))) {
            p.sendMessage(ChatColor.GREEN + "Success! You may now chat.");
            e.setCancelled(true);
            data.set("players." + p.getUniqueId().toString() + ".verified", true);
            Main.getInstance().savePlayerData();
            return;

        } else {
            e.setCancelled(true);
            p.sendMessage(ChatColor.RED + "Incorrect password!");
            return;

        }
    }

}
