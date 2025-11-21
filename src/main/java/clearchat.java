import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class clearchat implements CommandExecutor {
    Component noPermission = Component.text("[Neightion] You do not have permission").color(TextColor.color(225,87,75));
    Component notPlayer = Component.text("[Neightion] You must be a player to do this").color(TextColor.color(225,87,75));
    @Override
    @SuppressWarnings("deprecated")
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player) && !(sender instanceof ConsoleCommandSender)) {
            sender.sendMessage(notPlayer);
            return false;
        }
        if (!sender.hasPermission("neightion.mod")) {
            sender.sendMessage(noPermission);
            return false;
        }
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (!player.hasPermission("neightion.mod")) {
                for (int i = 0; i<200; i++){
                    Component emptyMessage = Component.text("");
                    player.sendMessage(emptyMessage);
                }
            }

        }
        Component staffBroadcast = Component.text().append(Component.text("[").color(TextColor.color(254, 63, 63))
                .append(Component.text("STAFF").color(TextColor.color(254, 254, 63)).append(Component.text("] ").color(TextColor.color(254, 63, 63)))
                        .append(Component.text("Chat was cleared by ").color(TextColor.color(254, 254, 63)).
                                append(Component.text(sender.getName()).color(TextColor.color(254, 254, 63)).
                                        append(Component.text(" but you're immune, YIPPEE")).color(TextColor.color(254,254,63)))))).build();
        Component nonStaffBroadcast = Component.text("The chat was cleared by Staff").color(TextColor.color(254,63,63));
        for (Player staff : Bukkit.getOnlinePlayers()) {
            if (staff.hasPermission("neightion.mod")) {
                staff.sendMessage(staffBroadcast);
            }
            for (Player nonStaff : Bukkit.getOnlinePlayers()) {
                if (!nonStaff.hasPermission("neightion.mod")) {
                    nonStaff.sendMessage(nonStaffBroadcast);
                }
            }
        }
        return true;
    }
}
