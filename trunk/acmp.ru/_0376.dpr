program _0376;

{$APPTYPE CONSOLE}

uses
  SysUtils;

const may:array[1..12]of integer=(31,28,31,30,31,30,31,31,30,31,30,31);
var
d,m,td,tm,ty,ans:longint;
function vis:boolean;
begin
 if (ty mod 100=0)and(ty mod 400=0) then begin vis:=true; exit; end else
 if (ty mod 100=0)and(ty mod 400<>0) then begin vis:=false; exit; end;
 if ty mod 4=0 then begin vis:=true; exit; end;
 vis:=false;
end;
begin
 reset(input,'input.txt');
 rewrite(output,'output.txt');
 read(d,m,td,tm,ty);
 ans:=0;
 if (d=td)and(m=tm) then begin write(0); close(output); halt(0); end;
 while (tm<>m)or(td<>d) do
  if (td=31)and(tm=12) then begin ty:=ty+1;ans:=ans+1;tm:=1;td:=1;continue; end else
  if (vis)and(tm=2)and(td=29) then begin tm:=tm+1;ans:=ans+1;td:=1;continue; end else
  if (vis)and(td=28)and(tm=2) then begin td:=td+1; ans:=ans+1; continue; end else
  if td=may[tm] then begin tm:=tm+1;td:=1; ans:=ans+1;continue; end else
  begin td:=td+1;ans:=ans+1;end;
 write(ans);
end. 
