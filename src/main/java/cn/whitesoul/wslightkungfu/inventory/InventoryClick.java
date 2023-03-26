package cn.whitesoul.wslightkungfu.inventory;

import cn.whitesoul.wslightkungfu.data.Map;
import cn.whitesoul.wslightkungfu.utils.Config;
import cn.whitesoul.wslib.message.Message;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.UUID;

public class InventoryClick implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        try {
            Player player = (Player) event.getWhoClicked();
            UUID uuid = player.getUniqueId();
            int maxLevel = Config.MaxLevel;
            int level = Map.levels.get(uuid);
            int points = Map.points.get(uuid);
            if (event.getWhoClicked().getOpenInventory().getTitle().equalsIgnoreCase("§a§l轻功加点")) {
                event.setCancelled(true);
                if (event.getRawSlot() == 7) {
                    if (level < maxLevel) {
                        if (points >= 1) {
                            Map.levels.put(uuid, Map.levels.get(uuid) + 1);
                            Map.points.put(uuid, Map.points.get(uuid) - 1);
                            Message.sendMessage(player,Config.success.replace("%0%", Map.levels.get(uuid).toString()));
                            player.closeInventory();
                            MainInv.open(player);
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
        }catch (Exception ignored){}
    }

}
