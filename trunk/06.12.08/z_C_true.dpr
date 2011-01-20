program z_C_true;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var s:string;
    ss:array[-500000..500000] of char;
    l,r:integer;

procedure init;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    readln(s);
    close(input);
  end;

procedure solve;
var lastc:char; i:integer;
  begin
    lastc:='z';
    for i:=1 to length(s) do
      begin
        if (s[i]<=lastc) then
          begin
            dec(l);
            ss[l]:=s[i];
            lastc:=s[i];
          end else
          begin
            ss[r]:=s[i];
            inc(r);
          end;
      end;
  end;

procedure done;
var i:integer;
  begin
    for i:=l to r-1 do write(ss[i]);
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.
