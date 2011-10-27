program _0367;
{$APPTYPE CONSOLE}
uses
  SysUtils;

const base=10000000;

var h,g:array[0..4000] of int64;
    a,b:integer;

procedure init;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(a,b);
    close(input);
  end;

procedure solve;
var k,i,um:integer;
  begin
    h[0]:=1;
    h[1]:=1;
    for k:=1 to b do
      begin
        um:=0;
        g[0]:=h[0];
        for i:=1 to h[0] do
          begin
            g[i]:=(um+h[i]*a) mod base;
            um:=(um+h[i]*a) div base;
          end;
        while um>0 do
          begin
            inc(g[0]);
            g[g[0]]:=um mod 10;
            um:=um div 10;
          end;
        h:=g;
      end;
  end;

procedure done;
var i:integer;
  begin
    write(h[h[0]]);
    for i:=h[0]-1 downto 1 do
      begin
        if h[i]<1000000 then write(0);
        if h[i]<100000 then write(0);
        if h[i]<10000 then write(0);
        if h[i]<1000 then write(0);
        if h[i]<100 then write(0);
        if h[i]<10 then write(0);
        write(h[i]);
      end;
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.
