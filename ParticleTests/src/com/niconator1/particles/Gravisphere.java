package com.niconator1.particles;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import com.niconator1.particles.util.ParticleUtil;
import com.niconator1.particles.util.Util;

import net.minecraft.server.v1_10_R1.EnumParticle;

public class Gravisphere extends TestMultiparticle {
	private ArrayList<Location> basepoint = new ArrayList<Location>();

	public Gravisphere(Location mid, double radius, int circles) {
		super(mid, EnumParticle.REDSTONE, 1, Integer.MAX_VALUE);
		doPoints(mid, radius, circles);
		createLines();
	}

	public Gravisphere(Location mid, double radius, int circles, int dur) {
		super(mid, EnumParticle.REDSTONE, 1, dur);
		doPoints(mid, radius, circles);
		createLines();
	}

	@Override
	protected void handle() {
		for (Player p : Bukkit.getOnlinePlayers()) {
			for (int i = 0; i < basepoint.size(); i++) {
				ParticleUtil.sendParticlePacket(p, effect, basepoint.get(i), 0.33f, 1f, 1f, 1.0f, 0);
			}
			ParticleUtil.sendParticlePacket(p, effect, start, 0.33f, 1f, 1f, 1.0f, 0);
		}
	}

	private void doPoints(Location mid, double radius, int circles) {
		for (int j = 0; j < circles; j++) {
			int anz = 8;
			if (j == circles / 2) {
				anz = 19;
			}
			double distancebetween = 2.0 * Math.PI * radius / (double) (anz);
			for (double i = 0; i < 2 * Math.PI * radius; i += distancebetween) {
				double x = Math.sin(i / radius) * radius;
				double z = Math.cos(i / radius) * radius;
				Vector v = new Vector(x, 0, z);
				v = Util.rotateAroundAxisX(v, j * Math.PI / circles);
				Location particle = mid.clone().add(v);
				basepoint.add(particle);
			}
		}
	}

	private void createLines() {
		Random r = new Random();
		for (int i = 0; i < basepoint.size(); i++) {
			if (r.nextBoolean()) {
				Location destination = start.clone();
				Location start = basepoint.get(i);
				Vector color = new Vector(1f, 0.33f, 1f);
				double db = 0.25;
				int delay = 2;
				double animationtime = start.distance(destination) / db * (double) (delay);
				int startdelay = r.nextInt((int) (animationtime + 0.5));
				GraviLine l = new GraviLine(start, effect, destination, db, color, 1.0f, 0, delay, Integer.MAX_VALUE,
						startdelay);
				registerEffect(l);
			}
		}
	}
}
