//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.stirante.MoreProjectiles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public enum Particles {
    HUGE_EXPLOSION(EnumParticle.EXPLOSION_HUGE),
    LARGE_EXPLODE(EnumParticle.EXPLOSION_LARGE),
    FIREWORKS_SPARK(EnumParticle.FIREWORKS_SPARK),
    BUBBLE(EnumParticle.WATER_BUBBLE),
    SUSPEND(EnumParticle.SUSPENDED),
    DEPTH_SUSPEND(EnumParticle.SUSPENDED_DEPTH),
    TOWN_AURA(EnumParticle.TOWN_AURA),
    CRIT(EnumParticle.CRIT),
    MAGIC_CRIT(EnumParticle.CRIT_MAGIC),
    MOB_SPELL(EnumParticle.SPELL_MOB),
    MOB_SPELL_AMBIENT(EnumParticle.SPELL_MOB_AMBIENT),
    SPELL(EnumParticle.SPELL_MOB),
    INSTANT_SPELL(EnumParticle.SPELL_INSTANT),
    WITCH_MAGIC(EnumParticle.SPELL_WITCH),
    NOTE(EnumParticle.NOTE),
    PORTAL(EnumParticle.PORTAL),
    ENCHANTMENT_TABLE(EnumParticle.ENCHANTMENT_TABLE),
    EXPLODE(EnumParticle.EXPLOSION_NORMAL),
    FLAME(EnumParticle.FLAME),
    LAVA(EnumParticle.LAVA),
    FOOTSTEP(EnumParticle.FOOTSTEP),
    SPLASH(EnumParticle.WATER_SPLASH),
    LARGE_SMOKE(EnumParticle.SMOKE_LARGE),
    CLOUD(EnumParticle.CLOUD),
    RED_DUST(EnumParticle.REDSTONE),
    SNOWBALL_POOF(EnumParticle.SNOWBALL),
    DRIP_WATER(EnumParticle.DRIP_WATER),
    DRIP_LAVA(EnumParticle.DRIP_LAVA),
    SNOW_SHOVEL(EnumParticle.SNOW_SHOVEL),
    SLIME(EnumParticle.SLIME),
    HEART(EnumParticle.HEART),
    ANGRY_VILLAGER(EnumParticle.VILLAGER_ANGRY),
    HAPPY_VILLAGER(EnumParticle.VILLAGER_HAPPY);

    private static final double MAX_RANGE = 60.0D;
    private final EnumParticle particle;

    private Particles(EnumParticle enumParticle) {
        this.particle = enumParticle;
    }

    private static List<Player> getPlayersInRange(Location loc, double range) {
        List<Player> players = new ArrayList();
        double sqr = range * range;
        Iterator var6 = loc.getWorld().getPlayers().iterator();

        while(var6.hasNext()) {
            Player p = (Player)var6.next();
            if (p.getLocation().distanceSquared(loc) <= sqr) {
                players.add(p);
            }
        }

        return players;
    }

    private static PacketPlayOutWorldParticles createPacket(EnumParticle particle, Location loc, float offsetX, float offsetY, float offsetZ, float speed, int amount, int... ids) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount of particles has to be greater than 0");
        } else {
            try {
                return new PacketPlayOutWorldParticles(particle, true, (float)loc.getX(), (float)loc.getY(), (float)loc.getZ(), offsetX, offsetY, offsetZ, speed, amount, ids);
            } catch (Exception var9) {
                Bukkit.getLogger().warning("[Particles] Failed to create a particle packet!");
                var9.printStackTrace();
                return null;
            }
        }
    }

    private static PacketPlayOutWorldParticles createIconCrackPacket(int id, Location loc, float offsetX, float offsetY, float offsetZ, float speed, int amount) {
        return createPacket(EnumParticle.ITEM_CRACK, loc, offsetX, offsetY, offsetZ, speed, amount, id);
    }

    private static PacketPlayOutWorldParticles createBlockCrackPacket(int id, byte data, Location loc, float offsetX, float offsetY, float offsetZ, float speed, int amount) {
        return createPacket(EnumParticle.BLOCK_CRACK, loc, offsetX, offsetY, offsetZ, speed, amount, id, data);
    }

    private static PacketPlayOutWorldParticles createBlockDustPacket(int id, byte data, Location loc, float offsetX, float offsetY, float offsetZ, float speed, int amount) {
        return createPacket(EnumParticle.BLOCK_DUST, loc, offsetX, offsetY, offsetZ, speed, amount, id, data);
    }

    private static void sendPacket(Player player, PacketPlayOutWorldParticles packet) {
        if (packet != null) {
            ((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet);
        }

    }

    private static void sendPacket(Collection<Player> players, PacketPlayOutWorldParticles packet) {
        Iterator var2 = players.iterator();

        while(var2.hasNext()) {
            Player p = (Player)var2.next();
            sendPacket(p, packet);
        }

    }

    public static void displayIconCrack(Location loc, int id, float offsetX, float offsetY, float offsetZ, float speed, int amount, Player... players) {
        sendPacket((Collection)Arrays.asList(players), createIconCrackPacket(id, loc, offsetX, offsetY, offsetZ, speed, amount));
    }

    public static void displayIconCrack(Location loc, int id, float offsetX, float offsetY, float offsetZ, float speed, int amount) {
    }

    public static void displayIconCrack(Location loc, double range, int id, float offsetX, float offsetY, float offsetZ, float speed, int amount) {
        if (range > 60.0D) {
            throw new IllegalArgumentException("Range has to be lower/equal the maximum of 20");
        } else {
            sendPacket((Collection)getPlayersInRange(loc, range), createIconCrackPacket(id, loc, offsetX, offsetY, offsetZ, speed, amount));
        }
    }

    public static void displayBlockCrack(Location loc, int id, byte data, float offsetX, float offsetY, float offsetZ, float speed, int amount, Player... players) {
        sendPacket((Collection)Arrays.asList(players), createBlockCrackPacket(id, data, loc, offsetX, offsetY, offsetZ, speed, amount));
    }

    public static void displayBlockCrack(Location loc, int id, byte data, float offsetX, float offsetY, float offsetZ, float speed, int amount) {
    }

    public static void displayBlockCrack(Location loc, double range, int id, byte data, float offsetX, float offsetY, float offsetZ, float speed, int amount) {
        if (range > 60.0D) {
            throw new IllegalArgumentException("Range has to be lower/equal the maximum of 20");
        } else {
            sendPacket((Collection)getPlayersInRange(loc, range), createBlockCrackPacket(id, data, loc, offsetX, offsetY, offsetZ, speed, amount));
        }
    }

    public static void displayBlockDust(Location loc, int id, byte data, float offsetX, float offsetY, float offsetZ, float speed, int amount, Player... players) {
        sendPacket((Collection)Arrays.asList(players), createBlockDustPacket(id, data, loc, offsetX, offsetY, offsetZ, speed, amount));
    }

    public static void displayBlockDust(Location loc, int id, byte data, float offsetX, float offsetY, float offsetZ, float speed, int amount) {
        displayBlockDust(loc, 60.0D, id, data, offsetX, offsetY, offsetZ, speed, amount);
    }

    public static void displayBlockDust(Location loc, double range, int id, byte data, float offsetX, float offsetY, float offsetZ, float speed, int amount) {
        if (range > 60.0D) {
            throw new IllegalArgumentException("Range has to be lower/equal the maximum of 20");
        } else {
            sendPacket((Collection)getPlayersInRange(loc, range), createBlockDustPacket(id, data, loc, offsetX, offsetY, offsetZ, speed, amount));
        }
    }

    private PacketPlayOutWorldParticles createPacket(Location loc, float offsetX, float offsetY, float offsetZ, float speed, int amount) {
        return createPacket(this.particle, loc, offsetX, offsetY, offsetZ, speed, amount);
    }

    public void display(Location loc, float offsetX, float offsetY, float offsetZ, float speed, int amount, Player... players) {
        sendPacket((Collection)Arrays.asList(players), this.createPacket(loc, offsetX, offsetY, offsetZ, speed, amount));
    }

    public void display(Location loc, float offsetX, float offsetY, float offsetZ, float speed, int amount) {
        this.display(loc, 60.0D, offsetX, offsetY, offsetZ, speed, amount);
    }

    public void display(Location loc, double range, float offsetX, float offsetY, float offsetZ, float speed, int amount) {
        if (range > 60.0D) {
            throw new IllegalArgumentException("Range has to be lower/equal the maximum of 60.0");
        } else {
            sendPacket((Collection)getPlayersInRange(loc, range), this.createPacket(loc, offsetX, offsetY, offsetZ, speed, amount));
        }
    }
}
