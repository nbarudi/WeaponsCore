package ca.bungo.weapons;

import java.util.ArrayList;

import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagList;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import ca.bungo.core.Core;
import ca.bungo.weapons.classes.AEBow;
import ca.bungo.weapons.classes.AbominableSnowman;
import ca.bungo.weapons.classes.AngelWings;
import ca.bungo.weapons.classes.Assassin8;
import ca.bungo.weapons.classes.ForsakenDefender;
import ca.bungo.weapons.classes.BattleAxe;
import ca.bungo.weapons.classes.Beast;
import ca.bungo.weapons.classes.BlindingRod;
import ca.bungo.weapons.classes.BoneSword;
import ca.bungo.weapons.classes.BunnyBeater;
import ca.bungo.weapons.classes.BunnyEars;
import ca.bungo.weapons.classes.CaptainAmerica;
import ca.bungo.weapons.classes.Chainsaw;
import ca.bungo.weapons.classes.CloudBoots;
import ca.bungo.weapons.classes.Clover;
import ca.bungo.weapons.classes.CupidBow;
import ca.bungo.weapons.classes.RelentlessReaver;
import ca.bungo.weapons.classes.EMP;
import ca.bungo.weapons.classes.EggsplodingEgg;
import ca.bungo.weapons.classes.VenomSnake;
import ca.bungo.weapons.classes.ElectroMagnet;
import ca.bungo.weapons.classes.EnderBow;
import ca.bungo.weapons.classes.Excalibur;
import ca.bungo.weapons.classes.FireBall;
import ca.bungo.weapons.classes.FireWorkBow;
import ca.bungo.weapons.classes.FortuneShears;
import ca.bungo.weapons.classes.FortyFoot;
import ca.bungo.weapons.classes.GoldTurkey;
import ca.bungo.weapons.classes.HealingShears;
import ca.bungo.weapons.classes.HulkSmash;
import ca.bungo.weapons.classes.IceBlade;
import ca.bungo.weapons.classes.InvisRing;
import ca.bungo.weapons.classes.IronManSuit;
import ca.bungo.weapons.classes.LOLSword;
import ca.bungo.weapons.classes.MagicFeather;
import ca.bungo.weapons.classes.MagicSperm;
import ca.bungo.weapons.classes.MoneyBag;
import ca.bungo.weapons.classes.TrainingSword;
import ca.bungo.weapons.classes.WoefulSabre;
import ca.bungo.weapons.classes.NoobsBlade;
import ca.bungo.weapons.classes.ParkourBoots;
import ca.bungo.weapons.classes.Payday;
import ca.bungo.weapons.classes.PianoKey;
import ca.bungo.weapons.classes.PoisonBow;
import ca.bungo.weapons.classes.Prot4;
import ca.bungo.weapons.classes.Prot4AE;
import ca.bungo.weapons.classes.Prot5;
import ca.bungo.weapons.classes.Prot5AE;
import ca.bungo.weapons.classes.Prot6;
import ca.bungo.weapons.classes.Prot6AE;
import ca.bungo.weapons.classes.RabbitsFoot;
import ca.bungo.weapons.classes.RocketBoots;
import ca.bungo.weapons.classes.SnowArmor;
import ca.bungo.weapons.classes.SparringAxe;
import ca.bungo.weapons.classes.SpellBook;
import ca.bungo.weapons.classes.SpidermanBow;
import ca.bungo.weapons.classes.SunGlasses;
import ca.bungo.weapons.classes.ThorsHammer;
import ca.bungo.weapons.classes.RemorsefulBlade;
import ca.bungo.weapons.classes.WildTurkey;
import ca.bungo.weapons.classes.WitchHat;
import ca.bungo.weapons.classes.WitchsMagic;
import ca.bungo.weapons.classes.ZeusBow;

public class ItemManager
{
	private static ArrayList<BallerItem> items;
	private static ItemManager instance;
	
	public static void setup(Core core)
	{
		items = new ArrayList<>();
		items.add(new ElectroMagnet(core));
		//items.add(new IronMan());
		items.add(new Assassin8());
		items.add(new VenomSnake());
		items.add(new RemorsefulBlade());
		items.add(new MagicSperm(core));
		items.add(new ForsakenDefender());
		items.add(new WildTurkey());
		items.add(new Chainsaw());
		items.add(new EMP());
		items.add(new BlindingRod());
		items.add(new GoldTurkey());
		items.add(new IceBlade(core));
		items.add(new Excalibur());
		items.add(new NoobsBlade());
		items.add(new Prot4());
		items.add(new Prot4AE());
		items.add(new Prot5());
		items.add(new Prot5AE());
		items.add(new Prot6());
		items.add(new Prot6AE());
		items.add(new WitchHat());
		items.add(new WitchsMagic());
		items.add(new HealingShears());
		items.add(new RabbitsFoot(core));
		items.add(new SnowArmor(core));
		items.add(new AbominableSnowman(core));
		items.add(new BunnyEars(core));
		items.add(new TrainingSword());
		items.add(new WoefulSabre());
		items.add(new SparringAxe());
		items.add(new InvisRing(core));
		items.add(new Beast());
		items.add(new RelentlessReaver());
		items.add(new RocketBoots(core));
		items.add(new FortuneShears());
		items.add(new AEBow(core));
		items.add(new FortyFoot(core));
		items.add(new EnderBow(core));
		items.add(new ZeusBow(core));
		items.add(new PoisonBow(core));
		items.add(new CupidBow(core));
		items.add(new FireWorkBow(core));
		items.add(new BattleAxe());
		items.add(new ParkourBoots());
		items.add(new Clover());
		items.add(new CloudBoots());
		items.add(new BoneSword(core));
		items.add(new SunGlasses());
		items.add(new EggsplodingEgg());
		items.add(new BunnyBeater());
		items.add(new ThorsHammer(core));
		items.add(new CaptainAmerica(core));
		items.add(new IronManSuit(core));
		items.add(new HulkSmash());
		items.add(new Payday(core));
		items.add(new MagicFeather());
		items.add(new SpellBook(core));
		items.add(new PianoKey(core));
		items.add(new SpidermanBow(core));
		items.add(new MoneyBag(core));
		items.add(new LOLSword(core));
        items.add(new AngelWings());
        items.add(new FireBall());
	}
	
	public boolean isBallerItem(ItemStack itemstack, BallerItem item)
	{
		if(itemstack != null && itemstack.hasItemMeta() && itemstack.getItemMeta().hasLore() && item.getLore() != null)
		{
			for(int i = 0; i <= item.getLore().size() - 1; i++)
			{
				if(!itemstack.getItemMeta().getLore().get(i).equals(item.getLore().get(i)))
					return false;
			}
			return true;
		}
		return false;
		//return itemstack != null && itemstack.hasItemMeta() && itemstack.getItemMeta().hasLore() && item.getLore().containsAll(itemstack.getItemMeta().getLore());
	}
	
	public boolean isBallerItemName(ItemStack itemstack, BallerItem item)
	{
		return itemstack != null && itemstack.hasItemMeta() && itemstack.getItemMeta().hasDisplayName() && itemstack.getItemMeta().getDisplayName().equalsIgnoreCase(item.getName());
	}
	
	public String getBallerItemName(ItemStack item)
	{
		for(BallerItem bitem : this.getItems())
		{
			if(this.isBallerItem(item, bitem))
			{
				return bitem.getName();
			}
		}
		return null;
	}
	
	public ItemStack getItemStack(BallerItem item)
	{
		return this.getItemStack(item, 1);	
	}
	
	public ItemStack addGlow(ItemStack item)
	{ 
		  net.minecraft.server.v1_8_R3.ItemStack nmsStack = CraftItemStack.asNMSCopy(item);
		  NBTTagCompound tag = null;
		  if (!nmsStack.hasTag()) 
		  {
		      tag = new NBTTagCompound();
		      nmsStack.setTag(tag);
		  }
		  if (tag == null) tag = nmsStack.getTag();
		  NBTTagList ench = new NBTTagList();
		  tag.set("ench", ench);
		  nmsStack.setTag(tag);
		  return CraftItemStack.asCraftMirror(nmsStack);
	}

	public ItemStack getItemStack(BallerItem item, int amount)
	{
		ItemStack[] itemstack = {new ItemStack(item.getMaterial(), amount)};
		ItemMeta meta = itemstack[0].getItemMeta();
		for(Enchantment ench : item.getEnchantments().keySet())
			meta.addEnchant(ench, item.getEnchantments().get(ench), true);	
		meta.setDisplayName(item.getName());
		meta.setLore(item.getLore());
		itemstack[0].setItemMeta(meta);
		if(item.shouldGlow())
			return this.addGlow(itemstack[0]);
		else
			return itemstack[0];
	}
	
	public ItemStack getItemStackWithMeta(BallerItem item, int amount)
	{
		if(item.hasOwner())
		{
			ItemStack[] itemstack = {new ItemStack(item.getMaterial(), amount, (short)item.getMeta())};
			SkullMeta skullmeta = (SkullMeta)itemstack[0].getItemMeta();
			skullmeta.setOwner(item.getOwner());
			for(Enchantment ench : item.getEnchantments().keySet())
				skullmeta.addEnchant(ench, item.getEnchantments().get(ench), true);	
			skullmeta.setDisplayName(item.getName());
			skullmeta.setLore(item.getLore());
			itemstack[0].setItemMeta(skullmeta);
			return itemstack[0];		
		}
		
		if(item.hasMeta() && !item.hasOwner())
		{
			ItemStack[] itemstack = {new ItemStack(item.getMaterial(), amount, (short)item.getMeta())};
			ItemMeta meta = itemstack[0].getItemMeta();
			for(Enchantment ench : item.getEnchantments().keySet())
				meta.addEnchant(ench, item.getEnchantments().get(ench), true);	
			meta.setDisplayName(item.getName());
			meta.setLore(item.getLore());
			itemstack[0].setItemMeta(meta);
			return itemstack[0];
		}
		
		if(item.isLeatherArmor())
		{
			ItemStack[] itemstack = {new ItemStack(item.getMaterial(), amount)};
			LeatherArmorMeta meta = (LeatherArmorMeta) itemstack[0].getItemMeta();
			for(Enchantment ench : item.getEnchantments().keySet())
				meta.addEnchant(ench, item.getEnchantments().get(ench), true);	
			meta.setDisplayName(item.getName());
			meta.setLore(item.getLore());
			meta.setColor(Color.WHITE);
			itemstack[0].setItemMeta(meta);
			return itemstack[0];
		}
		return null;
	}
	
	public ItemStack[] getItemStacks(BallerItem item, int amount)
	{
		if(item.getMaterials() == null)
			return new ItemStack[]{getItemStack(item)};
		ItemStack[] stacks = new ItemStack[item.getMaterials().length];
		for(int i = 0; i < item.getMaterials().length; i++)
		{
			ItemStack itemstack = new ItemStack(item.getMaterials()[i], amount);
			ItemMeta meta = itemstack.getItemMeta();
			for(Enchantment ench : item.getEnchantments().keySet())
				meta.addEnchant(ench, item.getEnchantments().get(ench), true);	
			meta.setDisplayName(item.getName());
			meta.setLore(item.getLore());
			itemstack.setItemMeta(meta);
			stacks[i] = itemstack;
		}
		return stacks;
	}
	
	public void giveItem(Player player, BallerItem item, int amount)
	{
		if(item.hasOwner())
			player.getInventory().addItem(this.getItemStackWithMeta(item, amount));
		else if(item.hasMeta() && !item.hasOwner())
			player.getInventory().addItem(this.getItemStackWithMeta(item, amount));
		else if(item.hasMultipleMaterials())
			player.getInventory().addItem(this.getItemStacks(item, amount));
		else if(item.isLeatherArmor())
			player.getInventory().addItem(this.getItemStackWithMeta(item, amount));
		else
			player.getInventory().addItem(this.getItemStack(item, amount));
		player.updateInventory();
	}
	
	public void giveItem(Player player, BallerItem item)
	{
		giveItem(player, item, 1);
	}
	
	public BallerItem getItemByName(String name)
	{
		for(BallerItem item : items)
		{
			if(item.getName().equalsIgnoreCase(name))
				return item;
			else if(ChatColor.stripColor(item.getName()).equalsIgnoreCase(name))
				return item;
			else
			{
				for(String alias : item.getAliases())
				{
					if(alias.equalsIgnoreCase(name))
						return item;
				}	
			}
		}
		return null;
	}
	
	public boolean isBallerItem(String s)
	{
		for(BallerItem item : items)
		{
			if(item.getName().equalsIgnoreCase(s))
				return true;
			
			for(String alias : item.getAliases())
			{
				if(alias.equalsIgnoreCase(s))
					return true;
			}
		}
		return false;
	}
	
	public ItemStack getItemStack(BallerItem item, Material material)
	{
		if(item.hasMultipleMaterials())
		{
			if(item.equals(new Prot4()))
			{
				return ((Prot4)item).getItemStack(material);
			}
			else if(item.equals(new Prot4AE()))
			{
				return ((Prot4AE)item).getItemStack(material);
			}
			else if(item.equals(new Prot5()))
			{
				return ((Prot5)item).getItemStack(material);
			}
			else if(item.equals(new Prot5AE()))
			{
				return ((Prot5AE)item).getItemStack(material);
			}
			else if(item.equals(new Prot6()))
			{
				return ((Prot6)item).getItemStack(material);
			}
			else if(item.equals(new Prot6AE()))
			{
				return ((Prot6AE)item).getItemStack(material);
			}
		}
		return null;
	}
	
	public ArrayList<BallerItem> getItems()
	{
		return items;
	}
	
	public void registerItems(Core core)
	{
		for(BallerItem item : this.getItems())
		{
			core.getServer().getPluginManager().registerEvents(item, core);
		}
	}
	
	public static ItemManager getInstance()
	{
		if(instance == null)
			instance = new ItemManager();
		return instance;	
	}
}
