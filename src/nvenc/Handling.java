package nvenc;

import org.bukkit.*;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

import java.util.List;
import java.util.Objects;

public class Handling implements Listener {

    public boolean dragonAlive() {
        World w = Bukkit.getServer().getWorld("world_the_end");
        List<LivingEntity> end = w.getLivingEntities();
        for (LivingEntity j : end) {
            if (j instanceof EnderDragon) return true;

        } return false;
    }
    public boolean canSetBlock() {
        return !Objects.equals(instance.config.getString("X, Y, Z (a)"), "[]") && !Objects.equals(instance.config.getString("X, Y, Z (b)"), "[]") && !Objects.equals(instance.config.getString("X, Y, Z (c)"), "[]") && !Objects.equals(instance.config.getString("X, Y, Z (d)"), "[]");
    }

    public Location convertStr(String abc) {
        String[] next = abc.split(", ");
        return new Location(Bukkit.getServer().getWorld("world_the_end"), Double.parseDouble(next[0]), Double.parseDouble(next[1]), Double.parseDouble(next[2]), 0, 0);
    }


    Start instance;

    public Handling(Start config) {
        instance = config;
    }

    public long respawnTime = 60000;
    public long now;

    @EventHandler
    public void onDragonDie(EntityDeathEvent deathEvent) {
        if (deathEvent.getEntity() instanceof EnderDragon) {
            Player killer = deathEvent.getEntity().getKiller();
            now = System.currentTimeMillis();
            Bukkit.getServer().broadcastMessage(ChatColor.GRAY + "The Ender Dragon has been slain by " + killer.getDisplayName() + ChatColor.GRAY + "!");
            Bukkit.getServer().broadcastMessage(ChatColor.DARK_GRAY + "???: " + ChatColor.LIGHT_PURPLE + "I... Will.. .. Returnnnnn...");
        }
    }

    @EventHandler
    public void onDragonSpawn(CreatureSpawnEvent spawnEvent) {
        if (spawnEvent.getEntity() instanceof EnderDragon) {
            now = System.currentTimeMillis();
            Bukkit.getServer().broadcastMessage(ChatColor.DARK_GRAY + "???: " + ChatColor.LIGHT_PURPLE + "tsk tsk tsk ... When will those mortals learn?");
        }
    }
}
