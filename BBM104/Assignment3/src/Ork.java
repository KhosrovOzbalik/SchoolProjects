public class Ork extends Zorde {
    public static final int HealPoints = Constants.orkHealPoints;


    public Ork(String id) {
        super(id, Constants.orkAP, 200, Constants.orkMaxMove);
    }


    public void heal(Entity[][] board) {
        int x = getCoords()[0];
        int y = getCoords()[1];
        int boardSize = board.length;
        int xTop = (x == boardSize - 1) ? x : (x + 1);
        int xDown = (x == 0) ? x : x - 1;
        int yTop = (y == boardSize - 1) ? y : (y + 1);
        int yDown = (y == 0) ? y : y - 1;
        //ADD ALL ALLIES +10
        for (int i = yDown; i <= yTop; i++) {
            for (int j = xDown; j <= xTop ; j++) {
                if (board[i][j] instanceof Zorde) board[i][j].setHP(board[i][j].getHP() + HealPoints);
            }
        }
    }


    @Override
    public void move(Entity[][] board, String[] moves) throws OutOfBoundaries {
        int x = getCoords()[0],  y = getCoords()[1];
        heal(board);
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
