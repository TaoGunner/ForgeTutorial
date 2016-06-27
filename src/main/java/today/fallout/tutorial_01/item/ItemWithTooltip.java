package today.fallout.tutorial_01.item;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import today.fallout.tutorial_01.common.CommonRegistryHandler;

import java.util.List;

/*
	Это класс предмета отличается от ItemSimple тем, что имеет описание в игре, причем:
	1. Первая строка "захардкожена" - вписана в код класса и не меняется.
	2. Вторая строка - берется из файлов локализации ( ./assets/lang/наименование_языка.lang)
	Замечение: метод addInformation - требуется только для клиента игры. На это указывает аннотация @SideOnly(Side.CLIENT)
*/
public class ItemWithTooltip extends Item
{
	public ItemWithTooltip()
	{
		this.setMaxStackSize(32);
		this.setCreativeTab(CommonRegistryHandler.tabTutorial);
		this.setRegistryName("item_with_tooltip");
		this.setUnlocalizedName(this.getRegistryName().toString());
		GameRegistry.register(this);
		if (FMLCommonHandler.instance().getSide() == Side.CLIENT)
		{
			ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(this.getRegistryName().toString()));
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
	{
		tooltip.add("Hey! That item has tooltip!");
		tooltip.add(I18n.format("tooltip." + this.getUnlocalizedName().substring(5) + ".text"));
	}
}
