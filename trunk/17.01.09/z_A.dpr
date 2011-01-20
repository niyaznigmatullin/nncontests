program z_A;
{$APPTYPE CONSOLE}
uses
  SysUtils;

const InputFile='maze.in';
      OutputFile='maze.out';

var a:array[0..101,0..101] of boolean;
    b:array[0..101,0..101] of integer;
    w:array[0..11,0..101] of integer;
    u,v,c:array[0..1001] of integer;
    r,kol:array[0..11] of integer;
    used:array[0..101] of boolean;
    n,m,k,s,t,q:integer;

procedure dfs(x,kk:integer);
var i,e,j:integer;
  begin
    used[x]:=true;
    if (k>q) then
      begin
        used[x]:=false;
        exit;
      end;
    if (x=t) then
      begin
        if (q>kk) then q:=kk;
        exit;
      end;
    for j:=1 to k do if (r[j]=x) then
      begin
        for e:=1 to kol[j] do
          begin
            b[u[w[j][e]],v[w[j][e]]]:=(b[u[w[j][e]],v[w[j][e]]]+1) mod 2;
            b[v[w[j][e]],u[w[j][e]]]:=(b[v[w[j][e]],u[w[j][e]]]+1) mod 2;
          end;
        for i:=1 to n do if (not used[i]) then if (a[x,i]) and (b[x,i]=1) then
          begin
            dfs(i,kk+1);
          end;
        for e:=1 to kol[j] do
          begin
            b[u[w[j][e]],v[w[j][e]]]:=(b[u[w[j][e]],v[w[j][e]]]+1) mod 2;
            b[v[w[j][e]],u[w[j][e]]]:=(b[v[w[j][e]],u[w[j][e]]]+1) mod 2;
          end;
      end;
    for i:=1 to n do if (not used[i]) then if (a[x,i]) and (b[x,i]=1) then
      begin
        dfs(i,kk+1);
      end;
    used[x]:=false;
  end;

procedure init;
var i,j:integer;
  begin
    reset(input,InputFile);
    rewrite(output,OutputFile);
    read(n,m);
    fillchar(a,sizeof(a),false);
    for i:=1 to m do
      begin
        read(u[i],v[i],c[i]);
        a[u[i],v[i]]:=true;
        a[v[i],u[i]]:=true;
        b[u[i],v[i]]:=c[i];
        b[v[i],u[i]]:=c[i];
      end;
    read(k);
    for i:=1 to k do
      begin
        read(r[i]);
        read(kol[i]);
        for j:=1 to kol[i] do read(w[i][j]);
      end;
    read(s,t);
    close(input);
  end;

procedure solve;
  begin
    q:=maxint;
    dfs(s,0);
  end;

procedure done;
  begin
    if (q=maxint) then write(-1) else write(q);
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.
