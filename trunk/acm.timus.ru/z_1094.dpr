program z_1094;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var ss, s : string;
    ans : array[1..80] of char;

procedure init;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    while (not eof) do begin readln(ss); s := s + ss; end;
  end;

procedure solve;
var now, i : integer;
  begin
    now := 1;
    for i := 1 to 80 do ans[i] := ' ';
    for i := 1 to length(s) do
      begin
        if (s[i] = '<') then dec(now) else if (s[i] = '>') then inc(now) else begin
          ans[now] := s[i];
          inc(now);
          end;
        if (now < 1) or (now > 80) then now := 1;
      end;
    for i := 1 to 80 do write(ans[i]);
  end;

begin
  init; solve;
end.
