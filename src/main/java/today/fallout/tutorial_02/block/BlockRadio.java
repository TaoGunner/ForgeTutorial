package today.fallout.tutorial_02.block;

import com.google.common.collect.ImmutableMap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import today.fallout.tutorial_02.common.CommonRegistryHandler;

/*
	Пример блока с моделью. Сама модель находится в json-файлах.
*/
public class BlockRadio extends Block
{
	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	static AxisAlignedBB bBoxX = new AxisAlignedBB(0.375D, 0.0D, 0.1875D, 0.625D, 0.3125D, 0.8125D);
	static AxisAlignedBB bBoxY = new AxisAlignedBB(0.1875D, 0.0D, 0.375D, 0.8125D, 0.3125D, 0.625D);

	public BlockRadio()
	{
		super(Material.IRON);
		this.setCreativeTab(CommonRegistryHandler.tabTutorial);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
		this.setRegistryName("block_radio");
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

	// Здесь мы определяем состояние блока. Не могу описать подробнее.
	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, FACING)
		{
			@Override
			protected StateImplementation createState(Block block, ImmutableMap<IProperty<?>, Comparable<?>> properties, ImmutableMap<net.minecraftforge.common.property.IUnlistedProperty<?>, com.google.common.base.Optional<?>> unlistedProperties)
			{
				return new StateImplementation(block, properties)
				{
					@Override   // Определяем прозрачность блока. Так как у нас модель - возвращаем false;
					public boolean isOpaqueCube() { return false; }

					@Override   // Определяем, полный ли у нас блок. Так как у нас модель - возвращаем false;
					public boolean isFullCube()
					{
						return false;
					}

					@Override   // Действие при изменение блока снизу. Если блок разбили - дропаем наш блок.
					public void neighborChanged(World worldIn, BlockPos pos, Block block)
					{
						if (!worldIn.isRemote)
						{
							boolean flag = false;
							if (!worldIn.getBlockState(pos.down()).isSideSolid(worldIn, pos.down(), EnumFacing.UP)) { flag = true; }

							if (flag && !worldIn.isAirBlock(pos))
							{
								block.dropBlockAsItem(worldIn, pos, this, 0);
								worldIn.setBlockToAir(pos);
							}
						}
					}

					@Override   // Определяем размеры области взаимодействия с игроком.
					public AxisAlignedBB getBoundingBox(IBlockAccess blockAccess, BlockPos pos)
					{
						EnumFacing enumfacing = (EnumFacing)this.getValue(FACING);
						return enumfacing.getAxis() == EnumFacing.Axis.X ? bBoxX : bBoxY;
					}
				};
			}
		};
	}

	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer()
	{
		return BlockRenderLayer.CUTOUT;
	}

	@Override   // Запрещаем ставить на неполные блоки
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
	{
		return worldIn.getBlockState(pos).getBlock().isReplaceable(worldIn, pos) && worldIn.getBlockState(pos.down()).isSideSolid(worldIn, pos, EnumFacing.UP);
	}

	@Override
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
	{
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing());
	}


	@Override
	@SuppressWarnings("deprecation")    // Mojang, please...
	public IBlockState getStateFromMeta(int meta)
	{
		EnumFacing enumfacing = EnumFacing.getFront(meta);
		if (enumfacing.getAxis() == EnumFacing.Axis.Y) { enumfacing = EnumFacing.NORTH; }
		return this.getDefaultState().withProperty(FACING, enumfacing);
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		return state.getValue(FACING).getIndex();
	}

	// Всё, что ниже - не проверено.
	@Override
	public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn)
	{
		worldIn.playSound(playerIn, pos, CommonRegistryHandler.block_radio_sound, SoundCategory.MUSIC, 1.0F, 1.0F);
	}

}
