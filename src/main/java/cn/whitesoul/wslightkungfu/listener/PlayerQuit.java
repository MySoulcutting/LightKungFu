package cn.whitesoul.wslightkungfu.listener;

import cn.whitesoul.wslightkungfu.data.Map;
import cn.whitesoul.wslib.database.mysql.Mysql;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class PlayerQuit implements Listener {
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) throws SQLException {
        UUID uuid = event.getPlayer().getUniqueId();
        //更新数据库
        PreparedStatement preparedStatement;
        String updateTableSQL = "UPDATE lightkungfu_table SET LEVELS=?, POINTS=? "
                + " WHERE UUID = ?";
        preparedStatement = Mysql.getConn().prepareStatement(updateTableSQL);
        preparedStatement.setInt(1, Map.levels.get(uuid));
        preparedStatement.setInt(2, Map.points.get(uuid));
        preparedStatement.setString(3, uuid.toString());
        preparedStatement.executeUpdate();
    }
}
