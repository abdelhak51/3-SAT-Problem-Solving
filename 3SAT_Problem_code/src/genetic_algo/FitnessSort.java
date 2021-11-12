package genetic_algo;
import java.util.Comparator;


class FitnessSort implements Comparator<Solution> 
{ 

    @Override
    public int compare(Solution o1, Solution o2) {
        return o2.getFitness() - o1.getFitness();
    }
    
} 
