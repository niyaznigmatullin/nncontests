program _0359;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var n:integer;
    last,p,r:int64;

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
    last:=1;
    r:=1;
    for i:=2 to n do
      begin
        if odd(i) then inc(r,2);
        inc(last,r);
      end;
    p:=0;
    while (last div 10)>p div 10  do
      begin
        r:=p;
        p:=last;
        inc(last,last div 10-(r div 10));
      end;
  end;

procedure done;
  begin
    if n=1 then write(1) else write(last);
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.
