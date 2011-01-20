program z_F;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var a:array[0..1000001] of shortint;
    b:array[-1000001..1000001] of integer;
    q:int64;
    n:integer;

procedure init;
var i:integer; c:char;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    readln(n);
    for i:=1 to n do
      begin
        read(c);
        if c='a' then a[i]:=1 else a[i]:=-1;
      end;
    close(input);
  end;

procedure solve;
var now,i:integer;
  begin
    now:=0;
    b[0]:=1;
    for i:=1 to n do
      begin
        inc(now,a[i]);
        inc(q,b[now]);
        inc(b[now]);
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
  solve;
  done;
end.
 