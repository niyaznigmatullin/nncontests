{$A8,B-,C+,D+,E-,F-,G+,H+,I+,J-,K-,L+,M-,N+,O+,P+,Q-,R-,S-,T-,U-,V+,W-,X+,Y+,Z1}
{$MINSTACKSIZE $00004000}
{$MAXSTACKSIZE $01000000}
program z_A;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var a:array[0..201,0..201] of char;
    i,j,q,n,m:integer;

procedure dfs(x,y:byte);
  begin
    a[x][y]:='.';
    if (x>1) then if (a[x-1][y]='#') then dfs(x-1,y);
    if (y>1) then if (a[x][y-1]='#') then dfs(x,y-1);
    if (x<n) then if (a[x+1][y]='#') then dfs(x+1,y);
    if (y<m) then if (a[x][y+1]='#') then dfs(x,y+1);
  end;

begin
  reset(input,'input.txt');
  rewrite(output,'output.txt');
  readln(n,m);
  for i:=1 to n do
    begin
      for j:=1 to m do read(a[i][j]);
      readln;
    end;
    q:=0;
  close(input);
  for i:=1 to n do
  for j:=1 to m do if (a[i][j]='#') then
    begin
      inc(q);
      dfs(i,j);
    end;
  write(q);
  close(output);
end.
 