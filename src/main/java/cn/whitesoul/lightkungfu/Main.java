package cn.whitesoul.lightkungfu;

import cn.whitesoul.lightkungfu.command.MainCommand;
import cn.whitesoul.lightkungfu.inventory.InventoryClick;
import cn.whitesoul.lightkungfu.listener.*;
import cn.whitesoul.wslib.database.mysql.Mysql;
import cn.whitesoul.wslib.message.ServerInfo;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

import static cn.whitesoul.lightkungfu.data.Map.*;
import static cn.whitesoul.lightkungfu.utils.Config.*;

public final class Main extends JavaPlugin {
    public static Main INSTANCE;


    @Override
    public void onEnable() {
        saveDefaultConfig();
        INSTANCE = this;
        Mysql.setConn(mysqlUrl, mysqlDatabase, mysqlName, mysqlPassword);
        ServerInfo.sendInfo("§f[§a轻功§f] §c插件已加载 V0.0.2");
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
            Mysql.closeConn();
            Mysql.getConn().createStatement().close();
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
        jumps.clear();
        levels.clear();
        points.clear();
    }
}
