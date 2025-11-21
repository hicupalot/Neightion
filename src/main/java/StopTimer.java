import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class StopTimer implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Component noPermission = Component.text("[Neightion] You do not have permission").color(TextColor.color(254,63,63));
        Component notPlayer = Component.text("[Neightion] You must be a player to do this").color(TextColor.color(254,63,63));
        FileConfiguration config = Neightion.getInstance().getConfig();
        if (!(sender instanceof Player)) {
            sender.sendMessage(notPlayer);
            return false;
        }
        if (!sender.hasPermission("neightion.mod")) {
            sender.sendMessage(noPermission);
            return false;
        }
        if (args.length > 0) {
            Component usage = Component.text("[Neighton] Command Usage /stoptimer").color(TextColor.color(190,0,0));
            sender.sendMessage(usage);
            return false;
        }
        if (!config.getBoolean("timer")){
            String noTimer = ChatColor.translateAlternateColorCodes('&',"&c[Neighton] There is no timer running!");
            sender.sendMessage(noTimer);
            return false;
        }
        Bukkit.getScheduler().cancelTasks(Neightion.getInstance());
        String cancelled = ChatColor.translateAlternateColorCodes('&', "&c[Neightion] Cancelled the current timer");
        String cancelAnnounce = ChatColor.translateAlternateColorCodes('&',"&c&l[Neightion] "+"&eThe current timer was cancelled by "+
                sender.getName());
        sender.sendMessage(cancelled);
        config.set("timer",false);
        Neightion.getInstance().saveConfig();
        for (Player player : Bukkit.getOnlinePlayers()){
            if (player!=sender){
                player.sendMessage(cancelAnnounce);
            }
        }
        return true;
    }
}
