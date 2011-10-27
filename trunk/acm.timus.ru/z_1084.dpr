program z_1084;
{$APPTYPE CONSOLE}
uses
  SysUtils,
  Math;

const eps=1e-6;

var n,r,d,ang1,area:extended;

procedure init;
  begin
//    reset(input,'input.txt');
//    rewrite(output,'output.txt');
    read(n,r);
  end;

procedure solve;
  begin
    if (n*cos(pi/4)-r<eps) then
      begin
        write(n*n:0:3);
        halt(0);
      end;
    area:=pi*r*r;
    if (n/2<r) then
      begin
        d:=n/2;
        ang1:=2*arccos(d/r);
        area:=area-(4*r*r/2*(ang1-sin(ang1)));
      end;
  end;

procedure done;
  begin
    write(area:0:3);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.
 