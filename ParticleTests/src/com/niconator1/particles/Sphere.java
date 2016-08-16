package com.niconator1.particles;

import org.bukkit.Location;
import org.bukkit.util.Vector;

import net.minecraft.server.v1_10_R1.EnumParticle;

public class Sphere extends TestMultiparticle {
	protected float speed = 0f;
	protected int anz = 0;
	protected double radius;
	protected Vector v = new Vector(0, 0, 0);

	public Sphere(Location mid, EnumParticle effect, double radius, double distancebetween, Vector v, float speed,
			int anz) {
		super(mid, effect);
		this.v = v;
		this.speed = speed;
		this.anz = anz;
		this.radius = radius;
		doCircles(distancebetween);
	}

	public Sphere(Location mid, EnumParticle effect, double radius, double distancebetween, Vector v, float speed,
			int anz, int delay, int dur, int startdelay) {
		super(mid, effect, delay, dur, startdelay);
		this.v = v;
		this.speed = speed;
		this.anz = anz;
		this.radius = radius;
		doCircles(distancebetween);
	}

	@Override
	protected void handle() {
		// Nothing to do here because subtypes are doing everything
	}

	private void doCircles(double distancebetween) {
		for (double i = 0; i < 2.0 * Math.PI * radius; i += distancebetween) {
			double y = Math.sin(0.5 * (i / radius - Math.PI)) * radius;
			double radiusa = Math.sin(i / 2.0 / radius) * radius;
			Circle c = new Circle(start.clone().add(0, y, 0), effect, radiusa, distancebetween, v, speed, anz);
			registerEffect(c);
		}
	}

}
