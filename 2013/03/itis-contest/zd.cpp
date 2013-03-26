#include <iostream>
#include <cstdio>

using namespace std;

int main() {    
    freopen("input.txt", "r", stdin);
    freopen("output.txt", "w", stdout);
    string s;
    cin >> s;
    int n = s.length();
    int cpp = 0;
    int java = 0;
    int bad = s[0] == '_';
    if (s[0] >= 'A' && s[0] <= 'Z') bad = 1;
    if (s[n - 1] == '_') bad = 1;
    for (int i = 0; i < n; i++) {
        if (s[i] == '_') cpp = 1;
        if (i + 1 < n && s[i] == '_' && s[i + 1] == '_') bad = 1;
        if (s[i] >= 'A' && s[i] <= 'Z') java = 1;
    }
    if (bad || (cpp && java)) {
        cout << "Error!\n";
        return 0;    
    }
    if (cpp) {
        for (int i = 0; i < n; ) {
            int j = i;
            while (j < n && s[j] != '_') {
                j++;
            }
            if (i != 0) cout << (char) (s[i] - 'a' + 'A'); else cout << s[i];
            for (int k = i + 1; k < j; k++) cout << s[k];
            i = j + 1;
        }
    } else {
        for (int i = 0; i < n; ) {
            int j = i + 1;
            while (j < n && (s[j] < 'A' || s[j] > 'Z')) {
                j++;
            }
            if (i != 0) {
                cout << '_' << (char) (s[i] - 'A' + 'a');
            } else {
                cout << s[i];
            }
            for (int k = i + 1; k < j; k++) cout << s[k];
            i = j;
        }
    }
    cout << "\n";
}