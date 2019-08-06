import java.util.*;

public class u10684_jackpot {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			int N = sc.nextInt();
			if(N == 0) break;
			
			int[] val = new int[N];
			for(int i=0; i<N; i++) val[i] = sc.nextInt();
			int ans = -1;
			
			/*
			// longest increasing subsequence
			int[][] lis = new int[N][N];
			
			// lis of array length = 1
			for(int i=0; i<N; i++) {
				lis[0][i] = val[i];
				ans = Math.max(ans, lis[0][i]);
			}
			// lis of array length = i+1, index j to j+i
			for(int i=1; i<N; i++) {
				for(int j=0; j+i<N; j++) {
					lis[i][j] = Math.max(lis[i-1][j+1] + val[j], lis[i-1][j] + val[j+i]);
					ans = Math.max(ans,  lis[i][j]);
				}
			}
			*/
			int max_ending_here = val[0];
			int max_so_far = val[0];
			
			for(int i=1; i<N; i++) {
				max_ending_here = Math.max(val[i], max_ending_here + val[i]);
				max_so_far = Math.max(max_so_far, max_ending_here);
			}
			ans = max_so_far;
				        
			if(ans > 0) {
				System.out.printf("The maximum winning streak is %d.\n", ans);
			} else {
				System.out.println("Losing streak.");
			}
		}
	}
}
