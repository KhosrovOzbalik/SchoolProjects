public class ItemStack<T> extends Stack<T>{
    public final String itemName;

    public ItemStack(String itemName) {
        super();
        this.itemName = itemName;
    }


    public ItemStack(String itemName, T... args) {
        super(args);
        this.itemName = itemName;
    }
}
