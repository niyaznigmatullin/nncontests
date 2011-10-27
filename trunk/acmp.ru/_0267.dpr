program _0267;

{$APPTYPE CONSOLE}
  {$o-}
uses
  SysUtils,Math;
var
n,x,y,q:integer;

function gcd(x,y:integer):integer;
  begin
    while x*y<>0 do if x>y then x:=x mod y else y:=y mod x;
    result:=x+y;
  end;

procedure init;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(n,x,y);
    dec(n);
    close(input);
    q:=min(x,y);
    if n=0 then
      begin
        write(q);
        close(output);
        halt(0);
      end;
  end;

procedure solve;
var nok,en,t1,t2:integer;
  begin
    nok:=(x div gcd(x,y))*y;
    en:=nok div x+nok div y;
    inc(q,(n div en)*nok);
    n:=n mod en;
    if n=0 then
      begin
        write(q);
        close(output);
        halt(0);
      end;
    t1:=0;
    t2:=0;
    while n<>0 do
      if t1+x<t2+y then begin inc(t1,x); dec(n); end else begin inc(t2,y); dec(n); end;
    inc(q,max(t1,t2));
  end;

procedure done;
  begin
    write(q);
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.
