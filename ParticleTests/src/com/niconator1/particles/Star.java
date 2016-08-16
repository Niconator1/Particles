package com.niconator1.particles;

import org.bukkit.Location;
import org.bukkit.util.Vector;

import net.minecraft.server.v1_10_R1.EnumParticle;

public class Star extends TestMultiparticle {
	protected float speed = 0f;
	protected int anz = 0;
	protected double radius;
	protected Vector v = new Vector(0, 0, 0);

	public Star(Location mid, EnumParticle effect, double radius, double distancebetween, Vector v, float speed,
			int anz) {
		super(mid, effect);
		this.v = v;
		this.speed = speed;
		this.anz = anz;
		this.radius = radius;
		doCircle(distancebetween);
		createLines(distancebetween);
	}

	public Star(Location mid, EnumParticle effect, double radius, double distancebetween, Vector v, float speed,
			int anz, int delay, int dur, int startdelay) {
		super(mid, effect, delay, dur, startdelay);
		this.v = v;
		this.speed = speed;
		this.anz = anz;
		this.radius = radius;
		doCircle(distancebetween);
		createLines(distancebetween);
	}

	@Override
	protected void handle() {
		// Nothing to do here because subtypes are doing everything
	}

	private void doCircle(double distancebetween) {
		Circle c = new Circle(start, effect, radius, distancebetween, v, speed, anz);
		registerEffect(c);
	}

	private void createLines(double distancebetween) {
		for (int i = 0; i < 5; i++) {
			int second = (i + 2) % 5;
			Location start = this.start.clone().add(Math.cos(2.0 * Math.PI * 0.2 * i) * radius, 0.0,
					Math.sin(2.0 * Math.PI * 0.2 * i) * radius);
			Location destination = this.start.clone().add(Math.cos(2.0 * Math.PI * 0.2 * second) * radius, 0.0,
					Math.sin(2.0 * Math.PI * 0.2 * second) * radius);
			Line l = new Line(start, effect, destination, distancebetween, new Vector(0, 0, 0), 0, 0);
			registerEffect(l);
		}
	}
}
