public abstract class Calliance extends Entity{
    public Calliance(String id, int ap, int maxhp, int maxmove) {
        super(id, ap, maxhp, maxmove);
    }


    @Override
    public void attack(Entity[][] board) {
        int x = getCoords()[0];
        int y = getCoords()[1];
        int boardSize = board.length;
        int xTop = (x == boardSize - 1) ? x : (x + 1);
        int xDown = (x == 0) ? x : (x - 1);
        int yTop = (y == boardSize - 1) ? y : (y + 1);
        int yDown = (y == 0) ? y : (y - 1);
        for (int i = yDown; i <= yTop; i++) {
            for (int j = xDown; j <= xTop ; j++) {
                Entity entity = board[i][j];
                if (board[i][j] instanceof Zorde) {
                    entity.setHP(board[i][j].getHP() - this.AP);
                    if (entity.getHP() == 0) {
                        board[i][j] = null;
                    }
                }
            }
        }
    }
}
