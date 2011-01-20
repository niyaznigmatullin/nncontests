{$A8,B-,C+,D+,E-,F-,G+,H+,I+,J-,K-,L+,M-,N+,O+,P+,Q+,R+,S+,T-,U-,V+,W-,X+,Y+,Z1}
//НИУИОТ ГОУВПО СПбГУ ИТМО 2009

program newqwreougkjgaksjfghkjasfd;

{$APPTYPE CONSOLE}

uses
  SysUtils, Math;

type Edge = record
       vertex:integer;
       val:extended;
       isKnown:boolean;
     end;

const sorSteps = 15; //кол-во шагов, которое выполнится при решении системы уравнений, вне зависимости от значений EPS
      solveEquationSteps = 100000; //кол-во шагов по времени, которое выполнится(изначально отключен:)) 

var headRes, headCap: array of integer;
    nextRes, nextCap: array of integer;
    valRes, valCap : array of Edge;
    newVoltage, rVoltage, vk, allVoltage, voltage : array of extended;
    epsCurrent, h, w:extended;
    steps, dig, printSteps, n0, n, mr, mr0, mc, mc0:integer;
    cNodesRes, cNodesCap:integer;
    rungeDif, rungeEps, curTime, maxDiscr, maxChange:extended;

procedure OpenFiles();
  begin
    assignFile(input,'input.txt');
    assignFile(output,'output.txt');
    reset(input);
    rewrite(output);
  end;

procedure addRes(x, y:integer; z:extended; isK:boolean);
  begin
    inc(cNodesRes);
    nextRes[cNodesRes] := headRes[x];
    headRes[x] := cNodesRes;
    with valRes[cNodesRes] do
      begin
        val := z;
        vertex := y;
        isKnown := isK;
      end;
  end;

procedure addCap(x, y:integer; z:extended; isK:boolean);
  begin
    inc(cNodesCap);
    nextCap[cNodesCap] := headCap[x];
    headCap[x] := cNodesCap;
    with valCap[cNodesCap] do
      begin
        val := z;
        vertex := y;
        isKnown := isK;
      end;
  end;

procedure ReadInformation();  //считывание данных
var x, y, i : integer; z:extended;
  begin
    read(n0, n, mr, mr0, mc, mc0);
    setLength(vk, n0 + 1);
    setLength(headRes, n + 1);
    setLength(headCap, n + 1);
    for i := 1 to n do
      begin
        headRes[i] := 0;
        headCap[i] := 0;
      end;
    setLength(nextRes, mr * 2 + mr0 + 1);
    setLength(nextCap, mc * 2 + mc0 + 1);
    setLength(valRes, mr * 2 + mr0 + 1);
    setLength(valCap, mc * 2 + mc0 + 1);
    setLength(allVoltage, n + 1);
    setLength(voltage, n + 1);
    setLength(rVoltage, n + 1);
    setLength(newVoltage, n + 1);
    for i := 1 to n0 do
      begin
        read(vk[i]);
      end;
    for i := 1 to mr do
      begin
        read(x, y, z);
        addRes(x, y, z, false);
        addRes(y, x, z, false);
      end;
    for i := 1 to mr0 do
      begin
        read(x, y, z);
        addRes(x, y, z, true);
      end;
    for i := 1 to mc do
      begin
        read(x, y, z);
        addCap(x, y, z, false);
        addCap(y, x, z, false);
      end;
    for i := 1 to mc0 do
      begin
        read(x, y, z);
        addCap(x, y, z, true);
      end;
    read(h);
    read(printSteps);
    read(epsCurrent);
    read(maxChange);
    read(maxDiscr);
    read(rungeEps);
    read(w);
    read(dig);
  end;

function caps(e:integer; v:integer):boolean;
  begin
    result := e = 0;
{    while (e <> 0) do
      begin
        if (valCap[e].vertex <> v) then
          begin
            result := false;
            exit;
          end;
        e := nextCap[e];
      end;
    result := true;}
  end;

function doStep(var voltage:array of extended):integer;

procedure getVertexPotentials(const v:Edge; var phi1, phi2:extended);
  begin
    if (v.isKnown) then
      begin
        phi1 := vk[v.vertex];
        phi2 := vk[v.vertex];
      end else
      begin
        phi1 := voltage[v.vertex];
        phi2 := allVoltage[v.vertex];
      end;
  end;

procedure addCoefficientsOfRess(var a, r:extended; const v:Edge; volt:extended);
var phi1, phi2:extended;
  begin
    getVertexPotentials(v, phi1, phi2);
    a := a + 1 / (2 * v.val);
    r := r + (-phi1 + volt - phi2) / (2 * v.val);
  end;

procedure addCoefficientsOfCaps(var a, r:extended; const v:Edge; volt:extended);
var phi1, phi2:extended;
  begin
    getVertexPotentials(v, phi1, phi2);
    a := a + v.val / h;
    r := r + v.val * (-phi1 - volt + phi2) / h;
  end;

function countCurrentForRess(const v:Edge; k:integer):extended;
var phi1, phi2:extended;
  begin
    getVertexPotentials(v, phi1, phi2);
    if (caps(headCap[v.vertex], k)) then
      begin
        result := (voltage[k] - phi1) / v.val;
      end else
      begin
        result := ((voltage[k] - phi1) + (allVoltage[k] - phi2)) / (2 * v.val);
      end;
  end;

function countCurrentForCaps(const v:Edge; k:integer):extended;
var phi1, phi2:extended;
  begin
    getVertexPotentials(v, phi1, phi2);
    result := v.val * ((voltage[k] - phi1) - (allVoltage[k] - phi2)) / h;
  end;


var a, c, r, approximatedValue, discr:extended;
    i, j, e:integer;

  begin
    j := 0;
    c := 1e1000;
    discr := 1e1000;
    while (j <= sorSteps) or (c > epsCurrent) or (discr > maxDiscr) do
      begin
        inc(j);
        discr := 0;
        for i := 1 to n do
          begin
            a := 0;
            r := 0;
            e := headRes[i];
            while (e <> 0) do
              begin
                if (not caps(headCap[valRes[e].vertex], i)) then
                  begin
                    addCoefficientsOfRess(a, r, valRes[e], allVoltage[i]);
                  end else
                  begin
                    a := a + 1 / valRes[e].val;
                    if (valRes[e].isKnown) then
                      r := r + (-vk[valRes[e].vertex]) / valRes[e].val else
                      r := r + (-voltage[valRes[e].vertex]) / valRes[e].val;
                  end;
                e := nextRes[e];
              end;
            e := headCap[i];
            while (e <> 0) do
              begin
                addCoefficientsOfCaps(a, r, valCap[e], allVoltage[i]);
                e := nextCap[e];
              end;
            approximatedValue := -r / a;
            discr := Math.max(discr, abs(w * (approximatedValue) + (1 - w) * voltage[i] - voltage[i]));
            voltage[i] := w * (approximatedValue) + (1 - w) * voltage[i];
          end;
        c := 0;
        for i := 1 to n do
          begin
            e := headRes[i];
            while (e <> 0) do
              begin
                c := c + countCurrentForRess(valRes[e], i);
                e := nextRes[e];
              end;
            e := headCap[i];
            while (e <> 0) do
              begin
                c := c + countCurrentForCaps(valCap[e], i);
                e := nextCap[e];
              end;
          end;
      end;
    result := j;
  end;

function cntChange():extended;
var i: integer;
  begin
    result := 0;
    for i := 1 to n do
      begin
        result := Math.max(result, abs(allVoltage[i] - voltage[i]));
      end;
  end;

function solveDiffEquation():extended;
var i, j:integer;
    iter, change:extended;

  begin
    j := 0;
    curTime := 0;
    change := 1e20;
    iter := 0;
    while (change > maxChange) do
      begin
        inc(j);
        if (j = solveEquationSteps) then
          begin
//            exit;                   //Условие против бесконечного выполнения цикла
          end;
        iter := iter + doStep(voltage);
        change := cntChange();
        for i := 1 to n do
          begin
            allVoltage[i] := voltage[i];
          end;
        curTime := curTime + h;
        if (j mod printSteps = 0) then //Print potentials ?? to check what's printing
          begin                        //So, now it's printing the right way
            write('Step #', j, '(time = ', curTime:0:dig, ') ');
            for i := 1 to n do
              begin
                write(allVoltage[i]:0:dig, ' ');
              end;
            writeln;
          end;
      end;
    result := iter / j;
  end;

function process():extended;
var p, averageIterations:extended;
    i:integer;
  begin
    curTime := 0;
    steps := 0;
    p := 0;
    for i := 1 to n do
      begin
        voltage[i] := 0;
        rVoltage[i] := 0;
        allVoltage[i] := 0;
      end;
    result := 0;
    averageIterations := solveDiffEquation();
    writeln('The average of the number of iterations in SOR on each step of time with the coefficient of relaxation w = ', w:0:3, ' is ', averageIterations:0:dig);
    writeln('The timeStep, which was counted by the rule of Runge is ', h:0:dig);
    writeln('The imprecission counted by the rule of Runge is ', rungeDif:0:dig);
  end;

procedure solveDiffEquationForRunge(var r1:array of extended);
var i, j:integer;
    iter, change:extended;
  begin
    j := 0;
    curTime := 0;
    change := 1e20;
    iter := 0;
    while (change > maxChange) do
      begin
        inc(j);
        if (j = solveEquationSteps) then
          begin
//            exit;                   //Условие против бесконечного выполнения цикла
          end;
        doStep(voltage);
        change := cntChange();
        for i := 1 to n do
          begin
            allVoltage[i] := voltage[i];
          end;
        curTime := curTime + h;
      end;
    for i := 1 to n do
      begin
        r1[i] := allVoltage[i];
      end;
  end;

procedure cntRunge();
var r1, r2:array of extended;
    dif:extended;
    i:integer;
  begin
    setLength(r1, n + 1);
    setLength(r2, n + 1);
    while (true) do
      begin
        solveDiffEquationForRunge(r1);
        h := h * 0.5;
        solveDiffEquationForRunge(r2);
        dif := 0;
        for i := 1 to n do
          begin
            dif := Math.max(dif, abs(r1[i] - r2[i]));
          end;
        rungeDif := dif;
        if (dif < rungeEps) then
          begin
            break;
          end;
      end;
  end;

procedure Solve();
  begin
    cntRunge();
    process();
  end;

procedure CloseFiles();
  begin
    close(input);
    close(output);
  end;

begin
  OpenFiles();
  ReadInformation();
  Solve();
  CloseFiles();
end.

