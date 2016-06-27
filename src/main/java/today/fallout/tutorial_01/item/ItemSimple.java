package today.fallout.tutorial_01.item;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import today.fallout.tutorial_01.common.CommonRegistryHandler;

/*
	Это класс "простого" предмета. Здесь есть только его коструктор, в котором:
	1. Устанавливается размер стака, равный 16.
	2. Устанавливается креативная вкладка для этого предмета.
	3. Устанавливается имя для регистрации в игре (обязательно!).
	4. Устанавливается имя предмета для локализации.
	5. Предмет регистрируется с помощью GameRegistry.register(this).
	6. Для клиентской части - указывается расположения файлов ресурсов (*.json) для этого предмета
*/
public class ItemSimple extends Item
{
	public ItemSimple()
	{
		this.setMaxStackSize(16);
		this.setCreativeTab(CommonRegistryHandler.tabTutorial);
		this.setRegistryName("item_simple");
		this.setUnlocalizedName(this.getRegistryName().toString());
		GameRegistry.register(this);
		if (FMLCommonHandler.instance().getSide() == Side.CLIENT)
		{
			ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(this.getRegistryName().toString()));
		}
	}
}
