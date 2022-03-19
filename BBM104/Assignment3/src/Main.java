import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<Entity> entities = new ArrayList<>();
        int boardSize = 0;
        Entity[][] board = null;


        // Resetting Output file
        try {
            FileWriter writer = new FileWriter(args[2]);
            writer.close();
        } catch (IOException e) {
            System.out.println("Output file not found.");
            e.printStackTrace();
        }


        // Board & initial positions
        try {
            File initials = new File(args[0]);
            Scanner scanner = new Scanner(initials);
            scanner.next();
            boardSize = Integer.parseInt(scanner.next().split("x")[0]);
            board = new Entity[boardSize][boardSize];
            while (scanner.hasNextLine()) {
                String type = scanner.next();
                if (type.equals("CALLIANCE") || type.equals("ZORDE")) {continue;}
                String[] line = scanner.nextLine().trim().split(" ");
                Entity entity;
                if (type.equals("ELF")) {entity = new Elf(line[0]);}
                else if (type.equals("DWARF")) {entity = new Dwarf(line[0]);}
                else if (type.equals("HUMAN")) {entity = new Human(line[0]);}
                else if (type.equals("GOBLIN")) {entity = new Goblin(line[0]);}
                else if (type.equals("TROLL")) {entity = new Troll(line[0]);}
                else {entity = new Ork(line[0]);}
                entity.setCoords(new int[] {Integer.parseInt(line[1]), Integer.parseInt(line[2])});
                board[Integer.parseInt(line[2])][Integer.parseInt(line[1])] = entity;
                entities.add(entity);
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("Initials file not found.");
            e.printStackTrace();
            System.exit(0);
        }
        entities.sort(Comparator.comparing((Entity e) -> e.ID));
        printBoard(board, entities, args[2]);


        // Commands
        try {
            File commands = new File(args[1]);
            Scanner scanner = new Scanner(commands);
            while (scanner.hasNextLine()) {
                String id = scanner.next();
                String[] moves = scanner.next().replaceAll(";$","").split(";");
                for (Entity entity : entities) {
                    if (entity.ID.equals(id)) {
                        int x = entity.getCoords()[0];
                        int y = entity.getCoords()[1];
                        try {
                            if (!(moves.length / 2 == entity.MAXMOVE)) {
                                throw new MovesDontMatch();
                            }
                            if (!(0 <= (x + Integer.parseInt(moves[0])) && (x + Integer.parseInt(moves[0])) < boardSize && 0 <= (y + Integer.parseInt(moves[1])) && (y + Integer.parseInt(moves[1])) < boardSize)) {
                                throw new OutOfBoundaries(false);
                            }
                            entity.move(board, moves);
                            printBoard(board, entities, args[2]);
                        }
                        catch(OutOfBoundaries o){
                            if (o.myBool) {
                                printBoard(board, entities, args[2]);
                            }
                            writeOutput(o + "\n\n",args[2]);
                        }
                        catch (MovesDontMatch m) {
                            writeOutput(m + "\n\n",args[2]);
                        }
                        break;
                    }
                }
                entities = entities.stream().filter((Entity e) -> e.getHP() != 0).collect(Collectors.toList());
                if (entities.stream().noneMatch((Entity e) -> e instanceof Zorde) || entities.stream().noneMatch((Entity e) -> e instanceof Calliance)) break;
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("Commands file not found.");
            e.printStackTrace();
        }


        // Who wins?
        if (entities.stream().anyMatch((Entity e) -> e instanceof Zorde)) writeOutput("\nGame Finished\nZorde Wins", args[2]);

        else writeOutput("\nGame Finished\nCalliance Wins", args[2]);
    }


    static void writeOutput(String text, String file) {
        try {
            FileWriter writer = new FileWriter(file, true);
            writer.write(text);
            writer.close();
        } catch (IOException e) {
            System.out.println("Output file not found.");
            e.printStackTrace();
        }
    }


    static void printBoard(Entity[][] board, List<Entity> entities, String file) {
        String text = (new String(new char[board.length + 1]).replace("\0", "**")) + "\n";
        for (Entity[] i : board) {
            text += "*";
            for (Entity j : i) {
                if (j == null) {
                    text += "  ";
                }
                else {
                    text += j.ID;
                }
            }
            text += "*\n";
        }
        text += (new String(new char[board.length + 1]).replace("\0", "**")) + "\n\n";

        entities = entities.stream().filter((Entity e) -> e.getHP() != 0).collect(Collectors.toList());
        for (Entity entity : entities) {
            text += entity.ID + "\t" + entity.getHP() + "\t(" + entity.MAXHP + ")\n";
        }
        writeOutput(text + "\n",file);
    }
}
