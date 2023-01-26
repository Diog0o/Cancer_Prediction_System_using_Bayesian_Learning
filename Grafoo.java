package grafo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.*;
import java.lang.*;
import java.io.*;

import amostra.Amostra;
import tuple.Tuple;
import floresta.Forest;


public class Grafoo {
	int dim;
	double[][] ma;
	
	public Grafoo(int n) {
		this.dim = n;
		this.ma = new double[n][n];
	}
	
	public void add_edge(int o, int d, double p) {
		// falta proteger a funcao (peso tem que dar - entre 0 e 1?)
		if (o<this.dim && d < this.dim && o>=0 && d>= 0 && p>= 0) { //&& p>= 0 && p<=1
			this.ma[o][d]=p;
			
		}
		else {
			if(p < 0) {
				throw new AssertionError("Edge is not supported");
			}
			else {
				throw new AssertionError("Node is not in graph");
			}
		}
	}
	
	
	 public int maxKey(double key[], Boolean mstSet[]){ 
		 //int key  - lista com os pesos
		 //mstSet - se o no ja ta ligado
		 //int V - numero de nos do grafo
		 
	        // Initialize min value
	        double max = Integer.MIN_VALUE;
	        int max_index = -1;
	 
	        for (int v = 0; v < this.dim; v++)
	            if (mstSet[v] == false && key[v] > max) {
	                max = key[v];
	                max_index = v;
	            }
	 
	        return max_index;
	    }
	 
	 
	 
	 public int[] max_spanning_tree(){
		 double[][] graph = this.ma;
		 int V = this.dim;
	        
	     int parent[] = new int[V]; //resultado
	 
	        
	     double key[] = new double[V]; //lista que vai guardar a aresta mais pesada para chegar a cada no (tipo na posicao 0 guarda a aresta mais pesada que temos para chegar ao no zero)
	 
	     
	     Boolean mstSet[] = new Boolean[V]; //true se o no daquela posicao ja tiver sido visitado
	 
	     
	     for (int i = 0; i < V; i++) { //como as listas sao inicializadas a 0
	    	 key[i] = Integer.MIN_VALUE; //as entradas sao todas com - inf
	         mstSet[i] = false; //ainda nao visitamos nenhum no
	     }
	     key[V-1] = 0; 
	     parent[V-1] = -1; // escolhemos o ultimo no (classe) como raiz
	
	     for (int count = 0; count < V - 1; count++) {
	    	 int u = maxKey(key, mstSet); 
	 
	         mstSet[u] = true; //ja o visitamos agora
	 
	         for (int v = 0; v < V; v++) {
	            if (u<v){
	            	if (graph[u][v] != 0 && mstSet[v] == false && graph[u][v] > key[v]) {
		                parent[v] = u;
		                key[v] = graph[u][v];
		            }
	            	}
	            
	            else {
	            	if (graph[v][u] != 0 && mstSet[v] == false && graph[v][u] > key[v]) {
		                    parent[v] = u;
		                    key[v] = graph[v][u];
		                }
	            	}
	            }
	                
	            
	        }
	  
	        return parent;
	    }
	 
	 public Forest max_spanning_tree1(){
		 double[][] graph = this.ma;
		 int V = this.dim;
	        
	     Forest res = new Forest(V);
	     double key[] = new double[V];
	     Boolean mstSet[] = new Boolean[V]; 
	     for (int i = 0; i < V; i++) {
	    	 key[i] = Integer.MIN_VALUE; 
	         mstSet[i] = false; 
	     }
	 
	        
	     key[V-1] = 0;
	     
	     for (int count = 0; count < V - 1; count++) { 
	    	 int u = maxKey(key, mstSet); 
	         mstSet[u] = true; 
	         for (int v = 0; v < V; v++) {
	        	 
	            if (u<v){ 
	            	
	            	if (graph[u][v] != 0 && mstSet[v] == false && graph[u][v] > key[v]) {
	            		res.set_parent(u, v);
		                key[v] = graph[u][v];
		            }
	            	}
	            
	            else {
	            	if (graph[v][u] != 0 && mstSet[v] == false && graph[v][u] > key[v]) {
		                    res.set_parent(u, v);
		                    key[v] = graph[v][u];
		                }
	            	}
	            	}
	                
	            
	        }
	  
	        return res;
	    }
	 
	
	
	public static double weight(Amostra amostra, int F, int P) {
		int[] filhos = {F};
		int f = amostra.domain(F);
		int[] pai = {P};
		int p = amostra.domain(P);
		int[] var = {F,P};
		double res = 0;
		for (int x =0; x < f; x++) {
			int[] valx = {x};
			for (int y=0; y < p; y++) {
				int[] val = {x,y};
				int[] valy = {y};
				double pbb_fora = amostra.count(var,val)/amostra.length(); 
				if (pbb_fora != 0) {
					double log = (amostra.count(var,val)*amostra.length())/(amostra.count(pai,valy)*amostra.count(filhos, valx));
					res+= pbb_fora* Math.log(log);
				}
			}
		
		}
		return res;	
	}
	
	
	public static Grafoo g_completo(Amostra amostra) { //falta testar
		int nr_nos = amostra.nr_var();
		Grafoo res = new Grafoo(nr_nos);
		for (int i = 0; i< nr_nos; i++) {
			for (int j = i+1; j<nr_nos; j++) {
				res.add_edge(i, j, weight(amostra,j,i));
			}
		}
		return res;
	}
	
	
	@Override
	public String toString() {
		return "Grafoo [dim=" + dim + ", ma=" + Arrays.deepToString(ma) + "]";
	}
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Amostra amostra = new Amostra("diabetes.csv");
		Grafoo exp = new Grafoo(6);
		exp.add_edge(0, 1, 6);
		exp.add_edge(0, 2, 1);
		exp.add_edge(0, 3, 5);
		exp.add_edge(1, 2, 2);
		exp.add_edge(1, 4, 5);
		exp.add_edge(2, 3, 2);
		exp.add_edge(2, 5, 4);
		exp.add_edge(2, 4, 6);
		exp.add_edge(4, 5, 3);
		exp.add_edge(3, 5, 4);
 		//System.out.println(exp);
 		Grafoo boa = g_completo(amostra);
 		System.out.println(boa);
 		System.out.println(Arrays.toString(boa.max_spanning_tree()));
 		System.out.println(boa.max_spanning_tree1());
	}
	}
