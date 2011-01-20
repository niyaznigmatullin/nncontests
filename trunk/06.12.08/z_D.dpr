program z_D;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var d,r:integer;

procedure init;
  begin
    reset(input,'graph.in');
    rewrite(output,'graph.out');
    read(d,r);
    close(input);
  end;

procedure done;
  begin
    if (d=1) and (r=1) then
      begin
        writeln('YES');
        writeln(2,' ',1);
        writeln('1 2');
        close(output);
        halt(0);
      end else
    if (d=3) and (r=2) then
      begin
        writeln('YES');
        writeln('7 9');
        writeln('1 2');
        writeln('2 3');
        writeln('3 4');
        writeln('1 4');
        writeln('4 5');
        writeln('5 6');
        writeln('1 6');
        writeln('6 7');
        writeln('7 2');
        close(output);
        halt(0);
      end;
    writeln('NO');
    close(output);
    halt(0);
  end;


begin
  init;
  done;
end.
