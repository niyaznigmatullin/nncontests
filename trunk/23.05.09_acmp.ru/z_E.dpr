program z_E;
{$APPTYPE CONSOLE}
uses
  SysUtils, Math;

var k,m:integer; s: string;

function ff(c:char):integer;
  begin
    if (c in ['0'..'9']) then result:= ord(c) - ord('0') else result:= ord(c) - ord('A') + 10;
  end;

procedure init;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    readln(k,m);
    readln(s);
    s := trim(s);
    close(input);
  end;

procedure solve;
var um,i:integer;
  begin
    um := 0;
    for i:=1 to length(s) do
      begin
        um := um * k + ff(s[i]);
        um := um mod m;
      end;
    write(um);
    close(output);
  end;

begin
  init;
  solve;
end.
