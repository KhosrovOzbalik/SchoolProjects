import java.util.*;

public class TokenBox{
    public List<TokenPriorityQueue> tokenQueues = new ArrayList<>();
    public PriorityQueue<Token> tokens = new PriorityQueue<>();


    private class TokenPriorityQueue extends PriorityQueue<Token> {
        public final String itemName;


        private TokenPriorityQueue(String itemName) {
            this.itemName = itemName;
        }

        private TokenPriorityQueue(String itemName, Token... args) {
            super(args);
            this.itemName = itemName;
        }
    }


    public void enqueue(Token token) {
        for (TokenPriorityQueue queue : tokenQueues) {
            if (queue.itemName.equals(token.itemName)) {
                queue.enqueue(token);
                tokens.enqueue(token);
                return;
            }
        }
        tokenQueues.add(new TokenPriorityQueue(token.itemName, token));
        tokens.enqueue(token);
    }


    public void buy(String name, int amount) {
        for (TokenPriorityQueue queue : tokenQueues) {
            if (queue.itemName.equals(name)) {
                while (amount > 0) {
                    amount = queue.peek().use(amount);
                    if (queue.peek().getAmount() == 0) {
                        tokens.remove(queue.peek());
                        queue.dequeue();
                    }
                    else {
                        Token token = queue.peek();
                        tokens.remove(token);
                        queue.dequeue();
                        tokens.enqueue(token);
                        queue.enqueue(token);
                    }
                }
                break;
            }
        }
    }
}
