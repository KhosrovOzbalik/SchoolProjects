import java.util.ArrayList;
import java.util.List;

/**
 * This class accomplishes Mission Nuke'm
 */
public class DefenseAgainstEnemyTroops {
    private ArrayList<Integer> numberOfEnemiesArrivingPerHour;

    public DefenseAgainstEnemyTroops(ArrayList<Integer> numberOfEnemiesArrivingPerHour){
        this.numberOfEnemiesArrivingPerHour = numberOfEnemiesArrivingPerHour;
    }

    public ArrayList<Integer> getNumberOfEnemiesArrivingPerHour() {
        return numberOfEnemiesArrivingPerHour;
    }

    private int getRechargedWeaponPower(int hoursCharging){
        return hoursCharging*hoursCharging;
    }

    /**
     *     Function to implement the given dynamic programming algorithm
     *     SOL(0) <- 0
     *     HOURS(0) <- [ ]
     *     For{j <- 1...N}
     *         SOL(j) <- max_{0<=i<j} [ (SOL(i) + min[ E(j), P(j − i) ] ]
     *         HOURS(j) <- [HOURS(i), j]
     *     EndFor
     *
     * @return OptimalEnemyDefenseSolution
     */
    public OptimalEnemyDefenseSolution getOptimalDefenseSolutionDP(){
        // TODO: YOUR CODE HERE
        List<Integer> SOL = new ArrayList<>();
        SOL.add(0);

        List<List<Integer>> HOURS = new ArrayList<>();
        HOURS.add(new ArrayList<>());

        List<Integer> E = numberOfEnemiesArrivingPerHour;

        for (int j = 1; j <= E.size(); j++) {

            ArrayList<Integer> hour = new ArrayList<>();
            int max = 0;
            int maxI = 0;
            int d = -1;
            for (int i = 0; i < j; i++) {
                int a = E.get(j - 1);
                int b = getRechargedWeaponPower(j - i);
                int ans = (SOL.get(i) + (Math.min(a, b)));
                if (ans > max) {
                    max = ans;
                    maxI = j;
                    if (i != 0) {
                        d = j - 1;
                    }
                }
            }

            if (d != -1) {
                hour.addAll(HOURS.get(d));
            }
            hour.add(maxI);
            HOURS.add(hour);
            SOL.add(max);
        }

        OptimalEnemyDefenseSolution op = new OptimalEnemyDefenseSolution(SOL.get(SOL.size()-1), (ArrayList<Integer>) HOURS.get(HOURS.size()-1));
        return op;
    }
}
