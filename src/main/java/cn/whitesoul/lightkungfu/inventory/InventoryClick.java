package cn.whitesoul.lightkungfu.inventory;

import cn.whitesoul.lightkungfu.data.Map;
import cn.whitesoul.lightkungfu.utils.Config;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.UUID;

public class InventoryClick implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        UUID uuid = player.getUniqueId();
        int maxLevel = Config.MaxLevel;
        int level = Map.levels.get(uuid);
        int points = Map.points.get(uuid);
        if (event.getClickedInventory().getTitle().equalsIgnoreCase("§a§l轻功加点")) {
            event.setCancelled(true);
            if (event.getRawSlot() == 7) {
                if (level < maxLevel) {
                    if (points >= 1) {
                        Map.levels.put(uuid, Map.levels.get(uuid) + 1);
                        Map.points.put(uuid, Map.points.get(uuid) - 1);
                        player.sendMessage(Config.success.replace(Map.levels.get(uuid).toString(),"%s%"));
                    } else {
                        player.sendMessage(Config.nopoint);
                        player.closeInventory();
                    }
                } else {
                    player.sendMessage(Config.maxlevel);
                    player.closeInventory();
                }
            }
        }
    }
}
