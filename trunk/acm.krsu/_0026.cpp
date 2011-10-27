#include <stdio.h>

int powe(int x, int k) {
	int ret=1;
	for (int i=0; i<k; i++) ret*=x;
	return ret;
}
int n;
int main() {
	scanf("%d", &n);
	printf("%d", powe(n,10));
	return 0;
}