package me.xiaozhangup.mooncube.island;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.PermissionType;
import com.iridium.iridiumskyblock.database.Island;
import com.iridium.iridiumskyblock.database.User;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.xenondevs.nova.api.protection.ProtectionIntegration;
import xyz.xenondevs.nova.api.tileentity.TileEntity;

import java.util.Optional;

public class NovaHook implements ProtectionIntegration {

    public static final IridiumSkyblock INSTANCE = IridiumSkyblock.getInstance();

    @Override
    public boolean canBreak(@NotNull OfflinePlayer offlinePlayer, @Nullable ItemStack itemStack, @NotNull Location location) {
        return checkIridiumSkyblockPlayer(offlinePlayer, location, PermissionType.BLOCK_BREAK);
    }

    @Override
    public boolean canHurtEntity(@NotNull OfflinePlayer offlinePlayer, @NotNull Entity entity, @Nullable ItemStack itemStack) {
        return checkIridiumSkyblockPlayer(offlinePlayer, entity.getLocation(), PermissionType.INTERACT_ENTITIES);
    }

    @Override
    public boolean canInteractWithEntity(@NotNull OfflinePlayer offlinePlayer, @NotNull Entity entity, @Nullable ItemStack itemStack) {
        return checkIridiumSkyblockPlayer(offlinePlayer, entity.getLocation(), PermissionType.INTERACT_ENTITIES);
    }

    @Override
    public boolean canPlace(@NotNull OfflinePlayer offlinePlayer, @NotNull ItemStack itemStack, @NotNull Location location) {
        return checkIridiumSkyblockPlayer(offlinePlayer, location, PermissionType.BLOCK_PLACE);
    }

    @Override
    public boolean canUseBlock(@NotNull OfflinePlayer offlinePlayer, @Nullable ItemStack itemStack, @NotNull Location location) {
        return checkIridiumSkyblockPlayer(offlinePlayer, location, PermissionType.OPEN_CONTAINERS);
    }

    @Override
    public boolean canUseItem(@NotNull OfflinePlayer offlinePlayer, @NotNull ItemStack itemStack, @NotNull Location location) {
        return checkIridiumSkyblockPlayer(offlinePlayer, location, PermissionType.INTERACT);
    }

    @NotNull
    @Override
    public ExecutionMode getExecutionMode() {
        return ExecutionMode.NONE;
    }

    @Override
    public boolean canBreak(@NotNull TileEntity tileEntity, @Nullable ItemStack itemStack, @NotNull Location location) {
        return canBreak(tileEntity.getOwner(), itemStack, location);
    }

    @Override
    public boolean canHurtEntity(@NotNull TileEntity tileEntity, @NotNull Entity entity, @Nullable ItemStack itemStack) {
        return canHurtEntity(tileEntity.getOwner(), entity, itemStack);
    }

    @Override
    public boolean canInteractWithEntity(@NotNull TileEntity tileEntity, @NotNull Entity entity, @Nullable ItemStack itemStack) {
        return canInteractWithEntity(tileEntity.getOwner(), entity, itemStack);
    }

    @Override
    public boolean canPlace(@NotNull TileEntity tileEntity, @NotNull ItemStack itemStack, @NotNull Location location) {
        return canPlace(tileEntity.getOwner(), itemStack, location);
    }

    @Override
    public boolean canUseBlock(@NotNull TileEntity tileEntity, @Nullable ItemStack itemStack, @NotNull Location location) {
        return canUseBlock(tileEntity.getOwner(), itemStack, location);
    }

    @Override
    public boolean canUseItem(@NotNull TileEntity tileEntity, @NotNull ItemStack itemStack, @NotNull Location location) {
        return canUseItem(tileEntity.getOwner(), itemStack, location);
    }

    private boolean checkIridiumSkyblockPlayer(OfflinePlayer player, Location location,  PermissionType permissionType) {
        User user = INSTANCE.getUserManager().getUser(player);
        Optional<Island> island = IridiumSkyblock.getInstance().getIslandManager().getIslandViaLocation(location);
        if (island.isEmpty())
            return true;
        return IridiumSkyblock.getInstance().getIslandManager().getIslandPermission(island.get(), user, permissionType);
    }
}
