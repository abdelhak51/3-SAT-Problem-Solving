package dfs_and_astar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import load_data.*;


public class Algorithmes {

    public static  long DEFAULT_TIMEOUT = 6000; // en ms
    public static List<XYChart.Data<Number, Number>> list_of_data_accuracyDFS = new ArrayList<XYChart.Data<Number,Number>>();
    public static List<XYChart.Data<Number, Number>> list_of_data_satisfactionDFS = new ArrayList<XYChart.Data<Number,Number>>();
    

    public static List<XYChart.Data<Number, Number>> list_of_data_accuracyAstar = new ArrayList<XYChart.Data<Number,Number>>();
    public static List<XYChart.Data<Number, Number>> list_of_data_satisfactionAstar = new ArrayList<XYChart.Data<Number,Number>>();
    private static final String DEPTH_FIRST_ALGORITHM = "Profondeur"; // en ms
    private static final String A_STAR_FIRST_ALGORITHM = "A*"; // en ms

    private long startTime, stopTime;
    private Solution best;

    private int best_sats;
    private int tmp_sats;

    private void printBestSolution(String algorithm) {
        System.out.println(algorithm + " Solution " + Arrays.toString(best.getValues()));
        //System.out.println("Satisfactions : " + Load.sat.satisfiedClauses(best) + " - " + (float) Load.sat.satisfiedClauses(best) / Load.CLAUSE_NUMBER * 100 + "%");
        System.out.println("Time : " + (stopTime - startTime) + "ms");
        System.out.println("--------------------------------------------");
    }

    // Profondeur Dabord
    public void depthFirstAlgorithm(int instence) {

        int[] values = new int[Load.VAR_NUM]; // setting variables a 0 

        Stack<Solution> sols = new Stack<Solution>(); // creation du Pile
        sols.push(new Solution(values, 0, 1)); // remplissage fils droit
        sols.push(new Solution(values, 0, 0)); // remplissage fils gauche
        Solution s = sols.pop(); // extraction du dernier element LIFO

        best = s;  // initialisation de la meilleur solution

        best_sats = Load.sat.satisfiedClauses(best); // nombre de clauses satisfaites
        tmp_sats = 0;

        startTime = System.currentTimeMillis(); // le temp de debut

        while (!Load.sat.satisfied(s) && (System.currentTimeMillis() - startTime) < DEFAULT_TIMEOUT) {
            if (s.getLvl() < Load.VAR_NUM - 1) {
                s.getKidsProfondeur(sols); // generation des fils
            }
            s = sols.pop(); // extraction du dernier element LIFO 
            tmp_sats = Load.sat.satisfiedClauses(s); // Mis a jours de la meilleur solution
            if (tmp_sats > best_sats) {
                best_sats = tmp_sats;
                best = s;
            }
        }

        stopTime = System.currentTimeMillis();

        printBestSolution(DEPTH_FIRST_ALGORITHM);// affichage de la solution
        
        //stokage de donnees pour les graphe de statistique
        int satisfactions = Load.sat.satisfiedClauses(best); 
        Long time_exe = stopTime - startTime;
        float accuracy = (float) satisfactions / Load.CLAUSE_NUMBER * 100;
        System.out.println("satisfactions :"+satisfactions+"     ////////////    accuracy :"+ accuracy+" instence : "+ instence);
		list_of_data_accuracyDFS.add(new XYChart.Data<Number, Number>(instence,accuracy));
        list_of_data_satisfactionDFS.add(new Data<Number, Number>(instence,time_exe));
		DfsAndAstar.DEPTH_FIRST_TOTAL_TIME += stopTime - startTime; // ajout a global algo time

    }

   

    // A etoile algorithme
    public void aStarAlgorithm(int instence) {

        startTime = System.currentTimeMillis();

        int[] values = new int[Load.VAR_NUM];

        Solution s = new Solution(values, -1, Load.sat); // creation de la solutions initial vecteur des 0
        Stack<Solution> pile = new Stack<>(); // intialisation de la pile

        List<Solution> passedSolutions = new ArrayList<>(); // Liste des solution deja testé

        boolean passed; //variable boolean si solution has passed or not

        while (!Load.sat.satisfied(s) && (System.currentTimeMillis() - startTime) < DEFAULT_TIMEOUT) {
            passed = false;
            for (Solution sol : passedSolutions) { // verifier si la solution has passed or not
                if (Arrays.equals(s.getValues(), sol.getValues())) { 
                    passed = true;
                }
            }
            if (passed == true) { // si passé -> retirer une autre solution 
                if (pile.empty()) {
                    break;
                }
                s = pile.pop();
            } else { // si non developer des fils
                if (s.getLvl() < Load.VAR_NUM - 1) {
                    s.getKidsAStar(pile, Load.VAR_NUM, Load.sat);
                }
                passedSolutions.add(s);
                s = pile.pop();
            }
        }

        stopTime = System.currentTimeMillis();

        best = s;
        
        printBestSolution(A_STAR_FIRST_ALGORITHM);
        
      //stokage de donnees pour les graphe de statistique
        int satisfactions = Load.sat.satisfiedClauses(best); 
        float accuracy = (float) satisfactions / Load.CLAUSE_NUMBER * 100;
        Long time_exe = stopTime - startTime;
        System.out.println("satisfactions :"+satisfactions+"     ////////////    accuracy :"+ accuracy+" instence : "+ instence);
		list_of_data_accuracyAstar.add(new XYChart.Data<Number, Number>(instence,accuracy));
        list_of_data_satisfactionAstar.add(new Data<Number, Number>(instence,time_exe));
		
        DfsAndAstar.A_STAR_TOTAL_TIME += stopTime - startTime;

    }

}
