package com.niconator1.particles.util;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import com.niconator1.particles.Circle;
import com.niconator1.particles.Gravisphere;
import com.niconator1.particles.Line;
import com.niconator1.particles.Sphere;
import com.niconator1.particles.Star;

import net.minecraft.server.v1_10_R1.EnumParticle;
import net.minecraft.server.v1_10_R1.PacketPlayOutWorldParticles;

public class ParticleUtil {

	public static void doLine(String particle, Location start, Location destination, double distancebetween) {
		EnumParticle effect = EnumParticle.a(particle);
		if (effect != null) {
			Line l = new Line(start, effect, destination, distancebetween, new Vector(0, 0, 0), 0, 0);
			l.start();
		}
	}

	public static void doCircle(String particle, Location mid, double radius, double distancebetween) {
		EnumParticle effect = EnumParticle.a(particle);
		if (effect != null) {
			Circle c = new Circle(mid, effect, radius, distancebetween, new Vector(0, 0, 0), 0, 0);
			c.start();
		}
	}

	public static void doSphere(String particle, Location mid, double radius, double distancebetween) {
		EnumParticle effect = EnumParticle.a(particle);
		if (effect != null) {
			Sphere c = new Sphere(mid, effect, radius, distancebetween, new Vector(0, 0, 0), 0, 0);
			c.start();
		}
	}

	public static void doStar(String particle, Location mid, double radius, double distancebetween) {
		EnumParticle effect = EnumParticle.a(particle);
		if (effect != null) {
			Star s = new Star(mid, effect, radius, distancebetween, new Vector(0, 0, 0), 0, 0);
			s.start();
		}
	}

	public static void doGraviSphere(Location mid, double radius, int circles, int dur) {
		Gravisphere g = new Gravisphere(mid, radius, circles, dur);
		g.start();
	}

	public static void sendParticlePacket(Player p, EnumParticle type, Location l, int count) {
		sendParticlePacket(p, type, l.getX(), l.getY(), l.getZ(), 0, 0, 0, 0, count);

	}

	public static void sendParticlePacket(Player p, EnumParticle type, Location l, Vector v3d, float v, int count) {
		sendParticlePacket(p, type, l.getX(), l.getY(), l.getZ(), (float) v3d.getX(), (float) v3d.getY(),
				(float) v3d.getZ(), v, count);
	}

	public static void sendParticlePacket(Player p, EnumParticle type, Location l, float vx, float vy, float vz,
			float v, int count) {
		sendParticlePacket(p, type, l.getX(), l.getY(), l.getZ(), vx, vy, vz, v, count);
	}

	public static void sendParticlePacket(Player p, EnumParticle type, double x, double y, double z, float vx, float vy,
			float vz, float v, int count) {
		PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(type, true, (float) x, (float) y,
				(float) z, vx, vy, vz, v, count, null);
		((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
	}
}
