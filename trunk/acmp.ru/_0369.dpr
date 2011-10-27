program _0369;
{$APPTYPE CONSOLE}
{$O-}
uses
  SysUtils;

type TGang=record
      t,p,s:integer;
      end;

var g:array[0..101] of TGang;
    kk,r:array[0..101] of integer;
    n,k,t:integer;

procedure init;
var i:integer;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(n,k,t);
    for i:=1 to n do read(g[i].t);
    for i:=1 to n do read(g[i].p);
    for i:=1 to n do read(g[i].s);
    close(input);
  end;

procedure solve;
var i,j:integer; p:TGang;
  begin
    for i:=1 to n-1 do
    for j:=i+1 to n do
      if g[i].t>g[j].t then
        begin
          p:=g[i];
          g[i]:=g[j];
          g[j]:=p;
        end;
    r[0]:=0;
    kk[0]:=0;
    g[0].t:=0;
    for i:=1 to n do
      begin
        for j:=0 to i-1 do
          begin
            if kk[j]>=0 then if g[i].t-g[j].t>=abs(g[i].s-g[j].s) then if r[i]<r[j]+g[i].p then
              begin
                r[i]:=r[j]+g[i].p;
                kk[i]:=g[i].s;
              end;
          end;
        if r[i]=0 then kk[i]:=-10000;
      end;
  end;

procedure done;
var i,maxy:integer;
  begin
    maxy:=0;
    for i:=1 to n do
      begin
        if maxy<r[i] then maxy:=r[i];
      end;
    write(maxy);
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.
 