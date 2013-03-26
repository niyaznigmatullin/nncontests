var x, y, x0, y0, kol : int64;
i, j : longint;
flag : boolean;

procedure d;
begin
    i := 1;
    flag := true;
    while (x0 <> x) or (y0 <> y) do begin
       j := 1
       while flag and ((x0 <> x) or (y0 <> y)) and (j <= i) do begin
        inc(x0);
        if (x0=x) and(y0=y) then flag := false;
       inc(j);
       end;
       if flag then inc(kol);
       j := 1;
       while flag and ((x0<>x) or (y0<>y)) and (j <= i) do begin
        inc(y0);
        if (x0=x) and (y0=y) then flag := false;
        inc(j);
       end;
       if flag then inc(kol);
       inc(i);
       
       j := 1
       while flag and (j <= i) and ((
    end;
end;

begin
    read(x, y);
end.