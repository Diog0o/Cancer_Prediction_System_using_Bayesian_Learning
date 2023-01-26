package floresta;

import java.io.Serializable;
import java.util.Arrays;

public class Forest implements Serializable{
	private static final long serialVersionUID = 1L;
	int dim;
	int[] pais;
	
	public Forest(int n) {
		this.dim = n;
		this.pais = new int[n];
		for (int i= 0; i<this.dim; i++) {
			this.pais[i]=-1;
		}
	}
	
	public void set_parent(int n, int m) {
		this.pais[m]=n;
	}
	
	public int pai_da_crianca(int f) {
		return this.pais[f];
	}
	
	public boolean treeQ() {
		int r = 0;
		int i=0;
		while (i < this.pais.length && r < 2) {
			if (this.pais[i]==-1) {
				r+=1;
			}
			i++;
		}
		return r==1 ;
	}
	
	public int dim() {
		return this.dim;
	}


	@Override
	public String toString() {
		return "Forest [dim=" + dim + ", pais=" + Arrays.toString(pais) + "]";
	}

	public static void main(String[] args) {
		Forest f = new Forest(4);
		System.out.println(f);
		f.set_parent(1, 2);
		f.set_parent(3, 1);
		f.set_parent(2, 0);
		System.out.println(f);
		System.out.println(f.treeQ());

	}

}
