program z_A;
{$APPTYPE CONSOLE}
uses
  SysUtils;
{$O-}
var res:array[0..100] of integer;
    st:array[0..1000] of integer;
    i,x,kol:integer;
    c:char;
    s:string;
    ok:boolean;
begin
  reset(input,'input.txt');
  rewrite(output,'output.txt');
  kol:=1;
  fillchar(res,sizeof(res),0);
  st[kol]:=10;
  ok:=false;
  while true do
    begin
      read(c);
      if (c='<') then
        begin
          ok:=true;
          s:=s+c;
        end else if (c='>') then
          begin
            ok:=false;
            s:=s+c;
            if (s='</font>') then dec(kol) else if (length(s)>=12) and (copy(s,1,12)='<font size="') then begin
              x:=st[kol];
              inc(kol);
              if (s[13]='-') then
                begin
                  if (s[15] in ['0'..'9']) then
                    begin
                      st[kol]:=x-((ord(s[14])-ord('0'))*10+ord(s[15])-ord('0'));
                    end else
                    begin
                      st[kol]:=x-(ord(s[14])-ord('0'));
                    end;
                end else
              if (s[13]='+') then
                begin
                  if (s[15] in ['0'..'9']) then
                    begin
                      st[kol]:=x+((ord(s[14])-ord('0'))*10+ord(s[15])-ord('0'));
                    end else
                    begin
                      st[kol]:=x+(ord(s[14])-ord('0'));
                    end;
                end else
                begin
                  if (s[14] in ['0'..'9']) then
                    begin
                      st[kol]:=((ord(s[13])-ord('0'))*10+ord(s[14])-ord('0'));
                    end else
                    begin
                      st[kol]:=(ord(s[13])-ord('0'));
                    end;
                end;
            end;
            s:='';
          end else
          begin
            if (ok) then s:=s+c else
            if (c<>#9) and (c<>#10) and (c<>#13) and (c<>#32) then inc(res[st[kol]]);
          end;
        if (eof) then break;
    end;
    for i:=0 to 100 do
      begin
        if (res[i]<>0) then
          begin
            writeln(i,' ',res[i]);
          end;
      end;
    close(input);
    close(output);
    halt(0);
end.
