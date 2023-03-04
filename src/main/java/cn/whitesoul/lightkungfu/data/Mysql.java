package cn.whitesoul.lightkungfu.data;


import cn.whitesoul.lightkungfu.Main;
import cn.whitesoul.lightkungfu.utils.Config;

import java.sql.*;
import java.util.UUID;

public class Mysql {
    public static Connection conn = null;
    public static Statement stmt = null;


    public static void create() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://" + Config.mysqlUrl + "/" + Config.mysqlDatabase, Config.mysqlName, Config.mysqlPassword);
        stmt = conn.createStatement();
        // 创建表
        String sql = "CREATE TABLE IF NOT EXISTS lightKungFu_table " +
                "(id INT UNSIGNED AUTO_INCREMENT, " +
                " UUID VARCHAR(255), " +
                " PLAYER VARCHAR(255), " +
                " LEVELS INTEGER not null, " +
                " POINTS INTEGER, " +
                " PRIMARY KEY ( id ))";
        stmt.executeUpdate(sql);
    }
}



