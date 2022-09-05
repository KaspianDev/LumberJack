package de.jeff_media.lumberjack.listeners;

import de.jeff_media.lumberjack.LumberJack;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.inventory.ItemStack;

import static org.bukkit.event.EventPriority.HIGHEST;

public class BlockChangeListener implements Listener {

    final LumberJack plugin;

    public BlockChangeListener(LumberJack plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = HIGHEST)
    public void onBlockChange(EntityChangeBlockEvent event) {
        if (event.getEntityType() != EntityType.FALLING_BLOCK
                    || plugin.getConfig().getBoolean("drop-all-logs")) {
            return;
        }

        FallingBlock block = (FallingBlock) event.getBlock();

        if (block.hasMetadata("lumberShouldDrop")) {
            ItemStack drop = new ItemStack(block.getBlockData().getMaterial());

            event.setCancelled(true);
            block.remove();
            block.getWorld().dropItemNaturally(block.getLocation(), drop);

        }
    }

}
