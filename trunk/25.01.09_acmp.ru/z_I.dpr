program z_I;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var n:integer;
    ans,two,five:integer;

procedure init;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(n);
    close(input);
  end;

procedure solve;
var i,p:integer;
  begin
    two:=0;
    five:=0;
    ans:=1;
    for i:=1 to n do
      begin
        p:=i;
        while p mod 2=0 do
          begin
            p:=p div 2;
            inc(two);
          end;
        while p mod 5=0 do
          begin
            p:=p div 5;
            inc(five);
          end;
        ans:=(ans*p) mod 10;
      end;
    for i:=1 to two-five do ans:=(ans*2) mod 10;
  end;

procedure done;
  begin
    write(ans);
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.
