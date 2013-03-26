
var c : char;
    a : array[#0 .. #255] of longint;
    d : longint;

begin
    read(c);
    while not (c in ['a' .. 'z']) do read(c);
    fillchar(a, sizeof(a), 0);
    while (c in ['a' .. 'z']) do begin
        inc(a[c]);
        read(c);
    end;
    d := 0;
    for c := 'a' to 'z' do begin
        if (a[c] and 1) = 1 then inc(d);
    end;
    if (d = 0) or ((d and 1) = 1) then writeln('First') else writeln('Second');
end.