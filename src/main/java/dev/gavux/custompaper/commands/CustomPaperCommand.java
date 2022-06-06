package dev.gavux.custompaper.commands;

import com.destroystokyo.paper.utils.PaperPluginLogger;
import dev.gavux.custompaper.CustomPaper;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CustomPaperCommand implements CommandExecutor {
    private CustomPaper plugin;

    public CustomPaperCommand(CustomPaper plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(!(commandSender instanceof Player player)) {
            commandSender.sendMessage("This is a player-only command.");
            return true;
        }

        if(strings[0].equalsIgnoreCase("coords")) {
            if(!plugin.getCustomConfig().contains(player.getUniqueId() + ".coords") || !plugin.getCustomConfig().getBoolean(player.getUniqueId() + ".coords")) {
                plugin.getCustomConfig().set(player.getUniqueId() + ".coords", true);
                player.sendMessage("Coordinates will now be shown.");
            } else {
                plugin.getCustomConfig().set(player.getUniqueId() + ".coords", false);
                player.sendMessage("Coordinates will not be shown anymore.");
            }
        } else {
            player.sendMessage("Usage: /custompaper coords");
        }
        return true;
    }
}
