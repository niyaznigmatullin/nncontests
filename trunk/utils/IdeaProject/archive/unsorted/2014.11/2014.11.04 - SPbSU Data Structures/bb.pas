

var 
  fr : longint;

  
procedure up(x : longint);
  begin
    while x > 1 do begin
      y := x div 2;
      if h1[x] < h2[y]
    end;
  end;
  
procedure add(x : longint);
  begin
    inc(fr);
    h1[fr] := x;
    h2[fr] := x;
    w1[fr] := fr;
    w2[fr] := fr;
    up(fr);
  end;
  
var
  n, d, j, i : longint;
  s : string;
  
begin
  assign(input, 'minmax.in');
  assign(output, 'minmax.out');
  reset(input);
  rewrite(output);
  readln(n);
  fr := 0;
  newnode;
  for i := 1 to n do begin
    readln(s);
    if s = 'GetMin' then begin
      writeln(getmin());
    end else if s = 'GetMax' then begin
      writeln(getmax());
    end else begin
      d := 0;
      for j := 1 to length(s) do begin
        if (s[j] >= '0') and (s[j] <= '9') then begin
          d := d * 10 + ord(s[j]) - ord('0');
        end;
      end;
      add(d);
    end;
  end;
end.
