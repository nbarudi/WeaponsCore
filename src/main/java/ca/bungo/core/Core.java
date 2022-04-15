package ca.bungo.core;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;

import ca.bungo.commands.GetItemsComand;
import co.aikar.commands.ACF;
import co.aikar.commands.CommandManager;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import ca.bungo.utils.AntiSpamBot;
import ca.bungo.weapons.ItemManager;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import com.sk89q.worldguard.protection.managers.RegionManager;

public class Core extends JavaPlugin implements Listener
{
	private static Core instance;
	
	private CommandManager commandManager;
//	private NewChat newChat = null;

	private static Economy econ = null;
	
	@Override
	public void onEnable()
	{
	    instance = this;

	    //Commands
	    commandManager = ACF.createManager(this);

        //Old Stuff
		ItemManager.setup(this);
		ItemManager.getInstance().registerItems(this);
		getConfig().options().copyDefaults(true);
		saveDefaultConfig();
		reloadConfig();

		this.getServer().getPluginManager().registerEvents(this, this);
		registerCommands();
	}

    @Override
    public void onDisable()
    {
        ItemManager.getInstance().getItems().clear();
    }

    private void registerCommands() {
        GetItemsComand gic = new GetItemsComand(this);
		this.getCommand("getitems").setExecutor(gic);
        Bukkit.getPluginManager().registerEvents(gic, this);
    }

	private boolean setupEconomy()
	{
		if (getServer().getPluginManager().getPlugin("Vault") == null)
		{
			return false;
		}
		RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
		if (rsp == null)
		{
			return false;
		}
		econ = rsp.getProvider();
		return econ != null;
	}

	public static Economy getEconomy()
	{
		return econ;
	}

	public static Core getInstance() {
		return instance;
	}

	private static WorldGuardPlugin getWorldGuard() {
		final Plugin plugin = Bukkit.getServer().getPluginManager()
				.getPlugin("WorldGuard");
		if (plugin == null || !(plugin instanceof WorldGuardPlugin)) {
			return null;
		}

		return (WorldGuardPlugin) plugin;
	}

	public static boolean isPlayerInPVP(Player player){
		final RegionManager regionManager = getWorldGuard().getRegionManager(player.getLocation().getWorld());
		final ApplicableRegionSet set = regionManager.getApplicableRegions(player.getLocation());
		final LocalPlayer localPlayer = getWorldGuard().wrapPlayer(player);
		return set.testState(localPlayer, DefaultFlag.PVP);
	}
	
	public static boolean isEntityInPVP(Entity entity){
		final RegionManager regionManager = getWorldGuard().getRegionManager(entity.getLocation().getWorld());
		final ApplicableRegionSet set = regionManager.getApplicableRegions(entity.getLocation());
		return set.testState(null, DefaultFlag.PVP);
	}

	public static boolean canBuildHere(Player player, Location location) {
		if (location == null) {
			return true;
		}

		final WorldGuardPlugin wg = getWorldGuard();
		return wg == null || wg.canBuild(player, location);
	}

	public static boolean canBuildHere(Player player, Block block) {
		if (block == null) {
			return true;
		}

		final WorldGuardPlugin wg = getWorldGuard();
		return wg == null || wg.canBuild(player, block);
	}

	public static boolean safeSetBlock(Player player, Block block, Material type) {
		if (!canBuildHere(player, block)) {
			return false;
		}

		block.setType(type);

		return true;
	}

	public FileConfiguration pvpFile()
	{
		File playerDir = new File(getDataFolder() + File.separator + "pvpchest.yml");
		return YamlConfiguration.loadConfiguration(playerDir);
	}

	public File pvpData()
	{
		return new File(getDataFolder() + File.separator + "pvpchest.yml");
	}

	public FileConfiguration playerData(UUID uuid)
	{
		return YamlConfiguration.loadConfiguration(playerFile(uuid));
	}

	public FileConfiguration playerData(OfflinePlayer player)
	{
		return playerData(player.getUniqueId());
	}

	public File playerFile(UUID uuid) {
		return new File(getDataFolder() + File.separator + "userdata" + File.separator + uuid.toString() + ".yml");
	}

    public File playerFile(Player player) {
        return new File(getDataFolder() + File.separator + "userdata" + File.separator + player.getUniqueId().toString() + ".yml");
    }
	
	private String mainDirectory;
	private File customConfigFile = null;
	private FileConfiguration customConfig = null;

	public void reloadCustomConfig() {
		mainDirectory = this.getDataFolder().getPath();
		customConfigFile = new File(String.valueOf(mainDirectory) + "/MoneyBagConfig.yml");
		new File(mainDirectory).mkdir();
		if (!customConfigFile.exists()) 
		{
			try 
			{
				customConfigFile.createNewFile();
			}
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			customConfig = YamlConfiguration.loadConfiguration(customConfigFile);
			customConfig.addDefault("Main.Items.example.Percentage", "100");
			customConfig.addDefault("Main.Items.example.Name", "Example Name");
			customConfig.createSection("Main.Items.example.Commands");
			List<String> commands = getCustomConfig().getStringList("Main.Items.example.Commands");
			commands.add("/EXAMPLECOMMAND {player}");
			commands.add("/EXAMPLECOMMAND {player}");
			customConfig.set("Main.Items.example.Commands", commands);
			customConfig.addDefault("Main.Broadcast Message", "&a{player} just looked in their Money Bag and received a random prize. A {item}! Money Bags are available for top donators, if you think you have what it takes visit buy.lolpvp.com and work your way up to the top spot now!");
			customConfig.options().copyDefaults(true);
			try {
				customConfig.save(customConfigFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		customConfig = YamlConfiguration.loadConfiguration(customConfigFile);
	}
	public FileConfiguration getCustomConfig() {
		if (customConfig == null) 
		{
			reloadCustomConfig();
		}
		return customConfig;
	}
	public void saveCustomConfig() {
		if (customConfig == null || customConfigFile == null) {
			return;
		}
		try 
		{
			getCustomConfig().save(customConfigFile);
		} 
		catch (IOException ex) 
		{
			getLogger().log(Level.SEVERE, "Could not save config to " + customConfigFile, ex);
		}
	}
}