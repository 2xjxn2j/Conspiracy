package org.jacob.spigot.plugins.AnarchyCore.modules;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import java.io.File;
import org.bukkit.craftbukkit.libs.org.apache.commons.io.FileUtils;

public class WorldSizeCommand implements CommandExecutor {


    @Override
    public boolean onCommand(final CommandSender commandSender, final Command command, final String s, final String[] strings) {

        if (s.equalsIgnoreCase("worldsize")) {

            long size = FileUtils.sizeOfDirectory(new File("/home/serv/world/"));
            double bytes = size;
            double kilobytes = (bytes / 1024);
            double megabytes = (kilobytes / 1024);
            double gigabytes = (megabytes / 1024);
            double worldsize = (double)(Math.round(gigabytes * 100.0) / 100.0);

            commandSender.sendMessage(ChatColor.AQUA + "The server's current world size is " + worldsize + "GB");

        }
        return true;
    }
}
