import org.bukkit.damage.DamageSource;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityCombustByEntityEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

import java.util.Collection;

public class NeightonMobGriefing implements Listener {
    DamageSource creeper = DamageSource.builder(DamageType.EXPLOSION).build();
    @EventHandler
    public void onCreeperExplode(EntityExplodeEvent e) {
        if (e.getEntityType() == EntityType.CREEPER) {
            e.setCancelled(true);
            Collection<LivingEntity> entityList = e.getLocation().getNearbyLivingEntities(3);
            if (entityList.isEmpty()) {
                return;
            }
            for (LivingEntity entity : entityList) {
                if (e.getLocation().getNearbyLivingEntities(1).contains(entity)) {
                    entity.damage(43, creeper);
                    entityList.remove(entity);
                    return;
                }
                if (e.getLocation().getNearbyLivingEntities(2).contains(entity)) {
                    entity.damage(23.5, creeper);
                    entityList.remove(entity);
                    return;
                }
                if (e.getLocation().getNearbyLivingEntities(3).contains(entity)) {
                    entity.damage(12.5, creeper);
                    entityList.remove(entity);
                }
            }
        }
    }
    public void onEntityFire(EntityCombustByEntityEvent e){
        if (e.getCombuster().getType() != EntityType.PLAYER){
            e.setCancelled(true);
        }
    }
    public void onEnderMan(EntityChangeBlockEvent e){
        if (e.getEntity().getType() == EntityType.ENDERMAN){
            e.setCancelled(true);
        }
    }
}