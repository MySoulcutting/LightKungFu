package cn.whitesoul.wslightkungfu.command;

import cn.whitesoul.wslightkungfu.Main;
import cn.whitesoul.wslightkungfu.data.Map;
import cn.whitesoul.wslightkungfu.inventory.MainInv;
import cn.whitesoul.wslib.message.Message;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class MainCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0){
            sender.sendMessage("§6§l指令帮助:");
            sender.sendMessage("§a§l/lightkungfu add [玩家名] [点数]");
            sender.sendMessage("§b§l§o——给玩家点数");
            sender.sendMessage("§a§l/lightkungfu gui");
            sender.sendMessage("§b§l§o——打开轻功加点");
            sender.sendMessage("§a§l/lightkungfu reload");
            sender.sendMessage("§b§l§o——重载配置文件");
        }
        if (args.length == 1 && args[0].equalsIgnoreCase("gui")){
            sender.sendMessage("§f[§a轻功§f] §e打开轻功加点");
            MainInv.open((Player) sender);
        }
        if (args.length == 3 && args[0].equalsIgnoreCase("add")){
            Player target = Bukkit.getPlayer(args[1]);
            int points = Integer.parseInt(args[2]);
            UUID uuid = target.getUniqueId();
            Map.points.put(uuid,Map.points.get(uuid) + points);
            sender.sendMessage("§f[§a轻功§f] §e你给予玩家§b " + target.getName() + "§d" + points + " §e点数!");
            Message.sendMessage(target,"§f[§a轻功§f] §e你获得了§b " + points + " §e点数!");
        }
        if (args.length == 1 && args[0].equalsIgnoreCase("reload")){
            Main.INSTANCE.reloadConfig();
            Message.sendMessage((Player) sender,"&f[&a轻功&f] &e重载成功!");
        }
        return false;
    }
}
