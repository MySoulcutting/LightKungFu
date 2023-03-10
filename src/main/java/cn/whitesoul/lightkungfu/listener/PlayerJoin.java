package cn.whitesoul.lightkungfu.listener;

import cn.whitesoul.lightkungfu.Main;
import cn.whitesoul.lightkungfu.data.Map;
import cn.whitesoul.lightkungfu.data.Mysql;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

import static cn.whitesoul.lightkungfu.data.Map.jumps;


public class PlayerJoin implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) throws SQLException {
        event.getPlayer().setAllowFlight(true);
        jumps.put(event.getPlayer().getUniqueId(), 0);
        UUID uuid = event.getPlayer().getUniqueId();
        String name = event.getPlayer().getName();
        //初始化数据库
        PreparedStatement preparedStatement = null;
        String sql = "SELECT UUID FROM lightKungFu_table WHERE UUID = ?";
        preparedStatement = Mysql.conn.prepareStatement(sql);
        preparedStatement.setString(1, uuid.toString());
        ResultSet rs = preparedStatement.executeQuery();
        if (!rs.next()) {
            String insertTableSQL = "INSERT INTO lightKungFu_table"
                    + "(ID, UUID, PLAYER, LEVELS, POINTS) VALUES"
                    + "(?,?,?,?,?)";
            preparedStatement = Mysql.conn.prepareStatement(insertTableSQL);
            preparedStatement.setString(1, null);
            preparedStatement.setString(2, uuid.toString());
            preparedStatement.setString(3, name);
            preparedStatement.setInt(4, 1);
            preparedStatement.setInt(5, 0);
            preparedStatement.executeUpdate();
        }
        //写入HashMap
        String level = "SELECT UUID, LEVELS , POINTS FROM lightKungFu_table WHERE UUID = ?";
        preparedStatement = Mysql.conn.prepareStatement(level);
        preparedStatement.setString(1, uuid.toString());
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int levels = resultSet.getInt("LEVELS");
            int points = resultSet.getInt("POINTS");
            System.out.println("DEBUG："+levels);
            Map.levels.put(uuid, levels);
            Map.points.put(uuid, points);
        }
    }
}

