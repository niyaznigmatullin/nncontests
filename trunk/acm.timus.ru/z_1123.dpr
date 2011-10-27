program z_1123;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var s:string;
    c:array[0..10000] of char;
    n:integer;

procedure init;
var i:integer;       cc:char;
  begin
    n:=0;
    while (not seekeoln) do
      begin
        inc(n);
        read(c[n]);
      end;
    for i:=1 to n div 2 do
      begin
        cc:=c[i];
        c[i]:=c[n-i+1];
        c[n-i+1]:=cc;
      end;
  end;

function check:boolean;
var i:integer;
  begin
    i:=n div 2 + n mod 2 + 1;
    while (i<=n) do
      begin
        if (c[n-i+1]>c[i]) then
          begin
            result:=false;
            exit;
          end else
        if (c[n-i+1]<c[i]) then
          begin
            result:=true;
            exit;
          end;
        inc(i);
      end;
    result:=true;
  end;

procedure make_answer;
var i:integer;
  begin
    for i:=1 to n div 2 do
      begin
        c[i]:=c[n-i+1];
      end;
  end;

procedure print;
var i:integer;
  begin
    for i:=n downto 1 do write(c[i]);
    readln;
    readln;
    halt(0);
  end;

procedure solve;
var i:integer;
  begin
    if (check) then
      begin
        make_answer;
        print;
      end else
      begin
        i:=n div 2 + 1;
        inc(c[i]);
        while (c[i]>'9') do
          begin
            c[i]:='0';
            inc(i);
            inc(c[i]);
          end;
        if (i>n) then
          begin
            inc(n);
            c[i]:='1';
          end;
        make_answer;
        print;
      end;
  end;

begin
  init;
  solve;
end.
