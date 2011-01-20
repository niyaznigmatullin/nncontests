program z_A;
{$APPTYPE CONSOLE}
uses
  SysUtils;

type TPoint=record
      x,y:extended;
      end;
     TVect=record
      a,b:extended;
      end;

const eps=1e-9;

var p0,p1,p2:TPoint;
    v:TVect;

procedure print(x:int64);
  begin
    write(p0.x+x*v.a:0:0,' ',p0.y+x*v.b:0:0);
    close(output);
    halt(0);
  end;

function dist(x:int64):extended;
var ss:TPoint;
  begin
    ss.x:=p0.x+x*v.a;
    ss.y:=p0.y+x*v.b;
    result:=sqrt(sqr(ss.x-p1.x)+sqr(ss.y-p1.y))+sqrt(sqr(ss.x-p2.x)+sqr(ss.y-p2.y));
  end;

procedure init;
var xx,yy:extended;
  begin
    reset(input,'point.in');
    rewrite(output,'point.out');
    read(p1.x,p1.y,p2.x,p2.y,p0.x,p0.y,xx,yy);
    v.a:=p0.x-xx;
    v.b:=p0.y-yy;
    close(input);
  end;

procedure solve;
var lo,hi,l,r:int64;
  begin
    if (abs(v.a)<=eps) then begin             lo:=trunc((p0.y-1e10)/abs(v.b));
    hi:=trunc((1e10-p0.y)/abs(v.b)); end else begin
    lo:=trunc((p0.x-1e10)/abs(v.a));
    hi:=trunc((1e10-p0.x)/abs(v.a));                end;
    while true do
      begin
        l:=(hi-lo) div 3+lo;
        r:=(hi-lo) div 3*2+lo;
        if dist(l)<dist(r) then hi:=r-1 else lo:=l+1;
        if hi-lo<=1 then
          begin
            break;
          end;
      end;
    if dist(lo)<dist(hi) then print(lo) else print(hi);
  end;

begin
  init;
  solve;
//  done;
end.
