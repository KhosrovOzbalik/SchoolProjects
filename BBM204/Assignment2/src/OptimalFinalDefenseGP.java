import java.util.ArrayList;
import java.util.Collections;

/**
 * This class accomplishes Mission Exterminate
 */
public class OptimalFinalDefenseGP
{
    private ArrayList<Integer> bombWeights;

    public OptimalFinalDefenseGP(ArrayList<Integer> bombWeights) {
        this.bombWeights = bombWeights;
    }

    public ArrayList<Integer> getBombWeights() {
        return bombWeights;
    }

    /**
     *
     * @param maxNumberOfAvailableAUAVs the maximum number of available AUAVs to be loaded with bombs
     * @param maxAUAVCapacity the maximum capacity of an AUAV
     * @return the minimum number of AUAVs required using first fit approach over reversely sorted items.
     * Must return -1 if all bombs can't be loaded onto the available AUAVs
     */
    public int getMinNumberOfAUAVsToDeploy(int maxNumberOfAvailableAUAVs, int maxAUAVCapacity)
    {
        // First sort all weights in decreasing order
        // Initialize result (Count of AUAVs)
        // Create an array to store remaining space in AUAVs, there can be at most maxNumberOfAvailableAUAVs AUAVs
        // Place items one by one

        Collections.sort(bombWeights, Collections.reverseOrder());
        ArrayList<Integer> space = new ArrayList<>();
        for (int i = 0; i < maxNumberOfAvailableAUAVs; i++) {
            space.add(0);
        }
        for (int bomb : bombWeights) {
            boolean flag = true;
            for (int i = 0; i < space.size(); i++) {
                if (space.get(i) + bomb <= maxAUAVCapacity) {
                    space.set(i, space.get(i) + bomb);
                    flag = false;
                    break;
                }
            }
            if (flag) return -1;
        }

        int result = 0;
        for (int i : space) {
            if (i == 0) break;
            result += 1;
        }
        return result == 0 ? -1 : result;
    }
    public void printFinalDefenseOutcome(int maxNumberOfAvailableAUAVs, int AUAV_CAPACITY){
        int minNumberOfAUAVsToDeploy = this.getMinNumberOfAUAVsToDeploy(maxNumberOfAvailableAUAVs, AUAV_CAPACITY);
        if(minNumberOfAUAVsToDeploy!=-1) {
            System.out.println("The minimum number of AUAVs to deploy for complete extermination of the enemy army: " + minNumberOfAUAVsToDeploy);
        }
        else{
            System.out.println("We cannot load all the bombs. We are doomed.");
        }
    }
}
