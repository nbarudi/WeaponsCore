package ca.bungo.weapons.classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import ca.bungo.core.Core;
import ca.bungo.weapons.BallerItem;
import ca.bungo.weapons.ItemManager;

public class VenomSnake extends BallerItem{

	public VenomSnake()
	{
		super(ChatColor.AQUA + "Venom Snake", Material.DIAMOND_SWORD, 6000000, lore(), enchantments(), "vensnake", "venomsnake");
	}
	
	
	@EventHandler
	public void onHit(EntityDamageByEntityEvent event)
	{
		if(event.getDamager() instanceof Player)
		{
			Player player = (Player)event.getDamager();
			if(event.getEntity() instanceof LivingEntity)
			{
				LivingEntity damaged = (LivingEntity)event.getEntity();
				
				if(damaged instanceof Player)
				{
					if(Core.isPlayerInPVP(player) && !InvisRing.in.contains(player.getName()))
					{
						if(ItemManager.getInstance().isBallerItem(player.getItemInHand(), this) && !InvisRing.in.contains(player.getName()))
						{
							damaged.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 8 * 20, 1));
							damaged.getWorld().playEffect(damaged.getLocation(), Effect.POTION_BREAK, 4);
						}	
					}
				}
				else
				{
					if(ItemManager.getInstance().isBallerItem(player.getItemInHand(), this))
					{
						damaged.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 8 * 20, 1));
						damaged.getWorld().playEffect(damaged.getLocation(), Effect.POTION_BREAK, 4);
					}	
				}
			}
		}
	}
	
	@SuppressWarnings("serial")
	private static List<String> lore()
	{
		return new ArrayList<String>(){{
			this.add(ChatColor.GRAY + "Poison VIII");
			this.add(ChatColor.DARK_GRAY + "The poison sword!");
		}};
	}
	
	@SuppressWarnings("serial")
	private static HashMap<Enchantment, Integer> enchantments()
	{
		return new HashMap<Enchantment, Integer>(){{
			this.put(Enchantment.DAMAGE_ALL, 8);
			this.put(Enchantment.DAMAGE_ARTHROPODS, 8);
			this.put(Enchantment.DAMAGE_UNDEAD, 8);
			this.put(Enchantment.FIRE_ASPECT, 8);
			this.put(Enchantment.LOOT_BONUS_MOBS, 8);
			this.put(Enchantment.DURABILITY, 8);
		}};
	}
}
