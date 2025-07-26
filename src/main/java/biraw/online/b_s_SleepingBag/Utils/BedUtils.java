package biraw.online.b_s_SleepingBag.Utils;

import biraw.online.b_s_SleepingBag.B_s_SleepingBag;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.TileState;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Bed;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

import java.util.Arrays;
import java.util.List;

public class BedUtils {

    public static List<Material> allBeds = Arrays.asList(
            Material.BLACK_BED,
            Material.BLUE_BED,
            Material.BROWN_BED,
            Material.CYAN_BED,
            Material.GRAY_BED,
            Material.GREEN_BED,
            Material.LIGHT_BLUE_BED,
            Material.LIGHT_GRAY_BED,
            Material.LIME_BED,
            Material.MAGENTA_BED,
            Material.ORANGE_BED,
            Material.PINK_BED,
            Material.PURPLE_BED,
            Material.RED_BED,
            Material.WHITE_BED,
            Material.YELLOW_BED
    );

    public static void removeWholeBed(Block bedBlock) {
        if (!allBeds.contains(bedBlock.getType())) return;

        BlockData data = bedBlock.getBlockData();
        if (!(data instanceof Bed)) return;

        Bed bedData = (Bed) data;
        BlockFace facing = bedData.getFacing();

        Block foot, head;

        if (bedData.getPart() == Bed.Part.FOOT) {
            foot = bedBlock;
            head = bedBlock.getRelative(facing);
        } else {
            head = bedBlock;
            foot = bedBlock.getRelative(facing.getOppositeFace());
        }

        foot.setType(Material.AIR,false);
        head.setType(Material.AIR,false);
    }

    public static Location PlaceBed(Player plr, Material bed) {
        if (!allBeds.contains(bed)) throw new RuntimeException("This is not a bed: "+bed.toString());

        Location head = plr.getLocation();
        Location foot = plr.getLocation().clone().add(plr.getFacing().getDirection());

        Block headBlock = plr.getWorld().getBlockAt(head);
        Block footBlock = plr.getWorld().getBlockAt(foot);

        Bed footData = (Bed) Bukkit.createBlockData(bed);
        footData.setPart(Bed.Part.FOOT);
        footData.setFacing(plr.getFacing().getOppositeFace());
        footBlock.setBlockData(footData);

        Bed headData = (Bed) Bukkit.createBlockData(bed);
        headData.setPart(Bed.Part.HEAD);
        headData.setFacing(plr.getFacing().getOppositeFace());
        headBlock.setBlockData(headData);

        TileState footState = (TileState) footBlock.getState();
        footState.getPersistentDataContainer().set(B_s_SleepingBag.bedKey, PersistentDataType.BOOLEAN,true);
        TileState headState = (TileState) headBlock.getState();
        headState.getPersistentDataContainer().set(B_s_SleepingBag.bedKey, PersistentDataType.BOOLEAN,true);

        footState.update();
        headState.update();

        return head;
    }
}
