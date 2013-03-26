
var n : longint;
    w, k, ans : int64;
    i, f, t : longint;
    
begin
    read(n, k);
    ans := -maxlongint;
    ans := 10 * ans;
    for i := 1 to n do begin
        read(f, t);
        if t > k then begin
            w := f - t + k;
        end else begin
            w := f;
        end;
        if ans < w then ans := w;
    end;
    writeln(ans);
end.