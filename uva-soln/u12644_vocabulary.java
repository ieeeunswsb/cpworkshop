import java.util.*;

public class u12644_vocabulary {
	private static Vector < Vector < Integer > > AdjList = new Vector < Vector < Integer > >();
	private static Vector < Integer > match, visited; // global variables

	private static int Aug(int l) {
		if (visited.get(l) == 1) return 0;
		visited.set(l, 1);

		Iterator it = AdjList.get(l).iterator();
		while (it.hasNext()) { // either greedy assignment or recurse
			Integer right = (Integer)it.next();
			if (match.get(right) == -1 || Aug(match.get(right)) == 1) {
				match.set(right, l);
				return 1; // we found one matching
			}
		}

		return 0; // no matching
	}
	
	// x contains all letters of y
	private static boolean isConnected(String x, String y) {
		int[] xCount = new int[26];
		int[] yCount = new int[26];
		Arrays.fill(xCount, 0);
		Arrays.fill(yCount, 0);
		
		for(int i=0; i<x.length(); i++) xCount[x.charAt(i)-'a']++;
		for(int i=0; i<y.length(); i++) yCount[y.charAt(i)-'a']++;
		
		for(int i=0; i<xCount.length; i++)
			if(xCount[i] < yCount[i])
				return false;
		return true;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		
		for(int caseIdx=0; sc.hasNext() ;caseIdx++) {
					
			int jackN = sc.nextInt();
			int jillN = sc.nextInt();
			
			String[] words = new String[jackN+jillN];
			
			for(int i=0; i<words.length; i++) words[i] = sc.next().toLowerCase();
	
			int V = jackN+jillN, V_l = jackN;
			AdjList.clear();
			for(int i=0; i<V; i++) {
				Vector<Integer> Neighbor = new Vector<Integer>();
				AdjList.add(Neighbor); // store blank vector first
			}
			for(int i=0; i<V_l; i++) {
				for(int j=V_l; j<V; j++) {
					if(isConnected(words[i], words[j])) {
						AdjList.get(i).add(j);
					}
				}
			}
	
			int MCBM = 0;
			match = new Vector < Integer > ();
			match.addAll(Collections.nCopies(V, -1));
			for (int l = 0; l < V_l; l++) {
				visited = new Vector < Integer > ();
				visited.addAll(Collections.nCopies(V_l, 0));
				MCBM += Aug(l);
			}
			System.out.printf("%d\n", MCBM);
		}
	}
}