package today.fallout.tutorial_01;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import today.fallout.tutorial_01.common.CommonProxy;

/*
	Это основной класс мода. В нем необходимо сделать следующие вещи:
	1. [ @Mod ] Указать уникальный идентификатор мода (MODID) и его версию (VERSION)
    2. [ @Mod.Instance ] Указать Forge на @Mod.Instance(MODID) мода
    3. [ @SidedProxy ] Указать пути до Прокси-классов
    4. [ @Mod.EventHandler ] Определить действия при том или ином этапе загрузки мода
*/
@Mod(modid = TutorialMod.MODID, name = TutorialMod.NAME, version = TutorialMod.VERSION)
public class TutorialMod
{
	public static final String MODID = "tutorial_01";
	public static final String NAME = "Tutorial #01 Mod";
	public static final String VERSION = "1.0";

	@Mod.Instance(MODID)
	public static TutorialMod instance;

	@SidedProxy(clientSide="today.fallout.tutorial_01.client.ClientProxy", serverSide="today.fallout.tutorial_01.common.CommonProxy")
	public static CommonProxy proxy;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		proxy.preInit();
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {}
}
