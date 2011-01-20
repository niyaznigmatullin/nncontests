program z_I;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var n:integer;
    c1,c2,c3:char;
procedure init;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(n);
    close(input);
  end;

procedure solve;
var i:integer; c:char;
  begin
    c1:='G';
    c2:='C';
    c3:='V';
    for i:=1 to n do
      begin
        c:=c3;
        c3:=c2;
        c2:=c;
        c:=c1;
        c1:=c2;
        c2:=c;
      end;
  end;

procedure done;
  begin
    write(c1+c2+c3);
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.
 