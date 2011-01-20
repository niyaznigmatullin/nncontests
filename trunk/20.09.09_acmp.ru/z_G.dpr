{$A8,B-,C+,D+,E-,F-,G+,H+,I+,J-,K-,L+,M-,N+,O+,P+,Q-,R-,S-,T-,U-,V+,W-,X+,Y+,Z1}
program z_G;
{$APPTYPE CONSOLE}
uses
  SysUtils,
  Math;

var
  minx, maxx, miny, maxy, i, j, n, x, y, d, ans: integer;
  a1, a2, d1, d2: array[-5010-1..5010+1, -5010-1..5010+1] of shortint;


begin
  reset(input, 'input.txt');
  rewrite(output, 'output.txt');
try
  read(n);
  x := 0;
  y := 0;
  minx := 0;
  maxx := 0;
  miny := 0;
  maxy := 0;
  for i := 1 to n do
    begin
      read(d);
      if (d = 1) then inc(x) else
        if (d = 2) then
          begin
            inc(a2[x - 1][y]);
            inc(a1[x][y - 1]);
            inc(x);
            dec(y);
          end else
        if (d = 3) then
          begin
            inc(a2[x - 1][y]);
            inc(a1[x - 1][y - 1]);
            dec(y);
          end else
        if (d = 4) then
          begin
            dec(x);
          end else
        if (d = 5) then
          begin
            dec(a2[x - 2][y + 1]);
            dec(a1[x - 1][y]);
            dec(x);
            inc(y);
          end else
        if (d = 6) then
          begin
            dec(a2[x - 1][y + 1]);
            dec(a1[x - 1][y]);
            inc(y);
          end;
    if x > maxx then maxx := x;
    if x < minx then minx := x;
    if y > maxy then maxy := y;
    if y < miny then miny := y;
  end;
  close(input);
  ans := 0;
  for j := miny to maxy do
    for i := maxx downto minx do begin
      a1[i][j] := a1[i][j] + a1[i + 1][j];
      a2[i][j] := a2[i][j] + a2[i + 1][j];
      if (a1[i][j] = 0) then d1[i][j] := 0 else
        if (a2[i][j] = 0) then d1[i][j] := 1 else
          d1[i][j] := Math.min(d1[i][j - 1], d1[i + 1][j - 1]) + 1;
      if (a2[i][j] = 0) then d2[i][j] := 0 else
        if (a1[i + 1][j - 1] = 0) then d2[i][j] := 1 else
          d2[i][j] := min(d2[i + 1][j], d2[i + 1][j - 1]) + 1;
      ans := ans + d1[i][j] + d2[i][j];
    end;
  writeln(ans);
  close(output);
  except
    write(0);
    close(output);
  end;
end.
