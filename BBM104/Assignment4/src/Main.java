import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        // args = parts.txt items.txt tokens.txt tasks.txt output.txt
        VendingMachine vendingMachine = new VendingMachine();
        TokenBox tokenBox = new TokenBox();

        // parts.txt
        try {
            File file = new File(args[0]);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                vendingMachine.parts.add(new ItemStack<>(scanner.next()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        // items.txt
        try {
            File file = new File(args[1]);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                vendingMachine.addItem(scanner.next(), scanner.next());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        // tokens.txt
        try {
            File file = new File(args[2]);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                tokenBox.enqueue(new Token(scanner.next(), scanner.next(), scanner.nextInt()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        // tasks.txt
        try {
            File file = new File(args[3]);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String command = scanner.next();
                String[] line = scanner.nextLine().trim().split("\t");
                if (command.equals("BUY")) {
                    for (String i : line) {
                        String[] na = i.split(",");
                        vendingMachine.buy(na[0], Integer.parseInt(na[1]));
                        tokenBox.buy(na[0], Integer.parseInt(na[1]));
                        System.out.println(tokenBox.tokens.size());
                    }
                }
                else if (command.equals("PUT")) {
                    for (String i : line) {
                        String[] items =  i.split(",");
                        String id = items[0];
                        for (int j = 1; j < items.length; j++) {
                            vendingMachine.addItem(items[j], id);
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        // output.txt
        try {
            FileWriter writer = new FileWriter(args[4]);
            StringBuilder bobTheBuilder = new StringBuilder();


            for (ItemStack<String> part : vendingMachine.parts) {
                bobTheBuilder.append(part.itemName).append(":\n");
                if (part.size() == 0) bobTheBuilder.append("\n");
                for (String id : part) {
                    bobTheBuilder.append(id).append("\n");
                }
                bobTheBuilder.append("---------------\n");
            }


            bobTheBuilder.append("Token Box:\n");
            List<String> tokens = new ArrayList<>();
            for (Token token : tokenBox.tokens) {
                tokens.add(token.id + " " + token.itemName + " " + token.getAmount());
            }
            Collections.reverse(tokens);
            bobTheBuilder.append(String.join("\n", tokens));
            writer.write(String.valueOf(bobTheBuilder));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
