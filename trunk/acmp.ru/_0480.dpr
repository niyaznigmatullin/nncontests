{$A8,B-,C+,D+,E-,F-,G+,H+,I+,J-,K-,L+,M-,N+,O+,P+,Q-,R-,S-,T-,U-,V+,W-,X+,Y+,Z1}
{$MINSTACKSIZE $00004000}
{$MAXSTACKSIZE $01000000}
program z_H;
{$APPTYPE CONSOLE}
uses
  SysUtils,
  Math;

var dp:array[0..200,0..3,0..200] of integer;
    a:array[0..200] of integer;
    n,k:integer;
    sum:array[0..200] of integer;

procedure rec(x,y,k:integer);
var i:integer;
  begin
    if (x=n+1) then
      begin
        dp[x][y][k]:=0;
        exit;
      end;
    if (dp[x][y][k]<>-1) then
      begin
        exit;
      end;
    dp[x][y][k]:=0;
    i:=0;
    while (i<k) and (i+x<=n) do
      begin
        rec(x+i+1,3-y,i+1);
        dp[x][y][k]:=max(dp[x][y][k],sum[x]-dp[x+i+1][3-y][i+1]);
        inc(i);
      end;
  end;

procedure init;
var i:integer;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(n);
    for i:=1 to n do read(a[i]);
    sum[n]:=a[n];
    for i:=n-1 downto 1 do sum[i]:=sum[i+1]+a[i];
    read(k);
    close(input);
  end;

procedure solve;
var i,j,t:integer;
  begin
    for i:=0 to n do
    for j:=1 to 2 do
    for t:=0 to n do dp[i][j][t]:=-1;
    rec(1,1,k);
    write(dp[1][1][k]);
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
end.
