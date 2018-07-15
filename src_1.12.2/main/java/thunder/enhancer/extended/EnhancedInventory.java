package thunder.enhancer.extended;

import thunder.enhancer.inventory.InventoryEnhanced;

public class EnhancedInventory implements IEnhancedInventory {

	public final InventoryEnhanced inventory = new InventoryEnhanced();
		
	public InventoryEnhanced getInventory(){
		return this.inventory;
	}
	
	@Override
	public void copyInventory(IEnhancedInventory inventory) {
		this.inventory.copy(inventory.getInventory());
	}

}
