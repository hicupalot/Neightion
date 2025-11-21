import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.UUID;

public class mutechat implements CommandExecutor {
    Component noPermission = Component.text("[Neightion] You do not have permission").color(TextColor.color(225,87,75));
    Component notPlayer = Component.text("[Neightion] You must be a player to do this").color(TextColor.color(225,87,75));
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        FileConfiguration config = Neightion.getInstance().getConfig();
        if (!(sender instanceof Player) && !(sender instanceof ConsoleCommandSender)) {
            sender.sendMessage(notPlayer);
            return false;
        }
        if (!sender.hasPermission("neightion.mod")) {
            sender.sendMessage(noPermission);
            return false;
        }
        if (!config.getBoolean("chatMuted")) {
            Component muteAlert = Component.text("The Chat Has Been Muted").color(TextColor.color(254,63,63));
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.sendMessage(muteAlert);
            }
            config.set("chatMuted", true);
            Neightion.getInstance().saveConfig();
        }
        else if (config.getBoolean("chatMuted")){
            config.set("chatMuted",false);
            Neightion.getInstance().saveConfig();
            Component alert = Component.text("The Chat Has Been Unmuted").color(TextColor.color(63,254,254));
            for(Player player : Bukkit.getOnlinePlayers()){
                player.sendMessage(alert);
            }
        }
        return true;
    }
}
