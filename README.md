#Solving-Traveling  Salesman Problem using Ant Colony Optimization (with Particle Swarm Optimization for its parameter tuning)

Authors:  
https://github.com/Yashar-H08  
https://github.com/ArazG5

Algorithm:  
For this problem, ACO algorithm is used which has the following parameters:  
alpha: the importance of pheromone level in comparison to length of edge (alpha>1 means that the pheromone level is more important).  
vaporization rate: at the end of each round, pheromone levels are lowered by vaporization.  
no. of rounds: number of times that all ants will tour the whole graph.  
no. of ants: number of ants  
  
Finding best set of parameters for ACO is itself an optimization problem which is solved using PSO:  
a position in PSO is defined as a tuple of size 4 of ACO's parameters. Velocity is defined similarly.  
Fitness function is ACO's answer and our goal is to minimize it.  
Also, each parameter has a range (minimum and maximum).  
PSO gives us the best set of parameters which is then used by ACO to give us the optimum answer (closest to minimum tour cost) and  
the path.  
Time complexity: O(INRA(n^3))  
I: number of times that PSO is executed multiplied by number of times that ACO is executed to find fitness of a position  
N: number of particles (for PSO) which is a multiple of number of cities  
R: no. of rounds  
A: no. of ants  
n: number of cities  
  
Results on adjacency matrix on https://people.sc.fsu.edu/~jburkardt/datasets/tsp/dantzig42_d.txt (with 42 nodes and 699 as the ultimate correct answer):  
    I=50  
    maxRound=1 maxAnts=1 => ans:2070   time:4s  
    maxRound=10 maxAnts=10 => ans:893   time:102s  
    maxRound=5 maxAnts=20 => ans:966   time:110s  
    maxRound=10 maxAnts=20 => ans:796   time:195s  
      
   I=20  
   maxRound=10 maxAnts=20 => ans:844   time:79s  
