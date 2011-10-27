program _0288;

{$APPTYPE CONSOLE}

uses
  SysUtils;


var
i,k,kom:integer;
c:char;
label lb;
label lb2;
begin
  reset(input,'input.txt');
  rewrite(output,'output.txt');
k:=0;
while not eof do
begin
 while not eoln do
 begin
 lb2:
  read(c);
   lb:
    if (k=0) and (ord(c)=39) then begin k:=-1;  goto lb2;  end;
    if (k=0) and (c='{') then k:=1;
    if (k=0) and (c='(') then
     if not eoln then
          begin
            read(c); if c='*' then k:=2 else goto lb;
          end;
    if (k=0) and (c='/') then
     if not eoln then
          begin
           read(c); if c='/' then k:=3 else goto lb;
          end;

    if (k=-1) and (ord(c)=39) then k:=0;
    if (k=1) and (c='}') then begin inc(kom); k:=0; end;
    if (k=2) and (c='*') then
     if not eoln then
          begin
            read(c); if c=')' then begin inc(kom); k:=0; end else goto lb;
          end;
 end;
if k=3 then begin inc(kom); k:=0;  end;
readln;
end;
//   reset(input); read(c);  writeln(ord(c));
write(kom);
end.
