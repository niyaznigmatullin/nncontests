program z_E;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var a,b,c,k:integer;
    ans:array[0..6] of integer;

procedure swap(var x,y:integer);
var p:integer;
  begin
    p := x;
    x := y;
    y := p;
  end;

procedure init;
  begin
    reset(input,'painting.in');
    rewrite(output,'painting.out');
    read(a,b,c,k);
    close(input);
  end;

procedure solve;
  begin
    fillchar(ans,sizeof(ans),0);
    if (a > b) then swap(a,b);
    if (a > c) then swap(a,c);
    if (b > c) then swap(b,c);
    if (a > 1) then
      begin
        ans[3] := 8;
        ans[2] := (a + b + c - 6) * 4;
        ans[1] := ((a - 2) * (b - 2) + (a - 2) * (c - 2) + (c - 2) * (b - 2)) * 2;
        ans[0] := a * b * c - ans[3] - ans[2] - ans[1];
      end else
    if (a = 1) and (b > 1) then
      begin
        ans[4] := 4;
        ans[3] := (b + c - 4) * 2;
        ans[2] := b * c - 4 - ans[3];
      end else
    if (a = 1) and (b = 1) and (c = 1) then ans[6] := 1 else if (a = 1) and (b = 1) and (c > 1) then
      begin
        ans[5] := 2;
        ans[4] := c - 2;
      end else halt(1);
    write(ans[k]);
    close(output);
  end;

begin
  init;
  solve;
end.
