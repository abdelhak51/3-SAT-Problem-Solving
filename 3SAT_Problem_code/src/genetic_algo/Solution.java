package genetic_algo;
import java.util.Arrays;
import java.util.Random;

import load_data.Load;


public class Solution {

    private int[] values;
    private int fitness;

    public Solution() {
        Random random = new Random();
        values = new int[Load.VAR_NUM];
        for (int i = 0; i < Load.VAR_NUM; i++) {
            values[i] = random.nextInt(2);
        }
        fitness = GA.sat.satisfiedClauses(values);
    }
    
    public Solution(int[] values) {
        this.values = values.clone();
        fitness = GA.sat.satisfiedClauses(values);
    }

    /**
     * @return the values
     */
    public int[] getValues() {
        return values;
    }

    /**
     * @param values the values to set
     */
    public void setValues(int[] values) {
        this.values = values;
    }

    /**
     * @return the fitness
     */
    public int getFitness() {
        return fitness;
    }

    /**
     * @param fitness the fitness to set
     */
    public void setFitness(int fitness) {
        this.fitness = fitness;
    }
    
    public String toString(){
        return Arrays.toString(values);
    }

}
