{
Московская городская олимпиада 2003/04
Задача "Еловая аллея"
Ильичёв Алексей
}

{$R+,Q+}
Const
  InputFile = 'input.txt';
  OutputFile = 'output.txt';
  MAXM = 100;
  MAXN = 100;

Var
  W, E : Array[1..MAXM] of Integer;
  X : Array[1..MAXN] of Integer;
  N, M, I, J, K, L : Integer;
  Res : Array[0..MAXN, 1..MAXN+1] of Integer;
  PI, PJ, T : Array[0..MAXN, 1..MAXN+1] of ShortInt;
  Max, MI, MJ : Integer;
  PC : Integer;
  PT, PP : Array[1..MAXN] of Integer;

Function FindPit(Y : LongInt) : Integer;
Var
  D, U, C : Integer;
Begin
  D := 1;
  U := N + 1;
  Repeat
    C := (D + U) div 2;
    If X[C] < Y Then D := C + 1;
    If X[C] >= Y Then U := C;
  Until D = U;
  FindPit := D;
End;

Procedure Plant(I, J, K : Integer);
Var
  NI : Integer;
Begin
  If (J > 0) and (X[J] > X[I] - LongInt(W[K])) Then Exit;
  NI := FindPit(X[I] + LongInt(E[K]));
  If Res[I, NI] < Res[J, I] + 1 Then Begin
    Res[I, NI] := Res[J, I] + 1;
    T[I, NI] := K;
    PI[I, NI] := I;
    PJ[I, NI] := J;
    If Res[I, NI] > Max Then Begin
      MI := NI;
      MJ := I;
      Max := Res[I, NI];
    End;
  End;
End;

Procedure AddPair(P, T : Integer);
Begin
  Inc(PC);
  PP[PC] := P;
  PT[PC] := T;
End;

Begin
  Assign(Input, InputFile);
  Reset(Input);
  Read(M);
  For I := 1 to M Do Read(W[I], E[I]);
  Read(N);
  For I := 1 to N Do Read(X[I]);
  Close(Input);

  FillChar(Res, SizeOf(Res), 0);
  FillChar(PI, SizeOf(PI), 0);
  FillChar(PJ, SizeOf(PJ), 0);
  FillChar(T, SizeOf(T), 0);

  For I := 1 to N Do
    For J := 0 to I - 1 Do Begin
      If (I > 1) And (Res[J, I - 1] > Res[J, I]) Then Begin
        Res[J, I] := Res[J, I - 1];
        PI[J, I] := PI[J, I - 1];
        PJ[J, I] := PJ[J, I - 1];
        T[J, I] := T[J, I - 1];
      End;
      For K := 1 to M Do Plant(I, J, K);
    End;

  PC := 0;
  I := MI;
  J := MJ;
  For K := 1 to Max Do Begin
    AddPair(J, T[J, I]);
    L := PI[J, I];
    J := PJ[J, I];
    I := L;
  End;

  Assign(Output, OutputFile);
  ReWrite(Output);
  WriteLn(Max);
  For I := 1 to Max Do WriteLn(PP[I], ' ', PT[I]);
  Close(Output);
End.
