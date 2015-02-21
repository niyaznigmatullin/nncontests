var
  a : array[0 .. 1234567] of longint;
  n : longint;
  
procedure sort(l, r : longint);
var
  i, j, m, t : longint;
begin
  i := l;
  j := r;
  m := a[random(r - l + 1) + l];
  while i <= j do begin
    while a[i] < m do inc(i);
    while a[j] > m do dec(j);
    if i <= j then begin
      t := a[i]; a[i] := a[j]; a[j] := t;
      inc(i);
      dec(j);
    end;
  end;
  if i < r then sort(i, r);
  if l < j then sort(l, j);
end;

var
  i : longint;
  
begin
  assign(input, 'qsort.in');
  assign(output, 'qsort.out');
  reset(input);
  rewrite(output);
  read(n);
  for i := 1 to n do read(a[i]);
  sort(1, n);
  for i := 1 to n do begin
    if i > 1 then write(' ');
    write(a[i]);
  end;
  writeln;
end.