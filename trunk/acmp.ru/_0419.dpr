program _0419;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var i,j,n,kol,dx:integer;
    s:string;
    c:char;

procedure print(s:string);
  begin
    writeln('YES');
    writeln(s);
    close(output);
    halt(0);
  end;

procedure to_delete(s:string);
var i,j,dx,kol:integer;
  begin
    i:=1;
    j:=n;
    kol:=0;dx:=-1;
    while (i<j) do
      begin
        if (s[i]<>s[j]) then
          begin
            dx:=i;
            inc(i);
            inc(kol);
            continue;
          end;
        inc(i);
        dec(j);
      end;
    if (kol<2) then
      begin
        delete(s,dx,1);
        print(s);
      end;
  end;

begin
  reset( input, 'input.txt' );
  rewrite( output, 'output.txt' );
  while (not seekeof) do
    begin
      read(c);
      c:=upcase(c);
      if c in ['A'..'Z'] then s:=s+c;
    end;
  close(input);
  n:=length(s);
  i:=1;
  j:=n;
  kol:=0;
  dx:=-1;
  c:=#0;
  while (i<j) do
    begin
      if (s[i]<>s[j]) then
        begin
          inc(kol);
          dx:=i;
          c:=s[j];
        end;
      inc(i);
      dec(j);
    end;
  if (kol=0) then
    begin
      print(s);
    end;
  if (kol=1) then
    begin
      s[dx]:=c;
      print(s);
    end;
  to_delete(s);
  for i:=1 to n div 2 do
    begin
      c:=s[i];
      s[i]:=s[n-i+1];
      s[n-i+1]:=c;
    end;
  to_delete(s);
  writeln('NO');
  close(output);
  halt(0);
end.
