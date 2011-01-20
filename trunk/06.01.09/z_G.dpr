program z_G;
{$APPTYPE CONSOLE}


uses
  SysUtils;

const eps=1e-9;

var a,b,c,d:extended;

procedure init;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(a,b,c);
    close(input);
    if (abs(a)<=eps) and (abs(b)<=eps) and (abs(c)<=eps) then
      begin
        write(-1);
        close(output);
        halt(0);
      end;
    if (abs(a)<=eps) and (abs(b)<=eps) then
      begin
        write(0);
        close(output);
        halt(0);
      end;
  end;

procedure lin_al;
  begin
    writeln(1);
    write(-c/b);
    close(output);
    halt(0);
  end;

procedure solve;
  begin
    if (abs(a)<=eps) then
      begin
        lin_al;
      end;
    d:=b*b-4*a*c;
  end;

procedure done;
  begin
    if (abs(d)<=eps) then
      begin
        writeln(1);
        write(-b/(2*a));
      end else
    if (d<-eps) then
      begin
        write(0);
      end else
      begin
        writeln(2);
        writeln((-b+sqrt(d))/(2*a));
        writeln((-b-sqrt(d))/(2*a));
      end;
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.
