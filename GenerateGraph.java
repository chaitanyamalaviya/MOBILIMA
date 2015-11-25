import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class GenerateGraph {
	
    static int cc[];
    static boolean visited[];
    static int ccID;
    static final int MAXSIZE = 50000;
    
	public static void main(String[] args) {
		int vertex = 10000;
		int edge;
		cc = new int[MAXSIZE];
		visited = new boolean[MAXSIZE];
		List[] graph = new List[MAXSIZE];
		ccID = 1;
		long startTime, endTime;
		
		//Initialise a linkedlist for each vertex
        for(int i=0; i<MAXSIZE; i++){
            graph[i] = new LinkedList<Integer>();
        }  
        int d = 8;
        createGraph(graph,10000,80000);
		dfsSweep(graph);
        for(int i=1; i<=5; i++){
        	System.out.println("Num Of Nodes\t\tNum Of Edges(m=" + (d) + "n)\t\tTime Taken (ms)");   
        	for(int j=0; j<5; j++, vertex += 10000){
        		edge = d * vertex;
        		//Initialise graph
        		for(int k=0; k<vertex; k++){
                    graph[k].clear();
                    visited[k] = false;
                    cc[k] = 0;
        		}
        		createGraph(graph,vertex,edge);
        		startTime = System.currentTimeMillis();
        		dfsSweep(graph);
        		endTime = System.currentTimeMillis();
        		System.out.println(vertex + "\t\t\t" + edge + "\t\t\t\t" + (endTime-startTime));
        	}
        	vertex = 10000;
        	d += 1;
        }
	}
    public static void createGraph(List[] graph, int vertex, int edge){
        int vertex1, vertex2, totaledge=0;
        Random rn = new Random();            
        while(totaledge < edge){  
        	//Take any two random vertexes
        	vertex1 = rn.nextInt(vertex);
        	vertex2 = rn.nextInt(vertex);            
            if(vertex1 != vertex2){
                    // If these two distinct vertexes are not connected, add edge between them
            	if(!graph[vertex1].contains((int)vertex2)){
            		graph[vertex1].add(vertex2);
                	graph[vertex2].add(vertex1);
                	totaledge+=1; 
            	}
            }
        }       
    }
    
    public static void dfsSweep(List[] graph){
        for(int i=0;i<graph.length;i++){
            if(visited[i]==false){
                // Perform iterative dfs
                dfs(graph,i);
            }
        }
    }
    
    public static void dfs(List[] graph, int v){
        int vertexAdj,vertexPop;
       
        //Using stack instead of recursion
        Stack<Integer> st = new Stack();
        visited[v] = true;
        // Push all vertexes that are adjacent to v onto stack
        for(int i=0;i<graph[v].size();i++){
            if(graph[v].get(i)!=null)
                st.push((Integer)graph[v].get(i));
        }
        while(!st.empty()){
        	vertexPop = st.pop();
            cc[vertexPop] = ccID;
            visited[vertexPop] = true;
            // For all vertexes that are adjacent to vertexPop
            for(int i=0;i<graph[vertexPop].size();i++){
            	vertexAdj = (Integer)graph[vertexPop].get(i);
                if(visited[vertexAdj]==false){                   
                    st.push(vertexAdj);
                    visited[vertexAdj] = true;
                }
            }
        }
        //Stack is empty, assign the ccID to the entry vertex
        // find one connected component
        cc[v] = ccID;
        ccID++;
    }

}
