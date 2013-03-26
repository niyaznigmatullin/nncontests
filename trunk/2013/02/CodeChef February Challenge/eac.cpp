#ifdef _MSC_VER
#define LOCAL
#endif
 
#ifdef LOCAL
#define TEST
//#define DEBUG
#ifdef DEBUG
    //#define DEBUG_SLOW
#endif
#endif
 
const double TIME_LIMIT=0.95;
const double OP01_STEP_RATIO=0.25;
const double OP01_TIME_RATIO=0.9;
const double OP01_TIME_REGRESSION=0.7;
int N_STATES=32;
 
void set_params(int n)
{
    if (n<=30) N_STATES=64;
    if (n<=20) N_STATES=128;
}
 
#include <vector>
#include <list>
#include <map>
#include <set>
#include <deque>
#include <queue>
#include <stack>
#include <bitset>
#include <algorithm>
#include <functional>
#include <numeric>
#include <utility>
#include <sstream>
#include <iostream>
#include <iomanip>
#include <cstdio>
#include <cmath>
#include <cstdlib>
#include <cctype>
#include <string>
#include <cstring>
#include <ctime>
#include <stdarg.h>
 
using namespace std;
 
typedef unsigned int uint;
typedef long long int64;
typedef unsigned long long uint64;
#define SIZE(A) ((int)A.size())
#define LENGTH(A) ((int)A.length())
#define two(X) (1<<(X))
#define twoL(X) (((int64)1)<<(X))
#define contain(S,X) ((S&two(X))>0)
 
#ifdef DEBUG
#define ASSERT(_Expression) (void)((!!(_Expression)||(__assert((#_Expression),__LINE__),0)));
void __assert(const char *_Message,const unsigned _Line) {printf("ASSERTION FAILED\n");printf("Message = %ls\n",_Message);printf("Line = %u\n",_Line); exit(0);}
#endif
 
typedef unsigned long long uint64;
 
#ifdef _MSC_VER
#define TIMES_PER_SEC (1.75e9)
#else
#define TIMES_PER_SEC (3.05e9)
#endif
 
uint64 rdtsc()
{
#ifdef _MSC_VER
    __asm { rdtsc }
#else
    uint64 time;
    __asm__ volatile ("rdtsc" : "=A" (time));
    return time;
#endif
}
 
double gettime()
{
    return (double)rdtsc()/TIMES_PER_SEC;
}
 
#ifdef LOCAL
#define LENGTH(A) ((int)A.length())
class Timer
{
public:
    static uint64 timeUsed[1024];
    int id;
    uint64 startTime;
    Timer(int id=0) { this->id=id; startTime=rdtsc(); }
    ~Timer() { timeUsed[id]+=(rdtsc()-startTime); }
    static void show()
    {
        for (int i=0;i<1024;i++) if (timeUsed[i]>0)
        {
            char str[100]; sprintf(str,"%.6lf",timeUsed[i]/TIMES_PER_SEC);
            string s=str; if (LENGTH(s)<15) s=" "+s;
            printf("Timer   %4d %s\n",i,s.c_str());
        }
    }
};
uint64 Timer::timeUsed[1024]={0};
 
class Counter
{
public:
    static int cnt[1024];
    Counter(int id=0) {  cnt[id]++; }
    ~Counter() { }
    static void show()
    {
        for (int i=0;i<1024;i++) if (cnt[i]>0) printf("Counter %4d %9d\n",i,cnt[i]);
    }
};
int Counter::cnt[1024]={0};
#endif
 
#define MT_N 624
#define MT_M 397
#define MT_MSB 0x80000000U
#define MT_LS31B 0x7FFFFFFFU
#define MT_A 2567483615U
 
class MersenneTwister // pseudo-random number generator
{
    uint twistory[MT_N]; // history (i.e., previous states) of the generator
    int pos;
public:
    MersenneTwister(uint seed=0)
    {
        twistory[0]=seed;
        for (int i=1;i<MT_N;i++) twistory[i]=1812433253U*(twistory[i-1]^(twistory[i-1]>>30))+i;
        pos=0;
    }
    void reset(uint seed=0)
    {
        twistory[0]=seed;
        for (int i=1;i<MT_N;i++) twistory[i]=1812433253U*(twistory[i-1]^(twistory[i-1]>>30))+i;
        pos=0;
    }
    void generate(void)
    {
        uint tmp; int i;
        for (i=0;i<MT_N-MT_M;i++)
        {
            tmp=(twistory[i]&MT_MSB)+(twistory[i+1]&MT_LS31B);
            twistory[i]=twistory[i+MT_M]^(tmp>>1)^(MT_A&-(tmp&1));
        }
        for (;i<MT_N-1;i++)
        {
            tmp=(twistory[i]&MT_MSB)+(twistory[i+1]&MT_LS31B);
            twistory[i]=twistory[i+MT_M-MT_N]^(tmp>>1)^(MT_A&-(tmp&1));
        }
        tmp=(twistory[i]&MT_MSB)+(twistory[0]&MT_LS31B);
        twistory[i]=twistory[MT_M-1]^(tmp>>1)^(MT_A&-(tmp&1));
    }
    uint rand_unsigned()
    {
        if (pos==0) generate();
        uint ans=twistory[pos++];
        pos&=-(pos!=624); // if (pos == 624) pos = 0;
        ans^=ans>>11;
        ans^=(ans<<7)&2636928640U;
        ans^=(ans<<15)&4022730752U;
        ans^=ans>>18;
        return ans;
    }
    double next_double()
    {
        return next_int(32768)/32768.0;
    }
    int rand_signed()
    {
        return rand_unsigned()>>1;
    }
    int next_int(int n)
    {
        if (n==1) return 0;
        return rand_unsigned()%n; // close enough
    }
    int next_int(int a,int b)
    {
        if (a==b) return a;
        return rand_unsigned()%(b-a+1)+a; // close enough
    }
};
 
const int MAXN=64;
const int MAXN2=MAXN*MAXN;
const int MAX_N_STATES=256;
const int INF=100000000;
 
// Grid.
int n;
char target_grid[MAXN][MAXN];
int target[MAXN][MAXN];
int target3[MAXN][MAXN];
// Result.
int best_length,best_moves[MAXN2];
// Preprocess.
int cbit_parity[16];
int p_change_score[2*16*16][16]; // p_change_score[b,s,t][mask]
// Greedy.
int length,moves[MAXN*MAXN];
int a1[MAXN][MAXN];
int a2[MAXN][MAXN]; // a2[i][j] = xor for 4 neighbor cells.
int a3[MAXN][MAXN]; // a3[i][j] = pack 4 neighbor cells into 0-15.
// Start time.
double start_time;
// Random generator.
MersenneTwister rnd(1028);
// Dp on last a few rows.
const int DP_WIDTH=5;
const int DP_BITS=DP_WIDTH*(DP_WIDTH-1)/2;
int dp_pairs_a[DP_BITS],dp_pairs_b[DP_BITS];
int dp_score[MAXN][1<<DP_BITS];
int dp_n_exp0[1<<DP_WIDTH],dp_exp0[1<<DP_WIDTH][1<<DP_BITS];
int dp_n_exp1[1<<DP_WIDTH],dp_exp1[1<<DP_WIDTH][1<<DP_BITS];
 
void copy2d(int n,int s[MAXN][MAXN],int t[MAXN][MAXN]);
class State
{
public:
    int length,moves[MAXN*MAXN],a1[MAXN][MAXN],cost;
    void load()
    {
        ::length=length;
        memcpy(::moves,moves,length*sizeof(int));
        copy2d(n,a1,::a1);
    }
    void save()
    {
        length=::length;
        memcpy(moves,::moves,length*sizeof(int));
        copy2d(n,::a1,a1);
    }
};
 
#define PACK5(d,x1,y1,x2,y2) (((d)<<24)|((x1)<<18)|((y1)<<12)|((x2)<<6)|(y2))
inline void UPACK5(int s,int &d,int &x1,int &y1,int &x2,int &y2) { y2=s&63; s>>=6; x2=s&63; s>>=6; y1=s&63; s>>=6; x1=s&63; s>>=6; d=s; }
 
struct MaxMatching
{
    int n,match[MAXN];
    bool g[MAXN][MAXN];
    int q[MAXN],prev[MAXN],father[MAXN];
    bool visited[MAXN],is_shrinked[MAXN];
    bool is_in_path[MAXN];
 
    void clear()
    {
        for (int i=0;i<n;i++) match[i]=-1;
    }
    void init(int n)
    {
        this->n=n;
        memset(g,false,sizeof(g));
        clear();
    }
    void addedge(int u,int v)
    {
        g[u][v]=g[v][u]=true;
    }
    int ford()
    {
        int r=0;
        for (int i=0;i<n;i++) for (int j=0;j<n;j++) if (match[i]<0 && match[j]<0 && g[i][j]) match[i]=j,match[j]=i,r++;
        for (int i=0;i<n;i++) if (match[i]<0 && bfs(i)) r++;
        return r;
    }
    bool bfs(int src)
    {
        for (int i=0;i<n;i++) prev[i]=-1;
        for (int i=0;i<n;i++) visited[i]=false;
        for (int i=0;i<n;i++) father[i]=i;
        int op=0;
        q[op++]=src;
        visited[src]=true;
        for (int cl=0;cl<op;cl++) for (int key=q[cl],other=0;other<n;other++)
            if (g[key][other] && father[key]!=father[other] && other!=match[key])
                if (other==src || (match[other]>=0 && prev[match[other]]>=0))
                {
                    int next=contract(key,other);
                    for (int i=0;i<n;i++) if (is_shrinked[father[i]])
                    {
                        father[i]=next;
                        if (!visited[i]) visited[i]=true,q[op++]=i;
                    }
                }
                else if (prev[other]<0)
                {
                    prev[other]=key;
                    if (match[other]<0)
                    {
                        expand(other);
                        return true;
                    }
                    else
                    {
                        q[op++]=match[other];
                        visited[match[other]]=true;
                    }
                }
        return false;
    }
    void expand(int v)
    {
        while (v>=0)
        {
            int p=prev[v];
            int k=match[p];
            match[v]=p;
            match[p]=v;
            v=k;
        }
    }
    void change_blossom(int b,int u)
    {
        while (father[u]!=b)
        {
            int v=match[u];
            is_shrinked[father[v]]=is_shrinked[father[u]]=true;
            u=prev[v];
            if (father[u]!=b) prev[u]=v;
        }
    }
    int contract(int u,int v)
    {
        memset(is_shrinked,false,sizeof(is_shrinked));
        int key=get_father(father[u],father[v]);
        change_blossom(key,u);
        change_blossom(key,v);
        if (father[u]!=key) prev[u]=v;
        if (father[v]!=key) prev[v]=u;
        return key;
    }
    int get_father(int u,int v)
    {
        for (int i=0;i<n;i++) is_in_path[i]=false;
        while (1)
        {
            is_in_path[u]=true;
            if (match[u]<0) break;
            u=father[prev[match[u]]];
        }
        for (;!is_in_path[v];v=father[prev[match[v]]]);
        return v;
    }
};
MaxMatching max_matching;
 
void convert_a1_to_a3(int a1[MAXN][MAXN],int a3[MAXN][MAXN])
{
    for (int i=0;i<=n;i++) for (int j=0;j<=n;j++) a3[i][j]=0;
    for (int i=0;i<n;i++) for (int j=0;j<n;j++) if (a1[i][j]) { a3[i+1][j+1]^=1; a3[i+1][j]^=2; a3[i][j+1]^=4; a3[i][j]^=8; }
}
void convert_a1_to_a2(int a1[MAXN][MAXN],int a2[MAXN][MAXN])
{
    for (int i=0;i<=n;i++) for (int j=0;j<=n;j++) a2[i][j]=0;
    for (int i=0;i<n;i++) for (int j=0;j<n;j++) if (a1[i][j]!=target[i][j]) { a2[i+1][j+1]^=1; a2[i+1][j]^=1; a2[i][j+1]^=1; a2[i][j]^=1; }
}
void convert_a2_to_a1(int a2[MAXN][MAXN],int a1[MAXN][MAXN])
{
    int s[MAXN][MAXN];
    for (int i=0;i<=n;i++) for (int j=0;j<=n;j++) s[i][j]=0;
    for (int i=1;i<=n;i++) for (int j=1;j<=n;j++) s[i][j]=s[i-1][j-1]^s[i-1][j]^s[i][j-1]^a2[i-1][j-1];
    for (int i=0;i<n;i++) for (int j=0;j<n;j++) a1[i][j]=s[i+1][j+1]^target[i][j];
}
void init()
{
    for (int i=0;i<n;i++) for (int j=0;j<n;j++) target[i][j]=(int)(target_grid[i][j]=='B');
    convert_a1_to_a3(target,target3);
    set_params(n);
}
void check_result(int length,int moves[])
{
#ifdef DEBUG
    ASSERT(length>=0 && length<=n*n);
    for (int i=0;i<length;i++)
    {
        int b,x1,y1,x2,y2;
        UPACK5(moves[i],b,x1,y1,x2,y2);
        ASSERT(b>=0 && b<3 && x1>=0 && x1<=x2 && x2<n && y1>=0 && y1<=y2 && y2<n);
    }
#endif
    if (length<best_length) { best_length=length; memcpy(best_moves,moves,length*sizeof(int)); }
}
void prepare_dp()
{
    for (int idx=0,i=0;i<DP_WIDTH;i++) for (int j=i+1;j<DP_WIDTH;j++) dp_pairs_a[idx]=i,dp_pairs_b[idx++]=j;
    memset(dp_n_exp0,0,sizeof(dp_n_exp0));
    memset(dp_n_exp1,0,sizeof(dp_n_exp1));
    for (int set=0;set<(1<<DP_BITS);set++)
    {
        int c[DP_WIDTH];
        memset(c,0,sizeof(c));
        for (int i=0;i<DP_BITS;i++) if (set&(1<<i)) { c[dp_pairs_a[i]]++; c[dp_pairs_b[i]]++; }
        int mask=0,up=0;
        for (int i=0;i<DP_WIDTH;i++) { up+=c[i]/2; if (c[i]&1) mask|=(1<<i); }
        if (up==0) dp_exp0[mask][dp_n_exp0[mask]++]=set;
        if (up==1) dp_exp1[mask][dp_n_exp1[mask]++]=set;
    }
}
#define CID(b,s,t) (((s)<<5)|((t)<<1)|b)
void preprocess()
{
    for (int set=0;set<16;set++) cbit_parity[set]=(set==0)?0:(cbit_parity[set&(set-1)]^1);
    for (int mask=0;mask<16;mask++)
    {
        if (mask==0 || mask==9 || mask==6 || mask==14 || mask==13 || mask==11 || mask==7) continue;
        for (int b=0;b<2;b++) for (int s=0;s<16;s++)
        {
            int s2=(s&(15-mask))+b*mask;
            for (int t=0;t<16;t++) p_change_score[CID(b,s,t)][mask]=cbit_parity[s^t]-cbit_parity[s2^t];
        }
    }
    prepare_dp();
}
 
void random_permutation(int n,int p[],int s,int t)
{
    for (int i=0;i<n;i++) p[i]=i;
    for (int i=s;i<t;i++) swap(p[i],p[rnd.next_int(i,t)]);
}
void random_permutation(int n,int p[])
{
    random_permutation(n,p,0,n-1);
}
void transpose2d(int n,int a[MAXN][MAXN])
{
    for (int i=0;i<n;i++) for (int j=0;j<i;j++) swap(a[i][j],a[j][i]);
}
void transpose_move(int &move)
{
    int b,x1,y1,x2,y2;
    UPACK5(move,b,x1,y1,x2,y2);
    move=PACK5(b,y1,x1,y2,x2);
}
 
void copy2d(int n,int s[MAXN][MAXN],int t[MAXN][MAXN])
{
    for (int i=0;i<n;i++) memcpy(t[i],s[i],n*sizeof(int));
}
int64 hcode2d(int a[MAXN][MAXN])
{
    int64 hcode=0;
    for (int i=0;i<n;i++) for (int j=0;j<n;j++) hcode=hcode*3137+a[i][j];
    return hcode;
}
 
int get_cost(int a[MAXN][MAXN])
{
    int a2[MAXN][MAXN];
    convert_a1_to_a2(a,a2);
    int c=0;
    for (int i=0;i<=n;i++) for (int j=0;j<=n;j++) c+=a2[i][j];
    return c;
}
 
int apply_OP01(const double time_limit)
{
#ifdef TEST
    Timer t10(10);
#endif
    int total_benefits=0;
    while (gettime()<time_limit)
    {
        convert_a1_to_a3(a1,a3);
        int *c0[MAXN][MAXN],*c1[MAXN][MAXN];
        int opt_score=6,opt_move_cnt=0,opt_moves[256];
        for (int x=0;x<=n;x++) for (int y=0;y<=n;y++)
        {
            c0[x][y]=p_change_score[CID(0,a3[x][y],target3[x][y])];
            c1[x][y]=p_change_score[CID(1,a3[x][y],target3[x][y])];
        }
        for (int x1=0;x1<n;x1++)
        {
            int row[MAXN],s_row0[MAXN],t_row0[MAXN],s_row1[MAXN],t_row1[MAXN];
            for (int y=0;y<=n;y++)
            {
                row[y]=c0[x1][y][12];
                s_row0[y]=c0[x1][y][8];
                t_row0[y]=c0[x1][y][4];
                s_row1[y]=c1[x1][y][8];
                t_row1[y]=c1[x1][y][4];
            }
            for (int x2=x1+1;x2<=n;x2++)
            {
                int b_s_score0=-INF,b_pos_y0=-1,b_s_score1=-INF,b_pos_y1=-1,s=0;
                for (int y=0;y<=n;y++)
                {
                    int local_score0=s+t_row0[y]+c0[x2][y][1]+b_s_score0;
                    if (local_score0>=opt_score)
                    {
                        if (local_score0>opt_score) opt_score=local_score0,opt_move_cnt=0;
                        if (opt_move_cnt<256) opt_moves[opt_move_cnt++]=PACK5(0,x1,b_pos_y0,x2-1,y-1);
                    }
                    int local_score1=s+t_row1[y]+c1[x2][y][1]+b_s_score1;
                    if (local_score1>=opt_score)
                    {
                        if (local_score1>opt_score) opt_score=local_score1,opt_move_cnt=0;
                        if (opt_move_cnt<256) opt_moves[opt_move_cnt++]=PACK5(1,x1,b_pos_y1,x2-1,y-1);
                    }
#ifdef DEBUG_SLOW
                    if (b_pos_y0>=0)
                        for (int b=0;b<2;b++)
                        {
                            int t_a1[MAXN][MAXN]; memcpy(t_a1,a1,sizeof(t_a1));
                            int g[MAXN][MAXN],olds=0,news=0;
                            convert_a1_to_a2(a1,g); for (int x=0;x<=n;x++) for (int _y=0;_y<=n;_y++) olds+=g[x][_y];
                            for (int x=x1;x<x2;x++) for (int _y=((b==0)?b_pos_y0:b_pos_y1);_y<y;_y++) a1[x][_y]=b;
                            convert_a1_to_a2(a1,g); for (int x=0;x<=n;x++) for (int _y=0;_y<=n;_y++) news+=g[x][_y];
                            ASSERT((b==0?local_score0:local_score1)==olds-news);
                            memcpy(a1,t_a1,sizeof(t_a1));
                        }
#endif
                    s+=row[y]+c0[x2][y][3];
                    int base_score0=s_row0[y]+c0[x2][y][2]-s;
                    if (base_score0>=b_s_score0) b_s_score0=base_score0,b_pos_y0=y;
                    int base_score1=s_row1[y]+c1[x2][y][2]-s;
                    if (base_score1>=b_s_score1) b_s_score1=base_score1,b_pos_y1=y;
                    row[y]+=c0[x2][y][15];
                    s_row0[y]+=c0[x2][y][10];
                    t_row0[y]+=c0[x2][y][5];
                    s_row1[y]+=c1[x2][y][10];
                    t_row1[y]+=c1[x2][y][5];
                }
            }
        }
        if (opt_move_cnt>0)
        {
            total_benefits+=(opt_score-4);
#ifdef DEBUG
            ASSERT(opt_move_cnt>0);
#endif
            int next_move=opt_moves[rnd.next_int(0,opt_move_cnt-1)];
            int b,x1,y1,x2,y2;
            UPACK5(next_move,b,x1,y1,x2,y2);
#ifdef DEBUG
            ASSERT(b>=0 && b<3 && x1>=0 && x1<=x2 && x2<n && y1>=0 && y1<=y2 && y2<n);
#endif
            moves[length++]=next_move;
            for (int x=x1;x<=x2;x++) for (int y=y1;y<=y2;y++) a1[x][y]=b;
            continue;
        }
        break;
    }
    return total_benefits;
}
 
double split_time(const double time_limit,const double ratio)
{
    double current_time=gettime();
    if (current_time>=time_limit) return time_limit;
    return current_time+(time_limit-current_time)*ratio;
}
 
State current_states[MAX_N_STATES];
State next_states[MAX_N_STATES];
 
void solve(const double time_limit)
{
    int m=(int)(OP01_STEP_RATIO*n+0.5);
    double op01_time_limit=split_time(time_limit,OP01_TIME_RATIO);
    memset(a1,0,sizeof(a1));
    length=0;
    for (int i=0;i<N_STATES;i++) next_states[i].cost=INF;
    next_states[0].save();
    next_states[0].cost=get_cost(a1);
    int perm_x[MAXN],perm_y[MAXN];
    for (int step=0;;step++)
    {
        double local_time_limit;
        if (step<m)
            local_time_limit=split_time(op01_time_limit,1.0/pow(m-step,OP01_TIME_REGRESSION));
        else
            local_time_limit=split_time(time_limit,1.0/max(2,n-step));
        vector<pair<int,int> > order;
        for (int i=0;i<N_STATES;i++) order.push_back(make_pair(next_states[i].cost,i));
        sort(order.begin(),order.end());
        for (int i=0;i<N_STATES;i++) current_states[i]=next_states[order[i].second];
        for (int i=0;i<N_STATES;i++) next_states[i].cost=INF;
        for (int i=0;i<N_STATES;i++)
            if (step>=m && (current_states[i].cost+3)/4>=best_length)
                current_states[i].cost=INF;
        for (int i=0;i<N_STATES;i++) if (step>=m && current_states[i].cost<INF)
        {
            current_states[i].load();
            convert_a1_to_a2(a1,a2);
            int c2[MAXN];
            for (int x=0;x<=n;x++) c2[x]=0;
            for (int x=0;x<=n;x++) for (int y=0;y<=n;y++) c2[x]+=a2[x][y];
            int nz=0,zpos[MAXN];
            for (int x=0;x<=n;x++) if (c2[x]>0) zpos[nz++]=x;
            if (nz>DP_WIDTH) continue;
            for (int x=0;x<=n && nz<DP_WIDTH;x++) if (c2[x]==0) zpos[nz++]=x;
            for (int set=0;set<(1<<DP_BITS);set++) dp_score[0][set]=INF;
            dp_score[0][0]=0;
            int masks[MAXN];
            for (int y=0;y<=n;y++)
            {
                current_states[i].cost=INF;
                int mask=0;
                int *old_score=dp_score[y],*new_score=dp_score[y+1];
                for (int set=0;set<(1<<DP_BITS);set++) new_score[set]=INF;
                for (int i=0;i<DP_WIDTH;i++) if (a2[zpos[i]][y]) mask|=(1<<i);
                masks[y]=mask;
                for (int set=0;set<(1<<DP_BITS);set++) if (old_score[set]<INF)
                {
                    int tscore=old_score[set];
                    for (int *p=dp_exp0[mask],i=dp_n_exp0[mask]-1;i>=0;i--) if (tscore<new_score[set^p[i]]) new_score[set^p[i]]=tscore;
                    tscore++;
                    for (int *p=dp_exp1[mask],i=dp_n_exp1[mask]-1;i>=0;i--) if (tscore<new_score[set^p[i]]) new_score[set^p[i]]=tscore;
                }
            }
            if (dp_score[n+1][0]<=1) current_states[i].cost=INF;
            int est_length=dp_score[n+1][0]*2;
            for (int i=0;i<=n;i++) est_length+=c2[i];
#ifdef DEBUG
            ASSERT(est_length%4==0);
#endif
            est_length=est_length/4+length;
            if (est_length>=best_length) continue;
            int lasty[DP_BITS],last_set=0;
            for (int y=n;y>=0;y--)
            {
                int exp_score=dp_score[y+1][last_set],set_used=-1,mask=masks[y];
                for (int *p=dp_exp0[mask],i=dp_n_exp0[mask]-1;set_used<0 && i>=0;i--) if (exp_score==dp_score[y][last_set^p[i]]) set_used=p[i];
                for (int *p=dp_exp1[mask],i=dp_n_exp1[mask]-1;set_used<0 && i>=0;i--) if (exp_score==dp_score[y][last_set^p[i]]+1) set_used=p[i];
#ifdef DEBUG
                ASSERT(set_used>=0);
#endif
                for (int i=0;i<DP_BITS;i++)
                    if ((last_set&(1<<i)) && (set_used&(1<<i)))
                    {
                        int x1=zpos[dp_pairs_a[i]],x2=zpos[dp_pairs_b[i]],y1=y,y2=lasty[i];
                        moves[length++]=PACK5(2,min(x1,x2),min(y1,y2),max(x1,x2)-1,max(y1,y2)-1);
                    }
                    else if (!(last_set&(1<<i)) && (set_used&(1<<i)))
                        lasty[i]=y;
                last_set^=set_used;
            }
#ifdef DEBUG
            ASSERT(est_length==length && last_set==0);
#endif
            check_result(length,moves);
        }
        bool more_state=false;
        for (int i=0;i<N_STATES;i++) if (current_states[i].cost<INF)
            if (current_states[i].cost==current_states[i].length*4)
            {
                check_result(current_states[i].length,current_states[i].moves);
                current_states[i].cost=INF;
            }
            else if (step>=m && (current_states[i].cost+3)/4>=best_length)
                current_states[i].cost=INF;
            else
                more_state=true;
        if (!more_state) break;
        int visited[MAX_N_STATES][MAXN];
        for (int i=0;i<N_STATES;i++) for (int j=0;j<n;j++) visited[i][j]=0;
        for (int idx=0;;idx=(idx+1)%N_STATES)
        {
            if (current_states[idx].cost==INF) continue;
            if (next_states[0].cost<INF && gettime()>=local_time_limit) break;
            int worst=0;
            for (int i=0;i<N_STATES;i++) if (next_states[i].cost>next_states[worst].cost) worst=i;
            if (step>=m && current_states[idx].cost>=next_states[worst].cost) { current_states[idx].cost=INF; continue; }
#ifdef TEST
            Counter _ct((step<m)?0:1);
            Timer _tt((step<m)?0:1);
#endif
            current_states[idx].load();
            convert_a1_to_a2(a1,a2);
            random_permutation(n+1,perm_x);
            int c2[MAXN];
            for (int x=0;x<=n;x++) c2[x]=0;
            for (int x=0;x<=n;x++) for (int y=0;y<=n;y++) c2[x]+=a2[x][y];
            int x1=-1,smallest_c=INF;
            for (int i=0;i<=n;i++)
            {
                int x=perm_x[i],t=c2[x]+visited[idx][x];
                if (c2[x]>0 && t<smallest_c) smallest_c=t,x1=x;
            }
#ifdef DEBUG
            ASSERT(x1>=0);
#endif
            random_permutation(n+1,perm_y);
            visited[idx][x1]+=2;
#ifdef DEBUG
            ASSERT(x1>=0 && c2[x1]>0);
#endif
            int64 h[MAXN];
            for (int i=0;i<=n;i++) h[i]=0;
            for (int i=0;i<=n;i++) if (i!=x1) for (int j=0;j<=n;j++) if (a2[i][j]) h[j]|=twoL(i);
            int cnt=0,idx[MAXN];
            for (int k=0;k<=n;k++) if (a2[x1][perm_y[k]]) idx[cnt++]=perm_y[k];
#ifdef DEBUG
            ASSERT(cnt%2==0);
#endif
            max_matching.init(cnt);
            for (int i=0;i<cnt;i++) for (int j=i+1;j<cnt;j++) if (h[idx[i]]&h[idx[j]]) max_matching.addedge(i,j);
            max_matching.ford();
            int last=-1,*match=max_matching.match;
#ifdef DEBUG
            int _t3[MAXN];
            for (int i=0;i<cnt;i++) _t3[i]=0;
            for (int i=0;i<cnt;i++) if (match[i]>=0) _t3[match[i]]++;
            for (int i=0;i<cnt;i++) ASSERT(_t3[i]<=1);
            for (int i=0;i<cnt;i++) if (match[i]>=0) ASSERT(match[match[i]]==i);
#endif
            for (int i=0;i<cnt;i++) if (match[i]<0)
                if (last<0) last=i;
                else max_matching.match[last]=i,last=-1;
            for (int round=0;round<2;round++) for (int i=0;i<cnt;i++) if (match[i]>i)
            {
                if ((match[match[i]]==i)!=(round==0)) continue;
                int y1=idx[i],y2=idx[match[i]];
                int rc=(h[y1]&h[y2])?2:1;
                int best_cost=INF,best_x2=-1;
                for (int j=0;j<=n;j++)
                {
                    int x2=perm_x[j];
                    if (x2==x1 || a2[x2][y1]+a2[x2][y2]<rc) continue;
                    if (c2[x2]<best_cost) best_cost=c2[x2],best_x2=x2;
                }
#ifdef DEBUG
                ASSERT(best_x2>=0);
#endif
                if (best_x2>=0)
                {
                    int x2=best_x2;
#ifdef DEBUG
                    ASSERT(x1!=x2 && y1!=y2);
#endif
                    moves[length++]=PACK5(2,min(x1,x2),min(y1,y2),max(x1,x2)-1,max(y1,y2)-1);
                    c2[x2]-=a2[x2][y1]+a2[x2][y2];
                    c2[x2]+=a2[x2][y1]+a2[x2][y2];
                    a2[x1][y1]^=1; a2[x1][y2]^=1; a2[x2][y1]^=1; a2[x2][y2]^=1;
                }
            }
            convert_a2_to_a1(a2,a1);
            if (step<m) apply_OP01(local_time_limit);
            int cost=length*4+get_cost(a1);
            if (cost<next_states[worst].cost)
            {
                next_states[worst].save();
                next_states[worst].cost=cost;
                if (step>=m)
                {
                    int best_current=INF,worst_next=0;
                    for (int i=0;i<N_STATES;i++) if (current_states[i].cost<best_current) best_current=current_states[i].cost;
                    for (int i=0;i<N_STATES;i++) if (next_states[i].cost>worst_next) worst_next=next_states[i].cost;
                    if (best_current==worst_next) break;
                }
            }
        }
    }
}
 
void process()
{
    start_time=gettime();
    init();
    preprocess();
    best_length=n*n+1;
    solve(start_time+TIME_LIMIT);
}
 
#ifdef LOCAL
#define ERROR(s) do { printf("%s\n",((string)s).c_str()); exit(0); } while (0)
void generate(int seed)
{
    MersenneTwister rnd(seed);
    uint s10=0;
    for (int i=0;i<10;i++) s10|=rnd.rand_unsigned();
    if (s10==0) printf("Strange\n");
    n=rnd.next_int(10,50);
    double p=rnd.next_double()*0.2+0.4;
    for (int i=0;i<n;i++) for (int j=0;j<n;j++)
        if (rnd.next_double()<p)
            target_grid[i][j]='B';
        else
            target_grid[i][j]='W';
}
#endif
 
int main()
{
#ifdef LOCAL
#ifdef DEBUG
    printf("DEBUG ONLY\n");
#endif
    int cases=50;
    double total_score=0;
    double total_time_used=0;
    int total_cases=0;
    for (int seed=0;seed<cases;seed++)
    {
#endif
        double start_time=gettime();
#ifdef LOCAL
        generate(seed);
#else
        scanf("%d",&n);
        for (int i=0;i<n;i++) scanf("%s",target_grid[i]);
#endif
        process();
#ifdef LOCAL
        total_cases++;
        double end_time=gettime();
        total_time_used+=(end_time-start_time);
        char a[MAXN][MAXN];
        memset(a,'W',sizeof(a));
        double score=10.0*best_length/(n*n);
        total_score+=score;
        printf("Seed = %4d : Score = %.3lf    ",seed,score);
        printf("Time Used = %.3lf    N = %d\n",end_time-start_time,n);
        for (int i=0;i<best_length;i++)
        {
            char c[3]={'W','B','F'};
            int d,x1,y1,x2,y2;
            UPACK5(best_moves[i],d,x1,y1,x2,y2);
            if (!(d>=0 && d<3 && x1>=0 && x1<=x2 && x2<n && y1>=0 && y1<=y2 && y2<n)) ERROR("Invalid move");
            for (int x=x1;x<=x2;x++) for (int y=y1;y<=y2;y++)
                if (c[d]=='F')
                    a[x][y]='W'+'B'-a[x][y];
                else
                    a[x][y]=c[d];
        }
        for (int x=0;x<n;x++) for (int y=0;y<n;y++) if (a[x][y]!=target_grid[x][y]) ERROR("Not finished");
#else
        printf("%d\n",best_length);
        for (int i=0;i<best_length;i++)
        {
            char c[3]={'W','B','F'};
            int d,x1,y1,x2,y2;
            UPACK5(best_moves[i],d,x1,y1,x2,y2);
            printf("%d %d %d %d %c\n",x1,y1,x2,y2,c[d]);
        }
#endif
#ifdef LOCAL
    }
    printf("AVG_SCORE = %.3lf\n",(double)total_score/total_cases);
    printf("AVG_TIME  = %.3lf\n",(double)total_time_used/total_cases);
    Timer::show();
    Counter::show();
#ifdef DEBUG
    printf("DEBUG ONLY\n");
#endif
#endif
    return 0;
} 