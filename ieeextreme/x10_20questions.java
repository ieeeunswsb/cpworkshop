import java.util.*;
// Author: Luke Sy
// brute force + coder problem
public class x10_20questions {
	public static char[] color = new char[10];
	public static char[] opt = {'r', 'g', 'b'};
	public static boolean[][] possible;
	public static ArrayList<String[]> al1;
	public static ArrayList<Boolean> al2;
	public static int n;
	public static void generate(int level) {
		if (level==10) {
			int Fcnt = 0;
			int[] colorCount = new int[3];
			for(int j=0; j<colorCount.length; j++) colorCount[j] = 0;
			for(int j=0; j<color.length; j++) colorCount[color[j]]++;
			
			Iterator<Boolean> al2Iter = al2.iterator();
			for(String[] i: al1) {
				boolean logic, logicbuf;
				boolean al2Buf = al2Iter.next();
				logic = (i.length == 3 || i[3].charAt(0)=='a');
				
				for(int j=0; j<i.length; j+=4) {
					if(i[j].charAt(2)=='l') {
						logicbuf = (color[Integer.parseInt(i[j+1])-1] == char2idx(i[j+2].charAt(0)));
					} else {
						logicbuf = (colorCount[char2idx(i[j+1].charAt(0))] == Integer.parseInt(i[j+2]));
					}
					if(i.length == 3 || i[3].charAt(0)=='a') {
						logic &= logicbuf;
					} else {
						logic |= logicbuf;
					}
				}
				if (!((logic && al2Buf) || (!logic && !al2Buf))) Fcnt++;
			}
			if(Fcnt == n) {
				for(int i=0; i<color.length; i++) {
					possible[color[i]][i] = true;
				}
			}
		} else {
			for(char i=0; i<3; i++) {
				color[level] = i;
				generate(level+1);
			}
		}
	}
	public static int char2idx(char x) {
		return (x=='r')? 0: (x=='g')? 1: 2;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();	
		for(int TIdx=0; TIdx<T; TIdx++) {
			al1 = new ArrayList<String[]>();
			al2 = new ArrayList<Boolean>();
			
			sc.nextLine();
			int q = sc.nextInt();
			n = sc.nextInt();
			sc.nextLine();
			
			for(int qIdx=0; qIdx<q; qIdx++) {
				al1.add(sc.nextLine().split(" "));
				al2.add(sc.nextLine().trim().compareTo("yes")==0);
			}
			possible = new boolean[3][10];
			for(int i=0; i<possible.length; i++) {
				for(int j=0; j<possible[0].length; j++) possible[i][j] = false;
			}
			generate(0);

			for(int i=0; i<possible[0].length; i++) {
				if(possible[0][i]) System.out.print("r");
				if(possible[1][i]) System.out.print("g");
				if(possible[2][i]) System.out.print("b");
				System.out.print(" ");
			}
			System.out.println();
		}
	}
}
