package nvenc;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DragonCommand implements CommandExecutor {

    Start instance;

    public DragonCommand(Start command) {
        instance = command;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (commandSender.hasPermission("dragons.admin") && command.getName().equals("dragons") && commandSender instanceof Player) {
            try {
                Location bedrock = ((Player) commandSender).getLocation();
                String crysloc = Math.floor(bedrock.getX()) + ", " + Math.floor(bedrock.getY()) + ", " + Math.floor(bedrock.getZ());
                switch (String.join(" ", args)) {
                    case "crystal 1":
                        commandSender.sendMessage(instance.crystala + ", " + crysloc);
                        instance.cl1 = bedrock;
                        instance.getConfig().set("X, Y, Z (a)", crysloc);
                        break;
                    case "crystal 2":
                        commandSender.sendMessage(instance.crystalb + ", " + crysloc);
                        instance.cl2 = bedrock;
                        instance.getConfig().set("X, Y, Z (b)", crysloc);
                        break;
                    case "crystal 3":
                        commandSender.sendMessage(instance.crystalc + ", " + crysloc);
                        instance.cl3 = bedrock;
                        instance.getConfig().set("X, Y, Z (c)", crysloc);
                        break;
                    case "crystal 4":
                        commandSender.sendMessage(instance.crystald + ", " + crysloc);
                        instance.cl4 = bedrock;
                        instance.getConfig().set("X, Y, Z (d)", crysloc);
                        break;
                    default:
                        commandSender.sendMessage(new String[]{instance.help1, instance.help2, instance.help3});
                        break;
                }
                if (String.join(" ", args).contains("interval")) {
                    commandSender.sendMessage(instance.setInterval + Long.parseLong(args[2]));
                    instance.getConfig().set("respawn_interval", Long.parseLong(args[2]));
                    instance.handler.respawnTime = Long.parseLong(args[2]);
                }
                if (String.join(" ", args).contains("reload")) {
                    instance.reloadConfig();
                    commandSender.sendMessage("configuration reloaded");

                }

            } catch (Exception e) {
                commandSender.sendMessage(instance.error);
            }
        }
        return false;
    }
}
