package org.jacob.spigot.plugins.AnarchyCore.modules;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GamemodeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(s.equalsIgnoreCase("gm") || s.equalsIgnoreCase("gamemode")) {

            if(!(commandSender instanceof Player)) {
                commandSender.sendMessage(ChatColor.RED + "You must be a player!");
                return true;
            }

            Player p = (Player) commandSender;

            if(!p.hasPermission("conspiracy.gamemode")) {
                commandSender.sendMessage(ChatColor.RED + "No permission");
                return true;
            }

            if(strings.length == 0) {
                p.sendMessage(ChatColor.RED + "Incorrect Usage! " +
                        ChatColor.GOLD + "/<gm:gamemode> <c:s:sp:a>");
                return true;
            }

            if(strings.length != 1) {
                p.sendMessage(ChatColor.RED + "Incorrect Usage! " +
                        ChatColor.GOLD + "/<gm:gamemode> <c:s:sp:a>");
                return true;
            }

            if(strings[0].equalsIgnoreCase("c")) {

                p.setGameMode(GameMode.CREATIVE);
                p.sendMessage(ChatColor.GOLD + "Your gamemode has been set to " +
                ChatColor.WHITE + "Creative");

            }

            if(strings[0].equalsIgnoreCase("s")) {

                p.setGameMode(GameMode.SURVIVAL);
                p.sendMessage(ChatColor.GOLD + "Your gamemode has been set to " +
                        ChatColor.WHITE + "Survival");

            }

            if(strings[0].equalsIgnoreCase("sp")) {

                p.setGameMode(GameMode.SPECTATOR);
                p.sendMessage(ChatColor.GOLD + "Your gamemode has been set to " +
                        ChatColor.WHITE + "Spectator");

            }

            if(strings[0].equalsIgnoreCase("a")) {

                p.setGameMode(GameMode.ADVENTURE);
                p.sendMessage(ChatColor.GOLD + "Your gamemode has been set to " +
                        ChatColor.WHITE + "Adventure");

            }

            if(strings[0] == null) {
                p.sendMessage(ChatColor.GOLD + "Incorrect Usage! " +
                        ChatColor.WHITE + "/<gm:gamemode> <c:s:sp:a>");
            }

        }
        return true;
    }
}
