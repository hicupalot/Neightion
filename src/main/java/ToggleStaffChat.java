import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ToggleStaffChat implements CommandExecutor {
    public static final Component staffAdd = Component.text().append(Component.text("[").color(TextColor.color(254, 254, 63 )).
            append(Component.text("STAFFCHAT").color(TextColor.color(225, 87,75)).append(Component.text("]").color(TextColor.color(254,254,63)).append(
                    Component.text(" ENABLED").color(TextColor.color(225,87,75)))))).build();
    Component noPermission = Component.text("[Neightion] You do not have permission").color(TextColor.color(225,87,75));
    Component notPlayer = Component.text("[Neightion] You must be a player to do this").color(TextColor.color(225,87,75));
    Component staffRemove = Component.text().append(Component.text("[").color(TextColor.color(254, 254, 63 )).
            append(Component.text("STAFFCHAT").color(TextColor.color(225, 87,75)).append(Component.text("]").color(TextColor.color(254,254,63)).append(
                    Component.text(" DISABLED").color(TextColor.color(225,87,75)))))).build();
    String staffPrefix = ChatColor.translateAlternateColorCodes('&', "&e[&cSTAFFCHAT&e]");
    @SuppressWarnings("deprecated")
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(notPlayer);
            return false;
        }
        if (!sender.hasPermission("neightion.mod")) {
            sender.sendMessage(noPermission);
            return false;
        }
        UUID playerUUID = ((Player) sender).getUniqueId();
        if (args.length < 1) {
            if (!Neightion.staffToggle.containsKey(playerUUID)) {
                Neightion.staffToggle.put(playerUUID, true);
                sender.sendMessage(staffAdd);
                //----------------------------------------------------------------------------
                return true;
            } else Neightion.staffToggle.remove(playerUUID);
            sender.sendMessage(staffRemove);
            return true;
        }
        String name = ChatColor.translateAlternateColorCodes('&', "&6"+((Player) sender).getDisplayName());
        String message = staffPrefix + " "
                + name + ": ";
        for (String s : args) {
            message = message + ChatColor.GREEN+s + " ";
        }
        String colorMessage = ChatColor.translateAlternateColorCodes('&', message);
        Bukkit.broadcast(colorMessage, "neightion.mod");
        return true;
    }
}