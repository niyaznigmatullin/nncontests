#include <iostream>
#include <stdio.h>
#include <algorithm>
#include <math.h>
#include <vector>
#include <string>
#define sz size()
using namespace std;

string ans="",s1,s2;

void kmp(string s1, string s2) {
    s1="~"+s1;
    s2="~"+s2;
    int p[1100];
    p[1]=0;
    int k=0;
    for (int i=2; i<s1.sz; i++) {
        while (k>0 && tolower(s1[i])!=tolower(s1[k+1])) k=p[k];
        if (tolower(s1[i])==tolower(s1[k+1])) k++;
        p[i]=k;
    }
    k=0;
    for (int i=1; i<s2.sz; i++) {
        while (k>0 && tolower(s2[i])!=tolower(s1[k+1])) k=p[k];
        if (tolower(s2[i])==tolower(s1[k+1])) k++;
    }
    if (ans=="" || ans.sz>(s2.substr(1,s2.sz-k-1)+s1.substr(1)).sz || (ans.sz==(s2.substr(1,s2.sz-k-1)+s1.substr(1)).sz && ans>(s2.substr(1,s2.sz-k-1)+s1.substr(1)))) {
        ans=s2.substr(1,s2.sz-k-1)+s1.substr(1);
    }
}

int main() {
	freopen("input.txt","r",stdin);
	freopen("output.txt","w",stdout);
	getline(cin,s1);
	getline(cin,s2);
	string ss1=s1,ss2=s2;
	for (int i=0; i<ss1.length(); i++) ss1[i]=tolower(ss1[i]);
	for (int i=0; i<ss2.length(); i++) ss2[i]=tolower(ss2[i]);
	int p;
	if ((p=ss1.find(ss2))!=-1) {
		s1[p]=toupper(s1[p]);
		cout << s1;
		return 0;
	}
	if ((p=ss2.find(ss1))!=-1) {
		s2[p]=toupper(s2[p]);
		cout << s2;
		return 0;
	}
	kmp(s1,s2);
	kmp(s2,s1);
	cout << ans;
	return 0;
}
