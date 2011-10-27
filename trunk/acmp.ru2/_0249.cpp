#include <iostream>
#include <stdio.h>
#include <string>
#include <vector>
#include <algorithm>
#define pb push_back
#define sz size()
using namespace std;

int main() {
	freopen("input.txt","r",stdin);
	freopen("output.txt","w",stdout);
	string s;
	cin >> s;
	vector <char> s1;
	s1.pb(')');
	int q=0;
	for (int i=0; i<(int)s.sz; i++) {
		if (s[i]=='[' || s[i]=='(' || s[i]=='{') {
			s1.pb(s[i]);
		} else {
			if ((s[i]==']' && s1.back()=='[') || (s[i]==')' && s1.back()=='(') || (s[i]=='}' && s1.back()=='{')) {
				s1.pop_back();
			} else {
				q++;
			}
		}
	}
	q+=s1.sz-1;
	cout << q;
	return 0;
}
