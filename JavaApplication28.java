package javaapplication28;

import java.util.Vector;

public class JavaApplication28 {

    public static void main(String[] args) 
    {
        Graph G=new Graph();
        G.getAdj();        
        PSO pso=new PSO();
        Particle params=pso.run();
        ACO aco=new ACO((int)params.alpha,1.0,params.vap,((int)params.ants),((int)params.round));
        Particle.dbg("Minimum tour cost:\n");
        System.out.println(aco.Cost(aco));
        System.out.println("Optimum tour:");
        System.out.println(aco.GetPath(aco));
        Particle.dbg("Parameters for optimum ACO:\n");
        Particle.show(params);
    }
    /*
    Results on adjacency matrix on https://people.sc.fsu.edu/~jburkardt/datasets/tsp/dantzig42_d.txt (with 42 nodes):
    iter1=50
    maxRound=1 maxAnts=1 => ans:2070   time:4
    maxRound=10 maxAnts=10 => ans:893   time:102
    maxRound=5 maxAnts=20 => ans:966   time:110
    maxRound=10 maxAnts=20 => ans:796   time:195
    
    iter1=20
    maxRound=10 maxAnts=20 => ans:844   time:79
    */
}
