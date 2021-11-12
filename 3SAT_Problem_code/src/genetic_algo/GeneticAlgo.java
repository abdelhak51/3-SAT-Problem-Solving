package genetic_algo;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import load_data.Load;


public class GeneticAlgo {

    private static  int PERMANENT_SOLS = 0; // nombre de solutions père a conserver
    public static int NUMBER_OF_INSTENCES = 10;
    public static void setNUMBER_OF_INSTENCES(int nUMBER_OF_INSTENCES) {
		NUMBER_OF_INSTENCES = nUMBER_OF_INSTENCES;
	}

	private static  double MUTATION_CHANCE = .05; // taux de mulation
    public static void setMUTATION_CHANCE(double mUTATION_CHANCE) {
		MUTATION_CHANCE = mUTATION_CHANCE;
	}
    
    private static  double CROSSOVER_CHANCE = .95; // taux dde croisement 
    public static void setCROSSOVER_CHANCE(double cROSSOVER_CHANCE) {
		CROSSOVER_CHANCE = cROSSOVER_CHANCE;
	}

    private static  int SELECTION_SIZE = 30; // taille d'une selection
	public static void setSELECTION_SIZE(int sELECTION_SIZE) {
		SELECTION_SIZE = sELECTION_SIZE;
	}

	
    private Solution best = null; // meilleur solution 

    private Population population; // population 

    public GeneticAlgo() { // intialisation
        this.population = new Population();
        best = this.population.getSolutions().get(0);
    }

    public void crossOver() {// croisement
        List<Solution> newpop = new ArrayList<>();
        Solution selectionA, selectionB;
        for (int i = 0; i < PERMANENT_SOLS; i++) { // conserver les solutions pere
            newpop.add(getPopulation().getSolutions().get(i));
        }
        for (int i = PERMANENT_SOLS; i < Population.POPULATION_SIZE; i++) { 
            if (Math.random() < CROSSOVER_CHANCE) {
                selectionA = this.selectSolutions().get(0); // selectioner A
                selectionB = this.selectSolutions().get(0); // selectioner B
                newpop.add(this.crossOverSolution(selectionA, selectionB)); // mutation entre la meilleur sol du a et la meilleur du b
            } else {
                newpop.add(population.getSolutions().get(i)); // ajout a la nouvelle population
            }
        }
        getPopulation().setSolutions(newpop); // ecraser ancienne population
    }

    public void mutate() { // mutaiton
        List<Solution> newpop = new ArrayList<>();
        for (int i = 0; i < PERMANENT_SOLS; i++) { // conserver les solutions pere
            newpop.add(getPopulation().getSolutions().get(i));
        }
        for (int i = PERMANENT_SOLS; i < Population.POPULATION_SIZE; i++) { // applique la mution sur toute la population 
            newpop.add(this.mutateSolution(population.getSolutions().get(i)));
        }
        getPopulation().setSolutions(newpop); // ecraser ancienne population
        if(getPopulation().getSolutions().get(0).getFitness()>best.getFitness()){
            best = getPopulation().getSolutions().get(0); // calcule de la meilleur solution
        }
    }

    public Solution crossOverSolution(Solution sol1, Solution sol2) { // croisement entre 2 chromosome
        int[] values = new int[Load.VAR_NUM];
        for (int i = 0; i < Load.VAR_NUM; i++) {
            if (Math.random() < 0.5) {
                values[i] = sol1.getValues()[i];
            } else {
                values[i] = sol2.getValues()[i];
            }
        }
        return new Solution(values);
    }

    public Solution mutateSolution(Solution sol) { // mutation entre 2 chromosome
        int[] values = new int[Load.VAR_NUM];
        for (int i = 0; i < Load.VAR_NUM; i++) {
            if (Math.random() < MUTATION_CHANCE) {
                if (sol.getValues()[i] == 0) {
                    values[i] = 1;
                } else {
                    values[i] = 0;
                }
            } else {
                values[i] = sol.getValues()[i];
            }
        }
        return new Solution(values);
    }

    public List<Solution> selectSolutions() { // fonction de selection aleatoire
        List<Solution> sols = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < SELECTION_SIZE; i++) {
            Solution s = getPopulation().getSolutions().get(random.nextInt(Population.POPULATION_SIZE));
            if (!sols.contains(s)) {
                sols.add(s);
            } else {
                i--;
            }
        }
        sols.sort(new FitnessSort());
        return sols;
    }

    //setters and getters
    public Population getPopulation() {
        return population;
    }

    
    public void setPopulation(Population population) {
        this.population = population;
    }
    
    public Solution getBest() {
        return this.best;
    }

}
