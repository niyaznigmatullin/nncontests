{$A8,B-,C+,D+,E-,F-,G+,H+,I+,J-,K-,L+,M-,N+,O+,P+,Q-,R-,S-,T-,U-,V+,W-,X+,Y+,Z1}
{$MINSTACKSIZE $00004000}
{$MAXSTACKSIZE $01000000}
program z_I;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var dp:array[0..1 shl 10,0..1 shl 10] of int64;
    c:array[0..10,0..10] of char;
    n,m,k:integer;

function count_1(x:integer):integer;
  begin
    result:=0;
    while (x>0) do
      begin
        inc(result);
        x:=(x-1) and x;
      end;
  end;

procedure rec(mask1,mask2:integer);
var i,j:integer;
  begin
    if (count_1(mask1)=n-k) then
      begin
        dp[mask1][mask2]:=1;
        exit;
      end;
    if (dp[mask1][mask2]<>-1) then exit;
    dp[mask1][mask2]:=0;
    for i:=0 to n-1 do if (mask1 and (1 shl i))<>0 then
    for j:=0 to m-1 do if (mask2 and (1 shl j))<>0 then if c[i+1][j+1]='Y' then
      begin
        rec(mask1 xor (1 shl i),mask2 xor (1 shl j));
        dp[mask1][mask2]:=dp[mask1][mask2]+dp[mask1 xor (1 shl i),mask2 xor (1 shl j)];
      end;
  end;

procedure init;
var i,j:integer;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    readln(n,m,k);
    for i:=1 to n do
      begin
        for j:=1 to m do read(c[i][j]);
        readln;
      end;
    close(input);
  end;

procedure solve;
var i,j:integer; ans:int64;
  begin
    for i:=0 to 1 shl n do
    for j:=0 to 1 shl m do dp[i][j]:=-1;
    rec(1 shl n-1, 1 shl m-1);
    ans:=dp[1 shl n-1, 1 shl m-1];
    for i:=2 to k do ans:=ans div i;
    write(ans);
  end;

begin
  init;
  solve;
end.
