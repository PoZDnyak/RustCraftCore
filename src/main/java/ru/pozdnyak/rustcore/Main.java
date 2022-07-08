package ru.pozdnyak.rustcore;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import ru.pozdnyak.rustcore.Barrels.Barrels;
import ru.pozdnyak.rustcore.Barrels.BarrelsCommand;
import ru.pozdnyak.rustcore.DamageInfo.DamageInfo;
import ru.pozdnyak.rustcore.DisconnectBodies.DisconnectBodies;
import ru.pozdnyak.rustcore.PlayerFeed.PlayerFeed;
import ru.pozdnyak.rustcore.PlayerStars.PlayerStats;
import ru.pozdnyak.rustcore.Radiation.radiation.RadiationManager;
import ru.pozdnyak.rustcore.Radiation.commands.RadiationCommand;
import ru.pozdnyak.rustcore.Radiation.listeners.Listeners;
import ru.pozdnyak.rustcore.Radiation.utils.Config;
import ru.pozdnyak.rustcore.ResourceSpawner.cmds.Cmd;
import ru.pozdnyak.rustcore.ResourceSpawner.data.DataSaver;
import ru.pozdnyak.rustcore.ResourceSpawner.listeners.MainListener;
import ru.pozdnyak.rustcore.ResourceSpawner.recovery.RecoveryTimer;
import ru.pozdnyak.rustcore.SpawnResources.SpawnResources;

import java.io.File;
import java.sql.SQLException;

public final class Main extends JavaPlugin {

    public File cfg = new File(getDataFolder()+ File.separator+"config.yml");
    private MySQL mySQL;
    public static Main plugin;
    static Main main;
    Config cfgsecond;
    RadiationManager radiationManager;
    WorldGuardPlugin worldGuard;


    @Override
    public void onEnable() {
        if(!cfg.exists()){
            getLogger().info("Creating config.yml...");
            getConfig().options().copyDefaults(true);
            saveDefaultConfig();
        }
        getServer().getPluginManager().registerEvents(new Barrels(this),this);
        getServer().getPluginManager().registerEvents(new SpawnResources(this),this);
        getServer().getPluginManager().registerEvents(new PlayerStats(this),this);
        getServer().getPluginManager().registerEvents(new PlayerFeed(this),this);
        getServer().getPluginManager().registerEvents(new DisconnectBodies(this),this);
        getServer().getPluginManager().registerEvents(new DamageInfo(this),this);
        getServer().getPluginManager().registerEvents(new Listeners(this), this);



        plugin = this;
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new MainListener(), plugin);
        getCommand("rustm").setExecutor(new Cmd());
        getCommand("radiation").setExecutor(new RadiationCommand(this));


        DataSaver.instance().loadDataToServer();
        RecoveryTimer.instance().startTimer();


        connectDB();
        refreshBarrels();
        refreshScoreboard();
        minusFood();
        minusWater();

        getCommand("rc").setExecutor(new BarrelsCommand(this));
        getLogger().info("Enabled!");



        Main.main = this;
        this.cfgsecond = new Config();
        this.radiationManager = new RadiationManager();
        this.worldGuard = WorldGuardPlugin.inst();

        this.saveDefaultConfig();

    }

    @Override
    public void onDisable() {
        try {
            mySQL.closeConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        for(Player p : Bukkit.getOnlinePlayers()){
            p.kickPlayer(ChatColor.RED+"Reconnect!");
        }

        DataSaver.instance().saveDataToFile();

    }

    public void refreshBarrels(){
        BukkitTask run = new BukkitRunnable() {
            @Override
            public void run() {
                mySQL.refreshBarrels();
            }
        }.runTaskTimer(this,getConfig().getInt("barrels_cooldown")*1,getConfig().getInt("barrels_cooldown")*1);
    }

    public void connectDB(){
        try {
            mySQL = new MySQL(getConfig().getString("host"),getConfig().getString("database"),getConfig().getString("user"),getConfig().getString("password"));
            getLogger().info(ChatColor.WHITE+"Successfully connected!");
        }
        catch(Exception e) {
            e.printStackTrace();
            Bukkit.getPluginManager().disablePlugin(this);
            getLogger().warning(ChatColor.WHITE+"Connection error!");
        }
    }

    public void refreshScoreboard(){
        BukkitTask run = new BukkitRunnable() {
            @Override
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if(p.isOp())
                    if(p.getHealth()==0)continue;
                    boolean buildAccecc = false;

                    int x = (int) p.getLocation().getX();
                    int z = (int) p.getLocation().getZ();

                    Scoreboard newScoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
                    Objective o = newScoreboard.registerNewObjective("stats", "dummy");
                    o.setDisplayName(ChatColor.RED + "Star" + ChatColor.WHITE + "Rust");
                    o.setDisplaySlot(DisplaySlot.SIDEBAR);


                    o.getScore(ChatColor.GREEN + "Здоровье: " + (int) (p.getHealth() * 5)).setScore(11);
                    String health1 = ChatColor.GREEN + "";
                    String health2 = ChatColor.DARK_GREEN + "";
                    for (int i = 1; i <= 20; i++) {
                        if (i <= p.getHealth()) {
                            health1 = health1 + "❘";
                        } else {
                            health2 = health2 + "❘";
                        }
                    }
                    String health = health1 + health2;
                    o.getScore(health).setScore(10);

                    int waterlvl = PlayerStats.getWater(p);
                    int countwater = (int) (waterlvl/12.5);
                    o.getScore(ChatColor.AQUA + "Жажда: " + waterlvl).setScore(9);
                    String water1 = ChatColor.AQUA + "";
                    String water2 = ChatColor.DARK_AQUA + "";

                    for (int i = 1; i <= 20; i++) {
                        if (i <= countwater) {
                            water1 = water1 + "❘";
                        } else {
                            water2 = water2 + "❘";
                        }
                    }
                    String water = water1 + water2;
                    o.getScore(water).setScore(8);



                    int foodlvl = PlayerStats.getFood(p);
                    int countfood = foodlvl/25;
                    o.getScore(ChatColor.YELLOW + "Голод: " + foodlvl).setScore(7);
                    String food1 = ChatColor.YELLOW + "";
                    String food2 = ChatColor.GOLD + "";

                    for (int i = 1; i <= 20; i++) {
                        if (i <= countfood) {
                            food1 = food1 + "❘";
                        } else {
                            food2 = food2 + "❘";
                        }
                    }
                    String food = food1 + food2;
                    o.getScore(food).setScore(6);

                    int radiationlvl = PlayerStats.getRadiation(p);
                    int countradiation = radiationlvl/25;
                    o.getScore(ChatColor.RED + "Радиация: " + radiationlvl).setScore(5);
                    String radiation1 = ChatColor.RED + "";
                    String radiation2 = ChatColor.DARK_RED + "";

                    for (int i = 1; i <= 20; i++) {
                        if (i <= countradiation) {
                            radiation1 = radiation1 + "❘";
                        } else {
                            radiation2 = radiation2 + "❘";
                        }
                    }
                    String radiation = radiation1 + radiation2;
                    o.getScore(radiation).setScore(4);

                    o.getScore("      ").setScore(3);
                    if (buildAccecc) {
                        o.getScore(ChatColor.WHITE + "Строительство: " + ChatColor.GREEN + "разрешено").setScore(2);
                    } else {
                        o.getScore(ChatColor.WHITE + "Строительство: " + ChatColor.RED + "запрещено").setScore(2);
                    }
                    o.getScore(ChatColor.WHITE + "Координаты: " + ChatColor.GREEN + x + " " + z).setScore(1);

                    p.setScoreboard(newScoreboard);
                    if(foodlvl<=0||waterlvl<=0){
                        if(p.getHealth()<1){
                            p.setHealth(0);
                            continue;
                        }
                        p.setHealth(p.getHealth()-1);
                        continue;
                    }
                }
            }
        }.runTaskTimer(this,20,20);
    }

    public void minusWater() {
        BukkitTask run = new BukkitRunnable() {
            @Override
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    int water = PlayerStats.water.get(p);
                    if(water<=0){
                        continue;
                    }
                    PlayerStats.water.put(p,water-1);
                    p.setFoodLevel(18);
                }
            }
        }.runTaskTimer(this,10*20,10*20);
    }
    public void minusFood() {
        BukkitTask run = new BukkitRunnable() {
            @Override
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    int food = PlayerStats.food.get(p);
                    if(food<=0){
                        continue;
                    }
                    PlayerStats.food.put(p,food-1);
                    p.setFoodLevel(18);
                }
            }
        }.runTaskTimer(this,5*20,5*20);
    }


    public void reload() {
        this.cfgsecond = new Config();
    }

    public static Main getMain() {
        return Main.main;
    }

    public Config getCfg() {
        return this.cfgsecond;
    }

    public RadiationManager getRadiationManager() {
        return this.radiationManager;
    }

    public WorldGuardPlugin getWorldGuard() {
        return this.worldGuard;
    }

}
