program z_A;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var m,n,w:integer;
function sum_cifr(x:integer):integer;
  begin
    result:=0;
    while x>0 do
      begin
        inc(result,x mod 10);
        x:=x div 10;
      end;
  end;

procedure init;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(n,m);
    close(input);
    if n=0 then
      begin
        write(0);
        close(output);
        halt(0);
      end;
  end;

procedure solve;
var q,p,r,minr:integer;
  begin
    p:=m;
    while p<n do
      begin
        inc(p,m);
      end;
    minr:=maxint;
    q:=0;
    while q<2000000 do
      begin
        inc(q);
        r:=sum_cifr(p-n);
        if r<minr then
          begin
            minr:=r;
            w:=p-n;
          end;
        inc(p,m);
      end;
  end;

procedure done;
  begin
    write(w);
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.
