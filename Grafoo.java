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
	
	/*
	 * public int[] max_spanning_tree() {
	 
		//começar pelo no 0
		//ver arestas e adicionar as possiveis 
		// escolher a mais pesada entre as possiveis 
		//retira-la das possiveis
		//ir para esse no
		//adicionar o no aos visitados
		//repeat
		int[] r = new int[this.dim -1];
		//lista guardar os nos visitados
		// lista guardar arestas visitadas
		// lista de arestas possiveis, ordenadas por tamanho
		//garantir que aresta possivel nao vai para no visitado
		//funcao auxiliar para ver arestas (check)
		// funcao auxiliar para escolher a mais pesada (check)
		// ver se a mais pesada pode ser
		// retirar a escolhida
		
		//lista de arestas
		return r;
	}
	
*/
	
	
	

	
	/* public static void quickSort(ArrayList<Tuple> vec, int left, int right) { //do maior para o menor
		int r;
		if (right > left) {
		r = partition(vec, left, right); 
		quickSort(vec, left, r - 1); 
		quickSort(vec, r + 1, right); 
		} 
		} 
	
	private static int partition(ArrayList<Tuple> vec, int left, int right) {
		int i=left, j=left + 1;
		while(j<=right) {
			if (vec.get(j).weight>=vec.get(left).weight){
				i++;
				swap(vec,i,j);
				
				}
			
			j++;
		}
		swap(vec,left, i);
		return i;
		} 
	
	private static void swap(ArrayList<Tuple> vec,int a,int b) {
		Tuple tmp = vec.get(a);
		vec.set(a, vec.get(b));
		vec.set(b, tmp);
		
		}
	*/


	
	/* public ArrayList<Tuple> see_edges(int node) {
		ArrayList<Tuple> r = new ArrayList<Tuple>();
		double[] lista_nos=this.ma[node];
		for (int i = 0; i< this.dim; i++) {
			if(lista_nos[i]!=0) {
				Tuple aresta = new Tuple();
				aresta.weight = lista_nos[i];
				aresta.o = node;
				aresta.d = i;
				r.add(aresta);
			}
		}
		for(int i= 0; i<this.dim;i++) {
			if (this.ma[i][node]!=0) {
				Tuple aresta = new Tuple();
				aresta.weight = lista_nos[i];
				aresta.o = node;
				aresta.d = i;
				r.add(aresta);
			}
		}
		return r;
	}
	
	*/
	
	/*public Tuple fattest_edge(ArrayList<Tuple> lista_arestas) {
		double w = 0;
		Tuple res = new Tuple();
		for (int i =0; i<lista_arestas.size() ; i++) {
			if (lista_arestas.get(i).weight > w ) {
				w=lista_arestas.get(i).weight;
				res= lista_arestas.get(i);
				
			}
		}
		return res;
	}
	*/
	
	
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
	 
	        
	     key[V-1] = 0; //pomos so que a aresta para chegar a classe e zero, para que, sendo este o valor maior, seja escolhido em primeiro lugar
	     //e assim partimos da classe
	     //como dissemos que e zero tambem nao estamos a cometer erro nenhum para depois (hopefully)
	     
	     parent[V-1] = -1; // escolhemos o ultimo no (classe) como raiz
	
	     for (int count = 0; count < V - 1; count++) { //o ciclo so vai correr 10 vezes (so queremos passar por 10 nos)
	    	 int u = maxKey(key, mstSet); //vamos sempre escolher o vertice ainda nao visitado que pode ser atingido pela aresta mais pesada
	 
	         mstSet[u] = true; //ja o visitamos agora
	 
	         for (int v = 0; v < V; v++) {
	        	 //chegamos agora a um novo no
	        	 //queremos ver se ele se liga aos outros nos por arestas mais pesadas que os nos anteriores
	        	 //vamos ver as arestas dele para cada no (ciclo for)
	        	 //nao nos interessa atualizar as arestas para os nos que ja visitamos (dai o mstSet[v] == false)
	 
	                // graph[u][v] is non zero only for adjacent vertices of m
	        	 
	        	 
	            if (u<v){ //na nossa implementacao, como o grafo nao e orientado, preenchemos apenas a metade superior da matriz
	            	// ie, as arestas so estao representadas na entrada no no menor para o maior
	            	//assim, se u<v, vamos ver a entrada [u][v]
	            	
	            	if (graph[u][v] != 0 && mstSet[v] == false && graph[u][v] > key[v]) {
	            		//se o valor da aresta para o no v for superior ao valor da aresta que ja la esta, substituimos
	            		//tornamos tambem u o pai de v, porque, por agora, vai ser a maneira de la chegar
	            		
		                parent[v] = u;
		                key[v] = graph[u][v];
		            }
	            	}
	            
	            else {
	            	//caso u>v, vamos ver a entrada [v][u], mas o principio e o mesmo
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
	     for (int i = 0; i < V; i++) { //como as listas sao inicializadas a 0
	    	 key[i] = Integer.MIN_VALUE; //as entradas sao todas com - inf
	         mstSet[i] = false; //ainda nao visitamos nenhum no
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
	 
	 /*public void printMST(int parent[], int graph[][], int V)
	    {
	        System.out.println("Edge \tWeight");
	        for (int i = 1; i < V; i++)
	            System.out.println(parent[i] + " - " + i + "\t" + graph[i][parent[i]]);
	    }
	    
	    */
	

	
	/*public static void retira(ArrayList<Tuple> arestas, int pai) {
		for (int i=0; i < arestas.size(); i++) {
			if (arestas.get(i).d == pai) {
				arestas.remove(i);
			}
		}
	}
	*/
	
	
	public static double weight(Amostra amostra, int F, int P) {
		int[] filhos = {F};
		int f = amostra.domain(F); //f = numero de valores que Filho pode tomar
		int[] pai = {P};
		int p = amostra.domain(P);
		int[] var = {F,P};
		double res = 0;
		for (int x =0; x < f; x++) {
			int[] valx = {x};
			for (int y=0; y < p; y++) {
				//queremos contar quantas vezes o filho e x e o pai e y
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
