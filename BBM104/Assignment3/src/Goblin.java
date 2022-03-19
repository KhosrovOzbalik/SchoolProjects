public class Goblin extends Zorde{


    public Goblin(String id) {
        super(id, Constants.goblinAP, 80, Constants.goblinMaxMove);
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
                setCoords(new int[] {x, y});
                attack(board);

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
