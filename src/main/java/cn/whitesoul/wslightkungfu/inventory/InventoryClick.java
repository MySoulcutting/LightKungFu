package cn.whitesoul.wslightkungfu.inventory;

import cn.whitesoul.wslightkungfu.data.CaChe;
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
            int level = CaChe.levels.get(uuid);
            int points = CaChe.points.get(uuid);
            if (event.getWhoClicked().getOpenInventory().getTitle().equalsIgnoreCase("§a§l轻功加点")) {
                event.setCancelled(true);
                if (event.getRawSlot() == 7) {
                    if (level < maxLevel) {
                        if (points >= 1) {
                            CaChe.levels.put(uuid, CaChe.levels.get(uuid) + 1);
                            CaChe.points.put(uuid, CaChe.points.get(uuid) - 1);
                            Message.sendMessage(player,Config.success.replace("%0%", CaChe.levels.get(uuid).toString()));
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
