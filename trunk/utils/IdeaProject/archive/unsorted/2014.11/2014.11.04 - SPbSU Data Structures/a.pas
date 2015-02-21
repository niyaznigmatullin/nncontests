{$O+,R-,S-,Q-}

var n, x, y, a0, m, t, b0 : longint;
    i, left, right, tt : longint;
    a : array[0 .. 12345678] of int64;
    z : longint;
    
function next : longint;
  begin
    next := b0;
    b0 := (b0 * z + t) and (1 shl 30 - 1);
  end;
  
  var
    ans : int64;
    
begin
  assign(input, 'sum0.in');
  reset(input);
  assign(output, 'sum0.out');
  rewrite(output);
  read(n, x, y, a0);
  read(m, z, t, b0);
  a[0] := a0;
  for i := 1 to n do begin
    a[i] := (x * longint(a[i - 1]) + y) and 65535;
  end;
  for i := 1 to n do a[i] := a[i - 1] + a[i];
  ans := 0;
  for i := 1 to m do begin
    left := next;
    right := next;
    left := left mod n;
    right := right mod n;
    if left > right then begin
      tt := left;
      left := right;
      right := tt;
    end;
    ans := ans + a[right];
    if left > 0 then ans := ans - a[left - 1];
  end;
  writeln(ans);
end.