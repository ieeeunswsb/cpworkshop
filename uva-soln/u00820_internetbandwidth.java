import java.util.*;

public class u00820_internetbandwidth {
	private static final int MAX_N = 101; // enough for sample graph in Figure 4.24/4.25/4.26
	private static final int INF = 1000000000;

	// we need these global variables
	private static int[][] res; // define MAX_V appropriately
	private static int mf, f, s, t;
	private static Vector < Integer > p = new Vector < Integer > ();

	private static void augment(int v, int minEdge) { // traverse the BFS spanning tree as in print_path (section 4.3)
		if (v == s) { f = minEdge; return; } // reach the source, record minEdge in a global variable `f'
		else if (p.get(v) != -1) { augment(p.get(v), Math.min(minEdge, res[p.get(v)][v])); // recursive call
		res[p.get(v)][v] -= f; res[v][p.get(v)] += f; } // alter residual capacities
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		for(int caseIdx=1;;caseIdx++) {
			int n = sc.nextInt();
			if(n == 0) break;
			
			int s = sc.nextInt()-1; // source node
			int t = sc.nextInt()-1; // destination node
			int c = sc.nextInt();
			
//			System.out.println(n+" "+s+" "+t+" "+c);
			res = new int[n][];
			for(int i=0; i<res.length; i++) {
				res[i] = new int[n];
				Arrays.fill(res[i], 0);
			}
			
			for(int i=0; i<c; i++) {
				int src = sc.nextInt()-1;
				int dst = sc.nextInt()-1;
				int weight = sc.nextInt();
				
//				System.out.println(src+" "+dst+" "+weight);
				res[src][dst] += weight;
				res[dst][src] += weight;
			}
	
			mf = 0;
			while (true) { // run O(VE^2) Edmonds Karp to solve the Max Flow problem
				f = 0;
	
				// run BFS, please examine parts of the BFS code that is different than in Section 4.3
				Queue < Integer > q = new LinkedList < Integer > ();
				Vector < Integer > dist = new Vector < Integer > ();
				dist.addAll(Collections.nCopies(n, INF)); // #define INF 2000000000
				q.offer(s);
				dist.set(s, 0);
				p.clear();
				p.addAll(Collections.nCopies(n, -1)); // (we have to record the BFS spanning tree)
				while (!q.isEmpty()) {                // (we need the shortest path from s to t!)
					int u = q.poll();
					if (u == t) break; // immediately stop BFS if we already reach sink t
					for (int v = 0; v < n; v++) // note: enumerating neighbors with AdjMatrix is `slow'
						if (res[u][v] > 0 && dist.get(v) == INF) { // res[u][v] can change!
							dist.set(v, dist.get(u) + 1);
							q.offer(v);
							p.set(v, u); // parent of vertex v is vertex u
						}
				}
	
				augment(t, INF); // find the min edge weight `f' along this path, if any
				if (f == 0) break; // if we cannot send any more flow (`f' = 0), terminate the loop
				mf += f; // we can still send a flow, increase the max flow!
			}
	
			System.out.printf("Network %d\n", caseIdx);
			System.out.printf("The bandwidth is %d.\n", mf); // this is the max flow value of this flow graph
		}
	}
}
