program _0372;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var n:integer;

procedure rec(s:string; k,l:integer);
  begin
    if k=n then
      begin
        writeln(s);
        exit;
      end;
    rec('('+s+')',k+2,0);
    //rec('['+s+']',k+2,0);
    rec('()'+s,k+2,1);
    if l=0 then rec(s+'()',k+2,0);
  //  rec('[]'+s,k+2,1);
//    if l=0 then rec(s+'[]',k+2,0);
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
    rec('()',2,1);
    //rec('[]',2,1);
  end;

procedure done;
  begin
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.
