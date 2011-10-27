Const Eps=1e-7;
Var I,K,N:Longint;
    A:Array[1..1000,1..2] Of Real;
    Mi,Ma:Array[1..1000] Of Real;
    B:Array[1..1000] Of Boolean;
    k1,x:Real;
    BB:Boolean;

Procedure Solve;
var i,j:integer;
Begin
   For I:=1 To N Do
    Begin
      Mi[I]:=1;
      Ma[I]:=1e+9;
    End;
   For I:=1 To N Do
    For J:=1 To N Do
     If I<>J Then
      Begin
        k1:=A[I,1]-A[J,1];
        If k1>Eps Then
         Begin
           k1:=(A[J,2]-A[I,2])/k1;
           If Mi[I]<k1 Then
            Mi[I]:=k1;
         End;
      End;
   For I:=1 To N Do
    If A[I,1]*Mi[I]<100+Eps Then
     Begin
       BB:=True;
       x:=A[I,1]*Mi[I];
       For J:=1 To N Do
        If (A[J,1]*Mi[I]>100+Eps) Or (X+A[I,2]+Eps<A[J,1]*Mi[I]+A[J,2]) Then
         Begin
           BB:=False;
           Break;
         End;
       If BB Then
        B[I]:=True;
     End;
End;

Begin
  Assign(input,'input.txt');
  Reset(input);
  Assign(output,'output.txt');
  ReWrite(output);
   Read(N);
   FillChar(B,SizeOf(B),False);
   For I:=1 To N Do
    Read(A[I,1],A[I,2]);
   Solve;
   For I:=1 To N Do
    Begin
      x:=A[I,1];
      A[I,1]:=A[I,2];
      A[I,2]:=x;
    End;
   Solve;
   K:=0;
   For I:=1 To N Do
    If B[I] Then
     Inc(K);
   WriteLn(K);
   For I:=1 To N Do
    If B[I] Then
     Write(I,' ');
  Close(input);
  Close(output);
End.
 
