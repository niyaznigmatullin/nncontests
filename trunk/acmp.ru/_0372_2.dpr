program _0372_2;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var n:integer;
    stack:array[0..100] of char;
    kol:integer;

procedure rec(s:string; n,k:integer);
  begin
    if n=k then begin writeln(s); exit; end;
    if kol=0 then
      begin
        inc(kol);
        stack[kol]:='(';
        rec(s+'(',n,k+1);
        stack[kol]:='[';
        rec(s+'[',n,k+1);
        dec(kol);
      end else
      begin
        if kol>=n-k then
          begin
            if stack[kol]='[' then begin dec(kol); rec(s+']',n,k+1); inc(kol); stack[kol]:='['; end;
            if stack[kol]='(' then begin dec(kol); rec(s+')',n,k+1); inc(kol); stack[kol]:='('; end;
          end else
          begin
            inc(kol);
            stack[kol]:='(';
            rec(s+'(',n,k+1);
            stack[kol]:='[';
            rec(s+'[',n,k+1);
            dec(kol);
            if stack[kol]='[' then begin dec(kol); rec(s+']',n,k+1); inc(kol); stack[kol]:='['; end;
            if stack[kol]='(' then begin dec(kol); rec(s+')',n,k+1); inc(kol); stack[kol]:='('; end;
          end;
      end;
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
    rec('',n,0);
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
 