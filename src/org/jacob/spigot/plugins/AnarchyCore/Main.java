package org.jacob.spigot.plugins.AnarchyCore;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.jacob.spigot.plugins.AnarchyCore.events.*;
import org.jacob.spigot.plugins.AnarchyCore.modules.*;

import java.io.File;
import java.io.IOException;

public class Main extends JavaPlugin {

    private static Main instance;

    public static Main getInstance() {
        return instance;
    }

    private File playerDataFile;
    private FileConfiguration playerData;

    private File playerInventoryFile;
    private FileConfiguration playerInventory;

    @Override
    public void onEnable() {

        instance = this;

        createPlayerData();
        createPlayerInventory();

        getConfig().options().copyDefaults(true);
        getConfig().addDefault("server-motd", "&aA new Anarchy Server!");
        saveConfig();

        PluginManager pm = Bukkit.getServer().getPluginManager();
        getCommand("suicide").setExecutor(new SuicideCommand());
        getCommand("kill").setExecutor(new SuicideCommand());
        getCommand("help").setExecutor(new HelpCommand());
        getCommand("anarchyreload").setExecutor(new ReloadCommand());
        getCommand("mutechat").setExecutor(new MuteChat());
        getCommand("location").setExecutor(new LocationCommand());
        getCommand("worldsize").setExecutor(new WorldSizeCommand());
        getCommand("msg").setExecutor(new MsgCommand());
        getCommand("tp").setExecutor(new TeleportCommand());
        getCommand("teleport").setExecutor(new TeleportCommand());
        getCommand("tph").setExecutor(new TeleportHereCommand());
        getCommand("tphere").setExecutor(new TeleportHereCommand());
        getCommand("ignore").setExecutor(new IgnoreCommand());
        getCommand("gm").setExecutor(new GamemodeCommand());
        getCommand("gamemode").setExecutor(new GamemodeCommand());
        getCommand("firstjoin").setExecutor(new FirstJoinCommand());
        getCommand("invsee").setExecutor(new InvseeCommand());
        getCommand("password").setExecutor(new PasswordCommand());
        getCommand("vanish").setExecutor(new VanishCommand());
        getCommand("v").setExecutor(new VanishCommand());

        pm.registerEvents(new MuteChat(), this);
        pm.registerEvents(new JoinListener(), this);
        pm.registerEvents(new ServerListEvent(), this);
        pm.registerEvents(new PlayerDataJoinListener(), this);
        pm.registerEvents(new PlayerDataQuitListener(), this);
        pm.registerEvents(new IgnorePlayerChatEvent(), this);
        pm.registerEvents(new PlayerInventorySaveEvent(), this);
        pm.registerEvents(new PlayerPasswordChatListener(), this);
        pm.registerEvents(new PlayerPasswordCheckerListener(), this);
        pm.registerEvents(new PlayerPasswordDenyMoveEvent(), this);
        pm.registerEvents(new PlayerFirstJoinEvent(), this);
        pm.registerEvents(new PlayerVanishMoveEvent(), this);
        pm.registerEvents(new PlayerVanishJoinEvent(), this);

    }

    private void createPlayerData() {
        playerDataFile = new File(getDataFolder(), "data.yml");
        if (!playerDataFile.exists()) {
            playerDataFile.getParentFile().mkdirs();
            saveResource("data.yml", false);
        }

        playerData = new YamlConfiguration();
        try {
            playerData.load(playerDataFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }

    }

    private void createPlayerInventory() {
        playerInventoryFile = new File(getDataFolder(), "player-inventories.yml");
        if (!playerInventoryFile.exists()) {
            playerInventoryFile.getParentFile().mkdirs();
            saveResource("player-inventories.yml", false);
        }

        playerInventory = new YamlConfiguration();
        try {
            playerInventory.load(playerInventoryFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }

    }

    public FileConfiguration getPlayerInventory() {
        return this.playerInventory;
    }
    public FileConfiguration getPlayerData() {
        return this.playerData;
    }

    public void savePlayerData() {
        try {
            playerData.save(playerDataFile);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public void savePlayerInventory() {
        try {
            playerInventory.save(playerInventoryFile);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
