program z_D;
{$APPTYPE CONSOLE}
uses
  SysUtils, Math;

type TLong=array[0..100] of integer;

const base=10000;

var n,a,b:int64;
    ans:TLong;
    dp:array[0..100,0..100] of TLong;
    j,i:integer;


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

procedure long_mul_short(var a:TLong; const v:integer);
var i:integer;
  begin
    for i:=1 to a[0] do a[i]:=a[i]*v;
    norm(a);
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

procedure long_mul(var c:TLong; const a,b:TLong);
var i,j:integer;
  begin
    fillchar(c,sizeof(c),0);
    c[0]:=0;
    c[1]:=0;
    for i:=1 to a[0] do
      begin
        for j:=1 to b[0] do
          begin
            c[i+j-1]:=c[i+j-1]+a[i]*b[j];
            c[0]:=max(c[0],i+j-1);
          end;
        norm(c);
      end;
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

begin
  reset(input,'input.txt');
  rewrite(output,'output.txt');
  read(n,a,b);
  fillchar(dp,sizeof(dp),0);
  dp[0][0][0]:=1;
  dp[0][0][1]:=1;
  for i:=1 to 41 do
    begin
      dp[i][0][0]:=1;
      dp[i][0][1]:=1;
      for j:=1 to 41 do
        begin
          inc_long(dp[i][j],dp[i-1][j-1]);
          inc_long(dp[i][j],dp[i-1][j]);
        end;
    end;
  long_mul(ans,dp[n+a][a],dp[n+b][b]);
  print_long(ans);
  close(input);
  close(output);
end.
