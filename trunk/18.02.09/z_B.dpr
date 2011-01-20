program z_B;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var n,m:int64; kol:integer;
    a:array[0..1000] of integer;

procedure init;
var c:char;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(c);
    while c<>' ' do
      begin
        inc(kol);
        a[kol]:=ord(c)-ord('0');
      end;
    read(n);
    close(input);
  end;

procedure solve;
var i:integer;
  begin
    for i:=1 to kol do
      begin
        m:=(m*10+a[i]) mod n;
      end;
  end;

procedure done;
  begin
    write(m);
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.
 