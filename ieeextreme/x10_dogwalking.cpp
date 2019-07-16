//============================================================================
// Name        : 10-dog-walking.cpp
// Author      : Luke Sy
// Version     :
// Description : https://www.hackerrank.com/contests/ieeextreme-challenges/challenges/dog-walking/problem
//============================================================================

#include <iostream>
#include <algorithm>
#include <stdio.h>

using namespace std;

int T;
int main() {
	scanf("%d", &T);

	int N, K;
	for(int TIdx=0; TIdx<T; TIdx++) {
		scanf("%d %d", &N, &K);

		int vals[N];
		int diff[N-1];
		for(int i=0; i<N; i++) {
			scanf("%d", &vals[i]);
		}
		sort(vals, vals+N);
		for(int i=0; i<N-1; i++) {
			diff[i] = vals[i+1]-vals[i];
		}
		sort(diff, diff+N-1);

		long answer = vals[N-1]-vals[0];
		for(int i=0; i<K-1; i++) {
			answer -= diff[N-2-i];
		}
//		for(int i=0; i<N; i++) printf("%d ", vals[i]);
//		printf("\n");
//		for(int i=0; i<N-1; i++) printf("%d ", diff[i]);
//		printf("\n");
		printf("%d\n", answer);
	}
	return 0;
}
