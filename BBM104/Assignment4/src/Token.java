public class Token implements Comparable<Token> {
    public final String id;
    public final String itemName;
    private int amount;


    public Token(String id, String itemName, int amount) {
        this.id = id;
        this.itemName = itemName;
        this.amount = amount;
    }


    public int use(int times) {
        if (times > amount) {
            times = times - amount;
            amount = 0;
            return times;
        }
        amount = amount - times;
        return 0;
    }


    @Override
    public int compareTo(Token o) {
        if (this.amount > o.amount) return 1;
        else if (this.amount < o.amount) return -1;
        return 0;
    }



    public int getAmount() { return amount;}


    @Override
    public String toString() {
        return "Token{" +
                "id='" + id + '\'' +
                ", itemName='" + itemName + '\'' +
                ", amount=" + amount +
                '}';
    }
}
