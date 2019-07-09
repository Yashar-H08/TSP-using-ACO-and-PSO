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
        System.out.println("Enter N (number of nodes):");
        Scanner input = new Scanner(System.in);
        N = input.nextInt(); // number of nodes
        M=N*N;
        //S=0;
        System.out.println("Now enter adjacency matrix entries seperated by space or newline:");
        for(int i=0; i<N; i++)
        {
            for(int j=0; j<N; j++) 
            {  
                adj[i][j]=input.nextInt();
                if(i==j) adj[i][j]=10000000;
            }
        }
    }
    
}
