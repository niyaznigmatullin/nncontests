program z_G;
{$APPTYPE CONSOLE}
Uses SysUtils, Math;

var n:integer;
Begin
 Reset(Input, 'input.txt');
 ReWrite(Output, 'output.txt');
  read(n);
  write(n*(n+1) div 2+1);
  close(input);
 Close(Output);
End. 
