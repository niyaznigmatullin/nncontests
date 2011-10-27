{$APPTYPE CONSOLE}

uses SysUtils;
var p:pointer;
begin
getmem(p,100000000);
if (p=nil)then halt(0);
end.
