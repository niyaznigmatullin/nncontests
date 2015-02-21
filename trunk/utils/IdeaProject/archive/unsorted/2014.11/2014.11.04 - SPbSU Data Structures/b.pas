
var
  fr : longint;
  link : array[0 .. 2, 0 .. 4000000] of longint;
  cn : array[0 .. 4000000] of longint;
  
function newnode: longint;
  begin
    newnode := fr;
    link[0][fr] := -1;
    link[1][fr] := -1;
    cn[fr] := 0;
    inc(fr);
  end;
  
procedure add(x : longint);
var
  bit, z, v : longint;
  begin
    v := 0;
    inc(cn[v]);
    for bit := 30 downto 0 do begin
      z := (x shr bit) and 1;
      if link[z][v] < 0 then link[z][v] := newnode;
      v := link[z][v];
      inc(cn[v]);
    end;
  end;
  
function getmin(v, bit, num : longint) : longint;
  begin
    dec(cn[v]);
    if bit < 0 then begin
      getmin := num;
      exit;
    end;
    if (link[0][v] >= 0) and (cn[link[0][v]] > 0) then getmin := getmin(link[0][v], bit - 1, num) else
      getmin := getmin(link[1][v], bit - 1, num or (1 shl bit));
  end;

function getmax(v, bit, num : longint) : longint;
  begin
    dec(cn[v]);
    if bit < 0 then begin
      getmax := num;
      exit;
    end;
    if (link[1][v] >= 0) and (cn[link[1][v]] > 0) then getmax := getmax(link[1][v], bit - 1, num or (1 shl bit)) else
      getmax := getmax(link[0][v], bit - 1, num);
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
      writeln(getmin(0, 30, 0));
    end else if s = 'GetMax' then begin
      writeln(getmax(0, 30, 0));
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
