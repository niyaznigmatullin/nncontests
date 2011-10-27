program _0362;
{$APPTYPE CONSOLE}
uses
  SysUtils;

const eps=1e-9;

var a,b,c,d:integer;
    ang:extended;

procedure swap(var x,y:integer); var p:integer; begin p:=x; x:=y; y:=p; end;
procedure ok; begin write('Possible'); close(output); halt(0); end;
procedure no; begin write('Impossible'); close(output); halt(0); end;

procedure init;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(a,b,c,d);
    if a>b then swap(a,b);
    if c>d then swap(c,d);
    close(Input);
  end;

procedure solve;
var l,r:extended;
  begin
    if (a<=c) and (b<=d) then ok;
    ang:=arctan((d*b-a*c)/(c*b-a*d));
    l:=0;
    r:=pi/2;
    while r-l>=eps do
      begin
        ang:=(l+r)/2;
        if (abs(d-(b*sin(ang)+a*cos(ang)))<=eps) and (abs(c-(a*sin(ang)+b*cos(ang)))<=eps) then ok;
      end;
    no;
  end;

begin
  init;
  solve;
end.
