package thunder.enhancer.extended;

import thunder.enhancer.inventory.InventoryEnhanced;

public interface IEnhancedInventory {
	
	public void copyInventory(IEnhancedInventory inventory);
	
	public InventoryEnhanced getInventory();
	
}
