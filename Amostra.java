package amostra;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class Amostra implements Serializable{ //se calhar tirar o implements daqui
	private static final long serialVersionUID = 1L;
	private ArrayList<int []> list;


	public Amostra() {
		this.list = new ArrayList<int []>();
	}

	public Amostra(String csvFile) {
		this.list = new ArrayList<int []>();;

		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try {br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] country     = line.split(cvsSplitBy);
				int[] stringToIntVec = new int[country.length];
				for (int i = 0; i < country.length; i++)
					stringToIntVec[i] = Integer.parseInt(country[i]);	
				add(stringToIntVec);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void add(int[] v) {
		if (this.list.isEmpty()) {
			this.list.add(v);
		}
		else {
			if(this.nr_var() == v.length) {
				this.list.add(v);
			}
			else {
				throw new java.lang.AssertionError ("Invalid vector");
			}
		}
		
	}
	
	
	/* public double count(int[] var, int[] val) {
		double r = 0;
		ArrayList<int[]> pos = new ArrayList<int[]>();
		pos = this.list;
		boolean[] visited = new boolean[pos.size()];
		int i = 0;
		
		while (!pos.isEmpty() && i<var.length) {
			for (int j = 0; j<pos.size() && !visited[j]; j++ ) { //PODE MELHORAR
				if (pos.get(j)[var[i]] != val[i] ) {
					visited[j] = true;
				}
			}
			
			i++;
		}
		
		for (int k = 0; k<pos.size() ; k++) {
			if (!visited[k]) {
				r+=1;
			}
		}
		return r;
	}
	
	*/
	
	public double count(int[] var,int[] val) {
		
		double r=0;
		
		for (int i = 0; i< this.list.size();i++) {
			int[] vector = this.list.get(i);
			boolean skip = true;
			int j = 0;
			while (skip && j < var.length) {
				if (vector[var[j]]!=val[j]) {
					skip = false;
				}
				j++;
			}
			if (skip) {
				r+=1;
			}
		}
		
		return r;
	}
	
	
	
	public double length() {
		return this.list.size();		
	}
	
	
	public int[] element (int p) {
		if (p >= this.length()) {
			throw new java.lang.AssertionError ("Index out of bounds");
		}
		else {
			return this.list.get(p);
		}
	}
	
	
	/* public int domain (int [] posicoes) { 
		ArrayList<int[]> pos = new ArrayList<int[]>();
		// fazer com a transposta para ler as linhas
		// recebe uma amostra??? ver slack
		pos=this.list;
		int res =1;
		for (int i : posicoes) {
			int maximo=0;
			for (int j =0 ; j<pos.size(); j++) {
				int e = pos.get(j)[posicoes[i]];
				if (e>maximo) {
					maximo=e;
				}
			}
			res = res*(maximo+1);
		}
		return res;
	}
	*/
	
	public int domain (int posicao) {
		int maximo=0;
		for (int j =0 ; j< this.list.size(); j++) {
				int e = this.list.get(j)[posicao];
				if (e>maximo) {
					maximo=e;
				}
			}
		return maximo+1;
	}
	
	// ve o primeiro elemento e diz o seu comprimento
	
	public int nr_var() {
		if (this.length()>0) {
			return element(0).length;
		}
		else {
			throw new AssertionError("No elements");
		}
	}
	
	
	//salvaguarda: se tiver elementos, checka o primeiro e ve se tem o mm tamanho do que vamos adicionar
	//se nao tiver elementos, adiciona so

	
	/*public boolean[] exp1(int a) {
		boolean[] r = new boolean[a];
		return r;
	}
	*/

	
	@Override

  	public String toString() {
		String s="[";
		if (list.size()>0) s+=Arrays.toString(list.get(0));
		for (int i=1; i<list.size();i++)
			s+= "," + Arrays.toString(list.get(i)) ;
		s+="]";
			
		return " Amostra = " + s;
	}
	
	public static boolean equals_aux2(double x, double y) { // outra opçao de escrita
		double eps = 1e-10; //1*10^(-10)
		return Math.abs(x-y)<eps;
	}

	public boolean MemberQ(int v[]) { //APAGAR ISTO
		if(list.size() == 0 || list.get(0).length != v.length)
			return false;
		for (int u[] : list) {
			boolean found = true;
			for (int i = 0; found && i < v.length; i++) 
				if (v[i] != u[i])
					found = false;
			if(found)
				return true;
		}
		return false;
	}
		
	

	public static void main(String[] args) {
		Amostra amostra = new Amostra("diabetes.csv");
		Amostra exp = new Amostra();
		int[] u1 = {0,0,1};
		int[] u2 = {1,1,0};
		int[] u3 = {2,3,1};
		int[] u4 = {0,3,1};
		int[] var = {1,2};
		int[] val = {0,1};
		//int[] d0 = {0};
		exp.add(u1);
		exp.add(u2);
		exp.add(u3);
		exp.add(u4);
		System.out.println(exp);
		System.out.println(exp.count(var,val));
		System.out.println(amostra.length());

	}
	

}

