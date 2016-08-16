package com.niconator1.particles.main;

import org.bukkit.Location;
import org.bukkit.command.BlockCommandSender;
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
						if (args.length > 2) {
							LivingEntity le = Util.getClosestEntity(p);
							if (le != null) {
								ParticleUtil.doLine(args[1], p.getLocation().add(0, 0.9, 0), le.getLocation(),
										Double.parseDouble(args[2]));
							} else {
								sender.sendMessage("There are no entitys around you");
							}
						} else {
							sender.sendMessage("Usage /test line particle distance");
						}

					} else if (type.equalsIgnoreCase("circle")) {
						if (args.length > 3) {
							ParticleUtil.doCircle(args[1], p.getLocation(), Double.parseDouble(args[2]),
									Double.parseDouble(args[3]));
						} else {
							sender.sendMessage("Usage /test circle particle radius distance");
						}
					} else if (type.equalsIgnoreCase("star")) {
						if (args.length > 3) {
							ParticleUtil.doStar(args[1], p.getLocation(), Double.parseDouble(args[2]),
									Double.parseDouble(args[3]));
						} else {
							sender.sendMessage("Usage /test star particle radius distance");
						}
					} else if (type.equalsIgnoreCase("sphere")) {
						if (args.length > 3) {
							ParticleUtil.doSphere(args[1], p.getLocation().add(0, 0.9, 0), Double.parseDouble(args[2]),
									Double.parseDouble(args[3]));
						} else {
							sender.sendMessage("Usage /test sphere particle radius distance");
						}
					} else if (type.equalsIgnoreCase("gravisphere")) {
						if (args.length > 3) {
							ParticleUtil.doGraviSphere(p.getLocation().add(0, 0.9, 0), Double.parseDouble(args[1]),
									Integer.parseInt(args[2]), Integer.parseInt(args[3]));
						} else {
							sender.sendMessage("Usage /test gravisphere radius rings duration");
						}
					} else {
						sender.sendMessage("Usage /test type [args]");
					}
				} else {
					sender.sendMessage("Usage /test type [args]");
				}
			} else if (sender instanceof BlockCommandSender) {
				Location l = ((BlockCommandSender) sender).getBlock().getLocation();
				if (args.length > 0) {
					String type = args[0];
					if (type.equalsIgnoreCase("line")) {
						if (args.length > 2) {
							LivingEntity le = Util.getClosestEntity(l);
							if (le != null) {
								ParticleUtil.doLine(args[1], l.add(0, 0.9, 0), le.getLocation(),
										Double.parseDouble(args[2]));
							} else {
								sender.sendMessage("There are no entitys around you");
							}
						} else {
							sender.sendMessage("Usage /test line particle distance");
						}

					} else if (type.equalsIgnoreCase("circle")) {
						if (args.length > 3) {
							ParticleUtil.doCircle(args[1], l, Double.parseDouble(args[2]), Double.parseDouble(args[3]));
						} else {
							sender.sendMessage("Usage /test circle particle radius distance");
						}
					} else if (type.equalsIgnoreCase("star")) {
						if (args.length > 3) {
							ParticleUtil.doStar(args[1], l, Double.parseDouble(args[2]), Double.parseDouble(args[3]));
						} else {
							sender.sendMessage("Usage /test star particleradius distance");
						}
					} else if (type.equalsIgnoreCase("sphere")) {
						if (args.length > 3) {
							ParticleUtil.doSphere(args[1], l.add(0, 0.9, 0), Double.parseDouble(args[2]),
									Double.parseDouble(args[3]));
						} else {
							sender.sendMessage("Usage /test sphere particle radius distance");
						}
					} else if (type.equalsIgnoreCase("gravisphere")) {
						if (args.length > 3) {
							ParticleUtil.doGraviSphere(l.add(0, 0.9, 0), Double.parseDouble(args[1]),
									Integer.parseInt(args[2]), Integer.parseInt(args[3]));
						} else {
							sender.sendMessage("Usage /test gravisphere radius rings duration");
						}
					} else {
						sender.sendMessage("Usage /test type [args]");
					}
				} else {
					sender.sendMessage("Usage /test type [args]");
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
