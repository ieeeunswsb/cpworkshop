/* 
 * Author: Luke Wicent Sy
 * Date: 23rd Sept 2019
 */
 
import java.util.*;

public class u10759_dicethrowing {

	public static long gcd(long a, long b) {
        return (b == 0)? a : gcd(b, a%b);
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int nMax = 24;
		int xMax = 150;
		
		long val[][] = new long[nMax][xMax+1];
		long vSum[][] = new long[nMax][xMax+1];
		for(int i=0; i<nMax; i++) Arrays.fill(val[i], 0);
		for(int i=1; i<=6; i++) val[0][i] = 1;
		for(int i=1; i<val.length; i++) {
			for(int j=1; j<=6; j++) {
				for(int k=0; (k+j)<=xMax; k++) {
					val[i][k+j] += val[i-1][k];
				}
			}
		}
		for(int i=0; i<vSum.length; i++) {
			for(int j=xMax-1; j>=0; j--) {
				vSum[i][j] = vSum[i][j+1] + val[i][j];
			}
		}
		long pow6[] = new long[nMax]; 
		pow6[0] = 6;
		for(int i=1; i<pow6.length; i++) pow6[i] = 6*pow6[i-1];
		
//		System.out.println(Arrays.toString(val[1]));
//		System.out.println(Arrays.toString(val[2]));
//		System.out.println(Arrays.toString(vSum[1]));
//		System.out.println(Arrays.toString(vSum[2]));

		Scanner sc = new Scanner(System.in);
		
		while(true) {
			int n = sc.nextInt();
			int x = sc.nextInt();
			
			if(n == 0 && x == 0) break;
			
			long ansGCD = gcd(vSum[n-1][x], pow6[n-1]);
			long num = vSum[n-1][x]/ansGCD;
			long den = pow6[n-1]/ansGCD;
			
			if(num == 0) {
				System.out.println(0);
			} else if(num == 1 && den == 1) {
				System.out.println(1);
			} else {
				System.out.println(num+"/"+den);
			}
		}
	}

}
