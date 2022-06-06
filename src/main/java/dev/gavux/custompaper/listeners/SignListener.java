package dev.gavux.custompaper.listeners;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class SignListener implements Listener {
    @EventHandler
    public void onSignCreate(SignChangeEvent e) {
        for(int i = 0; i < e.getLines().length; i++) {
            e.setLine(i, ChatColor.translateAlternateColorCodes('&', e.getLine(i)));
        }
    }
}
