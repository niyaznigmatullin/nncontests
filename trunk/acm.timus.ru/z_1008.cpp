#include <iostream>
#include <string>

using namespace std;

class TPoint {
public:
	int x, y;
	TPoint() { x=y=0; }
	TPoint(int xx, int yy) { x=xx; y=yy; }
	bool operator < (TPoint b) {
		return x<b.x || ( x == b.x && y == b.y);
	}
};

TPoint p[100];
int n;
int main() {
	scanf("%d", &n);
	for (int i=0; i<n; i++) {
		scanf("%d%d", &p[i].x, &p[i].y);
	}
    return 0;
}