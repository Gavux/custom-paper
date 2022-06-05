package dev.gavux.custompaper;

import org.bukkit.plugin.java.JavaPlugin;

public class CustomPaper extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("Here I am!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Goodbye!");
    }
}
