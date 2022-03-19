public class OutOfBoundaries extends Exception{
    // True if something has changed in board.
    Boolean myBool;


    public OutOfBoundaries(Boolean myBool) {
        this.myBool = myBool;
    }


    public OutOfBoundaries() {
        myBool = true;
    }


    @Override
    public String toString() {
        return "Error : Game board boundaries are exceeded. Input line ignored.";
    }
}
