#include <iostream>
#include <stdio.h>
#include <string>
#include <set>
using namespace std;
int n,r,y,q,p;
set<string> c;
string s,ss;
int main() {
    scanf("%d%d%d\n",&y,&n,&q);
    for (int i=0; i<n; i++) {
        getline(cin,s);
        if (s.find("#")==string::npos) p=s.size(); else p=s.find("#");
        ss=s.substr(0,p);
        if (c.find(ss)==c.end()) {
            r++;
            c.insert(ss);
        }
        if (r>q) {
            cout << s;
            return 0;
        }
    }
	return 0;
}
