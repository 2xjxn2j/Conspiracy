package org.jacob.spigot.plugins.AnarchyCore.utils;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class IgnoreManager {

    private IgnoreManager() { }

    static IgnoreManager instance = new IgnoreManager();

    public static IgnoreManager getInstance() {
        return instance;
    }

    HashMap<Player, ArrayList<Player>> ignore = new HashMap<Player, ArrayList<Player>>();

    public HashMap<Player, ArrayList<Player>> getIgnore() {
        return ignore;
    }
}
