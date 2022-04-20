package ca.bungo.utils;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.server.v1_8_R3.EnumParticle;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.stirante.MoreProjectiles.ReflectionUtil;

public enum ParticleEffect
{
	HUGE_EXPLOSION("hugeexplosion", 0, EnumParticle.EXPLOSION_HUGE),
	LARGE_EXPLODE("largeexplode", 1, EnumParticle.EXPLOSION_LARGE),
	FIREWORKS_SPARK("fireworksSpark", 2, EnumParticle.FIREWORKS_SPARK),
	BUBBLE("bubble", 3, EnumParticle.WATER_BUBBLE),
	SUSPEND("suspend", 4, EnumParticle.SUSPENDED),
	DEPTH_SUSPEND("depthSuspend", 5, EnumParticle.SUSPENDED_DEPTH),
	TOWN_AURA("townaura", 6, EnumParticle.TOWN_AURA),
	CRIT("crit", 7, EnumParticle.CRIT),
	MAGIC_CRIT("magicCrit", 8, EnumParticle.CRIT_MAGIC),
	MOB_SPELL("mobSpell", 9, EnumParticle.SPELL_MOB),
	MOB_SPELL_AMBIENT("mobSpellAmbient", 10, EnumParticle.SPELL_MOB_AMBIENT),
	SPELL("spell", 11, EnumParticle.SPELL),
	INSTANT_SPELL("instantSpell", 12, EnumParticle.SPELL_INSTANT),
	WITCH_MAGIC("witchMagic", 13, EnumParticle.SPELL_WITCH),
	NOTE("note", 14, EnumParticle.NOTE),
	PORTAL("portal", 15, EnumParticle.PORTAL),
	ENCHANTMENT_TABLE("enchantmenttable", 16, EnumParticle.ENCHANTMENT_TABLE),
	EXPLODE("explode", 17, EnumParticle.EXPLOSION_NORMAL),
	FLAME("flame", 18, EnumParticle.FLAME),
	LAVA("lava", 19, EnumParticle.LAVA),
	FOOTSTEP("footstep", 20, EnumParticle.FOOTSTEP),
	SPLASH("splash", 21, EnumParticle.WATER_SPLASH),
	LARGE_SMOKE("largesmoke", 22, EnumParticle.SMOKE_LARGE),
	CLOUD("cloud", 23, EnumParticle.CLOUD),
	RED_DUST("reddust", 24, EnumParticle.REDSTONE),
	SNOWBALL_POOF("snowballpoof", 25, EnumParticle.SNOWBALL),
	DRIP_WATER("dripWater", 26, EnumParticle.DRIP_WATER),
	DRIP_LAVA("dripLava", 27, EnumParticle.DRIP_LAVA),
	SNOW_SHOVEL("snowshovel", 28, EnumParticle.SNOW_SHOVEL),
	SLIME("slime", 29, EnumParticle.SLIME),
	HEART("heart", 30, EnumParticle.HEART),
	ANGRY_VILLAGER("angryVillager", 31, EnumParticle.VILLAGER_ANGRY),
	HAPPY_VILLAGER("happyVillager", 32, EnumParticle.VILLAGER_HAPPY);

	private static final Map<String, ParticleEffect> NAME_MAP;
	private static final Map<Integer, ParticleEffect> ID_MAP;
	@SuppressWarnings("unused")
	private static final double MAX_RANGE = 20.0D;
	private static Constructor<?> PARTICLE_PACKET_CONSTRUCTOR;
	private String name;
	private int id;
	private EnumParticle enumRef;

	static
	{
		NAME_MAP = new HashMap<String, ParticleEffect>();
		ID_MAP = new HashMap<Integer, ParticleEffect>();
		for (ParticleEffect effect : values())
		{
			NAME_MAP.put(effect.name, effect);
			ID_MAP.put(Integer.valueOf(effect.id), effect);
		}
		try
		{
			PARTICLE_PACKET_CONSTRUCTOR = ReflectionUtil.getConstructor(ReflectionUtil.getClass("PacketPlayOutWorldParticles", ReflectionUtil.DynamicPackage.MINECRAFT_SERVER), new Class[0]);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private ParticleEffect(String name, int id, EnumParticle enumRef)
	{
		this.name = name;
		this.id = id;
		this.enumRef = enumRef;
	}

	public String getName()
	{
		return this.name;
	}

	public int getId()
	{
		return this.id;
	}

	public static ParticleEffect fromName(String name)
	{
		if (name != null) {
			for (Map.Entry<String, ParticleEffect> e : NAME_MAP.entrySet()) {
				if (((String)e.getKey()).equalsIgnoreCase(name)) {
					return (ParticleEffect)e.getValue();
				}
			}
		}
		return null;
	}

	public static ParticleEffect fromId(int id)
	{
		return (ParticleEffect)ID_MAP.get(Integer.valueOf(id));
	}

	private static List<Player> getPlayersInRange(Location loc, double range)
	{
		List<Player> players = new ArrayList<Player>();
		double sqr = range * range;
		for (Player p : loc.getWorld().getPlayers()) {
			if (p.getLocation().distanceSquared(loc) <= sqr) {
				players.add(p);
			}
		}
		return players;
	}

	private static Object createPacket(String name, Location loc, float offsetX, float offsetY, float offsetZ, float speed, int amount)
	{
		if (amount <= 0) {
			throw new IllegalArgumentException("Amount of particles has to be greater than 0");
		}
		try
		{
			Object p = PARTICLE_PACKET_CONSTRUCTOR.newInstance(new Object[0]);;
			ReflectionUtil.setValues(p, new ReflectionUtil.FieldEntry[] { new ReflectionUtil.FieldEntry("a", fromName(name).enumRef), new ReflectionUtil.FieldEntry("b", Float.valueOf((float)loc.getX())), new ReflectionUtil.FieldEntry("c", Float.valueOf((float)loc.getY())), new ReflectionUtil.FieldEntry("d", Float.valueOf((float)loc.getZ())), new ReflectionUtil.FieldEntry("e", Float.valueOf(offsetX)), new ReflectionUtil.FieldEntry("f", Float.valueOf(offsetY)), new ReflectionUtil.FieldEntry("g", Float.valueOf(offsetZ)), new ReflectionUtil.FieldEntry("h", Float.valueOf(speed)), new ReflectionUtil.FieldEntry("i", Integer.valueOf(amount)) });

			return p;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			Bukkit.getLogger().warning("[ParticleEffect] Failed to create a particle packet!");
		}
		return null;
	}

	private Object createPacket(Location loc, float offsetX, float offsetY, float offsetZ, float speed, int amount)
	{
		return createPacket(getName(), loc, offsetX, offsetY, offsetZ, speed, amount);
	}

	private static Object createIconCrackPacket(int id, Location loc, float offsetX, float offsetY, float offsetZ, float speed, int amount)
	{
		return createPacket("iconcrack_" + id, loc, offsetX, offsetY, offsetZ, speed, amount);
	}

	private static Object createBlockCrackPacket(int id, byte data, Location loc, float offsetX, float offsetY, float offsetZ, float speed, int amount)
	{
		return createPacket("blockcrack_" + id + "_" + data, loc, offsetX, offsetY, offsetZ, speed, amount);
	}

	private static Object createBlockDustPacket(int id, byte data, Location loc, float offsetX, float offsetY, float offsetZ, float speed, int amount)
	{
		return createPacket("blockdust_" + id + "_" + data, loc, offsetX, offsetY, offsetZ, speed, amount);
	}

	private static void sendPacket(Player p, Object packet)
	{
		if (packet != null) {
			try
			{
				Object entityPlayer = ReflectionUtil.invokeMethod("getHandle", p.getClass(), p, new Object[0]);
				Object playerConnection = ReflectionUtil.getValue("playerConnection", entityPlayer);
				ReflectionUtil.invokeMethod("sendPacket", playerConnection.getClass(), playerConnection, new Object[] { packet });
			}
			catch (Exception e)
			{
				Bukkit.getLogger().warning("[ParticleEffect] Failed to send a particle packet to " + p.getName() + "!");
			}
		}
	}

	private static void sendPacket(Collection<Player> players, Object packet)
	{
		for (Player p : players) {
			sendPacket(p, packet);
		}
	}

	public void display(Location loc, float offsetX, float offsetY, float offsetZ, float speed, int amount, Player... players)
	{
		sendPacket(Arrays.asList(players), createPacket(loc, offsetX, offsetY, offsetZ, speed, amount));
	}

	public void display(Location loc, float offsetX, float offsetY, float offsetZ, float speed, int amount)
	{
		display(loc, 20.0D, offsetX, offsetY, offsetZ, speed, amount);
	}

	public void display(Location loc, double range, float offsetX, float offsetY, float offsetZ, float speed, int amount)
	{
		if (range > 20.0D) {
			throw new IllegalArgumentException("Range has to be lower/equal the maximum of 20");
		}
		sendPacket(getPlayersInRange(loc, range), createPacket(loc, offsetX, offsetY, offsetZ, speed, amount));
	}

	public static void displayIconCrack(Location loc, int id, float offsetX, float offsetY, float offsetZ, float speed, int amount, Player... players)
	{
		sendPacket(Arrays.asList(players), createIconCrackPacket(id, loc, offsetX, offsetY, offsetZ, speed, amount));
	}

	public static void displayIconCrack(Location loc, int id, float offsetX, float offsetY, float offsetZ, float speed, int amount)
	{
		displayIconCrack(loc, 20.0D, id, offsetX, offsetY, offsetZ, speed, amount);
	}

	public static void displayIconCrack(Location loc, double range, int id, float offsetX, float offsetY, float offsetZ, float speed, int amount)
	{
		if (range > 20.0D) {
			throw new IllegalArgumentException("Range has to be lower/equal the maximum of 20");
		}
		sendPacket(getPlayersInRange(loc, range), createIconCrackPacket(id, loc, offsetX, offsetY, offsetZ, speed, amount));
	}

	public static void displayBlockCrack(Location loc, int id, byte data, float offsetX, float offsetY, float offsetZ, float speed, int amount, Player... players)
	{
		sendPacket(Arrays.asList(players), createBlockCrackPacket(id, data, loc, offsetX, offsetY, offsetZ, speed, amount));
	}

	public static void displayBlockCrack(Location loc, int id, byte data, float offsetX, float offsetY, float offsetZ, float speed, int amount)
	{
		displayBlockCrack(loc, 20.0D, id, data, offsetX, offsetY, offsetZ, speed, amount);
	}

	public static void displayBlockCrack(Location loc, double range, int id, byte data, float offsetX, float offsetY, float offsetZ, float speed, int amount)
	{
		if (range > 20.0D) {
			throw new IllegalArgumentException("Range has to be lower/equal the maximum of 20");
		}
		sendPacket(getPlayersInRange(loc, range), createBlockCrackPacket(id, data, loc, offsetX, offsetY, offsetZ, speed, amount));
	}

	public static void displayBlockDust(Location loc, int id, byte data, float offsetX, float offsetY, float offsetZ, float speed, int amount, Player... players)
	{
		sendPacket(Arrays.asList(players), createBlockDustPacket(id, data, loc, offsetX, offsetY, offsetZ, speed, amount));
	}

	public static void displayBlockDust(Location loc, int id, byte data, float offsetX, float offsetY, float offsetZ, float speed, int amount)
	{
		displayBlockDust(loc, 20.0D, id, data, offsetX, offsetY, offsetZ, speed, amount);
	}

	public static void displayBlockDust(Location loc, double range, int id, byte data, float offsetX, float offsetY, float offsetZ, float speed, int amount)
	{
		if (range > 20.0D) {
			throw new IllegalArgumentException("Range has to be lower/equal the maximum of 20");
		}
		sendPacket(getPlayersInRange(loc, range), createBlockDustPacket(id, data, loc, offsetX, offsetY, offsetZ, speed, amount));
	}
}
