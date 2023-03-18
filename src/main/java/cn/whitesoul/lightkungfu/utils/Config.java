package cn.whitesoul.lightkungfu.utils;

import cn.whitesoul.lightkungfu.Main;

public class Config {
    public static String mysqlUrl = Main.INSTANCE.getConfig().getString("Mysql.Url");
    public static String mysqlName = Main.INSTANCE.getConfig().getString("Mysql.Name");
    public static String mysqlPassword = Main.INSTANCE.getConfig().getString("Mysql.Password");
    public static String mysqlDatabase = Main.INSTANCE.getConfig().getString("Mysql.Database");
    public static int MaxLevel = Main.INSTANCE.getConfig().getInt("MaxLevel");
    public static String success = Main.INSTANCE.getConfig().getString("Message.success");
    public static String tired = Main.INSTANCE.getConfig().getString("Message.tired");
    public static String nopoint = Main.INSTANCE.getConfig().getString("Message.nopoint");
    public static String maxlevel = Main.INSTANCE.getConfig().getString("Message.maxlevel");
    public static boolean soundsEnable = Main.INSTANCE.getConfig().getBoolean("Sounds.Enable");
    public static boolean particleEnable = Main.INSTANCE.getConfig().getBoolean("Particle.Enable");
    public static boolean cancelFallDamage = Main.INSTANCE.getConfig().getBoolean("CancelFallDamage");


}
