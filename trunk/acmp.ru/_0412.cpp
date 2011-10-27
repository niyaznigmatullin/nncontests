#include <iostream>
#include <stdio.h>
#include <algorithm>
#include <math.h>
#include <vector>
#include <string>
#include <sstream>
#define sz size()
using namespace std;

string s1,s2,s3;
char a[10][10];

int main() {
	freopen("input.txt","r",stdin);
	freopen("output.txt","w",stdout);
	cin >> s1 >> s2 >> s3;
	a[s1[0]-'a'][s1[1]-'1']='W';
	a[s2[0]-'a'][s2[1]-'1']='A';
	a[s3[0]-'a'][s3[1]-'1']='B';
	int x=s2[0]-'a', y=s2[1]-'1';
	for (int xx=x, yy=y; xx>=0 && yy>=0 && a[xx][yy]!='W'; xx--, yy--) {
		if (a[xx][yy]=='B') {
			cout << "YES";
			return 0;
		}
	}
	for (int xx=x, yy=y; xx<8 && y<8 && a[xx][yy]!='W'; xx++, yy++) {
		if (a[xx][yy]=='B') {
			cout << "YES";
			return 0;
		}
	}
	for (int xx=x, yy=y; xx<8 && yy>=0 && a[xx][yy]!='W'; xx++, yy--) {
		if (a[xx][yy]=='B') {
			cout << "YES";
			return 0;
		}
	}
	for (int xx=x, yy=y; xx>=0 && y<8 && a[xx][yy]!='W'; xx--, yy++) {
		if (a[xx][yy]=='B') {
			cout << "YES";
			return 0;
		}
	}
	for (int xx=x, yy=y; xx>=0 && a[xx][yy]!='W'; xx--) {
		if (a[xx][yy]=='B') {
			cout << "YES";
			return 0;
		}
	}
	for (int xx=x, yy=y; yy>=0 && a[xx][yy]!='W'; yy--) {
		if (a[xx][yy]=='B') {
			cout << "YES";
			return 0;
		}
	}
	for (int xx=x, yy=y; xx<8 && a[xx][yy]!='W'; xx++) {
		if (a[xx][yy]=='B') {
			cout << "YES";
			return 0;
		}
	}
	for (int xx=x, yy=y; yy<8 && a[xx][yy]!='W'; yy++) {
		if (a[xx][yy]=='B') {
			cout << "YES";
			return 0;
		}
	}
	cout << "NO";
	return 0;
}
