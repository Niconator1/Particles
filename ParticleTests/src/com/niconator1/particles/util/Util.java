package com.niconator1.particles.util;

import org.bukkit.entity.LivingEntity;

public class Util {

	public static LivingEntity getClosestEntity(LivingEntity p) {
		LivingEntity ret = null;
		for (LivingEntity le:p.getWorld().getLivingEntities()) {
			if(le.getUniqueId().compareTo(p.getUniqueId())!=0){
				if(ret!=null){
					if(ret.getLocation().distance(p.getLocation())>le.getLocation().distance(p.getLocation())){
						ret=le;
					}
				}
				else{
					ret=le;
				}
			}	
		}
		return ret;
	}

}
