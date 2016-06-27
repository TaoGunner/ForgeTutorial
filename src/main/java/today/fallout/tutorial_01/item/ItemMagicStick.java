package today.fallout.tutorial_01.item;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import today.fallout.tutorial_01.common.CommonRegistryHandler;

public class ItemMagicStick extends Item
{
	public ItemMagicStick()
	{
		this.setMaxStackSize(1);
		this.setMaxDamage(100);
		this.setCreativeTab(CommonRegistryHandler.tabTutorial);
		this.setRegistryName("item_magic_stick");
		this.setUnlocalizedName(this.getRegistryName().toString());
		GameRegistry.register(this);
		if (FMLCommonHandler.instance().getSide() == Side.CLIENT)
		{
			ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(this.getRegistryName().toString()));
		}
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand)
	{
		if (!playerIn.capabilities.isCreativeMode) { itemStackIn.damageItem(5, playerIn); }
		worldIn.playSound((EntityPlayer)null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

		if (!worldIn.isRemote)
		{
			Vec3d vec3d = playerIn.getLook(1.0F);
			double d2 = playerIn.getLookVec().xCoord * 10.0D;
			double d3 = playerIn.getLookVec().yCoord * 10.0D;
			double d4 = playerIn.getLookVec().zCoord * 10.0D;

			EntityLargeFireball entitylargefireball = new EntityLargeFireball(worldIn, playerIn, d2, d3, d4);
			entitylargefireball.explosionPower = 1;
			entitylargefireball.posX = playerIn.posX + vec3d.xCoord * 2.5D;
			entitylargefireball.posY = playerIn.posY + vec3d.yCoord * 2.0D;
			entitylargefireball.posZ = playerIn.posZ + vec3d.zCoord * 2.5D;
			worldIn.spawnEntityInWorld(entitylargefireball);
		}

		return new ActionResult(EnumActionResult.SUCCESS, itemStackIn);
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
	{
		if (target instanceof EntityMob & attacker instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) attacker;
			World world = player.getEntityWorld();

			if (!player.capabilities.isCreativeMode) { stack.damageItem(10, player); }
			world.playSound((EntityPlayer)null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

			if (!world.isRemote)
			{
				EntityPig pig = new EntityPig(world);
				pig.setPositionAndRotation(target.posX, target.posY ,target.posZ, target.rotationPitch, target.rotationYaw);
				target.setDead();
				world.spawnEntityInWorld(pig);
				pig.playLivingSound();
			}
		}
		return true;
	}
}
