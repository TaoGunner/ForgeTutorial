package today.fallout.tutorial_01.common;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import today.fallout.tutorial_01.TutorialMod;
import today.fallout.tutorial_01.item.ItemMagicStick;
import today.fallout.tutorial_01.item.ItemSimple;
import today.fallout.tutorial_01.item.ItemWeaponCutter;
import today.fallout.tutorial_01.item.ItemWithTooltip;

/*
	Этот класс отвечает за хранение и регистрацию предметов. Здесь есть:
	1. Вкладка для креатива tabTutorial, в которой будут лежать предметы из примера
	2. Определение предметов (item_simple, item_with_tooltip и .т.д.)
	3. Метод registerItems(), который инициализирует предметы.
*/
public class CommonRegistryHandler
{
	public static final CreativeTabs tabTutorial = new CreativeTabs(TutorialMod.MODID + "." + "tabTutorial01")
	{
		@Override
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem() { return Items.COOKIE; }
	};

	public static Item item_simple;
	public static Item item_with_tooltip;
	public static Item item_weapon_cutter;
	public static Item item_magic_stick;

	public static void registerItems()
	{
		item_simple = new ItemSimple();
		item_with_tooltip = new ItemWithTooltip();
		item_weapon_cutter = new ItemWeaponCutter();
		item_magic_stick = new ItemMagicStick();
	}
}
