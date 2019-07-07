package javaapplication28;

import java.util.Random;

public class Particle {
    public static final double minAlpha=0.1, maxAlpha=10.0, minVap=0.01, maxVap=0.99, 
            minRound=5.0, maxRound=10.0, minAnts=5.0, maxAnts=20.0;
    
    public double alpha, vap, round, ants;
    private static Random myRand = new Random();
    
    public Particle() { //returns random particle in range [min,max]
        alpha=getRand(minAlpha,maxAlpha);
        vap=getRand(minVap,maxVap);
        round=getRand(minRound,maxRound);
        ants=getRand(minAnts,maxAnts);
    }
    
    public Particle(double alph, double bet, double vaporization, double roun, double ant) {
        alpha=alph;
        vap=vaporization;
        round=roun;
        ants=ant;
    }
    
    private static double getRand(double min, double max) {
        if(min>max) //swap
        {
            double db=min;
            min=max;
            max=db;
        }
        return min+(max-min)*myRand.nextDouble();
    }
    
    public static void copyInto(Particle p, Particle p2) {
        p.alpha=p2.alpha;
        p.vap=p2.vap;
        p.round=p2.round;
        p.ants=p2.ants;
    }
    
    public void roundUp() {
        round=Math.ceil(round);
        ants=Math.ceil(ants);
    }
    
    public static Particle mult(double w, Particle pt) {
        Particle res=new Particle();
        copyInto(res,pt);
        res.alpha*=w; res.vap*=w; res.round*=w; res.ants*=w;
        return res;
    }
    
    public static Particle sum(Particle pt, Particle pt2, int keepInRange) {
        Particle res=new Particle();
        copyInto(res,pt);
        res.alpha+=pt2.alpha; res.vap+=pt2.vap; res.round+=pt2.round; res.ants+=pt2.ants;
        if(keepInRange==1) //keeping parameters in their ranges
        {
            res.alpha=Math.max(res.alpha,minAlpha);
            res.alpha=Math.min(res.alpha,maxAlpha);

            res.vap=Math.max(res.vap,minVap);
            res.vap=Math.min(res.vap,maxVap);
            
            res.round=Math.max(res.round,minRound);
            res.round=Math.min(res.round,maxRound);
            
            res.ants=Math.max(res.ants,minAnts);
            res.ants=Math.min(res.ants,maxAnts);
        }
        return res;
    }
    
    public static Particle sub(Particle pt, Particle pt2) {
        return sum(pt,mult(-1.0,pt2),0);
    }
   
}
