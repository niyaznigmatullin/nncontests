#include <iostream>
#include <stdio.h>
#include <algorithm>
#include <vector>
#include <string>
using namespace std;
string s;
int i1,i2,ii,u[500][500],dx,dy,g;
bool rec(int x, int y, int k) {
    if (ii==s.size()) {
        i1=x;
        i2=y;
        u[i1][i2]=k;
        return true;
    }
    u[x][y]=k;
    bool ok=false;
    do {
        if (ii==s.size()) {
        i1=x;
        i2=y;
        u[i1][i2]=k;
        return true;
    }
        if (s[ii]=='N') {
            dx=-1;
            dy=0;
        } else if (s[ii]=='S') {
            dx=1;
            dy=0;
        } else if (s[ii]=='W') {
            dx=0;
            dy=-1;
        } else if (s[ii]=='E') {
            dx=0;
            dy=1;
        };
        if (u[x+dx][y+dy]==0) {
            ii++;
            ok=rec(x+dx,y+dy,k+1);
        } else {
            g=u[x+dx][y+dy];
            u[x][y]=0;
            ii++;
            return false;
        }
        if (g!=-1 && u[x][y]!=g) {
            u[x][y]=0;
            return false;
        } else if (g!=-1 && u[x][y]==g) g=-1;
    } while (!ok);
    return true;
}

int main() {
    freopen("input.txt","r",stdin);
    freopen("output.txt","w",stdout);
    cin >> s;
    int ii=0;
    g=-1;
    rec(200,200,1);
    string ans;
    while (u[i1][i2]!=1) {
        int p=u[i1][i2];
        if (p-1==u[i1-1][i2]) {
            i1--;
            ans+="N";
        } else if (p-1==u[i1+1][i2]) {
            i1++;
            ans+="S";
        } else if (p-1==u[i1][i2-1]) {
            i2--;
            ans+="W";
        } else {
            i2++;
            ans+="E";
        }
    }
    cout << ans;
    return 0;
}
