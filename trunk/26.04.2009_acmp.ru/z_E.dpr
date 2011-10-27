program z_E;
{$APPTYPE CONSOLE}
uses
  SysUtils,
  Math;

type TLong=array[0..100] of integer;
const base=100000;

var dp:array[0..9, 0..101] of TLong;
    n:integer;
    ans:TLong;
procedure norm(var a:TLong);
var i:integer;
  begin
    i:=1;
    while (i<=a[0]) do
      begin
        if (a[i]>=base) then
          begin
            inc(a[i+1],a[i] div base);
            a[i]:=a[i] mod base;
            if (i=a[0]) then inc(a[0]);
          end;
        inc(i);
      end;
  end;

procedure inc_long(var a:TLong; const b:TLong);
var i:integer;
  begin
    a[0]:=max(a[0],b[0]);
    for i:=1 to a[0] do
      begin
        inc(a[i],b[i]);
      end;
    norm(a);
  end;

procedure print_long(a:TLong);
var i,p:integer;
  begin
    write(a[a[0]]);
    for i:=a[0]-1 downto 1 do
      begin
        p:=base div 10;
        while (p>a[i]) and (p>1) do
          begin
            write(0);
            p:=p div 10;
          end;
        write(a[i]);
      end;
  end;

procedure init;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(n);
    close(input);
  end;

procedure solve;
var i:integer;
  begin
    fillchar(dp,sizeof(dp),0);
    for i:=1 to 9 do
      begin
        dp[i][1][0]:=1;
        dp[i][1][1]:=1;
      end;
    dp[8][1][0]:=0;
    dp[8][1][1]:=0;
    for i:=2 to n do
      begin
        inc_long(dp[1][i],dp[8][i-1]);
        inc_long(dp[1][i],dp[6][i-1]);
        inc_long(dp[2][i],dp[7][i-1]);
        inc_long(dp[2][i],dp[9][i-1]);
        inc_long(dp[3][i],dp[4][i-1]);
        inc_long(dp[3][i],dp[8][i-1]);
        inc_long(dp[4][i],dp[9][i-1]);
        inc_long(dp[4][i],dp[3][i-1]);
        inc_long(dp[4][i],dp[0][i-1]);
        inc_long(dp[6][i],dp[7][i-1]);
        inc_long(dp[6][i],dp[1][i-1]);
        inc_long(dp[6][i],dp[0][i-1]);
        inc_long(dp[7][i],dp[6][i-1]);
        inc_long(dp[7][i],dp[2][i-1]);
        inc_long(dp[8][i],dp[3][i-1]);
        inc_long(dp[8][i],dp[1][i-1]);
        inc_long(dp[9][i],dp[4][i-1]);
        inc_long(dp[9][i],dp[2][i-1]);
        inc_long(dp[0][i],dp[4][i-1]);
        inc_long(dp[0][i],dp[6][i-1]);
      end;
    fillchar(ans,sizeof(ans),0);
    for i:=0 to 9 do inc_long(ans,dp[i][n]);
    print_long(ans);
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
end.
