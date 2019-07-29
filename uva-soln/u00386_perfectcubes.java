import java.util.*;

public class u00386_perfectcubes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		for(int a=1; a<=200; a++) {
			for(int b=2; b<a; b++) {
				for(int c=b; c<a; c++) {
					for(int d=c; d<a; d++) {
						if(a*a*a == b*b*b+c*c*c+d*d*d) {
							System.out.printf("Cube = %d, Triple = (%d,%d,%d)\n", a, b, c, d);
						}
					}
				}
			}
		}
	}

}
