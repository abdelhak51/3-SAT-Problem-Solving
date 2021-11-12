package model_sat;
import java.util.List;

import aco.*;
import dfs_and_astar.Solution;


public class Sat {

    private List<Clause> clauses;

    public Sat(List<Clause> clauses) {
        this.clauses = clauses;
    }

    public List<Clause> getClauses() {
        return clauses;
    }

    public void setClauses(List<Clause> clauses) {
        this.clauses = clauses;
    }

    public boolean satisfied(Solution solution) {
        int[] values = solution.getValues();
        boolean sat = false;
        for (Clause c : clauses) {
            sat = false;
            for(Litteral l : c.getLitterals()){
                if(l.getLitteralValue(values[l.getVar()-1]) == 1){
                    sat = true;
                }
            }
            if(!sat)
                return false;
        }
        return true;
    }
    
    //////////////////////////////////////////  ANT COLONY OPTIMISATION 
    public boolean satisfied(Ant solution) {
        int[] values = solution.getValues();
        boolean sat = false;
        for (Clause c : clauses) {
            sat = false;
            for(Litteral l : c.getLitterals()){
                if(l.getLitteralValue(values[l.getVar()-1]) == 1){
                    sat = true;
                }
            }
            if(!sat)
                return false;
        }
        return true;
    }
    
    public int satisfiedClauses(Solution solution) {
        int satisfactions = 0;
        int[] values = solution.getValues();
        for (Clause c : clauses) {
            for(Litteral l : c.getLitterals()){
                if(l.getLitteralValue(values[l.getVar()-1]) == 1){
                    satisfactions ++;
                    break;
                }
            }
        }
        return satisfactions;
    }
    
    
    ////////////////////////////////////////////////// BFS AND Astar
    
    public int satisfiedClauses(int values[]) {
        int satisfactions = 0;
        for (Clause c : clauses) {
            for(Litteral l : c.getLitterals()){
                if(l.getLitteralValue(values[l.getVar()-1]) == 1){
                    satisfactions ++;
                    break;
                }
            }
        }
        return satisfactions;
    }

}
