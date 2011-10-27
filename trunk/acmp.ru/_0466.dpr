program z_C;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var n:integer;
function f(x:integer):int64;
  begin
    if (x=0) then result:=0 else
    if (x=1) then result:=1 else
    if (odd(x)) then result:=f(x div 2)+f(x div 2+1) else
    result:=f(x div 2);
  end;

procedure init;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(n);
    close(input);
  end;

procedure done;
  begin
    write(f(n));
    close(output);
    halt(0);
  end;

begin
  init;
  done;
end.
 