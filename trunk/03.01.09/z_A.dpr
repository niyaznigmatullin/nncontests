program z_A;
{$APPTYPE CONSOLE}
uses
  SysUtils;


var s:array[0..1001] of string;
      n,k:integer;


  procedure no;
    begin
      write('Impossible.');
      close(output);
      halt(0);
    end;

  procedure init;
  var i:integer;
    begin
      reset(input,'input.txt');
      rewrite(output,'output.txt');
      readln(k,n);
      for i:=1 to n do
        begin
          readln(s[i]);
          s[i]:=trim(s[i]);
          if length(s[i])>k then no;
        end;
      close(input);
    end;

  procedure solve;
  var i,j:integer;
    begin
      for i:=1 to n do
        begin
          for j:=1 to (k-length(s[i])) div 2 do s[i]:=' '+s[i];
          while k>length(s[i]) do s[i]:=s[i]+' ';
        end;
    end;

  procedure done;
  var i:integer;
    begin
      for i:=1 to n do
      writeln(s[i]);
      close(output);
      halt(0);
    end;

begin
  init;
  solve;
  done;
end.
