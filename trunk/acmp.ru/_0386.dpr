program _0386;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var n:integer;

procedure init;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(n);
    close(input);
  end;

procedure done;
var i,x,y:integer;
  begin
    randomize;
    writeln('YES');
    for i:=1 to n do
      begin
        x:=(random(7324233) xor 555743) mod 20000;
        y:=(random(7124912) xor 938467) mod 20000;
        x:=x-10000;
        y:=y-10000;
        writeln(x,' ',y);
      end;
    close(output);
    halt(0);
  end;

begin
  init;
  done;
end.

