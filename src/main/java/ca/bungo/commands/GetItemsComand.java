package ca.bungo.commands;

import ca.bungo.core.Core;
import ca.bungo.weapons.BallerItem;
import ca.bungo.weapons.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class GetItemsComand implements CommandExecutor, Listener {

    Inventory[] itemStorage = {Bukkit.createInventory(null, 54, ChatColor.GREEN + "Magik Weapons"), Bukkit.createInventory(null, 54, ChatColor.GREEN + "Magik Weapons")};

    ItemStack forward = new ItemStack(Material.PAPER);
    ItemStack backward = new ItemStack(Material.PAPER);

    Core core;

    public GetItemsComand(Core core){
        this.core = core;
        ItemManager itemManager = ItemManager.getInstance(); //I really hate this
        //The code was stroke inducing ;-;
        ArrayList<BallerItem> items = itemManager.getItems();

        ItemMeta forwardMeta = forward.getItemMeta();
        ItemMeta backwardMeta = backward.getItemMeta();

        forwardMeta.setDisplayName(ChatColor.AQUA + "Next Page");
        backwardMeta.setDisplayName(ChatColor.AQUA + "Previous Page");

        forward.setItemMeta(forwardMeta);
        backward.setItemMeta(backwardMeta);

        ArrayList<ItemStack> itemStacks = new ArrayList<>();
        for (BallerItem item : items){
            ItemStack[] itemTypes = itemManager.getItemStacks(item, 1);
            for(int i = 0; i < itemTypes.length; i++)
                itemStacks.add(itemTypes[i]);
        }

        itemStorage[0].setItem(49, forward);
        itemStorage[1].setItem(49, backward);

        int i = 0;
        for(ItemStack item : itemStacks){
            if(i >= 45){
                itemStorage[1].addItem(item);
            }else{
                itemStorage[0].addItem(item);
            }
            i++;
        }
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player)) return true;
        if(commandSender.hasPermission("weaponsCore.admin") || commandSender.isOp()){
            ((Player) commandSender).openInventory(itemStorage[0]);
        }
        return true;
    }


    @EventHandler
    public void onClick(InventoryInteractEvent event){
        if(event.getView().getTitle().equals(ChatColor.GREEN + "Magik Weapons")){
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onClick2(InventoryClickEvent event){
        if(event.getClickedInventory() == null)
            return;
        if(event.getView().getTitle().equals(ChatColor.GREEN + "Magik Weapons")){
            event.setCancelled(true);
            Player player = (Player)event.getWhoClicked();
            ItemStack item = event.getCurrentItem();
            if(item != null && item.getType() != Material.AIR){
                if(item.getItemMeta() != null){
                    if(item.getItemMeta().equals(forward.getItemMeta())){
                        player.closeInventory();
                        player.openInventory(itemStorage[1]);
                        return;
                    }else if(item.getItemMeta().equals(backward.getItemMeta())){
                        player.closeInventory();
                        player.openInventory(itemStorage[0]);
                        return;
                    }
                }
                player.getInventory().addItem(item);
                player.closeInventory();
                player.sendMessage(ChatColor.YELLOW + "Added custom weapon to inventory!");
            }
            return;
        }
    }

}
