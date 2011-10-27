program z_1023;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var n:integer;

procedure init;
  begin
//    reset(input,'input.txt');
  //  rewrite(output,'output.txt');
    read(n);
  end;

procedure solve;
var i:integer;
  begin
    i:=3;
    while i*i<=n do
      begin
        if (n mod i=0) then
          begin
            write(i-1);
            halt(0);
          end;
        inc(i);
      end;
    if (n mod 2=0) and (n div 2-1>1) then
      begin
        write(n div 2-1);
        halt(0);
      end;
  end;

procedure done;
  begin
    write(n-1);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.
 