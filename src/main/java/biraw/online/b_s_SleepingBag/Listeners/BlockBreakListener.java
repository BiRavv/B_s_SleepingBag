package biraw.online.b_s_SleepingBag.Listeners;

import biraw.online.b_s_SleepingBag.B_s_SleepingBag;
import org.bukkit.block.TileState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {
    @EventHandler
    private void BlockBreakEvent(BlockBreakEvent e){
        if (!((TileState) e.getBlock().getState()).getPersistentDataContainer().has(B_s_SleepingBag.bedKey)) return;
        e.setCancelled(true);
        e.getPlayer().sendMessage("§cYou are not breaking that!");
    }
}
