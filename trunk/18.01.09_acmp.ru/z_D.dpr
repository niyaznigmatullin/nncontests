{$APPTYPE CONSOLE}
{$O+,D-,I-,L-,Q-,R-,S-}
uses
  SysUtils;
const MaxN = 1 shl 18 - 1;
      inf = high(int64) shr 1;
var a, t, scar: array [0..200000] of int64;
    b: array [0..100000, 1..2] of int64;
    c: array [0..100000, 1..2] of integer;
    k, n, m, i, j, nn, l, r, mm, l1, r1: integer;
    ans: array [0..200000] of integer;
    tr: array [0..2*MaxN+1] of integer;
    f: array [0..100000] of boolean;
    x: int64;
procedure sort(l,r: integer);
var
  i, j: integer;
  x, y: int64;
begin
  i := l; j := r; x := t[(l+r) DIV 2];
  repeat
    while t[i]<x do i:=i+1;
    while x<t[j] do j:=j-1;
    if i<=j then
    begin
      y:=t[i]; t[i]:=t[j]; t[j]:=y;
      i:=i+1; j:=j-1;
    end;
  until i>j;
  if l<j then sort(l,j);
  if i<r then sort(i,r);
end;
function Find(x: int64): integer;
var l, r, m: integer;
begin
  l := 0;
  r := nn;
  while r - l > 1 do
  begin
    m := (l+r) shr 1;
    if a[m] < x then l := m else r := m
  end;
  if a[r] = x then result := r else result := 0
end;
function Play(x, y: int64; k: integer): boolean;
var i: integer;
begin
  i := 1;
  while ((x-1) shr i) <> ((y-1) shr i) do inc(i);
  result := i = k
end;
procedure Tinc(i: integer);
begin
  i := i + maxn;
  while i > 0 do
  begin
    inc(tr[i]);
    i := i shr 1
  end;
end;
function SumInt(l, r: integer): integer;
begin
  l := l + maxn;
  r := r + maxn;
  result := 0;
  while l <= r do
  begin
    if l and 1 = 1 then begin result := result + tr[l]; inc(l) end;
    if r and 1 = 0 then begin result := result + tr[r]; dec(r) end;
    l := l shr 1;
    r := r shr 1
  end;
end;
begin
  reset(input, 'input.txt');
  rewrite(output, 'output.txt');
  read(k, n, m);
  for i := 1 to n do
  begin
    read(b[i, 1], b[i, 2]);
    t[2*i-1] := b[i, 1];
    t[2*i] := b[i, 2]
  end;
  sort(1, 2*n);
  a[1] := t[1];
  nn := 1;
  for i := 2 to 2*n do
  if t[i] <> t[i-1] then
  begin
    inc(nn);
    a[nn] := t[i]
  end;
  for i := 1 to n do
    for j := 1 to 2 do
      c[i, j] := find(b[i, j]);
  a[nn+1] := inf;
  for i := 1 to k do
  begin
    for j := 1 to nn do
      if ans[j] <> 0 then scar[j] := -1 else
      begin
        l := 0;
        r := nn+1;
        while r - l > 1 do
        begin
          mm := (l+r) shr 1;
          if a[mm] < ((int64(a[j]-1) shr (i-1)) xor 1) shl (i-1) + 1 then l := mm else r := mm
        end;
        l1 := r;  
        l := 0;
        r := nn+1;
        while r - l > 1 do
        begin
          mm := (l+r) shr 1;
          if a[mm] < (((int64(a[j]-1) shr (i-1)) xor 1) + 1) shl (i-1) + 1 then l := mm else r := mm
        end;
        r1 := l;
        scar[j] := int64(1) shl (i-1) - sumint(l1, r1)
      end;
    for j := 1 to n do
      if not f[j] and play(a[c[j, 1]], a[c[j, 2]], i) and (ans[c[j, 1]] = 0) and (ans[c[j, 2]] = 0) then
      begin
        dec(scar[c[j, 2]]);
        f[j] := TRUE
      end;
    for j := 1 to nn do
      if scar[j] = 0 then
      begin
        ans[j] := i;
        tinc(j)
      end
  end;
  for i := 1 to nn do
    if ans[i] = 0 then ans[i] := k;
  for i := 1 to m do
  begin
    read(x);
    if find(x) = 0 then write(k, ' ') else write(ans[find(x)], ' ')
  end;
  close(input);
  close(output)
end.
 
