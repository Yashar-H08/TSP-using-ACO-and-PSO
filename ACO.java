/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication28;

import java.util.Random;
import java.util.Stack;
import java.util.Vector;

/**
 *
 * @author shila
 */
public class ACO 
{
    double alpha, beta, vaporization; // vap=0.8 bashe, yani har round 20% esh tabkhir mishe..........
    int ants, rounds;
    public ACO(double a ,double b, double v,int n, int r)
    {
        alpha=a;
        beta=b;
        vaporization=v;
        ants=n;
        rounds=r;
    }
    public double Cost(ACO A) //
    {   
        //Particle.dbg("Start of Cost\n");
        double ans=1000000000.0;
        for(int fun=0;fun<1;fun++) // khodam amdan 3 bar anjam midam hamasho az aval, just to be sure.......
        {
            double [][] ph=new double [50][50]; // phermonme levels
            double [][] ph2=new double [50][50]; // this is the phermones that each ant leaves behind which we sum it up until the round ends so we
                                                 // pour it int ph+=ph2 and we empty ph2=0 and do the same for the next round......
            for(int i=0;i<Graph.N;i++) // initilize phermone levels
                for(int j=0;j<Graph.N;j++)
                {
                    if(i!=j)ph[i][j]=1.0;
                    else ph[i][j]=0.001;
                }
            
            for(int i=0;i<rounds;i++) // do this for this many Rounds:
            {
                for(int e1=0;e1<Graph.N;e1++) // initilize phermone levels
                    for(int e2=0;e2<Graph.N;e2++)
                        ph2[e1][e2]=0.0;
                for(int j=0;j<ants;j++) // do this for this many Ants:
                {
                    boolean [] visited=new boolean [Graph.N]; // do a DFS to find a good path:
                    double sumDistThisAnt=0;
                    for(int k=0;k<Graph.N;k++)
                        visited[k]=false;
                    Vector<Integer> path = new Vector<>();
                    //path.add(Graph.S);
                    path.add(0);//////////////////////////////////////////////////////////////////new
                    //visited[Graph.S]=true;
                    visited[0]=true;/////////////////////////////////////////////////////////////new
                    /////////////////////////////
                    while(path.size()!=Graph.N) // find a good path for this ant
                    {
                        int u=path.get(path.size()-1); // current position
                        double sumProb=0.0; // jame ehtemalashun
                        for(int k=0;k<Graph.N;k++)
                        {
                            if(visited[k]==true) continue;
                            sumProb+=((double)(Math.pow((double)ph[u][k],(double)A.alpha))/(Math.pow((double)Graph.adj[u][k],(double)A.beta)));
                        }
                        Random r = new Random();
                        double random = 0.0 + (sumProb - 0.0) * r.nextDouble(); // random double [0 , sumProb]
                        
                        sumProb=0.0;
                        for(int k=0;k<Graph.N;k++)
                        {
                            if(visited[k]==true) continue;
                            sumProb+=((double)(Math.pow((double)ph[u][k],(double)A.alpha))/(Math.pow((double)Graph.adj[u][k],(double)A.beta)));
                            if(random<=sumProb)
                            {
                                path.add(k);
                                visited[k]=true;
                                sumDistThisAnt+=(Graph.adj[u][k]);
                                break;
                            }
                        }
                    }
                    //sumDistThisAnt+=Graph.adj[path.get(path.size()-1)][Graph.S]; // return to the starting point:
                    sumDistThisAnt+=Graph.adj[path.get(path.size()-1)][0]; // return to the starting point:///////////new
                    //path.add(Graph.S);
                    path.add(0);////////////////////////////////////////////////////new
                    ans=Math.min(ans,sumDistThisAnt);   
                    for(int k=0;k<Graph.N;k++) // hala ph2 : 1/sumDist bayad ezafe beshe be masire rafte shode be ph2, ph2: in round majmu phermone ant ha
                    {
                        ph2[path.get(k)][path.get(k+1)]+=((double)((double)1.0/(double)sumDistThisAnt));
                        ph2[path.get(k+1)][path.get(k)]+=((double)((double)1.0/(double)sumDistThisAnt));
                    }
                }
                // ant ha ke tamum shod, hala ph2 ro mizarim tooye ph va vaporization etefag miyofte:
                for(int j=0;j<Graph.N;j++)
                {
                    for(int k=0;k<Graph.N;k++)
                    {
                        ph[j][k]+=ph2[j][k];
                        ph[j][k]*=A.vaporization;
                    }
                }
            }
   /*
   ///////////////////////////////////////////
            //  DEBUGING:
            for(int i=0;i<Graph.N;i++)
            {
                for(int j=0;j<Graph.N;j++)
                {
                    System.out.print(ph[i][j]+" ");
                }
                System.out.print("\n");
            }
   //////////////////////////////////////////////
   */
        }
        //Particle.dbg("End of Cost\n");
        return ans;
    }
    public Vector<Integer> GetPath(ACO A) //
    {   
        //Particle.dbg("Start of GetPath\n");
        Vector<Integer> bestPath = new Vector<>();
        double ans=1000000000.0;
        for(int fun=0;fun<1;fun++) // khodam amdan 3 bar anjam midam hamasho az aval, just to be sure.......
        {
            double [][] ph=new double [50][50]; // phermonme levels
            double [][] ph2=new double [50][50]; // this is the phermones that each ant leaves behind which we sum it up until the round ends so we
                                                 // pour it int ph+=ph2 and we empty ph2=0 and do the same for the next round......
            for(int i=0;i<Graph.N;i++) // initilize phermone levels
                for(int j=0;j<Graph.N;j++)
                {
                    if(i!=j)ph[i][j]=1.0;
                    else ph[i][j]=0.001;
                }
            
            for(int i=0;i<rounds;i++) // do this for this many Rounds:
            {
                for(int e1=0;e1<Graph.N;e1++) // initilize phermone levels
                    for(int e2=0;e2<Graph.N;e2++)
                        ph2[e1][e2]=0.0;
                for(int j=0;j<ants;j++) // do this for this many Ants:
                {
                    boolean [] visited=new boolean [Graph.N]; // do a DFS to find a good path:
                    double sumDistThisAnt=0;
                    for(int k=0;k<Graph.N;k++)
                        visited[k]=false;
                    
                    Vector<Integer> path = new Vector<>();
                    //path.add(Graph.S);
                    path.add(0);/////////////////////////////////////////////new
                    //visited[Graph.S]=true;
                    visited[0]=true;///////////////////////////////////////new
                    /////////////////////////////
                    while(path.size()!=Graph.N) // find a good path for this ant
                    {
                        int u=path.get(path.size()-1); // current position
                        double sumProb=0.0; // jame ehtemalashun
                        for(int k=0;k<Graph.N;k++)
                        {
                            if(visited[k]==true) continue;
                            sumProb+=((double)(Math.pow((double)ph[u][k],(double)A.alpha))/(Math.pow((double)Graph.adj[u][k],(double)A.beta)));
                        }
                        Random r = new Random();
                        double random = 0.0 + (sumProb - 0.0) * r.nextDouble(); // random double [0 , sumProb]
                        
                        sumProb=0.0;
                        for(int k=0;k<Graph.N;k++)
                        {
                            if(visited[k]==true) continue;
                            sumProb+=((double)(Math.pow((double)ph[u][k],(double)A.alpha))/(Math.pow((double)Graph.adj[u][k],(double)A.beta)));
                            if(random<=sumProb)
                            {
                                path.add(k);
                                visited[k]=true;
                                sumDistThisAnt+=(Graph.adj[u][k]);
                                break;
                            }
                        }
                    }
                    //sumDistThisAnt+=Graph.adj[path.get(path.size()-1)][Graph.S]; // return to the starting point:
                    sumDistThisAnt+=Graph.adj[path.get(path.size()-1)][0]; // return to the starting point:////////////////////new
                    //path.add(Graph.S);
                    path.add(0);////////////////////////////////////////////////////new
                    ////////////////////////   the IMP part:
                    if(bestPath.isEmpty()==true || sumDistThisAnt<ans) // if this path better choose this as best path
                    {
                        bestPath=new Vector<>();
                        for(int f=0;f<path.size();f++)
                        {
                            bestPath.add(path.get(f));
                        }
                    }
                    ans=Math.min(ans,sumDistThisAnt);   
                    for(int k=0;k<Graph.N;k++) // hala ph2 : 1/sumDist bayad ezafe beshe be masire rafte shode be ph2, ph2: in round majmu phermone ant ha
                    {
                        ph2[path.get(k)][path.get(k+1)]+=((double)((double)1.0/(double)sumDistThisAnt));
                        ph2[path.get(k+1)][path.get(k)]+=((double)((double)1.0/(double)sumDistThisAnt));
                    }
                }
                // ant ha ke tamum shod, hala ph2 ro mizarim tooye ph va vaporization etefag miyofte:
                for(int j=0;j<Graph.N;j++)
                {
                    for(int k=0;k<Graph.N;k++)
                    {
                        ph[j][k]+=ph2[j][k];
                        ph[j][k]*=A.vaporization;
                    }
                }
            }
   /*
   ///////////////////////////////////////////
            //  DEBUGING:
            for(int i=0;i<Graph.N;i++)
            {
                for(int j=0;j<Graph.N;j++)
                {
                    System.out.print(ph[i][j]+" ");
                }
                System.out.print("\n");
            }
   //////////////////////////////////////////////
   */
        }
        //Particle.dbg("End of GetPath\n");
        return bestPath;
    }
}
