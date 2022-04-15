package ca.bungo.weapons.classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import ca.bungo.core.Core;
import ca.bungo.weapons.BallerItem;
import ca.bungo.weapons.ItemManager;

public class PoisonBow extends BallerItem
{
	ArrayList<Entity> arrows = new ArrayList<Entity>();
	Core plugin;
	public PoisonBow(Core core) 
	{
		super(ChatColor.AQUA + "Poison Bow", Material.BOW, 300000, lore(), enchantments(), "poisonbow", "pbow");
		this.plugin = core;
		// TODO Auto-generated constructor stub
	}
	
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent event)
	{
		if(event.getEntity() instanceof Player 
				&& event.getDamager() instanceof Arrow)
		{
			Player player = (Player)event.getEntity();
			Arrow arrow = (Arrow)event.getDamager();
			
			if(Core.isPlayerInPVP(player))
			{
				if(this.arrows.contains(arrow))
				{
					player.getWorld().playEffect(player.getLocation(), Effect.POTION_BREAK, 8260);
					player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 10 * 20, 0));
				}	
			}
		}
	}
	
	@EventHandler
	public void onArrowHit(ProjectileHitEvent event)
	{
		this.check(arrows, event.getEntity());
	}
	
	@EventHandler
	public void onShootBow(EntityShootBowEvent event)
	{
		if(event.getProjectile() instanceof Arrow)
		{
			Arrow arrow = (Arrow)event.getProjectile();
			ItemStack bow = event.getBow();
			
			if(ItemManager.getInstance().isBallerItem(bow, this))
			{
				this.arrows.add(arrow);
			}
		}
	}

	public void check(final ArrayList<Entity> a, final Entity e)
	{
		if (a.contains(e)) {
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable()
			{
				public void run()
				{
					a.remove(e);
				}
			}, 3L);
		}
	}

	@SuppressWarnings("serial")
	private static List<String> lore()
	{
		// TODO Auto-generated method stub
		return new ArrayList<String>()
		{{
			this.add(ChatColor.GRAY + "Poison I");
			this.add(ChatColor.DARK_GRAY + "Shoot enemies to poison them!");
		}};
	}

	@SuppressWarnings("serial")
	private static HashMap<Enchantment, Integer> enchantments()
	{
		// TODO Auto-generated method stub
		return new HashMap<Enchantment, Integer>()
		{{
			this.put(Enchantment.ARROW_DAMAGE, 3);
		}};
	}
}
