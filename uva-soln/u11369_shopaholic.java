import java.util.*;

public class u11369_shopaholic {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int caseT = sc.nextInt();
		for(int cIdx=0; cIdx < caseT; cIdx++) {
			int N = sc.nextInt();
			
			int[] val = new int[N];
			for(int i=0; i<N; i++) val[i] = sc.nextInt();
			
			Arrays.sort(val);
			
			long disc = 0;
			for(int i=val.length-3; i>=0; i-=3) disc += val[i];
//			System.out.println(Arrays.toString(val));
			System.out.println(disc);
		}
	}

}
