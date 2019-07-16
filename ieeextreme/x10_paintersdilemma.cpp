//============================================================================
// Name        : x10_paintersdilemma.cpp
// Author      : Luke Sy
// Version     :
// Description : https://www.hackerrank.com/contests/ieeextreme-challenges/challenges/painters-dilemma
//			     Dynamic programming problem
//============================================================================

#include <iostream>
#include <algorithm>
#include <stdio.h>

using namespace std;

static int NMAX = 1000;
static int CMAX = 20;
int T;
int main() {
	cin >> T;

	int N;
	for(int TIdx=0; TIdx<T; TIdx++) {
		cin >> N;

		int color[N];
		for(int i=0; i<N; i++) cin >> color[i];

		int val[CMAX+1][N];
		for(int i=0; i<=CMAX; i++) val[i][0] = NMAX;
		val[0][0] = 1;

//		for(int p=0; p<=CMAX; p++) cout << val[p][0] << ' ';
//		cout << '\n';

		for(int i=1; i<N; i++) {
			int inc = (color[i]==color[i-1])? 0 : 1;
			int minVal = NMAX;
			for(int j=0; j<=CMAX; j++) {
				if (val[j][i-1] == NMAX) {
					val[j][i] = NMAX;
				} else {
					val[j][i] = val[j][i-1] + inc;
				}

				int buf = val[j][i-1] + ((j==color[i]) ? 0 : 1);
				if(buf < minVal) {
					minVal = buf;
				}
			}
			val[color[i-1]][i] = minVal;

//			for(int p=0; p<=CMAX; p++) cout << val[p][i] << ' ';
//			cout << '\n';
		}

		int minVal = NMAX;
		for(int i=0; i<=CMAX; i++)
			if(val[i][N-1] < minVal)
				minVal = val[i][N-1];
		cout << minVal << '\n';

//		for(int i=0; i<N; i++) cout << color[i] << ' ';
//		printf("\n");
	}
	return 0;
}
