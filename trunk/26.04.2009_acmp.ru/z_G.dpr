program z_G;
{$APPTYPE CONSOLE}
Uses SysUtils, Math;
Const
 eps = 1e-9;
Var
 x1, y1, x2, y2 : longint;
 a, b, d : double;
 ans : longint;

Begin
 Reset(Input, 'input.txt');
 ReWrite(Output, 'output.txt');
 Read(x1, y1, x2, y2);
 d := abs((y2 * x1 - x2 * y1) / sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)));
 a := sqrt(x1 * x1 + y1 * y1);
 b := sqrt(x2 * x2 + y2 * y2);
 ans := 0;
 if (x1 * x1 + y1 * y1 - x2 * x2 - y2 * y2 + (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2) < 0) or
   (-x1 * x1 - y1 * y1 + x2 * x2 + y2 * y2 + (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2) < 0) then
 begin
  ans := ans + trunc(max(a, b) + eps) - trunc(min(a, b) - eps);
 end else
 begin
  ans := ans + trunc(a + eps) - trunc(d - eps);
  ans := ans + trunc(b + eps) - trunc(d - eps);
  if (abs(round(d) - d) < eps) and (abs(d) > eps) then
  begin
   dec(ans);
  end;
 end;
 Writeln(ans);
 Close(Input);
 Close(Output);
End. 
