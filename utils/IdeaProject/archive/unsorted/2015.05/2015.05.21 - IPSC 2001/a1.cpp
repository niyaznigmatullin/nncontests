#include <stdio.h>

int A[4000], B[4000];
int i,j,k,l,m;

int main(void) {
  for (i=0;i<4000;i++) { A[i]=B[i]=0; } k=-1; m=0;
  while (!A[1000]) {
    A[0]++; B[0]++;
    for (j=0;j<3999;j++) { B[j+1]+=B[j]/13; B[j]%=13; }
    for (j=0,l=-1;j<3999;j++) { 
      if ((A[j]>0) && (k<j)) { k=l=j; }
      A[j+1]+=A[j]/(j+2); A[j]%=(j+2); 
    }
    if (l>-1) m+=B[0];
  }
  printf("%d\n",m);
  return 0;
}
