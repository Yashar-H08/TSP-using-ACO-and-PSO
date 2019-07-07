package javaapplication28;

public class PSO {
    private int N=2; //number of particles
    private final int iter1=50, iter2=1;
    private final double c1=2.0, c2=2.0, minW=0.1, maxW=1.0, inertiaUpdate=0.99, chi=0.729, inf=1000000000.0;
    
    private Particle[] particles, velocity, particleBest; //each particle's position and velocity and personal best
    private double gbest, w; //best found answer of ACO and inertia
    Particle swarmBest; //global best
    private double[] pbest;
    
    public PSO() { //initialize parameters
        N*=Graph.N;
        w=maxW;
        gbest=inf;
        swarmBest=new Particle();
        pbest=new double[N];
        particleBest=new Particle[N];        
        for(int i=0; i<N; i++)
        {
            pbest[i]=inf;
            particleBest[i]=new Particle();
        }
        velocity=new Particle[N];
        for(int i=0; i<N; i++) velocity[i]=new Particle();
        particles=new Particle[N];
        for(int i=0; i<N; i++) particles[i]=new Particle();
        //randomized initialization covering the whole range:
        double num=(double) ((int) N/2);
        for(int i=0; i<N/2; i++)
        {
            particles[i]=new Particle();
            particles[i].alpha=Particle.minAlpha+((double)i)*(Particle.maxAlpha-Particle.minAlpha)/num;
            particles[i].vap=Particle.minVap+((double)i)*(Particle.maxVap-Particle.minVap)/num;
            particles[i].round=Particle.minRound+((double)i)*(Particle.maxRound-Particle.minRound)/num;
            particles[i].ants=Particle.minAnts+((double)i)*(Particle.maxAnts-Particle.minAnts)/num;
        }
        for(int i=0; i<N; i++) particles[i]=Particle.sum(particles[i],new Particle(0.0,0.0,0.0,0.0,0.0),1); //Keeping in range
    }
    
    public Particle run() { //run PSO
        for(int i1=0; i1<iter1 && w>=minW; i1++)
        {
            //System.out.println("Iteration "+i1+"...");
            for(int i=0; i<N; i++)
            {
                double best=inf;
                particles[i].roundUp();
                particles[i]=Particle.sum(particles[i],new Particle(0.0,0.0,0.0,0.0,0.0),1); //Keeping in range
                Particle pt=new Particle();
                Particle.copyInto(pt,particles[i]);
                for(int i2=0; i2<iter2; i2++)
                {
                    ACO aco=new ACO(pt.alpha,1.0,pt.vap,((int)pt.ants),((int)pt.round));
                    double val=aco.Cost(aco);
                    best=Math.min(best,val);
                }
                if(best<pbest[i])
                {
                    pbest[i]=best;
                    Particle.copyInto(particleBest[i],pt);
                }
                if(best<gbest)
                {
                    gbest=best;
                    Particle.copyInto(swarmBest,pt);
                }
            }
            w*=inertiaUpdate;
            for(int i=0; i<N; i++)
            {
                //updating velocity:
                double r1=Math.random(), r2=Math.random();
                Particle p1=Particle.mult(w,velocity[i]), 
                        p2=Particle.mult(c1*r1, Particle.sub(particleBest[i],particles[i])), 
                        p3=Particle.mult(c2*r2, Particle.sub(swarmBest,particles[i]));
                velocity[i]=Particle.sum(Particle.sum(p1,p2,0),p3,0);
                //updating particle positions:
                particles[i]=Particle.sum(particles[i],Particle.mult(chi, velocity[i]),1); //Keeping in range
            }
        }
        return swarmBest;
    }
    
}
