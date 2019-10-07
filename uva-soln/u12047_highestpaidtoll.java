import java.util.*;
import java.io.*;

class IntegerPair implements Comparable {
	Integer _first, _second;

	public IntegerPair(Integer f, Integer s) {
		_first = f;
		_second = s;
	}

	public int compareTo(Object o) {
		if (this.first() != ((IntegerPair)o).first())
			return this.first() - ((IntegerPair)o).first();
		else
			return this.second() - ((IntegerPair)o).second();
	}

	Integer first() { return _first; }
	Integer second() { return _second; }
}


public class u12047_highestpaidtoll {
	public static final int INF = 1000000000;
	private static Vector< Vector< IntegerPair > > AdjListF = new Vector< Vector< IntegerPair > >();
	private static Vector< Vector< IntegerPair > > AdjListB = new Vector< Vector< IntegerPair > >();

	public static void main(String[] args) throws Exception {


		/*
	    // Graph in Figure 4.17
	    5 7 2
	    2 1 2
	    2 3 7
	    2 0 6
	    1 3 3
	    1 4 6
	    3 4 5
	    0 4 1
		 */
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for(int caseIdx=0; caseIdx<T; caseIdx++) {
			int N = sc.nextInt(), M = sc.nextInt();
			int s = sc.nextInt()-1, t = sc.nextInt()-1; // src and dst node
			int pTaka = sc.nextInt();
			
//			int V, E, s, u, v, w;
//			V = sc.nextInt();
//			E = sc.nextInt();
//			s = sc.nextInt();

			AdjListF.clear();
			AdjListB.clear();
			for (int i=0; i<N; i++) {
				Vector< IntegerPair > NeighborF = new Vector < IntegerPair >();
				AdjListF.add(NeighborF); // add neighbor list to Adjacency List
				Vector< IntegerPair > NeighborB = new Vector < IntegerPair >();
				AdjListB.add(NeighborB); // add neighbor list to Adjacency List
			}

			for(int i=0; i<M; i++) {
				int src = sc.nextInt()-1;
				int dst = sc.nextInt()-1;
				int w = sc.nextInt();
				AdjListF.get(src).add(new IntegerPair (dst, w)); // first time using weight
				AdjListB.get(dst).add(new IntegerPair (src, w)); // first time using weight
			}

			// Dijkstra routine
			int u, v;
			
			Vector < Integer > distF = new Vector < Integer > ();
			distF.addAll(Collections.nCopies(N, INF)); distF.set(s, 0); // INF = 1*10^9 not MAX_INT to avoid overflow
			PriorityQueue < IntegerPair > pq = new PriorityQueue < IntegerPair >(1, 
					new Comparator< IntegerPair >() { // overriding the compare method 
				public int compare(IntegerPair i, IntegerPair j) {
					return i.first() - j.first();
				}
			}
					);
			pq.offer(new IntegerPair (0, s)); // sort based on increasing distance

			while (!pq.isEmpty()) { // main loop
				IntegerPair top = pq.poll(); // greedy: pick shortest unvisited vertex
				int d = top.first(); u = top.second();
				if (d > distF.get(u)) continue; // This check is important! We want to process vertex u only once but we can
				Iterator it = AdjListF.get(u).iterator();
				while (it.hasNext()) { // all outgoing edges from u
					IntegerPair p = (IntegerPair) it.next();
					v = p.first();
					int weight_u_v = p.second();
					if (distF.get(u) + weight_u_v < distF.get(v)) { // if can relax      (note: Record SP spanning tree)
						distF.set(v, distF.get(u) + weight_u_v); // relax                  (here if needed. This is similar)
						pq.offer(new IntegerPair(distF.get(v), v)); //      (as printpath in BFS)
						// enqueue this neighbor regardless whether it is already in pq or not    
					} } }
			
			Vector < Integer > distB = new Vector < Integer > ();
			distB.addAll(Collections.nCopies(N, INF)); distB.set(t, 0); // INF = 1*10^9 not MAX_INT to avoid overflow
			pq.clear();
			pq.offer(new IntegerPair (0, t)); // sort based on increasing distance
			while (!pq.isEmpty()) { // main loop
				IntegerPair top = pq.poll(); // greedy: pick shortest unvisited vertex
				int d = top.first(); u = top.second();
				if (d > distB.get(u)) continue; // This check is important! We want to process vertex u only once but we can
				Iterator it = AdjListB.get(u).iterator();
				while (it.hasNext()) { // all outgoing edges from u
					IntegerPair p = (IntegerPair) it.next();
					v = p.first();
					int weight_u_v = p.second();
					if (distB.get(u) + weight_u_v < distB.get(v)) { // if can relax      (note: Record SP spanning tree)
						distB.set(v, distB.get(u) + weight_u_v); // relax                  (here if needed. This is similar)
						pq.offer(new IntegerPair(distB.get(v), v)); //      (as printpath in BFS)
						// enqueue this neighbor regardless whether it is already in pq or not    
					} } }

			// debug code, index + 1 for final answer
//			for (int i = 0; i < N; i++) {
//				System.out.printf("SSSP(%d, %d) = %d\n", s + 1, i + 1, distF.get(i));
//				System.out.printf("SSSP(%d, %d) = %d\n", t + 1, i + 1, distB.get(i));
//			}
			int ans = -1;
			for (int src=0; src<N; src++) {
				for(IntegerPair j: AdjListF.get(src)) {
					int dst = j.first();
					int w = j.second();
					
					if(distF.get(src)+w+distB.get(dst) <= pTaka)
						ans = Math.max(ans, w);
				}
			}
			System.out.println(ans);
		}
	}
}
