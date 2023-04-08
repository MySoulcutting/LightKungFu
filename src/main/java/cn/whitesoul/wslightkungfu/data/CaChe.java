package cn.whitesoul.wslightkungfu.data;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class CaChe {
    public static ConcurrentHashMap<UUID, Integer> jumps = new ConcurrentHashMap<>();
    public static ConcurrentHashMap<UUID, Integer> levels = new ConcurrentHashMap<>();
    public static ConcurrentHashMap<UUID, Integer> points = new ConcurrentHashMap<>();


}
