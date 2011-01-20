program z_H;
{$APPTYPE CONSOLE}
uses
  SysUtils,
  Math;

var r,c,x1,x2,y2,y1,d:extended;
procedure init;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(r);
    read(x1,y1,x2,y2);
    close(input);
  end;

procedure solve;
  begin
    x1:=degtorad(x1);
    x2:=degtorad(x2);
    y1:=degtorad(y1);
    y2:=degtorad(y2);
    c:=y2-y1;
    d:=arccos(sin(x1)*sin(x2)+cos(x1)*cos(x2)*cos(c));
  end;

procedure done;
  begin
    write(d*r:0:2);
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.
