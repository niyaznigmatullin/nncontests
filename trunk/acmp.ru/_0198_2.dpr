program _0198_2;
{$APPTYPE CONSOLE}
uses
  SysUtils;

const eps=1e-9;

type TLine=array[0..101] of extended;

var n:integer;
    a:array[0..101] of TLine;
    b,x,p:TLine;

procedure init;
var i,j:integer;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(n);
    for i:=1 to n do
      begin
        for j:=1 to n do
          begin
            read(a[i,j]);
          end;
        read(b[i]);
      end;
    close(input);
  end;

function converge(xk,xkp:TLine):boolean;
var i:integer;
  begin
    result:=true;
    for i:=1 to n do if (xk[i]-xkp[i]<=eps) then begin result:=false; exit; end;
  end;

procedure solve;
var i,j:integer; v:extended;
  begin
    while not converge(x,p) do
      begin
        for i:=1 to n do
          begin
            v:=0;
            for j:=1 to n do if i<>j then v:=v+a[i,j]*x[j];
            p[i]:=x[i];
            x[i]:=(b[i]-v)/a[i,i];
          end;
      end;
  end;

procedure done;
var i:integer;
  begin
    for i:=1 to n do write(x[i]:0:10,' ');
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.
