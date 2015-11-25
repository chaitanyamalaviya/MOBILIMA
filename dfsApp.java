import java.awt.AlphaComposite;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class dfsApp {
    private static final int MAX_VERTICES = 10;
    static int cc[];
    static int ccIndex = 1;
    static boolean visited[];
    
    public static void main(String []args){
        List[] graph = new List[MAX_VERTICES];
        cc = new int[MAX_VERTICES];
        visited = new boolean[MAX_VERTICES];
        
        //initialize graph array to be an array of linked list
        for (int i=0; i<MAX_VERTICES; i++){
            graph[i] = new LinkedList<Integer>();
        }
        
        //create a toy graph with 10 vertices
        createGraph(graph);
        //System.out.println(graph);
        
        //set all vertices to unvisited
        for (int i=0; i<MAX_VERTICES; i++)
            visited[i] = false;
        
        dfsSweep(graph);
        
        //print out the array cc
        for (int i= 0; i<MAX_VERTICES; i++){
            System.out.print(cc[i]);
        }
        System.out.println();
    }
    
    public static void createGraph(List[] graphList){
        //read file into list
        String lineTemp;
        String value = "";
        char charTemp;
        int valueTemp;
        try{
            Scanner sc = new Scanner(new File("C:/graph.txt"));
            for (int i=0; i<graphList.length; i++){
                //copy each line of values from the text file into each linkedlist in graphList
                if ((lineTemp = sc.nextLine()) != null){
                    for (int j=0; j<lineTemp.length(); j++){
                        if ((charTemp = lineTemp.charAt(j)) != ','){
                            valueTemp = Character.getNumericValue(charTemp);
                            graphList[i].add(valueTemp);
                        }
                    }
                    /*while (j <= (lineTemp.length()-1)){
                        while ((lineTemp.charAt(j) != ',')){
                            value = Character.toString(lineTemp.charAt(j));
                            j++;
                            if (j>(lineTemp.length()-1))
                                break;
                        }
                        if (j>(lineTemp.length()-1))
                            break;
                        if (lineTemp.charAt(j) == ','){
                            valueTemp = Integer.parseInt(value);
                            graphList[i].add(valueTemp);
                            j++;
                        }                        
                    }*/
                }
            }
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
    
    public static void dfsSweep(List[] graph){
        //for each vertex in graph
        for (int i=0; i<graph.length; i++){
            //if vertex is unvisited
            if (visited[i] == false)
                dfs(graph, i);
        }       
    }
    
    public static void dfs(List[] graph, int v){
        //non-resursive
        int vertex1, vertex2;
        //create a stack
        Stack<Integer> st = new Stack();
        //mark vertice as visited
        visited[v] = true;
        //push vertices that are adjacent to v onto stack
        for (int i=0; i<graph[v].size(); i++){
            st.push((Integer)(graph[v].get(i)));
        }
        //while the stack is occupied
        while (st.empty() == false){
            //pop off the first vertex in the stack
            vertex1 = st.pop();
            cc[vertex1] = ccIndex;
            //mark pop off vertex as visited
            visited[vertex1] = true;
            //traverse all vertices adjacent to the vertex that was pop off
            for (int j=0; j<graph[vertex1].size(); j++){
                vertex2 = (Integer)(graph[vertex1].get(j));
                if (visited[vertex2] == false){
                   //mark vertex as visited
                   visited[vertex2] = true;
                   //push vertex onto the stack
                   st.push(vertex2);                    
                }
            }
        }
        cc[v] = ccIndex;
        ccIndex++;
        
        /* recursive
        int x, temp;
        //mark vertice as visited
        visited[v] = true;
        //for each neighbour w of v
        for (int i=0; i<graph[v].size(); i++){
            temp = (Integer)(graph[i].get(i));
            if (visited[temp] == false)
                dfs(graph,temp);
        }*/
    }
}
