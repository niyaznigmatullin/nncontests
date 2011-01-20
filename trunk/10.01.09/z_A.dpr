program z_A;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var dp,a:array[-2..10001] of int64;
    n,k:integer;
    sum:int64;

procedure init;
var i:integer;
  begin
    reset(input,'guitar.in');
    rewrite(output,'guitar.out');
    read(n,k);
    for i:=1 to n do read(a[i]);
    close(input);
  end;

procedure solve;
var i,j:integer;
  begin
    dp[-1]:=0;
    dp[-2]:=0;
    dp[0]:=0;
    for i:=1 to n do
      begin
        dp[i]:=dp[i-1];
        sum:=0;
        j:=i;
        while (j>=0) and (j>i-k) do
          begin
            inc(sum,a[j]);
            if (dp[i]<sum+dp[j-2]) then dp[i]:=sum+dp[j-2];
            dec(j);
          end;
      end;
  end;

procedure done;
  begin
    write(dp[n]);
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.
 