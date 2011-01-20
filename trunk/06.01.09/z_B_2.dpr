program z_B_2;
{$APPTYPE CONSOLE}
uses
  SysUtils;

type
  int = longint;

var
  n, i, j, tmp, s: int;
  x, y: array [1..110, 1..3] of int;
  l: array [1..110, 1..5] of int;

label
  next;

begin
  reset(input, 'input.txt');
  rewrite(output, 'output.txt');

  read(n);
  for i:=1 to n do
    read(x[i][1], y[i][1], x[i][2], y[i][2], x[i][3], y[i][3]);

  for i:=1 to n do
  begin
    s:=(x[i][2] - x[i][1]) * (y[i][3] - y[i][1]) - (x[i][3] - x[i][1]) * (y[i][2] - y[i][1]);
    assert(s <> 0);
    if (s < 0) then
    begin
      tmp:=x[i][2];
      x[i][2]:=x[i][3];
      x[i][3]:=tmp;

      tmp:=y[i][2];
      y[i][2]:=y[i][3];
      y[i][3]:=tmp;
    end;

    l[i][1]:=sqr(x[i][1] - x[i][2]) + sqr(y[i][1] - y[i][2]);
    l[i][2]:=sqr(x[i][2] - x[i][3]) + sqr(y[i][2] - y[i][3]);
    l[i][3]:=sqr(x[i][3] - x[i][1]) + sqr(y[i][3] - y[i][1]);
    l[i][4]:=l[i][1];
    l[i][5]:=l[i][2];
  end;

  for i:=2 to n do
  begin
    for j:=0 to 2 do
      if (l[i][1] = l[1][1 + j]) and (l[i][2] = l[1][2 + j]) and (l[i][3] = l[1][3 + j]) then
        goto next;

    writeln('NO');
    exit;
next:
  end;
  writeln('YES');
end.
