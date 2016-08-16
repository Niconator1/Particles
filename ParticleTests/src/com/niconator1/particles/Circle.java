package com.niconator1.particles;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import com.niconator1.particles.util.ParticleUtil;

import net.minecraft.server.v1_10_R1.EnumParticle;

public class Circle extends Testparticle {
	protected ArrayList<Location> points = new ArrayList<Location>();
	protected float speed = 0f;
	protected int anz = 0;
	protected double radius;
	protected Vector v = new Vector(0, 0, 0);

	public Circle(Location mid, EnumParticle effect, double radius, double distancebetween, Vector v, float speed,
			int anz) {
		super(mid, effect);
		this.v = v;
		this.speed = speed;
		this.anz = anz;
		this.radius = radius;
		doPoints(distancebetween);
	}

	public Circle(Location mid, EnumParticle effect, double radius, double distancebetween, Vector v, float speed,
			int anz, int delay, int dur, int startdelay) {
		super(mid, effect, delay, dur, startdelay);
		this.v = v;
		this.speed = speed;
		this.anz = anz;
		this.radius = radius;
		doPoints(distancebetween);
	}

	@Override
	protected void handle() {
		for (int i = 0; i < points.size(); i++) {
			for (Player p : Bukkit.getOnlinePlayers()) {
				ParticleUtil.sendParticlePacket(p, effect, points.get(i), v, speed, anz);
			}
		}
	}

	public double getRadius() {
		return radius;
	}

	private void doPoints(double distancebetween) {
		for (double i = 0; i < 2 * Math.PI * radius; i += distancebetween) {
			double x = Math.sin(i / radius) * radius;
			double z = Math.cos(i / radius) * radius;
			Location particle = start.clone().add(x, 0, z);
			points.add(particle);
		}
	}
}
