package biraw.online.b_s_SleepingBag.Utils;

import biraw.online.b_s_SleepingBag.B_s_SleepingBag;
import biraw.online.b_s_SleepingBag.Enum.CanSleep;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;

public class SleepUtils {

    public static CanSleep ValidateLoc(Player plr){
        if (plr.getWorld().isDayTime()) return CanSleep.BADTIME;
        if (areMonstersNearby(plr)) return CanSleep.MONSTERS;

        Location head = plr.getLocation();
        Location foot = plr.getLocation().clone().add(plr.getFacing().getDirection());

        Block headBlock = plr.getWorld().getBlockAt(head);
        Block footBlock = plr.getWorld().getBlockAt(foot);
        if (headBlock.getType() != Material.AIR || footBlock.getType() != Material.AIR) return CanSleep.NOSPACE;

        Block belowHeadBlock = plr.getWorld().getBlockAt(head.clone().subtract(0,1,0));
        Block belowFootBlock = plr.getWorld().getBlockAt(foot.clone().subtract(0,1,0));
        if (!belowFootBlock.isSolid() || !belowHeadBlock.isSolid()) return CanSleep.NOTSOLID;

        return CanSleep.CAN;
    }
    private static boolean areMonstersNearby(Player player) {
        return player.getNearbyEntities(8, 8, 8).stream()
                .anyMatch(entity -> entity instanceof Monster);
    }

    public static void Sleep(Player plr, Location bed){
        Location plrSpawn = plr.getRespawnLocation();
        plr.sleep(bed,true);
        Bukkit.getScheduler().runTaskLater(B_s_SleepingBag.getInstance(),()->plr.setRespawnLocation(plrSpawn,true),1);
    }
}
