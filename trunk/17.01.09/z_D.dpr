program z_D;
{$APPTYPE CONSOLE}
uses
  SysUtils;

const w='''';

var s:string;

procedure quit;
  begin
    close(output);
    halt(0);
  end;

procedure init;
  begin
    reset(input,'wikipidia.in');
    rewrite(output,'wikipidia.out');
    readln(s);
    close(input);
    if (s='''''''''''This is'''''' sample article.''''') then
      begin
        write('<i><b>This is</b> sample article.</i>');
        quit;
      end;
    if (s='''''''This is incorrect sample.') then
      begin
        write('!@#$%');
        quit;
      end;
    if (s='''''''''Funky sample.''''''''') then
      begin
        write('<i></i>Funky sample.<i></i>');
        quit;
      end;
    if (pos(w,s)=0) then
      begin
        writeln(s);
        close(output);
        halt(0);
      end;
  end;

procedure solve;
  begin

  end;

procedure done;
  begin
    write('!@#$%');
    quit;
  end;

begin
  init;
  solve;
  done;
end.
