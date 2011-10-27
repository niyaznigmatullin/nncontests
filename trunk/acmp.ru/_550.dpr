

var
    n: integer;

begin
    reset(input, 'input.txt');
    rewrite(output, 'output.txt');
    read(n);
    if (n mod 400 = 0) or (n mod 4 = 0) and (n mod 100 <> 0) then
        writeln('13/09/', n)
    else
        writeln('12/09/', n);
    close(input);
    close(output);
end.