package ru.pozdnyak.rustcore.Barrels;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.pozdnyak.rustcore.Main;
import ru.pozdnyak.rustcore.MySQL;

public class BarrelsCommand implements CommandExecutor {
    private final Main plugin;
    private MySQL mySQL;


    public BarrelsCommand(Main plugin) {
        this.plugin = plugin;
        try {
            mySQL = new MySQL(plugin.getConfig().getString("host"),plugin.getConfig().getString("database"),plugin.getConfig().getString("user"),plugin.getConfig().getString("password"));
        }
        catch(Exception e) {
            e.printStackTrace();
            Bukkit.getPluginManager().disablePlugin(plugin);
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)) return true;
        if(args.length != 3)return false;
        if (!sender.isOp()) return true;
        if(!args[0].equals("barrel"))return false;
        if(!args[1].equals("set"))return false;
        int color = 0;
        if(args[2].equals("blue")){
            color = 0;
        }else if(args[2].equals("red")){
            color = 1;
        }else {
            sender.sendMessage(ChatColor.RED+"Введи корректное значение цвета блять");
            return true;
        }
        int x = (int) ((Player) sender).getLocation().getX();
        int y = (int) ((Player) sender).getLocation().getY();
        int z = (int) ((Player) sender).getLocation().getZ();
        mySQL.addBarrel(x,y,z,color);

        return true;
    }
}
