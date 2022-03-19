import java.util.*;

public class VendingMachine {
    public List<ItemStack<String>> parts = new ArrayList<>();


    public void addItem(String id, String name) {
        for (ItemStack<String> part : parts) {
            if (part.itemName.equals(name)) {
                part.push(id);
                break;
            }
        }
    }


    public void buy(String name, int amount) {
        for (ItemStack<String> part : parts) {
            if (part.itemName.equals(name)) {
                for (int i = 0; i < amount; i++) {
                    part.pop();
                }
                break;
            }
        }
    }
}
