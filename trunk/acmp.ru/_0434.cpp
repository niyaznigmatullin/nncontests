#include <iostream>
#include <stdio.h>
#include <algorithm>
#include <math.h>
#include <vector>
#include <string>
#include <map>
#define sz size()
using namespace std;

map<int,int> miny,maxy;

pair <int,int> ans1[100000],ans2[100000],ans3[100000],ans4[100000];
int c[100000],x[100000],y[100000],n,lastx,lasty,kol1=0,kol2=0,kol3=0,kol4=0;

bool less_x(int i1, int i2) {
    return x[i1]<x[i2];
}
bool great_x(int i1, int i2) {
    return x[i1]>x[i2];
}

int main() {
	freopen("input.txt","r",stdin);
	freopen("output.txt","w",stdout);
    scanf("%d",&n);
    for (int i=0; i<n; i++) {
        scanf("%d%d",&x[i],&y[i]);
        if (miny.count(x[i])==0) miny[x[i]]=y[i]; else miny[x[i]]=min(miny[x[i]],y[i]);
        if (maxy.count(x[i])==0) maxy[x[i]]=y[i]; else maxy[x[i]]=max(maxy[x[i]],y[i]);
        c[i]=i;
    }
    sort(c,c+n,less_x);
    lastx=x[c[0]];
    lasty=maxy[x[c[0]]];
    ans1[kol1++]=make_pair(lastx,lasty);
    for (int i=1; i<n; i++) {
        if (lastx==x[c[i]]) continue;
        if (maxy[x[c[i]]]>lasty) {
            ans1[kol1++]=make_pair(x[c[i]],lasty);
            lasty=maxy[x[c[i]]];
            ans1[kol1++]=make_pair(x[c[i]],lasty);
        }
        lastx=x[c[i]];
    }
    lastx=x[c[0]];
    lasty=miny[x[c[0]]];
    ans2[kol2++]=make_pair(lastx,lasty);
    for (int i=1; i<n; i++) {
        if (lastx==x[c[i]]) continue;
        if (miny[x[c[i]]]<lasty){
            ans2[kol2++]=make_pair(x[c[i]],lasty);
            lasty=miny[x[c[i]]];
            ans2[kol2++]=make_pair(x[c[i]],lasty);
        }
        lastx=x[c[i]];
    }
    sort(c,c+n,great_x);
    lastx=x[c[0]];
    lasty=maxy[x[c[0]]];
    ans3[kol3++]=make_pair(lastx,lasty);
    for (int i=1; i<n; i++) {
        if (lastx==x[c[i]]) continue;
        if (maxy[x[c[i]]]>lasty) {
            ans3[kol3++]=make_pair(x[c[i]],lasty);
            lasty=maxy[x[c[i]]];
            ans3[kol3++]=make_pair(x[c[i]],lasty);
        }
        lastx=x[c[i]];
    }
    lastx=x[c[0]];
    lasty=miny[x[c[0]]];
    ans4[kol4++]=make_pair(lastx,lasty);
    for (int i=1; i<n; i++) {
        if (lastx==x[c[i]]) continue;
        if (miny[x[c[i]]]<lasty){
            ans4[kol4++]=make_pair(x[c[i]],lasty);
            lasty=miny[x[c[i]]];
            ans4[kol4++]=make_pair(x[c[i]],lasty);
        }
        lastx=x[c[i]];
    }
    printf("%d\n",kol1+kol2+kol3+kol4);
    for (int i=0; i<kol1; i++) printf("%d %d\n",ans1[i].first,ans1[i].second);
    for (int i=kol3-1; i>=0; i--) printf("%d %d\n",ans3[i].first,ans3[i].second);
    for (int i=0; i<kol4; i++) printf("%d %d\n",ans4[i].first,ans4[i].second);
    for (int i=kol2-1; i>=0; i--) printf("%d %d\n",ans2[i].first,ans2[i].second);
	return 0;
}
