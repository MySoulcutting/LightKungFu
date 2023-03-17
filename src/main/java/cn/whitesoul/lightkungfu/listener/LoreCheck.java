package cn.whitesoul.lightkungfu.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class LoreCheck implements Listener {
    //交互检测
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {

    }
    @EventHandler
    //背包关闭检测
    public void onPlayerCloseInventory(InventoryCloseEvent event) {

    }
}
