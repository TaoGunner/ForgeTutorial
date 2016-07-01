package today.fallout.tutorial_02.common;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import today.fallout.tutorial_02.TutorialMod;
import today.fallout.tutorial_02.block.BlockRadio;
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
	public static Block block_radio;

	public static void registerItems()
	{
		block_simple = new BlockSimple();
		block_sided = new BlockSided();
		block_radio = new BlockRadio();
	}

	// Всё, что ниже - не проверено.
	public static SoundEvent block_radio_sound;

	public static void registerSounds()
	{
		block_radio_sound = registerSound("music.block_radio_sound");
	}

	private static SoundEvent registerSound(String soundName)
	{
		final ResourceLocation soundID = new ResourceLocation(TutorialMod.MODID, soundName);
		return GameRegistry.register(new SoundEvent(soundID).setRegistryName(soundID));
	}
}
