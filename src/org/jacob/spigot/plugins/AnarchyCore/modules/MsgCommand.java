package org.jacob.spigot.plugins.AnarchyCore.modules;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class MsgCommand implements CommandExecutor {

    private static HashMap<UUID, UUID> replies = new HashMap<>();

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

        // base command
        if (commandLabel.equalsIgnoreCase("msg")) {

            // console sender check
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou must be a player!"));
                return true;
            }

            Player player = (Player) sender;

            if (!sender.hasPermission("conspiracy.msg")) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cNo permission"));
                return true;
            }

            else if (args.length <= 1) {
                sender.sendMessage(
                        ChatColor.RED + "Incorrect Usage! " + ChatColor.GOLD + "/msg <player> <message>");
                return true;
            }

            else if (args.length >= 2) {

                Player target = Bukkit.getServer().getPlayer(args[0]);

                if (target == null) {
                    sender.sendMessage(ChatColor.RED + "That player is offline!");
                    return true;
                }

                if (target == sender) {
                    sender.sendMessage(ChatColor.RED + "You can't message yourself!");
                    return true;
                }

                else {

                    String message = "";
                    for (int i = 1; i != args.length; i++)
                        message += args[i] + " ";

                    String meTo = (ChatColor.GOLD + "To " + target.getName() +  ":");
                    String toMe = (ChatColor.GOLD + "From " + player.getName() + ":");

                    sender.sendMessage(meTo + " " + ChatColor.DARK_GREEN + message);
                    target.sendMessage(toMe + " " + ChatColor.DARK_GREEN + message);

                    replies.put(target.getUniqueId(), player.getUniqueId());

                    return true;

                }
            }
        }

        // base command
        if (commandLabel.equalsIgnoreCase("r")) {

            // console sender check
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou must be a player!"));
                return true;
            }

            Player player = (Player) sender;

            if (!sender.hasPermission("vc.msg")) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cNo permission"));
                return true;
            }

            if (args.length < 1) {
                sender.sendMessage(ChatColor.DARK_GREEN + "Correct Usage: " + ChatColor.RED + "/r <message>");
                return true;
            }

            Player target = Bukkit.getPlayer(replies.get(player.getUniqueId()));

            if (target == null) {
                sender.sendMessage(ChatColor.RED + "That player is now offline!");
                return true;
            }

            if (replies.containsKey(player.getUniqueId())) {

                String message = "";
                for (String s : args) {
                    message = message + s + " ";
                }

                String meTo = (ChatColor.GOLD + "To " + target.getName() +  ":");
                String toMe = (ChatColor.GOLD + "From " + player.getName() + ":");

                sender.sendMessage(meTo + " " + ChatColor.DARK_GREEN + message);
                target.sendMessage(toMe + " " + ChatColor.DARK_GREEN + message);

                replies.put(target.getUniqueId(), player.getUniqueId());
            }

            else {
                sender.sendMessage(ChatColor.RED + "You do not have anyone to reply to!");
                return true;
            }

        }
        return true;
    }

}
