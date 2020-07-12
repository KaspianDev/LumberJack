package de.jeff_media.LumberJack;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class CommandLumberjack implements CommandExecutor {
	
	final LumberJack plugin;
	
	CommandLumberjack(LumberJack plugin) {
		this.plugin=plugin;
	}

	@Override
	public boolean onCommand(@NotNull CommandSender sender, Command command, @NotNull String alias, String[] args) {
		
		
		
		if(!command.getName().equalsIgnoreCase("lumberjack")) {
			return false;
		}
		if(!(sender instanceof Player)) {
			sender.sendMessage("You must be a player to run this command.");
			return true;
		}
		Player p = (Player) sender;
		if(!sender.hasPermission("lumberjack.use")) {
			sender.sendMessage(Objects.requireNonNull(Objects.requireNonNull(plugin.getCommand("lumberjack")).getPermissionMessage()));
			return true;
		}
		
		if(sender.hasPermission("lumberjack.force") && !sender.hasPermission("lumberjack.force.ignore")) {
			sender.sendMessage(plugin.messages.MSG_CAN_NOT_DISABLE);
			return true;
		}
		
		plugin.togglePlayerSetting(p);
		if(plugin.getPlayerSetting(p).gravityEnabled) {
			sender.sendMessage(plugin.messages.MSG_ACTIVATED);
		} else {
			sender.sendMessage(plugin.messages.MSG_DEACTIVATED);
		}
		return true;
		
	}

}