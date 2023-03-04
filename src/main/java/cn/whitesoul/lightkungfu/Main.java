package cn.whitesoul.lightkungfu;

import cn.whitesoul.lightkungfu.command.MainCommand;
import cn.whitesoul.lightkungfu.data.Mysql;
import cn.whitesoul.lightkungfu.inventory.InventoryClick;
import cn.whitesoul.lightkungfu.listener.EntityDamage;
import cn.whitesoul.lightkungfu.listener.PlayerJoin;
import cn.whitesoul.lightkungfu.listener.PlayerJump;
import cn.whitesoul.lightkungfu.listener.PlayerQuit;
import cn.whitesoul.lightkungfu.utils.Config;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

import static cn.whitesoul.lightkungfu.data.Map.*;

public final class Main extends JavaPlugin {
    public static Main INSTANCE;


    @Override
    public void onEnable() {
        saveDefaultConfig();
        INSTANCE = this;
        try {
            Mysql.create();
            System.out.println("§f[§a轻功§f] §c数据库连接成功");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("§f[§a轻功§f] §c数据库连接出错!");
            throw new RuntimeException(e);
        }
        getLogger().info("§f[§a轻功§f] §e启动");
        getServer().getPluginManager().registerEvents(new PlayerJump(),this);
        getServer().getPluginManager().registerEvents(new PlayerJoin(),this);
        getServer().getPluginManager().registerEvents(new PlayerQuit(),this);
        getServer().getPluginManager().registerEvents(new EntityDamage(),this);
        getServer().getPluginManager().registerEvents(new InventoryClick(),this);
        getCommand("lightkungfu").setExecutor(new MainCommand());

    }

    @Override
    public void onDisable() {
        try {
            Mysql.stmt.close();
            Mysql.conn.close();
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
        jumps.clear();
        levels.clear();
        points.clear();
    }
}
