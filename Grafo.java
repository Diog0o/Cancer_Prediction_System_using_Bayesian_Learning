package experiencias;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;


public class Grafo {
	int dim;
	double[][] ma;
	
	public Grafo(int n) {
		this.dim = n;
		this.ma = new double[n][n];
	}
	
	public class Tuple{

	    private double weight;
	    private int o;
	    private int d;
		@Override
		public String toString() {
			return "[w=" + weight + ", o=" + o + ", d=" + d + "]";
		}
	    
	    
	}
	
	private static void quickSort(ArrayList<Tuple> vec, int left, int right) {
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
			if (vec.get(j).weight<=vec.get(left).weight){
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
	
	public void add_edge(int o, int d, double p) {
		// falta proteger a funcao (peso tem que dar - entre 0 e 1?)
		if (o<this.dim && d < this.dim && o>=0 && d>= 0 ) { //&& p>= 0 && p<=1
			this.ma[o][d]=p;
		}
		else {
			if(p < 0 || p >1) {
				throw new AssertionError("Edge is not supported");
			}
			else {
				throw new AssertionError("Node is not in graph");
			}
		}
	}
	
	public int[] max_spanning_tree() {
		
		return mpt(0);
	}
	
	private int[] mpt(int no_inicial) {
		int[] nos_visitados = new int[this.dim];
		int no_atual = no_inicial;
		Tuple aresta_atual = new Tuple();
		nos_visitados[no_inicial]=1;
		ArrayList<Tuple> caminho = new ArrayList<Tuple>();
		ArrayList<Tuple> arestas_possiveis = new ArrayList<Tuple>();
		for (int i = 0; i<=this.dim; i++) {
			double[] lista_nos=this.ma[no_atual];
			for (int j = 0; j< this.dim; j++) {
				if(lista_nos[j]!=0 && nos_visitados[j]!=0) {
					Tuple aresta = new Tuple();
					aresta.weight = lista_nos[j];
					aresta.o = no_atual;
					aresta.d = j;
					arestas_possiveis.add(aresta);
				}
			quickSort(arestas_possiveis,0,arestas_possiveis.size()-1);
			aresta_atual = arestas_possiveis.get(-1);
			arestas_possiveis.remove(-1);
			caminho.add(aresta_atual);
			no_atual=aresta_atual.d;
			nos_visitados[no_atual]=1;
		}
	}
		return find_parents(caminho);
	}
	


	public static int[] find_parents(ArrayList<Tuple> caminho) { //funciona
		int[] r = new int[caminho.size()];
		int node = caminho.size();
		Stack<Integer> s = new Stack<Integer>();
		boolean[] visited = new boolean[caminho.size()+1];
		s.push(node);
		while (!s.isEmpty()) {
			int no_atual = s.pop();
			for (int i =0; i<caminho.size(); i++) {
				if (caminho.get(i).o == no_atual && !visited[caminho.get(i).d]) {
					r[caminho.get(i).d] = no_atual;
					s.push(caminho.get(i).d);
				}
				if (caminho.get(i).d == no_atual && !visited[caminho.get(i).o]) {
					r[caminho.get(i).o] = no_atual;
					s.push(caminho.get(i).o);
				}
			}
			visited[no_atual] =true;
		}
		return r;
		
	}


	
	public ArrayList<Tuple> see_edges(int node) {
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
		return r;
	}
	
	/* public Tuple fattest_edge(ArrayList<Tuple> lista_arestas) {
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

	@Override
	public String toString() {
		return "Grafoo [dim=" + dim + ", ma=" + Arrays.deepToString(ma) + "]";
	}
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Grafo exp = new Grafo(6);
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
		System.out.println(exp);
		System.out.println(exp.see_edges(1));
		System.out.print(exp.max_spanning_tree());
		
	


		
	}

}
