package cn.whitesoul.wslightkungfu.listener;

import cn.whitesoul.wslightkungfu.data.CaChe;
import cn.whitesoul.wslib.database.mysql.Mysql;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import static cn.whitesoul.wslightkungfu.data.CaChe.jumps;


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
        preparedStatement = Mysql.getConn().prepareStatement(sql);
        preparedStatement.setString(1, uuid.toString());
        ResultSet rs = preparedStatement.executeQuery();
        if (!rs.next()) {
            String insertTableSQL = "INSERT INTO lightKungFu_table"
                    + "(ID, UUID, PLAYER, LEVELS, POINTS) VALUES"
                    + "(?,?,?,?,?)";
            preparedStatement = Mysql.getConn().prepareStatement(insertTableSQL);
            preparedStatement.setString(1, null);
            preparedStatement.setString(2, uuid.toString());
            preparedStatement.setString(3, name);
            preparedStatement.setInt(4, 1);
            preparedStatement.setInt(5, 0);
            preparedStatement.executeUpdate();
        }
        //写入HashMap
        String level = "SELECT UUID, LEVELS , POINTS FROM lightKungFu_table WHERE UUID = ?";
        preparedStatement = Mysql.getConn().prepareStatement(level);
        preparedStatement.setString(1, uuid.toString());
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int levels = resultSet.getInt("LEVELS");
            int points = resultSet.getInt("POINTS");
            CaChe.levels.put(uuid, levels);
            CaChe.points.put(uuid, points);
        }
    }
}

