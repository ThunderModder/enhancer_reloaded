package thunder.enhancer.items.combat;

import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import thunder.enhancer.items.EnumModifierType;
import thunder.enhancer.items.ItemModifier;
import thunder.enhancer.utils.Constants;
import thunder.enhancer.utils.NBTItemProvider;
import thunder.enhancer.utils.Utils;

public class ItemCMEnemyRandomTeleport extends ItemModifier {

	public ItemCMEnemyRandomTeleport(String ulocalizedName, EnumModifierType type) {
		super(ulocalizedName, type);
	
	}
	
	@Override
	public void getDescription(ItemStack stack, List<String> tips){
    	
		tips.add(TextFormatting.DARK_AQUA + I18n.format("tooltip." + stack.getUnlocalizedName().substring(5) + ".description", new Object[]{}));
		tips.add(TextFormatting.DARK_GRAY + I18n.format("tooltip.energy", new Object[]{}) + TextFormatting.DARK_PURPLE + Constants.CM_ENEMYRANDOMTELEPORT_BASE * NBTItemProvider.getPower(stack) + " LT");
    }
	
	@Override
	public void onAttack(LivingAttackEvent event, EntityPlayer source, EntityLivingBase target, ItemStack stack) {
		
		if(!target.getEntityWorld().isRemote && NBTItemProvider.canDischarge(stack, Constants.CM_ENEMYRANDOMTELEPORT_BASE)){
			
			World world  = target.getEntityWorld();

			if (world.canSeeSky(new BlockPos(target))) {
				
				double power = (double)NBTItemProvider.getPower(stack);
				
				double c0 = target.posX + (Utils.getRandom().nextDouble() - 0.5D) * (8.0D * power);
		        double c1 = target.posY + (double)(Utils.getRandom().nextInt((int)(4 * power)) - 2);
		        double c2 = target.posZ + (Utils.getRandom().nextDouble() - 0.5D) * (8.0D * power);
		        
		        if(target.attemptTeleport(c0, c1, c2)){
		        	NBTItemProvider.discharge(stack, Constants.CM_ENEMYRANDOMTELEPORT_BASE);
		            world.playSound((EntityPlayer)null, target.prevPosX, target.prevPosY, target.prevPosZ, SoundEvents.ENTITY_ENDERMEN_TELEPORT, target.getSoundCategory(), 1.0F, 1.0F);
		        }
            }
		}
	}

}
