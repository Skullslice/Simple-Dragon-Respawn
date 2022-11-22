package nvenc;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public class Start extends JavaPlugin {

    Handling handler;
    FileConfiguration config = this.getConfig();
    DragonCommand cmd;

    String help1 = ChatColor.GOLD + "========= /Dragons =========";
    String help2 = ChatColor.GOLD + "crystal 1 - 4 * " + ChatColor.GRAY + "stand on the bedrock and set the 4 locations";
    String help3 = ChatColor.GOLD + "interval <time ms> * " + ChatColor.GRAY + "how often will the dragon respawn?";
    String error = ChatColor.GOLD + "Something went wrong while trying to execute the command";
    String setInterval = ChatColor.GOLD + "changed spawn interval to ";
    String crystala = ChatColor.GOLD + "crystal 1 will respawn at ";
    String crystalb = ChatColor.GOLD + "crystal 2 will respawn at ";
    String crystalc = ChatColor.GOLD + "crystal 3 will respawn at ";
    String crystald = ChatColor.GOLD + "crystal 4 will respawn at ";

    Location cl1;
    Location cl2;
    Location cl3;
    Location cl4;

    @Override
    public void onEnable() {
        this.getConfig().addDefault("respawn_interval", 60000); // 1 minute to set the crystals back
        this.getConfig().addDefault("X, Y, Z (a)", "[]");
        this.getConfig().addDefault("X, Y, Z (b)", "[]");
        this.getConfig().addDefault("X, Y, Z (c)", "[]");
        this.getConfig().addDefault("X, Y, Z (d)", "[]");
        config.options().copyDefaults(true);
        saveConfig();
        getCommand("dragons").setExecutor(new DragonCommand(this));
        handler.now = System.currentTimeMillis();
        getServer().getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "Dragon Respawn " + ChatColor.AQUA + "created by " + ChatColor.GOLD + "Holiday Plugin Development LLC");
        getServer().getPluginManager().registerEvents(new Handling(this), this);
        getServer().getConsoleSender().sendMessage(handler.dragonAlive() ? "Dragon verified alive!" : "Dragon verified dead!");

        BukkitScheduler s = getServer().getScheduler();
        s.scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {

                try {
                    cl1 = handler.convertStr(getConfig().getString("X, Y, Z (a)"));
                    cl2 = handler.convertStr(getConfig().getString("X, Y, Z (b)"));
                    cl3 = handler.convertStr(getConfig().getString("X, Y, Z (c)"));
                    cl4 = handler.convertStr(getConfig().getString("X, Y, Z (d)"));
                } catch (Exception e) {e.printStackTrace();}

                if (!handler.dragonAlive() && (handler.now + handler.respawnTime < System.currentTimeMillis())) {
                    if (handler.canSetBlock()) {
                        Bukkit.getServer().getWorld("world_the_end").getBlockAt(cl1).setType(Material.END_CRYSTAL);
                        Bukkit.getServer().getWorld("world_the_end").getBlockAt(cl2).setType(Material.END_CRYSTAL);
                        Bukkit.getServer().getWorld("world_the_end").getBlockAt(cl3).setType(Material.END_CRYSTAL);
                        Bukkit.getServer().getWorld("world_the_end").getBlockAt(cl4).setType(Material.END_CRYSTAL);
                    }else {
                        Bukkit.getServer().getConsoleSender().sendMessage("Couldn't set the end crystals.");
                    }
                }
            }
        }, 0L, 200L);

    }
}
