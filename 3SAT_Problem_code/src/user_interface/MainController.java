package user_interface;
import aco.*;
import dfs_and_astar.*;
import genetic_algo.*;

import java.net.URL;

import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

public class MainController implements Initializable {
	
	 // Create empty series
    private ObservableList<Series<Number,Number>> seriesList = FXCollections.observableArrayList();

	
	
	////////////////////////////////////////     bfs algo variables
	@FXML private Spinner<Integer> spinnerDFS ;
	int initialValue = 10;
	@FXML private Slider sliderDFS;
	@FXML private LineChart<Number,Number> lineChartDFS;
	@FXML private AreaChart<Number, Number> barChartDFS;
	@FXML private PieChart pieChartDFS;
	@FXML private Label TimeDFS;
	
	
	////////////////////////////////// algo  Astar variables
	
	 @FXML private Spinner<Integer> spinnerAstar;

	 @FXML private Slider sliderAstar;

	 @FXML private LineChart<Number, Number> lineChartAstar;

	 @FXML private AreaChart<Number, Number> barChartAstar;

	 @FXML private PieChart pieChartAstar;
	 @FXML private Label TimeAstar;
	 
	 
	 ////////////////////////////////////////////GA algo variables
	 
	 
	 @FXML private LineChart<Number,Number> lineChartGA;
	 @FXML private AreaChart<Number, Number> barChartGA;
	 @FXML private PieChart pieChartGA;
	 @FXML private Spinner<Integer> spinnerPopulationGA;
	 	   private int initialValuePopulation=75;
	 @FXML private Spinner<Double> spinnerCrossGA;
	 	   private double initialValuedoubleCross = .95;
	 @FXML private Spinner<Double> spinnerMutatioGA;
	 	   private double initialValuedoubleMutations = .05;
	 @FXML private Spinner<Integer> spinnerSelectionGA;
	 	   private int initialValueSelection = 30;
	 @FXML private Spinner<Integer>  spinnerNbrInstenceGA;
	 @FXML private Slider sliderGA;
	 @FXML private Label TimeGA;
	 
	//////////////////////////////////////////////  ACO algo
	 
	 	@FXML
	    private Spinner<Double> spinnerAlphaACS;
	 	double initialValueAlpha=0.3;
	    @FXML
	    private Spinner<Double> spinnerBetaACS;
	    double initialValueBeta=0.1;
	    @FXML
	    private Spinner<Double> spinnerVrateACS;
	    double initialValueVrate=0.1;
	    
	    @FXML
	    private Spinner<Integer> spinnerMaxIterACS;
	    int initialValueMaxIter=50000;
	    @FXML
	    private Spinner<Integer> spinnerNbrInstenceACS;
	    int initialValueNbrInstence=10;
	    @FXML
	    private Spinner<Integer> spinnerAntCountACS;
	    int initialValueAntCount=100;
	    @FXML
	    private Slider sliderTimeOutACS;

	    @FXML
	    private Label TimeACS;

	    @FXML
	    private LineChart<Number,Number> lineChartACS;

	    @FXML
	    private AreaChart<Number, Number> barChartACS;

	    @FXML 
	    private PieChart pieChartACS;
	    
	    ////////////////////////////////////////////////////////// comparaisson des algos
	    @FXML
	    private LineChart<Number,Number> comparatif_line_chart;

	    @FXML
	    private PieChart time_complexite_pie;
	    
	    //////////////////////////////////////////////////////////////// initialisation
	    @Override
		public void initialize(URL location, ResourceBundle resources) {
			
			SpinnerValueFactory<Integer> svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, initialValue);
			spinnerDFS.setValueFactory(svf);
			spinnerAstar.setValueFactory(svf);
			
			//////////////////////// initialisation Genetic algo
			spinnerNbrInstenceGA.setValueFactory(svf);
			
			SpinnerValueFactory<Integer> svf_selection = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, initialValueSelection);
			spinnerSelectionGA.setValueFactory(svf_selection);
			
			SpinnerValueFactory<Integer> svf_population = new SpinnerValueFactory.IntegerSpinnerValueFactory(75, 80, initialValuePopulation);
			spinnerPopulationGA.setValueFactory(svf_population);
			
			SpinnerValueFactory<Double> svf_double_cross = new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 2, initialValuedoubleCross,0.01);
			spinnerCrossGA.setValueFactory(svf_double_cross);
			
			SpinnerValueFactory<Double> svf_double_mutation = new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 2, initialValuedoubleMutations,0.01);
			spinnerMutatioGA.setValueFactory(svf_double_mutation);
			
			//////////////////////////initialisation ACO algo
			SpinnerValueFactory<Double> spfAlphaACS = new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 1, initialValueAlpha,0.001);
			spinnerAlphaACS.setValueFactory(spfAlphaACS);
			
			SpinnerValueFactory<Double> spfBetaACS = new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 1, initialValueBeta,0.01);
			spinnerBetaACS.setValueFactory(spfBetaACS);
			
			
			
			SpinnerValueFactory<Double> spfVrateACS = new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 1, initialValueVrate,0.01);
			spinnerVrateACS.setValueFactory(spfVrateACS);
			
			//SpinnerValueFactory<Integer> svfIterACS = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, initialValueSelection);
			spinnerNbrInstenceACS.setValueFactory(svf);

			SpinnerValueFactory<Integer> svfMaxIter = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100000, initialValueMaxIter);
			spinnerMaxIterACS.setValueFactory(svfMaxIter);

			SpinnerValueFactory<Integer> svfNbrAnt = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 200, initialValueAntCount);
			spinnerAntCountACS.setValueFactory(svfNbrAnt);

			
			
		}
	    
	    ///////////////////////////////////////////////////////////////  Ant colony optimisation algo
	    
	    
	    @FXML
	    void processACS(ActionEvent event) {
	    	// mis a jour de tout les paramatere
	    	ACO.setV_RATE(Double.valueOf(spinnerVrateACS.getValue()));
	    	ACO.setALPHA(Double.valueOf(spinnerAlphaACS.getValue()));
	    	ACO.setBETA(Double.valueOf(spinnerBetaACS.getValue()));
	    	ACO.setMAX_ITERATIONS(Integer.valueOf(spinnerMaxIterACS.getValue()));
	    	ACO.setNUMBER_OF_INSTENCES(Integer.valueOf(spinnerNbrInstenceACS.getValue()));
	    	ACO.setDEFAULT_TIMEOUT(sliderTimeOutACS.getValue());
	    	
	    	ACO.runACOAlgo();
	    	loadLineDataACS();
	    	loadBarDataACS();
	    	loadPieDataACS();
	    	TimeACS.setText("time :"+String.valueOf(ACO.ACO_TOTAL_TIME/60000)+" min");
	    }
	 
	    private void loadPieDataACS() {
	    	
			int i = 0;
			float global_accuracy=0;
			for (Data<Number, Number> data :ACO.load_data_of_linechart_ACO) {
				 global_accuracy = global_accuracy + Float.parseFloat(data.getYValue().toString());
				 i++;
			}
			global_accuracy = global_accuracy/i;
			ObservableList<PieChart.Data> ol = FXCollections.observableArrayList(
					new PieChart.Data("accuracy of global satisfed instences: %"+global_accuracy,global_accuracy),
					new PieChart.Data("accuracy of global no satisfed instences: %"+(100-global_accuracy),100-global_accuracy));
			pieChartACS.setData(ol);
			ACO.load_data_of_linechart_ACO.clear();
		}
	    private void loadBarDataACS() {
			
			Series<Number, Number> series2_ACS = new Series<Number, Number>();
			series2_ACS.setName("Time exe for each instence in ms (ACO)");
			for (Data<Number, Number> data : ACO.load_data_of_barchart_ACO) {
				series2_ACS.getData().add(data);
			}
			barChartACS.getData().add(series2_ACS);
			ACO.load_data_of_barchart_ACO.clear();
		}
		private void loadLineDataACS() {
			
			Series<Number, Number> series1_ACS = new Series<Number, Number>();
			series1_ACS.setName("accuracy of ACO");
			for(Data<Number, Number> data : ACO.load_data_of_linechart_ACO) {
				series1_ACS.getData().add(data);
			}
			lineChartACS.getData().add(series1_ACS);
			seriesList.add(series1_ACS);
			//comparatif_line_chart.getData().add(series1_ACS);
		}

		
/////////////////////////////////////////////  genetique algo
		
	 
	 @FXML
	 void processGA(ActionEvent event) {
		    GeneticAlgo.setCROSSOVER_CHANCE(Double.valueOf(spinnerCrossGA.getValue()));
		 	GeneticAlgo.setMUTATION_CHANCE(Double.valueOf(spinnerMutatioGA.getValue()));
		 	GeneticAlgo.setSELECTION_SIZE(Integer.valueOf(spinnerSelectionGA.getValue()));
		 	GeneticAlgo.setNUMBER_OF_INSTENCES(Integer.valueOf(spinnerNbrInstenceGA.getValue()));
		 	GA.setDEFAULT_TIMEOUT(sliderGA.getValue());
		 	 GA.runGeneticAlgo();
			 loadLineDataGA();
			 loadBarDataGA();
			 loadPieDataGA();
			 
			 TimeGA.setText("Time:"+String.valueOf((double)GA.TOTAL_TIME/60000)+" min");
	    }



	private void loadPieDataGA() {
		
		int i = 0;
		float global_accuracy=0;
		for (Data<Number, Number> data : GA.load_data_for_lineChart) {
			 global_accuracy = global_accuracy + Float.parseFloat(data.getYValue().toString());
			 i++;
		}
		global_accuracy = global_accuracy/i;
		ObservableList<PieChart.Data> ol = FXCollections.observableArrayList(
				new PieChart.Data("accuracy of global satisfed instences: %"+global_accuracy,global_accuracy),
				new PieChart.Data("accuracy of global no satisfed instences: %"+(100-global_accuracy),100-global_accuracy));
		pieChartGA.setData(ol);
		GA.load_data_for_lineChart.clear();
		
	}
	private void loadBarDataGA() {
		
		Series<Number, Number> series2_GA = new Series<Number, Number>();
		series2_GA.setName("Time exe for each instence in ms (GA)");
		for (Data<Number, Number> data : GA.load_data_for_BarChart) {
			series2_GA.getData().add(data);
		}
		barChartGA.getData().add(series2_GA);
		GA.load_data_for_BarChart.clear();
	}
	private void loadLineDataGA() {
		
		Series<Number, Number> series1_GA = new Series<Number, Number>();
		series1_GA.setName("accuracy of GA");
		for(Data<Number, Number> data : GA.load_data_for_lineChart) {
			series1_GA.getData().add(data);
		}
		lineChartGA.getData().add(series1_GA);
		seriesList.add(series1_GA);
		//comparatif_line_chart.getData().add(series1_GA);
		
	}


	
	/////////////////////////////////////////////// afs
	@FXML
	public void processDFS(ActionEvent e) {
		DfsAndAstar.NUMBER_OF_INSTANCES_TO_USE = Integer.valueOf(spinnerDFS.getValue());
		//System.out.println(Load.NUMBER_OF_INSTANCES_TO_USE);
		Algorithmes.DEFAULT_TIMEOUT = (long)sliderDFS.getValue();
		//System.out.println(Algorithmes.DEFAULT_TIMEOUT);
		
		
		DfsAndAstar.runAlgoDFS();
		
		loadLineDataDFS();
		loadBarDataDFS();
		loadPieDataDFS();
		
		TimeDFS.setText("Time: "+String.valueOf((double)DfsAndAstar.DEPTH_FIRST_TOTAL_TIME/60000)+" min");
	}

	private void loadPieDataDFS() {
		
		int i = 0;
		float global_accuracy=0;
		for (Data<Number, Number> data : Algorithmes.list_of_data_accuracyDFS) {
			 global_accuracy = global_accuracy + Float.parseFloat(data.getYValue().toString());
			 i++;
		}
		global_accuracy = global_accuracy/i;
		ObservableList<PieChart.Data> ol = FXCollections.observableArrayList(
				new PieChart.Data("accuracy of global satisfed instences: %"+global_accuracy,global_accuracy),
				new PieChart.Data("accuracy of global no satisfed instences: %"+(100-global_accuracy),100-global_accuracy));
		pieChartDFS.setData(ol);
		Algorithmes.list_of_data_accuracyDFS.clear();
	}
	private void loadBarDataDFS() {
		
		XYChart.Series<Number, Number> s = new XYChart.Series<Number, Number>();
		s.setName("Time exe for each instence in ms (BFS)");
	
		for (Data<Number, Number> data : Algorithmes.list_of_data_satisfactionDFS) {
			
			s.getData().add(data);
		}
		
		barChartDFS.getData().add(s);
		Algorithmes.list_of_data_satisfactionDFS.clear();
	}
	private void loadLineDataDFS() {
		
		
		XYChart.Series<Number, Number> serieBFS = new XYChart.Series<Number, Number>();
		serieBFS.setName("accuracy of DFS");
	
		for (XYChart.Data<Number,Number> data : Algorithmes.list_of_data_accuracyDFS) {
			serieBFS.getData().add(data);
		}
		
		lineChartDFS.getData().add(serieBFS);
		seriesList.add(serieBFS);
		//comparatif_line_chart.getData().add(serie);
		
	}
	
	
	
	////////////////////////////////////////////// astar

	 @FXML
	 void processAstar(ActionEvent event) {
		 	DfsAndAstar.NUMBER_OF_INSTANCES_TO_USE = Integer.valueOf(spinnerAstar.getValue());
			//System.out.println(Load.NUMBER_OF_INSTANCES_TO_USE);
			Algorithmes.DEFAULT_TIMEOUT = (long)sliderAstar.getValue();
			//System.out.println(Algorithmes.DEFAULT_TIMEOUT);
			
			
			DfsAndAstar.runAlgoAstar();
			
			loadLineDataAstar();
			loadBarDataAstar();
			loadPieDataAstar();
			
			TimeAstar.setText("Time:"+String.valueOf((double)DfsAndAstar.A_STAR_TOTAL_TIME/60000)+" min");
			
	 }
		private void loadPieDataAstar() {
	
			int i = 0;
			float global_accuracy=0;
			for (Data<Number, Number> data : Algorithmes.list_of_data_accuracyAstar) {
				 global_accuracy = global_accuracy + Float.parseFloat(data.getYValue().toString());
				 i++;
			}
			global_accuracy = global_accuracy/i;
			ObservableList<PieChart.Data> ol = FXCollections.observableArrayList(
					new PieChart.Data("accuracy of global satisfed instences: %"+global_accuracy,global_accuracy),
					new PieChart.Data("accuracy of global no satisfed instences: %"+(100-global_accuracy),100-global_accuracy));
			pieChartAstar.setData(ol);
			Algorithmes.list_of_data_accuracyAstar.clear();
		}
		private void loadBarDataAstar() {

			XYChart.Series<Number, Number> s = new XYChart.Series<Number, Number>();
			s.setName("Time exe for each instence in ms (Astar)");
		
			for (Data<Number, Number> data : Algorithmes.list_of_data_satisfactionAstar) {
				
				s.getData().add(data);
			}
			
			barChartAstar.getData().add(s);
			Algorithmes.list_of_data_satisfactionAstar.clear();
		}
		private void loadLineDataAstar() {
		
		XYChart.Series<Number, Number> serieAstar = new XYChart.Series<Number, Number>();
		serieAstar.setName("accuracy of Astar");
	
		for (XYChart.Data<Number,Number> data : Algorithmes.list_of_data_accuracyAstar) {
			serieAstar.getData().add(data);
		}
		
		lineChartAstar.getData().add(serieAstar);
		seriesList.add(serieAstar);
		
	}

	//////////////////////////////////////////////// comparaisson 
	@FXML
	void processCompare(ActionEvent event) {
		
		comparatif_line_chart.getData().addAll(seriesList);

	}
	
	
}
