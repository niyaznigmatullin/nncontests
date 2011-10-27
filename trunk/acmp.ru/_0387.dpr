program _0387;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var n,q:integer;
    s:string;

procedure init;
var i:integer;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    readln(n);
    for i:=1 to n do
      begin
        readln(s);
        s:=trim(s);
        if s[1]=s[4] then inc(q);
      end;
  end;

procedure done;
  begin
    write(q);
    close(output);
    halt(0);
  end;

begin
  init;
  done;
end.
