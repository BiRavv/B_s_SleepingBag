package biraw.online.b_s_SleepingBag.Listeners;
import biraw.online.b_s_SleepingBag.Enum.CanSleep;
import biraw.online.b_s_SleepingBag.Utils.BedUtils;
import biraw.online.b_s_SleepingBag.Utils.SleepUtils;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SleepListener implements Listener {

    @EventHandler
    private void InteractListener(PlayerInteractEvent e){
        if (e.getAction() != Action.RIGHT_CLICK_AIR) return;
        if (!BedUtils.allBeds.contains(e.getPlayer().getInventory().getItemInMainHand().getType())) return;

        CanSleep validation = SleepUtils.ValidateLoc(e.getPlayer());

        switch (validation)
        {
            case NOSPACE -> e.getPlayer().sendMessage("§cNot enough space!");
            case BADTIME -> e.getPlayer().sendMessage("§cNot the right time to sleep!");
            case MONSTERS -> e.getPlayer().sendMessage("§cMonsters nearby!");
            case NOTSOLID -> e.getPlayer().sendMessage("§cThe ground is not stable!");
            case CAN -> e.getPlayer().sendMessage("§7Your respawn was not set. Sleep well!");
        }
        if (validation != CanSleep.CAN) return;

        SleepUtils.Sleep(e.getPlayer(), BedUtils.PlaceBed(e.getPlayer(),e.getMaterial()));
    }
}
