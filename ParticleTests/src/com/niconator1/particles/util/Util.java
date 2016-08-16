package com.niconator1.particles.util;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

public class Util {

	public static LivingEntity getClosestEntity(LivingEntity p) {
		LivingEntity ret = null;
		for (LivingEntity le : p.getWorld().getLivingEntities()) {
			if (le.getUniqueId().compareTo(p.getUniqueId()) != 0) {
				if (ret != null) {
					if (ret.getLocation().distance(p.getLocation()) > le.getLocation().distance(p.getLocation())) {
						ret = le;
					}
				} else {
					ret = le;
				}
			}
		}
		return ret;
	}

	public static LivingEntity getClosestEntity(Location l) {
		LivingEntity ret = null;
		for (LivingEntity le : l.getWorld().getLivingEntities()) {
			if (ret != null) {
				if (le.getUniqueId().compareTo(ret.getUniqueId()) != 0) {
					if (ret.getLocation().distance(l) > le.getLocation().distance(l)) {
						ret = le;
					}
				}
			}
			else{
				ret = le;
			}
		}
		return ret;
	}

	public static final Vector rotateAroundAxisX(Vector v, double angle) {
		double y, z, cos, sin;
		cos = Math.cos(angle);
		sin = Math.sin(angle);
		y = v.getY() * cos - v.getZ() * sin;
		z = v.getY() * sin + v.getZ() * cos;
		return v.setY(y).setZ(z);
	}

	public static final Vector rotateAroundAxisY(Vector v, double angle) {
		double x, z, cos, sin;
		cos = Math.cos(angle);
		sin = Math.sin(angle);
		x = v.getX() * cos + v.getZ() * sin;
		z = v.getX() * -sin + v.getZ() * cos;
		return v.setX(x).setZ(z);
	}

}
