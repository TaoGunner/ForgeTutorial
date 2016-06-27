package today.fallout.tutorial_01.common;

/*
	Это общий Прокси-класс.
	Всё, что здесь написано будет выполняться и на клиенте, и на сервере.
	В этом примере, до инициализации мода (см. событие FMLPreInitializationEvent в основном классе мода)
	Выполняется регистрация предметов с помощью метода CommonRegistryHandler.registerItems()
*/
public class CommonProxy
{
	public void preInit()
	{
		CommonRegistryHandler.registerItems();
	}
}
