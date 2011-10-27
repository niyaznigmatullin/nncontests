program _0308;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var a1,b1,a2,b2,a3,b3:array[0..10000] of integer;
    c1,c2,c3,t,kola,kolb:integer;
    u:array[-1500..1500,-1500..1500] of boolean;

procedure init;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(c1,c2,c3,t);
    close(input);
  end;

procedure solve;
var q,i:integer;
  begin
    b1[1]:=c1;
    b2[1]:=0;
    b3[1]:=0;
    u[c1,0]:=true;
    q:=0;
    kolb:=1;
    repeat
      kola:=0;
      inc(q);
      for i:=1 to kolb do
        begin
          if (b1[i]<>0) and (b2[i]<c2) and (not u[0,b1[i]+b2[i]]) and (not u[b1[i]+b2[i]-c2,c2]) then
            begin
              inc(kola);
              if (b1[i]+b2[i]<=c2) then
                begin
                  a2[kola]:=b1[i]+b2[i];
                  a1[kola]:=0;
                  a3[kola]:=b3[i];
                end else
                begin
                  a2[kola]:=c2;
                  a1[kola]:=b1[i]+b2[i]-c2;
                  a3[kola]:=b3[i];
                end;
              u[a1[kola],a2[kola]]:=true;
              if a1[kola]=t then
                begin
                  write(q);
                  close(output);
                  halt(0);
                end;
            end;
          if (b2[i]<>0) and (b1[i]<c1) and (not u[b1[i]+b2[i],0]) and (not u[c1,b1[i]+b2[i]-c1]) then
            begin
              inc(kola);
              if (b1[i]+b2[i]<=c1) then
                begin
                  a1[kola]:=b1[i]+b2[i];
                  a2[kola]:=0;
                  a3[kola]:=b3[i];
                end else
                begin
                  a1[kola]:=c1;
                  a2[kola]:=b1[i]+b2[i]-c1;
                  a3[kola]:=b3[i];
                end;
              u[a1[kola],a2[kola]]:=true;
              if a1[kola]=t then
                begin
                  write(q);
                  close(output);
                  halt(0);
                end;
            end;
          if (b2[i]<>0) and (b3[i]<c3) and (not u[b1[i],0]) and (not u[b1[i],b2[i]+b3[i]-c3]) then
            begin
              inc(kola);
              if (b2[i]+b3[i]<=c3) then
                begin
                  a3[kola]:=b2[i]+b3[i];
                  a2[kola]:=0;
                  a1[kola]:=b1[i];
                end else
                begin
                  a3[kola]:=c3;
                  a2[kola]:=b2[i]+b3[i]-c3;
                  a1[kola]:=b1[i];
                end;
              u[a1[kola],a2[kola]]:=true;
            end;
          if (b3[i]<>0) and (b2[i]<c2) and (not u[b1[i],b2[i]+b3[i]]) and (not u[b1[i],c2]) then
            begin
              inc(kola);
              if (b2[i]+b3[i]<=c2) then
                begin
                  a2[kola]:=b2[i]+b3[i];
                  a3[kola]:=0;
                  a1[kola]:=b1[i];
                end else
                begin
                  a2[kola]:=c2;
                  a3[kola]:=b2[i]+b3[i]-c2;
                  a1[kola]:=b1[i];
                end;
              u[a1[kola],a2[kola]]:=true;
            end;
          if (b1[i]<>0) and (b3[i]<c3) and (not u[0,b2[i]]) and (not u[b1[i]+b3[i]-c3,b2[i]]) then
            begin
              inc(kola);
              if (b1[i]+b3[i]<=c3) then
                begin
                  a3[kola]:=b1[i]+b3[i];
                  a1[kola]:=0;
                  a2[kola]:=b2[i];
                end else
                begin
                  a3[kola]:=c3;
                  a1[kola]:=b1[i]+b3[i]-c3;
                  a2[kola]:=b2[i];
                end;
              u[a1[kola],a2[kola]]:=true;
              if a1[kola]=t then
                begin
                  write(q);
                  close(output);
                  halt(0);
                end;
            end;
          if (b3[i]<>0) and (b1[i]<c1) and (not u[b1[i]+b3[i],b2[i]]) and (not u[c1,b1[i]+b3[i]-c1]) then
            begin
              inc(kola);
              if (b1[i]+b3[i]<=c1) then
                begin
                  a1[kola]:=b1[i]+b3[i];
                  a3[kola]:=0;
                  a2[kola]:=b2[i];
                end else
                begin
                  a1[kola]:=c1;
                  a3[kola]:=b1[i]+b3[i]-c1;
                  a2[kola]:=b2[i];
                end;
              u[a1[kola],a2[kola]]:=true;
              if a1[kola]=t then
                begin
                  write(q);
                  close(output);
                  halt(0);
                end;
            end;
        end;
      kolb:=kola;
      b1:=a1;
      b2:=a2;
      b3:=a3;
    until kolb=0;
  end;

procedure done;
  begin
    write('IMPOSSIBLE');
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.
