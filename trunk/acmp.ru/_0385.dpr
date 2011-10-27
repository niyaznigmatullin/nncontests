program _0385;
{$APPTYPE CONSOLE}
uses
  SysUtils;

type TPoint=record
      x,y:extended;
      end;

const eps=1e-6;

var p:array[0..10000] of TPoint;
    n,kol:integer;
    w:array[0..10000] of extended;

function rast(s1,s2:TPoint):extended;
  begin
    result:=sqrt(sqr(s1.x-s2.x)+sqr(s1.y-s2.y));
  end;

procedure init;
var i:integer;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(n);
    for i:=1 to n do read(p[i].x,p[i].y);
    close(input);
  end;

procedure solve;
var i,j,ii,jj,k:integer; r:extended; ok,lok:boolean;
  begin
    {kol:=0;
    for i:=1 to n do
    for j:=1 to n do if (i<>j) then
      begin
        r:=rast(p[i],p[j]);
        lok:=true;
        for k:=1 to kol do if abs(r-w[k])<=eps then lok:=false;
        for ii:=1 to n do
          begin
            ok:=false;
            for jj:=1 to n do if abs(rast(p[ii],p[jj])-r)<=eps then begin ok:=true; break; end;
            lok:=lok and ok;
          end;
        if lok then
          begin
            inc(kol);
            w[kol]:=r;
          end;
        end;
        }
    kol:=0;
    for i:=1 to n-1 do
    for j:=i+1 to n do
      begin
        r:=rast(p[i],p[j]);
        ok:=true;
        for k:=1 to kol do if (abs(r-w[k])<=eps) then begin ok:=false; break; end;
        if ok then begin inc(kol); w[kol]:=r; end;
      end;
      for i:=1 to kol-1 do
      for j:=i+1 to kol do
      if w[i]>w[j] then begin r:=w[i]; w[i]:=w[j]; w[j]:=r; end;
  end;

procedure done;
var i:integer;
  begin
    writeln(kol);
    for i:=1 to kol do writeln(w[i]);
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.
