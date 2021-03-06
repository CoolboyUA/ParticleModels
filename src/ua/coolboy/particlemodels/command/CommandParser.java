package ua.coolboy.particlemodels.command;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import ua.coolboy.particlemodels.ParticleModels;
import ua.coolboy.particlemodels.drawer.Drawer;
import ua.coolboy.particlemodels.utils.ChatUtil;

public abstract class CommandParser {

    private static ParticleModels plugin = ParticleModels.getInstance();

    public static boolean parse(Player player, Command cmd, String commandLabel, String[] args) {
        if (args.length == 2 && args[0].equals("load")) {
            boolean success = plugin.loadModel(args[1]);
            String msg = success ? "&aLoaded model &2" + args[1] : "&cCan't load model &2" + args[1];
            ChatUtil.send(player, msg);
            return true;
        }

        if (args.length == 2 && args[0].equals("draw")) {
            Drawer model = ParticleModels.getModel(args[1]);
            if (model != null) {
                Location loc = new Location(player.getWorld(), 0, 100, 10);
                model.draw(loc);
            }
        }
        if (args.length == 1 && args[0].equals("debug")) {
            ChatUtil.sendCenteredMessage(player, "&2===ParticleModel===");
            ChatUtil.send(player, "&6Memory: &a" + Runtime.getRuntime().maxMemory() / 1048576 + " MB");
            ChatUtil.send(player, "&6Free memory: &a" + Runtime.getRuntime().freeMemory() / 1048576 + " MB");
            ChatUtil.send(player, "&6Processors: &a" + Runtime.getRuntime().availableProcessors());
        }
        
        if(args.length == 2 && args[0].equals("repeat")) { //can start massive client lag
            Drawer model = ParticleModels.getModel(args[1]);
            if (model != null) {
                Location loc = new Location(player.getWorld(), 0, 100, 10);
                model.repeat(loc);
            }
        }
        
        if(args.length == 3 && args[0].equals("scale")) { //scales model (be careful with optimization!)
            Drawer model = ParticleModels.getModel(args[1]);
            if (model != null) {
                model.scale(Float.parseFloat(args[2]));
            }
        }

        return false;
    }

}
