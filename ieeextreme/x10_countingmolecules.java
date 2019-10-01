import java.util.*;

public class x10_countingmolecules {

	public static double tol = 1e-6;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		while(sc.hasNext()) {
			double c = sc.nextLong();
			double h = sc.nextLong();
			double o = sc.nextLong();
			
			double water = h/4.0 + o/2.0 - c;
			double co2 = -h/4.0 + o/2.0;
			double glucose = (h/4.0 - o/2.0 + c)/6.0;
			
//			if(Math.abs(water) < tol) water = 0;
//			if(Math.abs(co2) < tol) co2 = 0;
//			if(Math.abs(glucose) < tol) glucose = 0;
			if(isInt(water) && isInt(co2) && isInt(glucose)) {
				System.out.printf("%.0f %.0f %.0f\n", water, co2, glucose);
			} else {
				System.out.printf("Error\n");
			}
//			System.out.printf("%b %b %b %.2f %.2f %.2f\n", isInt(water), isInt(co2), isInt(glucose), 
//					water, co2, glucose);
		}
	}

	public static boolean isInt(double x) {
		return  (x >= 0.0) && (x == Math.round(x));
	}
}
