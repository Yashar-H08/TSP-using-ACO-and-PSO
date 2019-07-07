package javaapplication28;

import java.util.Scanner;

public class Graph 
{
    public static int N,M; //  No.Nodes , No.Edges .......  Nodes range : [0 , N-1]
    private final static int maxNodes=200;
    private final static double inf=10000000.0;
    public static double [][] adj=new double [maxNodes][maxNodes];
    
    public Graph()
    {
        N=1;
    }
    
    public Graph(int numberOfNodes,double[][] arr)
    {
        N=numberOfNodes;
        for(int i=0;i<N;i++)
            for(int j=0;j<N;j++)
                adj[i][j]=arr[i][j];
    }
    
    public void getAdj() {
        Scanner input = new Scanner(System.in);
        N = input.nextInt(); // number of nodes
        M=N*N;
        for(int i=0; i<N; i++)
        {
            for(int j=0; j<N; j++) adj[i][j]=input.nextDouble();
        }
    }
    
    public void GetGraph()
    {
        Scanner input = new Scanner(System.in);
        N = input.nextInt(); // number of nodes
        M = input.nextInt(); // number of edges 
        
        for(int i=0;i<N;i++)
            for(int j=0;j<N;j++)
                adj[i][j]=inf;
        
        for(int i=0;i<M;i++)
        {
            int a,b;
            double c;
            a = input.nextInt(); // node 1
            b = input.nextInt(); // node 2
            c = input.nextDouble(); // distance
            adj[a][b]=adj[b][a]=c;
        }
    }
    
}
