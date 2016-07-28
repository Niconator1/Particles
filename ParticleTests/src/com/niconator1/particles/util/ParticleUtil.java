package com.niconator1.particles.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftPlayer;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import net.minecraft.server.v1_10_R1.EnumParticle;
import net.minecraft.server.v1_10_R1.PacketPlayOutWorldParticles;

public class ParticleUtil {

	public static void doLine(LivingEntity l, LivingEntity le) {
		Location start = l.getLocation();
		Location destication = le.getLocation();
		double distance = destication.distance(start);
		Vector direction = destication.subtract(start).toVector().normalize();
		for (double i = 0; i < distance; i += 0.1) {
			Location particle = start.clone().add(direction.clone().multiply(i));
			for (Player p : Bukkit.getOnlinePlayers()) {
				sendParticlePacket(p, EnumParticle.FLAME, particle, 1);
			}
		}
	}

	private static void sendParticlePacket(Player p, EnumParticle type, Location l, int count) {
		sendParticlePacket(p, type, l.getX(), l.getY(), l.getZ(), 0, 0, 0, 0, count);

	}

	public static void sendParticlePacket(Player p, EnumParticle type, double x, double y, double z, float vx, float vy,
			float vz, float v, int count) {
		PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(type, true, (float) x, (float) y,
				(float) z, vx, vy, vz, v, count, null);
		((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
	}
}
