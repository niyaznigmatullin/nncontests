{$APPTYPE CONSOLE}

uses
  SysUtils, Math;


type
  int = longint;
  cube = array[1..6] of int;
  perm = array[1..6] of int;

var
  c1, c2 : cube;
  i : int;
  qw : array[1..6] of perm = ((1, 2, 3, 4, 5, 6),
                                     (1, 2, 3, 4, 5, 6),
                                     (1, 2, 3, 4, 5, 6),
                                     (1, 2, 3, 4, 5, 6),
                                     (1, 2, 3, 4, 5, 6),
                                     (1, 2, 3, 4, 5, 6));
  perms : array[1..720] of perm;
  pcnt : int;
  w : array[1..6] of boolean;
  cur : perm;

  yescnt : int = 0;
  ok : boolean;

procedure gen(pos : int);
var
  i : int;
begin
  if pos = 6 then
  begin
    inc(pcnt);
    perms[pcnt] := cur;
    exit;
  end;
  for i := 1 to 6 do
  begin
    if not w[i] then
    begin
      cur[pos + 1] := i;
      w[i] := true;
      gen(pos + 1);
      w[i] := false;
    end;
  end;
end;

function apply(c : cube; p : perm) : cube;
var
  i : int;
begin
  fillchar(result, sizeof(result), 0);
  for i := 1 to 6 do
    begin
      result[p[i]] := c[i];
    end;
end;

procedure shift(var a1, a2, a3, a4 : int);
var
  t : int;
begin
  t := a4;
  a4 := a3;
  a3 := a2;
  a2 := a1;
  a1 := t;
end;

procedure go(n : int; c : cube);
var
  i : int;
begin
  if n < 0 then exit;
  if ok then exit;
  ok := true;
  for i := 1 to 6 do
    begin
      if c[i] <> c2[i] then ok := false;
    end;
  if ok then
    begin
      inc(yesCnt);
      writeln('YES');
      exit;
    end;
  for i := 1 to 6 do
    begin
      go(n - 1, apply(c, qw[i]));
    end;
end;


begin
  fillchar(w, sizeof(w), false);
  gen(0);

  reset(input, 'input.txt');
  rewrite(output, 'output.txt');
  for i := 1 to 6 do
    read(c1[i]);
  for i := 1 to 6 do
    read(c2[i]);

  shift(qw[1][1], qw[1][6], qw[1][2], qw[1][5]);
  shift(qw[2][5], qw[2][2], qw[2][6], qw[2][1]);

  shift(qw[3][1], qw[3][3], qw[3][2], qw[3][4]);
  shift(qw[4][4], qw[4][2], qw[4][3], qw[4][1]);

  shift(qw[5][3], qw[5][6], qw[5][4], qw[5][5]);
  shift(qw[6][5], qw[6][4], qw[6][6], qw[6][3]);
  ok := false;
  go(6, c1);
  if not ok then
    writeln('NO');
end.
