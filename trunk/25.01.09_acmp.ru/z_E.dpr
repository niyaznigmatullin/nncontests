program z_E;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var n,k,m,maxp,maxn:int64;

procedure check(n,x:int64);
var h:int64;
  begin
    h:=0;
    while (n-k) mod x=0 do
      begin
        dec(n,k);
        n:=n div x;
        inc(h);
      end;
    if (h>maxp) then
      begin
        maxp:=h;
        maxn:=x;
      end;
  end;

procedure init;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(n,k);
    close(input);
    if (n=k) then
      begin
        writeln(n+1,' ',1);
        close(output);
        halt(0);
      end;
  end;

procedure solve;
var i:int64;
  begin
    maxn:=2;
    maxp:=0;
    if (k=0) then begin maxn:=n; maxp:=1; end;
    m:=n-k;
    i:=2;
    while i*i<=m do if (m mod i=0)  then
      begin
        if (i>k) then check(n,i);
        check(n,m div i);
        inc(i);
      end else inc(i);
  end;

procedure done;
  begin
    write(maxn,' ',maxp);
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.
 