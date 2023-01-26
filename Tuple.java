package tuple;

import java.util.ArrayList;


public class Tuple {
	public double weight;
    public int o;
    public int d;
    
	@Override
	public String toString() {
		return "[w=" + weight + ", o=" + o + ", d=" + d + "]";
	}
    
    public static boolean compare(Tuple a, Tuple b) {
    	return a.weight == b.weight && ((a.o == b.o && a.d == b.d) || (a.o == b.d && a.d == b.o)) ;
    }
    
    public static boolean contain(ArrayList<Tuple> n, Tuple a) {
    	int i =0;
    	boolean found = false;
    	while(!found && i<n.size()){
    		if(compare(n.get(i),a)){
    	        found = true;
    	    }
    		i++;
    }
    	return found;
}
}
