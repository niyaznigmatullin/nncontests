program z_1027;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var s:string = '';
    i,j,k:integer;
    c:char;
    ok:boolean;

procedure no;
  begin
    write('NO');
    halt(0);
  end;

begin
  reset(input,'input.txt');
  rewrite(output,'output.txt');
  while (not eof) do
    begin
      read(c);
      if not (c in [#10,#13]) then s:=s + c;
    end;
  i:=1;
  while (i<=length(s)) do
    begin
      if (i<length(s)) and (s[i] + s[i + 1] = '(*') then
        begin
          j:=i+2;
          ok:=false;
          while (j<length(s)) do
            begin
              if (s[j] + s[j + 1] = '*)') then
                begin
                  delete(s,i,j-i+2);
                  ok:=true;
                  break;
                end;
              inc(j);
            end;
          if (not ok) then no;
        end else
        begin
          inc(i);
        end;
    end;
  i:=1;
  while (i<=length(s)) do
    begin
      if (s[i] = '(') then
        begin
          k:=1;
          j:=i+1;
          while (j<=length(s)) and (k>0) do
            begin
              if not (s[j] in ['=','+','-','*','/','0'..'9','(',')']) then no;
              if (s[j] = '(') then inc(k);
              if (s[j] = ')') then dec(k);
              inc(j);
            end;
          if (k>0) then no;
          delete(s,i,j-i);
        end else
        begin
          inc(i);
        end;
    end;
  for i:=1 to length(s) do
    begin
      if (s[i] in ['(',')']) then no;
    end;
  write('YES');
  readln;
  readln;
  halt(0);
end.
