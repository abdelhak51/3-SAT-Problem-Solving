package genetic_algo;
import java.util.ArrayList;
import java.util.List;

public class Population {

    public static final int POPULATION_SIZE = 80;

    private List<Solution> solutions;
    public Population() {
        solutions = new ArrayList<>();
        for (int i = 0; i < POPULATION_SIZE; i++) {
            solutions.add(new Solution());
        }
        sortPopulation();
    }

    public void sortPopulation() {
        solutions.sort(new FitnessSort());
    }

    /**
     * @return the solutions
     */
    public List<Solution> getSolutions() {
        return solutions;
    }

    /**
     * @param solutions the solutions to set
     */
    public void setSolutions(List<Solution> solutions) {
        this.solutions = solutions;
        sortPopulation();
    }

    
    
    public int getBestFitness(){
        return solutions.get(0).getFitness();
    }

}
