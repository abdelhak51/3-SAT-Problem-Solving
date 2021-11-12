package dfs_and_astar;
import load_data.*;

public class DfsAndAstar {
	
	public static int NUMBER_OF_INSTANCES_TO_USE = 3;
    public static long DEPTH_FIRST_TOTAL_TIME = 0;
    public static long A_STAR_TOTAL_TIME = 0;
	

	// execution de A etoile algorithme
	public static void runAlgoAstar() {
		Algorithmes algos = new Algorithmes();
        System.out.println("--------------------------------------------\nA*\n--------------------------------------------");
        for (int x = 1; x <= NUMBER_OF_INSTANCES_TO_USE; x++) {
            System.out.println("Instance " + x);
            Load.sat = Load.LoadSat("/res/uf75-325/uf75-0" + x + ".cnf");
            algos.aStarAlgorithm(x);
            
    		//Load.list_of_data_BFS.add(new XYChart.Data<Number, Number>(x,accuracy));
        } 
        System.out.println("Total A* Time = " + A_STAR_TOTAL_TIME + "ms");
	}

	//execution de recharche en profendeur algorithme
	public static void runAlgoDFS() {
		Algorithmes algos = new Algorithmes();

        System.out.println("--------------------------------------------\nProfondeur\n--------------------------------------------");
        for (int x = 1; x <= NUMBER_OF_INSTANCES_TO_USE; x++) {
            System.out.println("Instance " + x);
            Load.sat = Load.LoadSat("/res/uf75-325/uf75-0" + x + ".cnf");
            algos.depthFirstAlgorithm(x);
            
        }
        System.out.println("Total Profondeur Time = " + DEPTH_FIRST_TOTAL_TIME + "ms");
	}

}
