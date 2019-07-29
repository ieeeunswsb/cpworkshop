import java.util.*;

public class u00750_8queensproblem {
	public static int[] ans = new int[8];
	public static boolean[] rowStat = new boolean[8];
	public static boolean[] colStat = new boolean[8];
	public static int solIdx;

	public static boolean place(int colIdx, int val, boolean skip) {
		if (!skip) {
			if (rowStat[val] || colStat[colIdx])
				return false;
		}
		for (int i = 0; i < colIdx; i++) {
			if (Math.abs(val - ans[i]) == (colIdx - i)) {
				return false;
			}
		}
//		System.out.println(colIdx+" "+val+" "+Arrays.toString(ans));
		return true;
	}

	public static void backtrack(int colIdx) {
		if (colIdx == 8) {
			System.out.printf("%2d     ", solIdx++);
			for (int i = 0; i < ans.length; i++)
				System.out.printf(" %d", ans[i] + 1);
			System.out.println();
		} else if (ans[colIdx] == -1) {
			for (int i = 0; i < 8; i++) {
				if (place(colIdx, i, false)) {
					ans[colIdx] = i;
					colStat[colIdx] = true;
					rowStat[i] = true;
					backtrack(colIdx + 1);
					colStat[colIdx] = false;
					rowStat[i] = false;
					ans[colIdx] = -1;
				}
			}
		} else {
			if (place(colIdx, ans[colIdx], true)) {
				backtrack(colIdx + 1);
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int TIdx = 0; TIdx < T; TIdx++) {

			int r = sc.nextInt();
			int c = sc.nextInt();

			Arrays.fill(ans, -1);
			Arrays.fill(rowStat, false);
			Arrays.fill(colStat, false);
			ans[c - 1] = r - 1;
			rowStat[r - 1] = true;
			colStat[c - 1] = true;

			if (TIdx > 0)
				System.out.println();
			System.out.printf("SOLN       COLUMN\n");
			System.out.printf(" #      1 2 3 4 5 6 7 8\n\n");
			solIdx = 1;
			backtrack(0);
		}
	}

}
