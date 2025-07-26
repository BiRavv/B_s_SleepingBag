package biraw.online.b_s_SleepingBag.Listeners;

import biraw.online.b_s_SleepingBag.B_s_SleepingBag;
import biraw.online.b_s_SleepingBag.Utils.BedUtils;
import org.bukkit.block.TileState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedLeaveEvent;

import java.util.logging.Level;

public class GetUpListener implements Listener {
    @EventHandler
    private void GetsUp(PlayerBedLeaveEvent e){
        if (!((TileState) e.getBed().getState()).getPersistentDataContainer().has(B_s_SleepingBag.bedKey)) return;
        BedUtils.removeWholeBed(e.getBed());
    }
}


