program z_G;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var dp:array[0..40,0..20,0..20] of integer;
    n,k:integer;
    c:array[0..20,0..20] of char;

procedure init;
var i,j:integer;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    readln(n,k);
    for i:=1 to n do
      begin
        for j:=1 to n do read(c[i][j]);
        readln;
      end;
    close(input);
  end;

procedure solve;
var i,j,t:integer;
  begin
    fillchar(dp,sizeof(dp),0);
    dp[0][1][1]:=1;
    for t:=1 to k do
      begin
        for i:=1 to n do
        for j:=1 to n do if (c[i][j]='0') then
          begin
            dp[t][i][j]:=dp[t-1][i-1][j]+dp[t-1][i+1][j]+dp[t-1][i][j-1]+dp[t-1][i][j+1];
          end;
      end;
  end;

procedure out;
  begin
    write(dp[k][n][n]);
    close(output);
  end;

begin
  init;
  solve;
  out;
end.
