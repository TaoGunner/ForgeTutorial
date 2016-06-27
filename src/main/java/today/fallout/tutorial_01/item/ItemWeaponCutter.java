package today.fallout.tutorial_01.item;

import com.google.common.collect.Multimap;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import today.fallout.tutorial_01.common.CommonRegistryHandler;

/*
	Это класс бокорезов - оружия и инструмента в одном предмете.
	Этот предмет имеет повышеный урон по живым существам (4,5).
	Более того, он позволяет быстро добывать прутья металлической решетки.
*/
public class ItemWeaponCutter extends Item
{
	private final float attackDamage;

	public ItemWeaponCutter()
	{
		this.setMaxStackSize(1);
		this.setMaxDamage(50);
		this.attackDamage = 3.5f;
		this.setCreativeTab(CommonRegistryHandler.tabTutorial);
		this.setRegistryName("item_weapon_cutter");
		this.setUnlocalizedName(this.getRegistryName().toString());
		GameRegistry.register(this);
		if (FMLCommonHandler.instance().getSide() == Side.CLIENT)
		{
			ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(this.getRegistryName().toString()));
		}
	}

	/*
		В этом методе добавляются 2 аттрибута для предмета:
		1. ATTACK_DAMAGE - урон по живым объектам
		2. ATTACK_SPEED - скорость атаки (добавлено в 1.10)
	 */
	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack)
	{
		Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(slot, stack);

		if (slot == EntityEquipmentSlot.MAINHAND)
		{
			multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getAttributeUnlocalizedName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", (double)this.attackDamage, 0));
			multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getAttributeUnlocalizedName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", -2.4000000953674316D, 0));
		}
		return multimap;
	}

	/*
		Этот метод отвечает за событие удара по живому объекту.
		Уменьшаем прочность предмета на 1 и возвращаем true для потверждения удара
	 */
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
	{
		stack.damageItem(1, attacker);
		return true;
	}

	/*
		Метод определяет, может ли предмет добывать тот или иной блок.
		Если блок является металлическими прутьями - то возвращается true
	 */
	@Override
	public boolean canHarvestBlock(IBlockState blockIn)
	{
		return blockIn.getBlock() == Blocks.IRON_BARS;
	}

	/*
		Метод определяет скорость добычи блоков.
		Если блок является металлическими прутьями - то возвращаем силу 20.0F
		В остальных случаях - 1.0F
	 */
	@Override
	public float getStrVsBlock(ItemStack stack, IBlockState state)
	{
		if (state.getBlock() == Blocks.IRON_BARS) { return 20.0F; }
		return 1.0F;
	}

	/*
		Метод определяет то, что происходит при уничтожении блока этим предметом.
		Если мы уничтожили блок прутьев - то повреждаем предмет на 1.
		В остальных случаях - повреждаем предмет на 2.
		Возвращаем true для подтверждения разрушения.
	 */
	public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving)
	{
		if (state.getBlock() == Blocks.IRON_BARS) { stack.damageItem(1, entityLiving); }
		else { stack.damageItem(2, entityLiving); }
		return true;
	}

	/*
		Этот клиентский метод отвечает за отрисовку предмета.
		При возвращении true - предмет будет отображаться более объемным (меч, удочка и т.п.)
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public boolean isFull3D()
	{
		return true;
	}
}
