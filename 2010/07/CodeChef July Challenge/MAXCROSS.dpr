program MAXCROSS;
{$APPTYPE CONSOLE}
uses
  SysUtils, Math;

var ans, left, up, lu, ru : array[0 .. 1000, 0 .. 1000] of integer;
    n, i, j : integer;
    c : char;
begin
  readln(n);
  for i := 1 to n do begin
    for j := 1 to n do begin
      read(c);
      if (c = '.') then begin
        left[i][j] := 0;
        up[i][j] := 0;
        lu[i][j] := 0;
        ru[i][j] := 0;
        continue;
      end;
      if (j = 1) then begin
        left[i][j] := 1;
      end else left[i][j] := left[i][j - 1] + 1;
      if (i = 1) then begin
        up[i][j] := 1;
      end else up[i][j] := up[i - 1][j] + 1;
      if (i = 1) or (j = 1) then
        lu[i][j] := 1
      else
        lu[i][j] := lu[i - 1][j - 1] + 1;
      if (i = 1) or (j = n) then
        ru[i][j] := 1
      else
        ru[i][j] := ru[i - 1][j + 1] + 1;
    end;
    readln;
  end;
  for i := n downto 1 do begin
    for j := n downto 1 do begin
      if (up[i][j] = 0) then begin
        ans[i][j] := 0;
        continue;
      end;
      ans[i][j] := max(max(up[i][j], left[i][j]), max(lu[i][j], ru[i][j]));
      if (i <> n) then begin
        ans[i][j] := max(ans[i][j], up[i + 1][j]);
        up[i][j] := max(up[i][j], up[i + 1][j]);
      end;
      if (j <> n) then begin
        ans[i][j] := max(ans[i][j], left[i][j + 1]);
        left[i][j] := max(left[i][j], left[i][j + 1]);
      end;
      if (i <> n) and (j <> n) then begin
        ans[i][j] := max(ans[i][j], lu[i + 1][j + 1]);
        lu[i][j] := max(lu[i][j], lu[i + 1][j + 1]);
      end;
      if (i <> n) and (j <> 1) then begin
        ans[i][j] := max(ans[i][j], ru[i + 1][j - 1]);
        ru[i][j] := max(ru[i][j], ru[i + 1][j - 1]);        
      end;
    end;
  end;
  for i := 1 to n do begin
    for j := 1 to n do begin
      if (j <> 1) then
        write(' ');
      write(ans[i][j]);
    end;
    writeln;
  end;
end.
