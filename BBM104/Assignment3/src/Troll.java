public class Troll extends Zorde{
    public Troll(String id) {
        super(id, Constants.trollAP, 150, Constants.trollMaxMove);
    }


    @Override
    public void move(Entity[][] board, String[] moves) throws OutOfBoundaries {
        int x = getCoords()[0],  y = getCoords()[1];
        try {
            for (int i = 0; i < moves.length; i += 2) {
                int newX = x + Integer.parseInt(moves[i]);
                int newY = y + Integer.parseInt(moves[i + 1]);
                if (!(board[newY][newX] == null)) {
                    if (board[newY][newX] instanceof Calliance) {
                        board[newY][newX].setHP(board[newY][newX].getHP() - AP);
                        board[newY][newX] = fightToDeath(this, board[newY][newX]);
                        board[y][x] = null;
                        x = newX;
                        y =newY;
                    }
                    break;
                }
                else {
                    board[newY][newX] = this;
                    board[y][x] = null;
                    x = newX;
                    y =newY;
                }
                if (i == moves.length - 2) {
                    setCoords(new int[] {x, y});
                    attack(board);
                }
            }
        }
        catch (ArrayIndexOutOfBoundsException a) {
            throw new OutOfBoundaries();
        }
        finally {
            setCoords(new int[] {x, y});
        }
    }
}
