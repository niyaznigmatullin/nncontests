#include <iostream>
#include <string>

using namespace std;
string s;
int main() {
    cin >> s;
    for (int i=0; i<s.size()-2; i++) {
        if (s[i]==s[i+1] && s[i]==s[i+2]) {
            while(i<s.size() && s[i+1]==s[i]) s.erase(i+1,1);
        }
    }
    bool ok;
    do {
        ok=false;
        for (int i=0; i<s.size()-1; i++) if (s[i]==s[i+1]) { ok=true; s.erase(i,2); i--; }
    } while (ok);
    cout << s;
    return 0;
}
