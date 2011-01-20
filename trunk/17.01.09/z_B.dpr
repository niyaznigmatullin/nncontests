program z_B;
{$APPTYPE CONSOLE}
uses
  SysUtils;

const InputFile='root.in';
      OutputFile='root.out';

var p,k:integer;
    kol:integer;
    w:array[0..100000] of integer;

procedure quit;
  begin
    close(output);
    halt(0);
  end;

function power(n,k:int64):int64;
var ret:int64;
  begin
    if (k=0) then result:=1 else
    if (odd(k)) then
      begin
        result:=(power(n,k-1)*n) mod p;
      end else
      begin
        ret:=power(n,k div 2);
        result:=(ret*ret) mod p;
      end;
  end;

function gcd(x,y:int64):int64;
  begin
    while (x*y<>0) do
      begin
        if (x>y) then x:=x mod y else y:=y mod x;
      end;
    result:=x+y;
  end;

procedure init;
  begin
    reset(input,InputFile);
    rewrite(output,OutputFile);
    read(p,k);
    close(input);
  end;

procedure solve;
var i:integer;
  begin
    for i:=1 to p-1 do
      begin
        if (power(i,k)=1) then begin inc(kol); w[kol]:=i; end;
      end;
  end;

procedure done;
var i:integer;
  begin
    writeln(kol);
    for i:=1 to kol do write(w[i],' ');
    quit;
  end;

begin
  init;
  solve;
  done;
end.
