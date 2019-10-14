import java.util.*;

public class u10650_determinateprime {
	static int _sieve_size;
	static boolean[] bs;   // 10^7 should be enough for most cases
	static List<Integer> primes = new ArrayList<Integer>();   // compact list of primes in form of vector<int>
	static int MAXN = 100000;
	static int MAXN2 = 32001;
	
	static void sieve(int upperbound) {          // create list of primes in [0..upperbound]
		_sieve_size = upperbound + 1;                   // add 1 to include upperbound
		bs = new boolean[_sieve_size];
		Arrays.fill(bs,true);                                    // set all bits to 1
		bs[0] = bs[1] = false;                                     // except index 0 and 1
		for (long i = 2; i < _sieve_size; i++) if (bs[(int)i]) {
			// cross out multiples of i starting from i * i!
			for (long j = i * i; j < _sieve_size; j += i) bs[(int)j] = false;
			primes.add((int)i);  // also add this vector containing list of primes
		} }                                           // call this method in main method

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//		Scanner sc = new Scanner(System.in);
		sieve(MAXN);                       // can go up to 10^7 (need few seconds)

		Scanner sc = new Scanner(System.in);
		
		List<ArrayList<Integer>> dpSets = new ArrayList<ArrayList<Integer>>();
		for(int i=0; primes.get(i) < MAXN2; i++) {
			int diff = primes.get(i+1)-primes.get(i);
			int j = i;
			for(;(primes.get(j+1)-primes.get(j))==diff; j++);
			if((j-i >= 2) && primes.get(j) <= MAXN2) {
				ArrayList<Integer> dpBuf = new ArrayList<Integer>();
				dpBuf.add(primes.get(i));
				for(int k=i+1; k<=j; k++) 
					dpBuf.add(primes.get(k));
				dpSets.add(dpBuf);
				i += j-i-1;
			}
		}
		
		while(true) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			
			if(a == 0 && b == 0) break;
			for(ArrayList<Integer> tmp: dpSets) {
				if(tmp.get(0) >= a && tmp.get(tmp.size()-1) <= b) {
					System.out.print(tmp.get(0));
					for(int i=1; i<tmp.size(); i++)
						System.out.print(" "+tmp.get(i));
					System.out.println();
				}
			}
		}
	}
}
