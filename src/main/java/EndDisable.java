import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.PortalCreateEvent;

public class EndDisable implements Listener {
    @EventHandler
    public void onPortal(PortalCreateEvent e) {
        if (e.getReason().equals(PortalCreateEvent.CreateReason.END_PLATFORM)){
            e.setCancelled(true);
        }
    }
}


