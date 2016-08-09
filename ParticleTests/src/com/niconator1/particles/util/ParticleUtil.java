package com.niconator1.particles.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import net.minecraft.server.v1_10_R1.EnumParticle;
import net.minecraft.server.v1_10_R1.PacketPlayOutWorldParticles;

public class ParticleUtil {

	public static void doLine(Location start, Location destination, double distancebetween) {
		double distance = destination.distance(start);
		Vector direction = destination.subtract(start).toVector().normalize();
		for (double i = 0; i < distance; i += distancebetween) {
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

	public static void doSphere(Location mid, double maxradius, double distancey,double distancexz) {
		for (double i = 0; i < 2.0 * Math.PI * maxradius; i += distancey) {
			double y = Math.sin(0.5 * (i / maxradius - Math.PI)) * maxradius;
			double radius = Math.sin(i / 2.0 / maxradius) * maxradius;
			doCircle(mid.clone().add(0, y, 0), radius, distancexz);
		}
	}

	public static void doCircle(Location mid, double radius, double distancebetween) {
		for (double i = 0; i < 2 * Math.PI * radius; i += distancebetween) {
			double x = Math.sin(i / radius) * radius;
			double z = Math.cos(i / radius) * radius;
			Location particle = mid.clone().add(x, 0, z);
			for (Player p : Bukkit.getOnlinePlayers()) {
				sendParticlePacket(p, EnumParticle.FLAME, particle, 1);
			}
		}
	}

	public static void doPylonInverted(Location mid, double maxradius, double distancebetween) {
		for (double i = 0; i < Math.PI * maxradius; i += 0.1) {
			double y = -0.5 * Math.PI * maxradius + i;
			double radius = i;
			doCircle(mid.clone().add(0, y, 0), radius, distancebetween);
		}
	}
	
	public static void doStar(Location mid, double radius, double distancebetween) {
		doCircle(mid, radius, distancebetween);
		for (int i = 0; i < 5; i++) {
			int second = (i+2)%5;
			Location start = mid.clone().add(Math.cos(2.0*Math.PI*0.2*i)*radius, 0.0, Math.sin(2.0*Math.PI*0.2*i)*radius);
			Location destination = mid.clone().add(Math.cos(2.0*Math.PI*0.2*second)*radius, 0.0, Math.sin(2.0*Math.PI*0.2*second)*radius);
			doLine(start, destination, distancebetween);
		}
	}
}
