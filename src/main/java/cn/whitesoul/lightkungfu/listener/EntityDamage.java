package cn.whitesoul.lightkungfu.listener;


import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import static cn.whitesoul.lightkungfu.utils.Config.cancelFallDamage;


public class EntityDamage implements Listener {
    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (cancelFallDamage && event.getEntity().getType().equals(EntityType.PLAYER) && event.getCause().equals(EntityDamageEvent.DamageCause.FALL)) {
            event.setCancelled(true);

        }
    }
}
