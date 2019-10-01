/* 
 * Author: Luke Wicent Sy
 * Date: 23rd Sept 2019
 */

import java.util.*;

public class u10003_cuttingsticks {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			int l = sc.nextInt();
			
			if(l == 0) break;
			int n = sc.nextInt();
			int cutVal[] = new int[n+2];
			for(int i=1; i<=n; i++) {
				cutVal[i] = sc.nextInt();
			}
			cutVal[0] = 0; cutVal[n+1] = l;
			
			int cnt[][] = new int[n+2][n+2];
			for(int i=0; i<cnt.length; i++) Arrays.fill(cnt[i], Integer.MAX_VALUE);
			for(int i=0; i+1<n+2; i++) cnt[i][i+1] = 0;
			// cnt[i][i+1] = 0; // as it is just one piece
			for(int i=2; i<=n+1; i++) {
				for(int j=0; j+i<n+2; j++) {
					for(int k=j+1; k<j+i; k++) {
						cnt[j][j+i] = Math.min(cnt[j][j+i], cutVal[j+i]-cutVal[j]+cnt[j][k]+cnt[k][j+i]);
					}
				}
			}
			
//			System.out.println(cnt[0][n+1]+" "+Arrays.toString(cutVal));
			System.out.printf("The minimum cutting is %d.\n", cnt[0][n+1]);
		}
	}
}
