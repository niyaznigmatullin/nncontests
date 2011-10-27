program _0381;
{$APPTYPE CONSOLE}
uses
  SysUtils;

const wc='O';

var oldi,oldj,newi,newj:array[0..3000] of integer;
    c:array[0..51,0..51] of char;
    u:array[0..51,0..51] of boolean;
    p1,p2:array[0..51,0..51] of integer;
    kolnew,kolold,n,m,i1,i2,j1,j2:integer;
    ok:boolean;

procedure no;
  begin
    write('No');
    close(output);
    halt(0);
  end;

procedure print(x,y:integer);
var xx,yy,i,j:integer;
  begin
    writeln('Yes');
    while (x<>i1) or (y<>j1) do
      begin
        xx:=p1[x,y];
        yy:=p2[x,y];
        x:=xx;
        y:=yy;
        c[x,y]:='+';
      end;
    for i:=1 to n do
      begin
        for j:=1 to m do write(c[i,j]);
        writeln;
      end;
    close(output);
    halt(0);
  end;

procedure init;
var i,j:integer;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    readln(n);
    m:=n;
    for i:=1 to n do
      begin
        for j:=1 to m do
          begin
            read(c[i,j]);
            if c[i,j]='X' then
              begin
                i1:=i;
                j1:=j;
              end else
            if c[i,j]='@' then
              begin
                i2:=i;
                j2:=j;
              end;
          end;
        readln;
      end;
    close(input);
  end;


procedure solve;
var i:integer;
  procedure add_que(x,y:integer);
    begin
      u[x,y]:=true;
      inc(kolnew);
      newi[kolnew]:=x;
      newj[kolnew]:=y;
    end;
  function check(x,y:integer):boolean;
    begin
      result:=c[x,y]='@';
    end;
  begin
    fillchar(oldi,sizeof(oldi),0);
    fillchar(oldj,sizeof(oldj),0);
    kolold:=1;
    fillchar(u,sizeof(u),false);
    u[i1,j1]:=true;
    oldi[1]:=i1;
    oldj[1]:=j1;
    ok:=false;
    repeat
      kolnew:=0;
      for i:=1 to kolold do
        begin
          if (oldi[i]>1) and (not u[oldi[i]-1,oldj[i]]) and (c[oldi[i]-1,oldj[i]]<>wc) then
            begin
              add_que(oldi[i]-1,oldj[i]);
              p1[oldi[i]-1,oldj[i]]:=oldi[i];
              p2[oldi[i]-1,oldj[i]]:=oldj[i];
              if check(oldi[i]-1,oldj[i]) then
                begin
                  ok:=true;
                  break;
                end;
            end;
          if (oldj[i]>1) and (not u[oldi[i],oldj[i]-1]) and (c[oldi[i],oldj[i]-1]<>wc) then
            begin
              add_que(oldi[i],oldj[i]-1);
              p1[oldi[i],oldj[i]-1]:=oldi[i];
              p2[oldi[i],oldj[i]-1]:=oldj[i];
              if check(oldi[i],oldj[i]-1) then
                begin
                  ok:=true;
                  break;
                end;
            end;
          if (oldi[i]<n) and (not u[oldi[i]+1,oldj[i]]) and (c[oldi[i]+1,oldj[i]]<>wc) then
            begin
              add_que(oldi[i]+1,oldj[i]);
              p1[oldi[i]+1,oldj[i]]:=oldi[i];
              p2[oldi[i]+1,oldj[i]]:=oldj[i];
              if check(oldi[i]+1,oldj[i]) then
                begin
                  ok:=true;
                  break;
                end;
            end;
          if (oldj[i]<n) and (not u[oldi[i],oldj[i]+1]) and (c[oldi[i],oldj[i]+1]<>wc) then
            begin
              add_que(oldi[i],oldj[i]+1);
              p1[oldi[i],oldj[i]+1]:=oldi[i];
              p2[oldi[i],oldj[i]+1]:=oldj[i];
              if check(oldi[i],oldj[i]+1) then
                begin
                  ok:=true;
                  break;
                end;
            end;
        end;
      kolold:=kolnew;
      oldi:=newi;
      oldj:=newj;
    until (kolold=0) or ok;
    if not ok then no else print(i2,j2);
  end;

begin
  init;
  solve;
end.
