package today.fallout.tutorial_02;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import today.fallout.tutorial_02.common.CommonProxy;

@Mod(modid = TutorialMod.MODID, name = TutorialMod.NAME, version = TutorialMod.VERSION)
public class TutorialMod
{
	public static final String MODID = "tutorial_02";
	public static final String NAME = "Tutorial #02 Mod";
	public static final String VERSION = "1.0";

	@Mod.Instance(MODID)
	public static TutorialMod instance;

	@SidedProxy(clientSide="today.fallout.tutorial_02.client.ClientProxy", serverSide="today.fallout.tutorial_02.common.CommonProxy")
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
