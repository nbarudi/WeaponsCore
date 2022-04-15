package com.stirante.MoreProjectiles.event;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.LivingEntity;

import com.stirante.MoreProjectiles.projectile.CustomProjectile;

/**
 * BlockProjectileHitEvent is fired when falling block projectile hits entity or
 * block.
 */
public class BlockProjectileHitEvent extends CustomProjectileHitEvent {
    
    private final Material mat;
    
    /**
     * Instantiates a new block projectile hit event.
     * 
     * @param pro
     * projectile
     * @param b
     * hit block
     * @param f
     * block face
     * @param mat
     * block id
     */
    public BlockProjectileHitEvent(CustomProjectile pro, Block b, BlockFace f, Material mat) {
        super(pro, b, f);
        this.mat = mat;
    }
    
    /**
     * Instantiates a new block projectile hit event.
     * 
     * @param pro
     * projectile
     * @param ent
     * hit entity
     * @param mat
     * block id
     */
    public BlockProjectileHitEvent(CustomProjectile pro, LivingEntity ent, Material mat) {
        super(pro, ent);
        this.mat = mat;
    }
    
    /**
     * Gets the block id.
     * 
     * @return the block id
     */
    public Material getMaterial() {
        return mat;
    }
    
    /**
     * Gets the data.
     * 
     * @return damage value of block
     */
    
}
