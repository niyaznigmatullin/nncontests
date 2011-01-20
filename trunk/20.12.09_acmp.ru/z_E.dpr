program z_66;

{$APPTYPE CONSOLE}
 {$o-}
uses
  SysUtils;

var
a:char;
s:string;
i:longint;
begin
 reset(input,'input.txt');
 rewrite(output,'output.txt');
 s:='qwertyuiopasdfghjklzxcvbnmq';
 read(a);
 for i:=1 to 100 do
 if s[i]=a then begin write(s[i+1]); close(output); halt(0); end;
end.