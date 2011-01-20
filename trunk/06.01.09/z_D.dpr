program z_D;
{$APPTYPE CONSOLE}
uses
  SysUtils;

type
    int = longint;
const
    maxn = 100000;
var
    ff, c, m, i, j, r: int;
    v, pr, ne: array[1 .. maxn] of int;
    ch: char;
begin
    reset(input, 'input.txt');
    rewrite(output, 'output.txt');

    ff := 1;

    ne[1] := 0;
    pr[1] := 0;
    v [1] := 0;
    c := 1;

    m := 0;
    r := 0;

    while (not eoln) do begin
        read(ch);
        inc(r);
        case ch of
            '|': begin
                if (ne[c] > 0) then
                    c := ne[c];
            end;
            '\': begin
                inc(ff);
                i := ne[c];

                if (i > 0) then
                    pr[i] := ff;
                pr[ff] := c;

                ne[ff] := i;
                ne[c] := ff;

                c := ff;
            end;
            '^': begin
                if (pr[c] > 0) then
                    c := pr[c];
            end;
            '<': begin
                if (v[c] > 0) then
                    dec(v[c])
                else begin
                    i := pr[c];
                    if (i > 0) then begin
                        j := ne[c];
                        ne[i] := j;
                        c := i;
                        if (j > 0) then
                          pr[j] := i;
                    end;
                end;
            end;
            else begin
                inc(v[c]);
                if (v[c] > m) then
                    m := v[c];
            end;
        end;
    end;
    write(m);
end.
 