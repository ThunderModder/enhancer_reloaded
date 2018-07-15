package thunder.enhancer.blocks;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import thunder.enhancer.core.Enhancer;
import thunder.enhancer.guis.GuiHandler;
import thunder.enhancer.tileentities.TileEntityUltimateCharger;

public class BlockUltimateCharger extends BlockContainer{
	
	public static final PropertyDirection FACING = BlockHorizontal.FACING;

	public BlockUltimateCharger(String unlocalizedName) {
		
		super(Material.ROCK);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
     
        this.setHardness(1.0f);
        
		this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(unlocalizedName);	
		this.setCreativeTab(Enhancer.ENHANCER_TAB_BLOCKSNITEMS);
		ForgeRegistries.BLOCKS.register(this);
		ForgeRegistries.ITEMS.register(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
  
    @Nullable
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
    	
        return Item.getItemFromBlock(BlockRegistry.BlockUltimateCharger);
    }
       
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
    	
        this.setDefaultFacing(worldIn, pos, state);
    }
      
    private void setDefaultFacing(World worldIn, BlockPos pos, IBlockState state) {
    	
        if (!worldIn.isRemote) {
        	
            IBlockState iblockstate = worldIn.getBlockState(pos.north());
            IBlockState iblockstate1 = worldIn.getBlockState(pos.south());
            IBlockState iblockstate2 = worldIn.getBlockState(pos.west());
            IBlockState iblockstate3 = worldIn.getBlockState(pos.east());
            EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);

            if (enumfacing == EnumFacing.NORTH && iblockstate.isFullBlock() && !iblockstate1.isFullBlock()) {
            	
                enumfacing = EnumFacing.SOUTH;
            }
            else if (enumfacing == EnumFacing.SOUTH && iblockstate1.isFullBlock() && !iblockstate.isFullBlock()) {
            	
                enumfacing = EnumFacing.NORTH;
            }
            else if (enumfacing == EnumFacing.WEST && iblockstate2.isFullBlock() && !iblockstate3.isFullBlock()) {
            	
                enumfacing = EnumFacing.EAST;
            }
            else if (enumfacing == EnumFacing.EAST && iblockstate3.isFullBlock() && !iblockstate2.isFullBlock()) {
            	
                enumfacing = EnumFacing.WEST;
            }

            worldIn.setBlockState(pos, state.withProperty(FACING, enumfacing), 2);
        }
    }
      
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ){
        
    	if (worldIn.isRemote) {
    		
            return true;
        }
        else {
        	
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof TileEntityUltimateCharger) {
            	
            	playerIn.openGui(Enhancer.INSTANCE, GuiHandler.ULTIMATE_CHARGER_GUI_ID, worldIn, pos.getX(), pos.getY(), pos.getZ());      	
            }

            return true;
        }
    }
    
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
    	
        return new TileEntityUltimateCharger();
    }
           
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
    	
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }
    
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
    	
        worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
    }
    
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
    	
    	TileEntity tileentity = worldIn.getTileEntity(pos);
        if (tileentity instanceof TileEntityUltimateCharger) {
        	
            InventoryHelper.dropInventoryItems(worldIn, pos, (TileEntityUltimateCharger)tileentity);
            worldIn.updateComparatorOutputLevel(pos, this);
        }
      	
        super.breakBlock(worldIn, pos, state);
    }
    
    public boolean hasComparatorInputOverride(IBlockState state) {
    	
        return true;
    }

    public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos) {
    	
        return Container.calcRedstone(worldIn.getTileEntity(pos));
    }

    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
    	
        return new ItemStack(BlockRegistry.BlockUltimateCharger);
    }
    
    public EnumBlockRenderType getRenderType(IBlockState state) {
    	
        return EnumBlockRenderType.MODEL;
    }
    
    public IBlockState getStateFromMeta(int meta) {
    	
        EnumFacing enumfacing = EnumFacing.getFront(meta);

        if (enumfacing.getAxis() == EnumFacing.Axis.Y) {
        	
            enumfacing = EnumFacing.NORTH;
        }

        return this.getDefaultState().withProperty(FACING, enumfacing);
    }
    
    public int getMetaFromState(IBlockState state) {
    	
        return ((EnumFacing)state.getValue(FACING)).getIndex();
    }
    
    public IBlockState withRotation(IBlockState state, Rotation rot) {
    	
        return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
    }
    
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
    	
        return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
    }

    protected BlockStateContainer createBlockState() {
    	
        return new BlockStateContainer(this, new IProperty[] {FACING});
    }
	

}
