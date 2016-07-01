package today.fallout.tutorial_02.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import today.fallout.tutorial_02.common.CommonRegistryHandler;

public class BlockSimple extends Block
{
	public BlockSimple()
	{
		super(Material.IRON);
		this.setCreativeTab(CommonRegistryHandler.tabTutorial);
		this.setRegistryName("block_simple");
		GameRegistry.register(this);
		this.setUnlocalizedName(this.getRegistryName().toString());
		GameRegistry.register(new ItemBlock(this).setRegistryName(this.getRegistryName()));
		Item item = Item.getItemFromBlock(this);
		item.setMaxStackSize(64);
		if (FMLCommonHandler.instance().getSide() == Side.CLIENT)
		{
			ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName().toString()));
		}
	}
}
