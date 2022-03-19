public class Elf extends Calliance{
    public static final int RANGED = Constants.elfRangedAP;


    public Elf(String id) {
        super(id, Constants.elfAP, 70, Constants.elfMaxMove);
    }


    public void rangedAttack(Entity[][] board) {
        int x = getCoords()[0];
        int y = getCoords()[1];
        int boardSize = board.length;
        int xTop = (x >= boardSize - 3) ? (boardSize - 1) : (x + 2);
        int xDown = (x <= 2) ? 0 : (x - 2);
        int yTop = (y >= boardSize - 3) ? (boardSize - 1) : (y + 2);
        int yDown = (y <= 2) ? 0 : (y - 2);
        for (int i = yDown; i <= yTop; i++) {
            for (int j = xDown; j <= xTop ; j++) {
                Entity entity = board[i][j];
                if (board[i][j] instanceof Zorde) {
                    entity.setHP(board[i][j].getHP() - RANGED);
                    if (entity.getHP() == 0) {
                        board[i][j] = null;
                    }
                }
            }
        }
    }


    @Override
    public void move(Entity[][] board, String[] moves) throws OutOfBoundaries {
        int x = getCoords()[0],  y = getCoords()[1];
        try {
            for (int i = 0; i < moves.length; i += 2) {
                int newX = x + Integer.parseInt(moves[i]);
                int newY = y + Integer.parseInt(moves[i + 1]);
                if (!(board[newY][newX] == null)) {
                    if (board[newY][newX] instanceof Zorde) {
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
                if (i == moves.length - 2) rangedAttack(board);
                else attack(board);

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
