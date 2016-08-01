package com.niconator1.particles.main;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.niconator1.particles.util.ParticleUtil;
import com.niconator1.particles.util.Util;

public class ParticlePlugin extends JavaPlugin {

	@Override
	public void onEnable() {
		this.getLogger().info("Starting Particle Test");
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (cmd.getName().equalsIgnoreCase("test")) {
			if (sender instanceof Player) {
				Player p = (Player) sender;
				if (args.length > 0) {
					String type = args[0];
					if (type.equalsIgnoreCase("line")) {
						if (args.length > 1) {
							LivingEntity le = Util.getClosestEntity(p);
							if (le != null) {
								ParticleUtil.doLine(p.getLocation().add(0, 0.9, 0), le.getLocation(),
										Double.parseDouble(args[1]));
							} else {
								p.sendMessage("There are no entitys around you");
							}
						} else {
							p.sendMessage("Usage /test line distance");
						}

					} else if (type.equalsIgnoreCase("circle")) {
						if (args.length > 2) {
							ParticleUtil.doCircle(p.getLocation(), Double.parseDouble(args[1]),
									Double.parseDouble(args[2]));
						} else {
							p.sendMessage("Usage /test circle radius distance");
						}
					} else if (type.equalsIgnoreCase("star")) {
						if (args.length > 2) {
							ParticleUtil.doStar(p.getLocation(), Double.parseDouble(args[1]),
									Double.parseDouble(args[2]));
						} else {
							p.sendMessage("Usage /test circle radius distance");
						}
					} else if (type.equalsIgnoreCase("sphere")) {
						if (args.length > 3) {
							ParticleUtil.doSphere(p.getLocation().add(0, 0.9, 0), Double.parseDouble(args[1]),
									Double.parseDouble(args[2]), Double.parseDouble(args[2]));
						} else {
							p.sendMessage("Usage /test sphere radius distancey distancexz");
						}
					} else {
						p.sendMessage("Usage /test type [args]");
					}
				} else {
					p.sendMessage("Usage /test type [args]");
				}
			} else {
				sender.sendMessage("You have to be a player");
			}
		}
		return false;
	}

	@Override
	public void onDisable() {
		this.getLogger().info("Disabling Particle Test");
	}
}
