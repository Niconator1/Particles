package com.niconator1.particles;

import java.util.ArrayList;

import org.bukkit.Location;

import net.minecraft.server.v1_10_R1.EnumParticle;

public abstract class TestMultiparticle extends Testparticle {

	protected ArrayList<Testparticle> effects = new ArrayList<Testparticle>();

	public TestMultiparticle(Location start, EnumParticle effect) {
		super(start, effect);
	}

	public TestMultiparticle(Location start, EnumParticle effect, int delay, int dur) {
		super(start, effect, delay, dur);
	}

	public TestMultiparticle(Location start, EnumParticle effect, int delay, int dur, int startdelay) {
		super(start, effect, delay, dur, startdelay);
	}

	@Override
	public void start() {
		super.start();
		for (int i = 0; i < effects.size(); i++) {
			Testparticle effect = effects.get(i);
			effect.start();
		}
	}

	@Override
	public void stop() {
		super.stop();
		for (int i = 0; i < effects.size(); i++) {
			Testparticle effect = effects.get(i);
			effect.stop();
		}
	}

	public void registerEffect(Testparticle effect) {
		effects.add(effect);
	}

	public ArrayList<Testparticle> getEffects() {
		return effects;
	}

	@Override
	protected void onDisable() {
		super.onDisable();
		for (int i = 0; i < effects.size(); i++) {
			Testparticle effect = effects.get(i);
			effect.stop();
		}
	}
}
