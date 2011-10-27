program _0364;
{$APPTYPE CONSOLE}
uses
  SysUtils;

const s:array[1..8] of int64=(
6,
28,
496,
8128,
33550336,
8589869056,
137438691328,
2305843008139952128);

var b:boolean;
    n,m:int64;

procedure init;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(n,m);
    close(input);
  end;

procedure done;
var i:integer;
  begin
    b:=false;
    for i:=1 to 8 do if (n<=s[i]) and (m>=s[i]) then
      begin
        writeln(s[i]);
        b:=true;
      end;
    if not b then write('Absent');
    close(output);
    halt(0);
  end;

begin
  init;
  done;
end.
