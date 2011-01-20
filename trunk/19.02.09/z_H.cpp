#include <iostream>
#include <stdio.h>
#include <string>
using namespace std;
int p[100000];
string s;
int main(){
    freopen("input.txt","r",stdin);
    freopen("output.txt","w",stdout);
    cin >> s;
    p[0]=-1;
    int n=s.size(),k=-1;
    for (int i=1; i<n; i++) {
        while (k>=0 && s[i]!=s[k+1]) k=p[k];
        if (s[i]==s[k+1]) k++;
        p[i]=k;
    }
    cout << n-p[n-1]-1;
    return 0;
}
