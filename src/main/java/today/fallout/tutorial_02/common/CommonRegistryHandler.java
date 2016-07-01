package today.fallout.tutorial_02.common;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import today.fallout.tutorial_02.TutorialMod;
import today.fallout.tutorial_02.block.BlockSided;
import today.fallout.tutorial_02.block.BlockSimple;

public class CommonRegistryHandler
{
	public static final CreativeTabs tabTutorial = new CreativeTabs(TutorialMod.MODID + "." + "tabTutorial02")
	{
		@Override
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem() { return Items.COOKIE; }
	};

	public static Block block_simple;
	public static Block block_sided;

	public static void registerItems()
	{
		block_simple = new BlockSimple();
		block_sided = new BlockSided();
	}
}
