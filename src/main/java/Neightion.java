import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public final class Neightion extends JavaPlugin {
    private static Neightion instance;

    public static Neightion getInstance() {
        return instance;
    }
    public static final HashMap<UUID, Boolean> staffToggle = new HashMap<UUID, Boolean>();

    @Override
    public void onEnable() {
        instance = this;
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new StaffChat(),this);
        getServer().getPluginManager().registerEvents(new ChatMuted(),this);
        getServer().getPluginManager().registerEvents(new NeightonMobGriefing(), this);
        getCommand("ToggleStaffChat").setExecutor(new ToggleStaffChat());
        getCommand("clearchat").setExecutor(new clearchat());
        getCommand("mutechat").setExecutor(new mutechat());
        getCommand("timer").setExecutor(new Timer());
        getCommand("coinflip").setExecutor(new CoinFlip());
        getCommand("stoptimer").setExecutor(new StopTimer());
    }
    @Override
    public void onDisable() {
    }
}