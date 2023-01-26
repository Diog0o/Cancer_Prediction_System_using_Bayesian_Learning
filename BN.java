package redes_bayes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import amostra.Amostra;
import grafo.Grafoo;
import floresta.Forest;

public class BN implements Serializable{
	private static final long serialVersionUID = 1L;
	//int[] arvore;
	Forest arvore;
	ArrayList<double[][]> DFO;
	
	

	public double[][] bob_mb(int f, int p, Amostra amostra){
		//double[][] r = new double[amostra.domain(f)][amostra.domain(p)];
		return new double[amostra.domain(f)][amostra.domain(p)];
	}
	
	public double[][] bob_mbo(int r, Amostra amostra){
		// double[][] res = new double[amostra.domain(r)][1];
		return new double[amostra.domain(r)][1];
	}
	
	public BN (Forest arvore, Amostra amostra, double S){
		this.arvore= arvore;
		int nr_nos = amostra.nr_var();
		this.DFO = new ArrayList<double[][]>();
		for (int i = 0; i < nr_nos ; i++) { // i percorre os nos
			int pai = arvore.pai_da_crianca(i);
			int[] Fz = {i}; //Fz e a variavel (cor)
			int[] Pz = {pai}; //Pz e a variavel (tamanho)
			// i e o filho
			
			if (pai==-1) { //se encontrarmos a raiz
				double[][] matriz = bob_mbo(i,amostra);
				for (int k = 0; k<amostra.domain(i); k++) {
					int[] K = {k}; //ks sao os valores da variavel (branco, castanho, azul...)
					matriz[k][0] = (amostra.count(Fz, K) + S) / (amostra.length() + S* amostra.domain(i)) ;
				}
				this.DFO.add(matriz);
			}
			
			else {
				double[][] matriz = bob_mb(i, pai, amostra);
				for (int k = 0; k < amostra.domain(i); k++) {
					for (int j = 0; j < amostra.domain(pai) ; j++) {
						int[] var = {i, pai};
						int[] val = {k,j};
						//int[] K= {k};
						int[] J = {j};
						matriz[k][j] = (amostra.count(var, val) + S) / (amostra.count(Pz,J) + S* amostra.domain(i)) ;
					}
				}
				this.DFO.add(matriz);
			}
		}
	}
 	
	public Forest exp() {
		return this.arvore;
	}
	
	
	
	public double prob (int[] vector) { 
		double res = 1;
		for (int i = 0 ; i< vector.length ; i++) {
			//ir a arvore ver quem e o pai 
			//ir a lista de matrizes buscar a matriz do filho
			//ver a entrada [vector[i]][vector[pai]]
			int pai = this.arvore.pai_da_crianca(i);
			if (pai==-1) {
				double[][] matriz_raiz = this.DFO.get(i);
				res= res * matriz_raiz[vector[i]][0];
			}
			else {
				double[][] matriz_filho = this.DFO.get(i);
				res= res * matriz_filho[vector[i]][vector[pai]];
			}
		}
		return res;
	}
	
	public int d_classe() {
		int nr_nos = this.arvore.dim() -1;
		double[][] matriz = this.DFO.get(nr_nos);
		return matriz.length;
	}
	
	

	@Override
	public String toString() {
		return "BN [arvore=" + arvore + ", DFO=" + DFO + "]";
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Amostra amostra = new Amostra("bcancer.csv");
		Amostra biologica = new Amostra("iris.csv");
		Grafoo boa = Grafoo.g_completo(amostra);
		Grafoo biol = Grafoo.g_completo(biologica);
		BN exp2 = new BN (biol.max_spanning_tree1(), biologica, 0.5);
 		System.out.println(Arrays.toString(boa.max_spanning_tree()));
 		BN exp = new BN (boa.max_spanning_tree1(), amostra, 0.5);
 		//System.out.println(exp);
 		int[] a = {0,0,0,0,0,0,0,0,0,0,0};
 		int[] b = {0,0,0,0,0,0,0,0,0,0,1};
 		System.out.println(exp.prob(a));
		System.out.println(exp.prob(b));
		System.out.println(exp.prob(a)>exp.prob(b));
		System.out.println(exp2.d_classe());

	}

}
