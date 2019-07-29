import java.util.*;

public class u12488_startgrid {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		
		while(sc.hasNext()) {
			int N = sc.nextInt();
			
			int[] start = new int[N];
			int[] finish = new int[N];
			
			for(int i=0; i<start.length; i++)
				start[i] = sc.nextInt();
			for(int i=0; i<finish.length; i++)
				finish[i] = sc.nextInt();
			sc.nextLine();
			
			int disp = 0;
			for(int i=0; i<finish.length; i++) {
				for(int j=0; j<start.length; j++) {
					if(start[j]==finish[i]) {
						start[j] = -1;
						break;
					} else if(start[j] != -1){
						disp++;
					}
				}
			}
			System.out.println(disp);
		}
	}

}
