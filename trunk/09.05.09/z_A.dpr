program z_A;
{$APPTYPE CONSOLE}

uses
  SysUtils;

var _1,n:integer;
    a:array[0..1 shl 17] of integer;

procedure init;
var i:integer; c:char;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    readln(n);
    for i:=1 to n do
      begin
        read(c);
        a[i] := ord(c) - ord('0');
        if (c = '1') then inc(_1);
      end;
    close(input);
  end;

procedure solve;
var i:integer; ok:boolean;
  begin
    i:=n;
    while (_1 > 0) do
      begin
        inc(a[i]);
        if (i>1) then a[i-1] := a[i-1] + a[i] shr 1;
        a[i]:=a[i] and 1;
        dec(_1);
        dec(i);
      end;
    while (a[i] > 1) do
      begin
        if (i>1) then a[i-1] := a[i-1] + a[i] shr 1;
        a[i] := a[i] and 1;
        dec(i);
      end;
    for i:=1 to n do a[i] := a[i] xor 1;
    inc(a[n]);
    i:=n;
    while (a[i] > 1) do
      begin
        if (i>1) then a[i-1] := a[i-1] + a[i] shr 1;
        a[i] := a[i] and 1;
        dec(i);
      end;
    ok:=false;
    for i:=1 to n do
      begin
        if (ok) or (a[i] = 1) then
          begin
            write(a[i]);
            ok:=true;
          end;
      end;
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
end.
