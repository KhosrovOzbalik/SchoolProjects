public class MovesDontMatch extends Exception{
    @Override
    public String toString() {
        return "Error : Move sequence contains wrong number of move steps. Input line ignored.";
    }
}
