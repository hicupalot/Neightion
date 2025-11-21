import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatMuted implements Listener {
    @EventHandler
    public void onChatMutedMessageSend(AsyncChatEvent e) {
        FileConfiguration config = Neightion.getInstance().getConfig();
        if (config.getBoolean("chatMuted", true)) {
            Player player = e.getPlayer();
            if (!player.hasPermission("neightion.mod")) {
                e.setCancelled(true);
                Component reason = Component.text().append(Component.text("[").color(TextColor.color(254, 63, 63))
                        .append(Component.text("NEIGHTION").color(TextColor.color(254, 254, 63)).append(Component.text("] ")
                                .color(TextColor.color(254, 63, 63)).
                                append(Component.text("You cannot send messages as the chat is currently muted")).color(TextColor.color(254, 63, 63))))).build();
                e.getPlayer().sendMessage(reason);
            }
        }
    }
}