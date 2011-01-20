#include <iostream>
#include <sstream>
#include <stdio.h>
#include <string>
using namespace std;

int dm[12]={31,28,31,30,31,30,31,31,30,31,30,31};
double x,p;
int d;
string s;
int main() {
    freopen("deposit.in","r",stdin);
    freopen("deposit.out","w",stdout);
    cin >> x >> p;
    scanf("%d\n",&d);
    cin >> s;
    for (int i=0; i<(int)s.size(); i++) if (s[i]=='-') s[i]=' ';
    stringstream is(s);
    int day,mon;
    is >> day >> mon;
    int i=--mon;
    while (dm[i]<d) {
        x+=x*(dm[i]-day+1)/365.*p*.01;
        d-=dm[i]-day+1;
        day=1;
        i++;
    }
    if (d) x+=x*d/365.*p*.01;
    printf("%.10f",x);
    return 0;
}
