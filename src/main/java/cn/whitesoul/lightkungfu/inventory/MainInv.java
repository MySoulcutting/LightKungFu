package cn.whitesoul.lightkungfu.inventory;

import cn.whitesoul.lightkungfu.data.Map;
import cn.whitesoul.lightkungfu.utils.Config;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainInv {
    public static Inventory inventory;
    public static void create(Player player) {
        UUID uuid = player.getUniqueId();
        inventory = Bukkit.createInventory(null,9, "§a§l轻功加点");
        //玻璃板
        ItemStack banner = new ItemStack(Material.STAINED_GLASS_PANE);
        ItemMeta bannerMeta = banner.getItemMeta();
        bannerMeta.setDisplayName("§c§l轻(Light) 功(KungFu)");
        banner.setItemMeta(bannerMeta);
        int[] ints = {0,2,3,4,5,6,8};
        for (int i : ints){
         inventory.setItem(i,banner);
        }
        //信息显示
        ItemStack paper = new ItemStack(Material.PAPER);
        ItemMeta paperMeta = paper.getItemMeta();
        paperMeta.setDisplayName("§d§l轻功信息");
        List<String> lore = new ArrayList<>();
        lore.add("§a§l当前点数: §e"+ Map.points.get(uuid));
        lore.add("");
        lore.add("§a§l当前等级: §e"+ Map.levels.get(uuid));
        paperMeta.setLore(lore);
        paper.setItemMeta(paperMeta);
        inventory.setItem(1,paper);
        //加点按钮
        ItemStack button = new ItemStack(Material.REDSTONE);
        ItemMeta buttonMeta = button.getItemMeta();
        buttonMeta.setDisplayName("§d§l加点！");
        List<String> buttonlore = new ArrayList<>();
        buttonlore.add("§a§l点我加点");
        buttonlore.add("");
        buttonlore.add("§a§l最大等级: §e§l"+ Config.MaxLevel);
        buttonMeta.setLore(buttonlore);
        button.setItemMeta(buttonMeta);
        inventory.setItem(7,button);
    }
    public static void open(Player player){
        create(player);
        player.openInventory(inventory);
    }
}
