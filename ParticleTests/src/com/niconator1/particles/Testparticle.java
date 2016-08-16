package com.niconator1.particles;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import com.niconator1.particles.main.ParticlePlugin;

import net.minecraft.server.v1_10_R1.EnumParticle;

public abstract class Testparticle {
	protected Location start;
	protected EnumParticle effect;
	protected int dur;
	protected int delay;
	protected boolean isRepeating = false;
	protected boolean isRunning = false;
	protected int startdelay = 0;

	public Testparticle(Location start, EnumParticle effect) {
		this.start = start.clone();
		this.effect = effect;
	}

	public Testparticle(Location start, EnumParticle effect, int delay, int dur) {
		this.start = start.clone();
		this.delay = delay;
		this.dur = dur;
		this.effect = effect;
		this.isRepeating = true;
	}

	public Testparticle(Location start, EnumParticle effect, int delay, int dur, int startdelay) {
		this.start = start.clone();
		this.delay = delay;
		this.dur = dur;
		this.startdelay = startdelay;
		this.effect = effect;
		this.isRepeating = true;
	}

	protected abstract void handle();

	private int count = 0;

	public void start() {
		isRunning = true;
		if (!isRepeating) {
			handle();
		} else {
			count = Bukkit.getServer().getScheduler()
					.scheduleSyncRepeatingTask(JavaPlugin.getPlugin(ParticlePlugin.class), new Runnable() {
						public void run() {
							handle();
							if (dur > 0) {
								dur--;
							} else {
								onDisable();
								Bukkit.getServer().getScheduler().cancelTask(count);
								return;
							}
							if (isRunning == false) {
								onDisable();
								Bukkit.getServer().getScheduler().cancelTask(count);
								return;
							}
						}
					}, startdelay, delay);
		}
	}

	public int getDurationRemaining() {
		return dur;
	}

	public int getDelay() {
		return delay;
	}

	public void stop() {
		if (isRunning) {
			isRunning = false;
			Bukkit.getServer().getScheduler().cancelTask(count);
		}
	}

	protected void onDisable() {
	};
}
