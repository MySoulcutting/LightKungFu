package cn.whitesoul.lightkungfu.listener;

import cn.whitesoul.lightkungfu.Main;
import cn.whitesoul.lightkungfu.data.Map;
import cn.whitesoul.lightkungfu.utils.Config;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.util.Vector;
import top.zoyn.particlelib.pobject.Circle;

import java.sql.SQLException;
import java.util.UUID;

import static cn.whitesoul.lightkungfu.data.Map.jumps;

public class PlayerJump implements Listener {

    @EventHandler
    public void onJump(PlayerToggleFlightEvent event){
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        //判断是否为创造模式
        if (player.getGameMode() == GameMode.CREATIVE) {
            return;
        }
        //HashMap读取数据
            int levels = Map.levels.get(uuid);
            int multipy = Main.INSTANCE.getConfig().getInt("Level." + levels + ".Multiply");
            int y = Main.INSTANCE.getConfig().getInt("Level." + levels + ".Y");
            int cd = Main.INSTANCE.getConfig().getInt("Level." + levels + ".Cd");
            int jump = Main.INSTANCE.getConfig().getInt("Level." + levels + ".Jumps");
            //取消普通飞行
            event.setCancelled(true);
            //芜湖起飞
            Vector vector = player.getLocation().getDirection();
            vector.multiply(multipy);
            vector.setY(y);
            player.setVelocity(vector);
            //播放音效
        if (Config.soundsEnable){
            player.playSound(player.getLocation(), Sound.valueOf(Main.INSTANCE.getConfig().getString("Sounds.Sound")), Main.INSTANCE.getConfig().getInt("Sounds.Volume"), Main.INSTANCE.getConfig().getInt("Sounds.Pitch"));
        }
            //特效
        if (Config.particleEnable){
            Circle circle = new Circle(player.getLocation());
            circle.setParticle(Particle.SNOWBALL)
                    .setColor(Color.WHITE);
            circle.setRadius(0.3);
            circle.show();
        }
            //限制次数
            jumps.put(player.getUniqueId(), jumps.get(player.getUniqueId()) + 1);
            //冷却
            if (jumps.get(player.getUniqueId()) >= jump) {
                player.setAllowFlight(false);
                player.sendMessage(Config.tired);
                //延迟执行
                BukkitScheduler scheduler = Bukkit.getScheduler();
                scheduler.runTaskLater(Main.INSTANCE, () -> {
                    jumps.put(player.getUniqueId(), 0);
                    player.setAllowFlight(true);
                }, 20L * cd);
            }
        }

    }

