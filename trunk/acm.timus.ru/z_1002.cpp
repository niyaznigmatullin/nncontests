#include <iostream>
#include <string>

using namespace std;

string nn, s[50000], sn, ans;

int dp[100], pr[100], p1[100], last[100], p[50000][100], k, n, len[50000], numb[256];

string numb1[]={"oqz","ij","abc","def","gh","kl","mn","prs","tuv","wxy"};
int main() {
	for (int i=0; i<10; i++) {
		for (int j=0; j<(int)numb1[i].size(); j++) {
			numb[(int)numb1[i][j]]=i;
		}
	}
	cin >> nn;	
	while (nn!="-1") {
		memset(dp,127,sizeof(dp));
		dp[0]=0;
		cin >> n;
		for (int i=0; i<n; i++) {
			cin >> s[i];
			sn="";
			for (int j=0; j<(int)s[i].size(); j++) {
				sn+=(char)(numb[(int)s[i][j]]+'0');
			}
			p1[0]=-1;
			k=-1;
			len[i]=sn.size();
			for (int j=1; j<(int)sn.size(); j++) {
				while (k>-1 && sn[k+1]!=sn[j]) k=p1[k];
				if (sn[k+1]==sn[j]) k++;
				p1[j]=k;
			}
			k=-1;
			for (int j=0; j<(int)nn.size(); j++) {
				while (k>-1 && sn[k+1]!=nn[j]) k=p1[k];
				if (sn[k+1]==nn[j]) k++;
				p[i][j]=k;
			}
		}
		for (int i=0; i<(int)nn.size(); i++) {
			for (int j=0; j<n; j++) {
				if (p[j][i]==len[j]-1) {
					if (dp[i+1]>dp[i-len[j]+1]+1) {
						dp[i+1]=dp[i-len[j]+1]+1;
						pr[i+1]=i-len[j]+1;
						last[i+1] = j;
					}
				}
			}
		}
		pr[0]=-1;
		if (dp[nn.size()]<100000) {
			ans = "";
			int i=nn.size();
			while (i>0) {
				ans = s[last[i]] + " " + ans;
				i = pr[i];
			}
			ans = ans.substr(0,ans.size()-1);
			cout << ans << "\n";
		} else printf("No solution.\n");
		cin >> nn;
	}
    return 0;
}