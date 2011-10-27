program z_D;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var ans, n:integer;

procedure add(x, y:integer);
  begin
    ans := ans - x * (n div y);
  end;

procedure init;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(n);
    close(input);
  end;

procedure solve;
  begin
    ans := n;
    add(1, 2);
    add(1, 3);
    add(1, 5);
    add(-1, 6);
    add(-1, 15);
    add(-1, 10);
    add(1, 2 * 3 * 5);
    write(ans);
    close(output);
  end;

begin
  init;
  solve;
end.
 