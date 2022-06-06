package dev.gavux.custompaper;

import dev.gavux.custompaper.commands.CustomPaperCommand;
import dev.gavux.custompaper.listeners.SignListener;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class CustomPaper extends JavaPlugin {

    private File customConfigFile;
    private FileConfiguration customConfig;
    @Override
    public void onEnable() {
        getLogger().info("Here I am!");


        createCustomConfig();
        getCommand("custompaper").setExecutor(new CustomPaperCommand(this));
        getServer().getPluginManager().registerEvents(new SignListener(), this);

        getServer().getScheduler().runTaskTimer(this, new Runnable() {
            @Override
            public void run() {
                for(Player p : Bukkit.getOnlinePlayers()) {
                    if(customConfig.getBoolean(p.getUniqueId() + ".coords")) {
                        p.sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§7X: §e" + p.getLocation().getBlockX() + "§7 Y: §e" + p.getLocation().getBlockY() + "§7 Z: §e" + p.getLocation().getBlockZ()));
                    }
                }
            }
        }, 0L, 1L);

    }

    public FileConfiguration getCustomConfig() {
        return customConfig;
    }

    public void saveCustomConfig() {
        try {
            customConfig.save(customConfigFile);
        } catch (Exception e) {
            getLogger().warning("Could not save player configuration.");
        }
    }

    private void createCustomConfig() {
        customConfigFile = new File(getDataFolder(), "players.yml");
        if(!customConfigFile.exists()) {
            customConfigFile.getParentFile().mkdirs();
            saveResource("players.yml", false);
        }

        customConfig = YamlConfiguration.loadConfiguration(customConfigFile);
    }

    @Override
    public void onDisable() {
        saveCustomConfig();
        getLogger().info("Goodbye!");
    }
}
