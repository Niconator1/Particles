package com.niconator1.particles;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import com.niconator1.particles.util.ParticleUtil;

import net.minecraft.server.v1_10_R1.EnumParticle;

public class Line extends Testparticle {
	protected ArrayList<Location> points = new ArrayList<Location>();
	protected Location destination;
	protected float speed = 0f;
	protected int anz = 0;
	protected Vector v = new Vector(0, 0, 0);

	public Line(Location start, EnumParticle effect, Location destination, double distancebetween, Vector v,
			float speed, int anz) {
		super(start, effect);
		this.destination = destination.clone();
		this.v = v;
		this.speed = speed;
		this.anz = anz;
		doPoints(distancebetween);
	}

	public Line(Location start, EnumParticle effect, Location destination, double distancebetween, Vector v,
			float speed, int anz, int delay, int dur, int startdelay) {
		super(start, effect, delay, dur, startdelay);
		this.destination = destination.clone();
		this.v = v;
		this.speed = speed;
		this.anz = anz;
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

	public Location getDestination() {
		return destination;
	}

	private void doPoints(double distancebetween) {
		double distance = destination.clone().distance(start);
		Vector direction = destination.clone().subtract(start).toVector().normalize();
		for (double i = 0; i < distance; i += distancebetween) {
			Location particle = start.clone().add(direction.clone().multiply(i));
			points.add(particle);
		}
	}
}
