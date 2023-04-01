package cn.whitesoul.wslightkungfu;

import cn.whitesoul.wslib.database.mysql.SQL;
import cn.whitesoul.wslightkungfu.command.MainCommand;
import cn.whitesoul.wslightkungfu.inventory.InventoryClick;
import cn.whitesoul.wslightkungfu.listener.*;
import cn.whitesoul.wslib.database.mysql.Mysql;
import cn.whitesoul.wslib.message.ServerInfo;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

import static cn.whitesoul.wslightkungfu.data.Map.*;
import static cn.whitesoul.wslightkungfu.utils.Config.*;

public final class Main extends JavaPlugin {
    public static Main INSTANCE;


    @Override
    public void onEnable() {
        saveDefaultConfig();
        INSTANCE = this;
        Mysql.setConn(mysqlUrl, mysqlDatabase, mysqlName, mysqlPassword);
        SQL.createTable("lightkungfu_table","id INT(10) UNSIGNED NOT NULL AUTO_INCREMENT, " +
                "UUID VARCHAR(255) NULL, " +
                "PLAYER VARCHAR(255) NULL, " +
                "LEVELS INT(11) NOT NULL, " +
                "POINTS INT(11) NULL, " +
                "PRIMARY KEY ( id )");
        ServerInfo.sendInfo("§f[§a轻功§f] §c插件已加载 V1.0.3");
        getServer().getPluginManager().registerEvents(new PlayerJump(),this);
        getServer().getPluginManager().registerEvents(new PlayerJoin(),this);
        getServer().getPluginManager().registerEvents(new PlayerQuit(),this);
        getServer().getPluginManager().registerEvents(new EntityDamage(),this);
        getServer().getPluginManager().registerEvents(new InventoryClick(),this);
        getCommand("lightkungfu").setExecutor(new MainCommand());
    }

    @Override
    public void onDisable() {
        ServerInfo.sendInfo("§f[§a轻功§f] §c插件已加载 V1.0.3");
        Mysql.closeConn();
    }
}
