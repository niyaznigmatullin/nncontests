program z_A_2;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var maxr,r,n:int64;
    ans,ans1:array[0..20] of int64;
    i:integer;

function check(x:int64):integer;
  begin
    result:=0;
    while (x mod 2=0) do begin inc(result); x:=x div 2; end;
  end;

procedure rec(x,k:int64);
  begin
    if (k=n) then
      begin
        r:=check(x);
        if (r>maxr) then
          begin
            maxr:=r;
            ans[n]:=x;
            ans1[n]:=maxr;
          end;
        exit;
      end;
    rec(x*10+1,k+1);
    rec(x*10+2,k+1);
  end;

begin
  rewrite(output,'output.txt');
  for i:=1 to 19 do
    begin
      maxr:=0;
      n:=i;
      rec(0,0);
    end;
  for i:=1 to 19 do
    begin
      writeln(ans[i],' ', ans1[i]);
    end;
end.
