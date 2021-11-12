package genetic_algo;

import java.util.ArrayList;
import java.util.List;
import model_sat.Clause;
import model_sat.Litteral;
import model_sat.Sat;
import load_data.*;
import javafx.scene.chart.XYChart.Data;


public class GA {

    public static List<Clause> clauses;
    public static List<Litteral> litterals;
    public static Litteral litteral;
    public static Clause clause;
    public static Sat sat;
    public static List<Data<Number, Number>> load_data_for_lineChart = new ArrayList<Data<Number, Number>>();
    public static List<Data<Number, Number>> load_data_for_BarChart = new ArrayList<Data<Number, Number>>();
    
    // Parametres
    public static double DEFAULT_TIMEOUT = 60000; // instance timeout
    public static void setDEFAULT_TIMEOUT(double dEFAULT_TIMEOUT) {
		DEFAULT_TIMEOUT = dEFAULT_TIMEOUT;
	}
    public static long TOTAL_TIME = 0; // temps total
    public static long startTime; // temps de debut

    public static void main(String[] args) {
        
        runGeneticAlgo();
    }

	
	public static void runGeneticAlgo() {
		GeneticAlgo geneticAlgo; 

        for (int x = 1; x <= GeneticAlgo.NUMBER_OF_INSTENCES; x++) { // pour les 100 instnaces 
            
            sat = Load.LoadSat("/res/uf75-325/uf75-0" + x + ".cnf"); // chargement de l'istance
            geneticAlgo = new GeneticAlgo();
            startTime = System.currentTimeMillis();// temps de debut
            int iter = 0;
            while (geneticAlgo.getPopulation().getBestFitness() < Load.CLAUSE_NUMBER && System.currentTimeMillis() - startTime < DEFAULT_TIMEOUT) { // si problem n'est pas satisfait et tmps < timeout
                geneticAlgo.crossOver(); // croisement
                geneticAlgo.mutate(); // mutation
                iter++;
            }
            
            TOTAL_TIME += System.currentTimeMillis() - startTime;
            //stokage de donnees pour les graphe de statistique
            int satisfactions = geneticAlgo.getBest().getFitness(); 
            float accuracy = (float)satisfactions/Load.CLAUSE_NUMBER*100;
            
            load_data_for_lineChart.add(new Data<Number, Number>(x,accuracy));
            load_data_for_BarChart.add(new Data<Number, Number>(x, iter));
           
            printSolution(x, geneticAlgo); // affichage de la solution
            
        }
        System.out.println("Total Time : " + TOTAL_TIME + "ms");
        System.out.println("--------------------------------------------");
	}

    public static void printSolution(int iteration, GeneticAlgo geneticAlgo) {
        
        System.out.println(iteration + " : " + geneticAlgo.getBest().toString() + "\n" + geneticAlgo.getBest().getFitness());
        
        System.out.println("--------------------------------------------");
    }

   
    
}
