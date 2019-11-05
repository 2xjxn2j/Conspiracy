package org.jacob.spigot.plugins.AnarchyCore.events;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jacob.spigot.plugins.AnarchyCore.Main;

public class PlayerVanishMoveEvent implements Listener {

    FileConfiguration data = Main.getInstance().getPlayerData();

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();

        if(!p.hasPermission("conspiracy.vanish")) {
            return;
        }

        if(p.isSprinting() || p.isFlying() || p.isOnGround()) {
            if(data.getBoolean("players." + p.getUniqueId().toString() + ".vanished", true)) {
                p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 300, 255));
                return;

            }
        }

        if(data.getBoolean("players." + p.getUniqueId().toString() + ".vanished", true)) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 300, 255));
            return;

        }

        if(data.getBoolean("players." + p.getUniqueId().toString() + ".vanished", false)) {

            if(p.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
                p.removePotionEffect(PotionEffectType.INVISIBILITY);
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "effect clear " + e.getPlayer().getName());
                return;

            } else {
                return;

            }
        }
    }
}
