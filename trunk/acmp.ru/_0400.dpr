program _0400;

{$APPTYPE CONSOLE}

uses
  SysUtils;

var a,b:array[0..10] of integer; q,p,i,j:integer;
    z:array[0..100000] of integer;

procedure no; begin write('IMPOSSIBLE'); close(output); halt(0); end;
procedure yes; begin write('POSSIBLE'); close(output); halt(0); end;
begin
  reset(input,'input.txt');
  rewrite(output,'output.txt');
  for i:=1 to 6 do read(a[i],b[i]);
  for i:=1 to 6 do if b[i]>a[i] then begin p:=b[i]; b[i]:=a[i]; a[i]:=p; end;
  q:=0;
  fillchar(z,sizeof(z),false);
  for i:=1 to 6 do
    begin
      if z[a[i]]=0 then inc(q);
      inc(z[a[i]]);
    end;
  for i:=1 to 6 do
    begin
      if z[b[i]]=0 then inc(q);
      inc(z[b[i]]);
    end;
  if q>3 then no;
  if q=2 then for i:=1 to 6 do begin if (z[a[i]]=6) or (z[a[i]]=8) or (z[a[i]]=4) then else no; if (z[b[i]]=6) or (z[b[i]]=8) or (z[b[i]]=4)then else no; end;
  for i:=1 to 5 do
  for j:=i+1 to 6 do
    begin
      if a[i]>a[j] then begin p:=a[i]; a[i]:=a[j]; a[j]:=p; p:=b[i]; b[i]:=b[j]; b[j]:=p; end else if a[i]=a[j] then if b[i]>b[j] then begin p:=a[i]; a[i]:=a[j]; a[j]:=p; p:=b[i]; b[i]:=b[j]; b[j]:=p; end;
    end;
  if (a[1]=a[2]) and (b[1]=b[2]) and (a[3]=a[4]) and (b[3]=b[4]) and (a[5]=a[6]) and (b[5]=b[6]) then  else no;
  yes;
end.
