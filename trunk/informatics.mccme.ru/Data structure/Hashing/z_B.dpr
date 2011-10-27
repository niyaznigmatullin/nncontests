program z_B;
{$APPTYPE CONSOLE}
uses
  SysUtils;

const pr=95989;
      p1=17;
      p2=19;

var i,cnodes,h1,h2:integer;
    free,hash,prev,next:array[0..1000001] of integer;
    c:char;
    s:string;
    head:array[0..100000] of integer;

function count_hash(p:integer):integer;
var ret,i:integer;
  begin
    ret:=0;
    for i:=1 to length(s) do
      begin
        ret:=(ret*p+ord(s[i])) mod pr;
      end;
    result:=ret;
  end;

function hashtab_found(h1,h2:integer):boolean;
var node:integer;
  begin
    result:=false;
    node:=head[h1];
    while (node<>0) do
      begin
        result:=result or (hash[node]=h2);
        node:=next[node];
      end;
  end;

procedure hashtab_delete(h1,h2:integer);
var node:integer;
  begin
    node:=head[h1];
    while (node<>0) do
      begin
        if (hash[node]=h2) then
          begin
            if (prev[node]=0) then head[h1]:=next[node] else next[prev[node]]:=next[node];
            if (next[node]<>0) then prev[next[node]]:=prev[node];
            free[cnodes]:=node;
            dec(cnodes);
            exit;
          end;
        node:=next[node];
      end;
  end;

procedure hashtab_insert(h1,h2:integer);
var node:integer;
  begin
    inc(cnodes);
    node:=free[cnodes];
    next[node]:=head[h1];
    prev[node]:=0;
    prev[next[node]]:=node;
    head[h1]:=node;
    hash[node]:=h2;
  end;

begin
  cnodes:=0;
  for i:=1 to 1000000 do free[i]:=i;
  while (true) do
    begin
      read(c);
      if (c='#') then break;
      readln(s);
      s:=trim(s);
      h1:=count_hash(p1);
      h2:=count_hash(p2);
      if (c='+') then hashtab_insert(h1,h2) else
      if (c='-') then hashtab_delete(h1,h2) else
      if (hashtab_found(h1,h2)) then writeln('YES') else writeln('NO');
    end;
  readln;
end.
