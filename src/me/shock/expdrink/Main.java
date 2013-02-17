package me.shock.expdrink;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
	
	public Main plugin;
	
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getPlayer().getItemInHand().getAmount() == 1) {
			int id = event.getPlayer().getItemInHand().getTypeId();
			if(id == 374 && event.getPlayer().getItemInHand().getDurability() == 0) {
				Player player = event.getPlayer();
				if(player.hasPermission("expdrink.drink")) {
					float exp = player.getExp();
					ItemStack bottle = player.getItemInHand();
					ItemMeta meta = bottle.getItemMeta();
					ArrayList<String> lore = new ArrayList<String>();
					if(!meta.hasLore()) {
						lore.add(ChatColor.AQUA + "Exp: " + exp);
						meta.setLore(lore);
						bottle.setItemMeta(meta);
						player.setExp(0);
						player.sendMessage(ChatColor.GREEN + "Successfully put " + exp + " levels on that bottle!");
						return;
					} else //if(lore.contains("Exp")) 
						{
						String stringLore = lore.toString();
						String afterExp = stringLore.split("Exp: ")[0];
						int poop = checkNumber(afterExp);
						float newexp = poop + exp;
						lore.set(0, ChatColor.AQUA + "Exp: " + (newexp));
						meta.setLore(lore);
						player.sendMessage(ChatColor.GREEN + "Successfully put " + newexp + " levels on that bottle!");
						return;
						}
					}
				}
			}
		}
	
	private Integer checkNumber(String s) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < s.length(); i++) {
			Character car = s.charAt(i);
			if(Character.isDigit(i)) {
				sb.append(car);
			}
		}
		System.out.println("sb: " + sb);
		return Integer.parseInt(sb.toString());
	}
}
