package com.niconator1.particles;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import com.niconator1.particles.util.ParticleUtil;

import net.minecraft.server.v1_10_R1.EnumParticle;

public class GraviLine extends Line {
	protected int number = 0;

	public GraviLine(Location start, EnumParticle effect, Location destination, double distancebetween, Vector v,
			float speed, int anz, int delay, int dur, int startdelay) {
		super(start, effect, destination, distancebetween, v, speed, anz, delay, dur, startdelay);
	}

	@Override
	protected void handle() {
		number = (number + 1) % points.size();
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (Math.random() < 0.8) {
				ParticleUtil.sendParticlePacket(p, effect, points.get(number), v, speed, anz);
			}
			ParticleUtil.sendParticlePacket(p, effect, points.get(number), 0.33f, 1f, 1f, speed, anz);

		}
	}
}
