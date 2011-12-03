#include <cstdio>

typedef unsigned long long gribovLong;
typedef unsigned int gribovInt;
typedef gribovInt** Matrix;

const int base = 11;
const int odd = 5;
const int even = 6;
const gribovInt MOD = 4294967143LL;
const int GROUP = 8, PGROUP = 1<<8;

Matrix oddPows[GROUP][PGROUP];
Matrix evenPows[GROUP][PGROUP];

Matrix oddmatrix, evenmatrix;
Matrix oddres, oddtemp;
Matrix evenres, eventemp;

void multiply(Matrix const &a, Matrix const &b, Matrix &res, int size) {
  for (int i = 0; i < size; i++) {
    gribovInt* aa = a[i];
    for (int j = i; j < size; j++) {
      gribovInt* bb = b[j];
      gribovLong sum = 0;
      for (int k = 0; k < size; k++) {
        sum += (gribovLong) aa[k] * (gribovLong) bb[k];
        sum %= MOD;
      }
      res[i][j] = (gribovInt)sum;
      res[j][i] = res[i][j];
    }
  }      
}

void multiply_odd(Matrix const &a, Matrix const &b, Matrix &res) {
  for (int i = 0; i < odd ; i++) {
    gribovInt* aa = a[i];
    for (int j = i; j < odd; j++) {
      if (i > odd - 1 - i) {
        res[i][j] = res[odd - 1 - i][odd - 1 - j];
        res[j][i] = res[i][j];
        continue;
      }
      gribovInt* bb = b[j];
      gribovInt sum = 0;
      gribovInt cur = 0;

      if (i == 2 && j == 2) {
        sum = (gribovLong) aa[2] * (gribovLong) bb[2] % MOD;
        for (int k = 0; k < 2; k++) {
          cur = (gribovLong) aa[k] * (gribovLong) bb[k] % MOD;
          sum += cur;
          if (cur > sum) sum -= MOD;
          sum += cur;
          if (cur > sum) sum -= MOD;
        }
        if (sum >= MOD) sum -= MOD;
        res[i][j] = (gribovInt)sum;
        res[j][i] = res[i][j];
        continue;

      }

      for (int k = 0; k < odd; k++) {
        cur = (gribovLong) aa[k] * (gribovLong) bb[k] % MOD;
        sum += cur;
        if (cur > sum) sum -= MOD;
      }
      if (sum >= MOD) sum -= MOD;
      res[i][j] = (gribovInt)sum;
      res[j][i] = res[i][j];
    }
  }      
}

void multiply_even(Matrix const &a, Matrix const &b, Matrix &res) {
  for (int i = 0; i < even; i++) {
    gribovInt* aa = a[i];
    for (int j = i; j < even; j++) {
      if (i > even - 1 - i) {
        res[i][j] = res[even - 1 - i][even - 1 - j];
        res[j][i] = res[i][j];
        continue;
      }
      gribovInt* bb = b[j];
      gribovInt sum = 0;
      gribovInt cur = 0;
      for (int k = 0; k < even; k++) {
        cur = (gribovLong) aa[k] * (gribovLong) bb[k] % MOD;
        sum += cur;
        if (cur > sum) sum -= MOD;
      }
      if (sum >= MOD) sum -= MOD;
      res[i][j] = (gribovInt)sum;
      res[j][i] = res[i][j];
    }
  }
}

Matrix make_matrix(int size) {
  gribovInt** res = new gribovInt*[size];
  for (int i = 0; i < size; i++) {
    res[i] = new gribovInt[size];
  }
  for (int i = 0; i < size; i++) {
    for (int j = 0; j < size; j++) {
      res[i][j] = i == j;
    }
  }
  return res;
}


void init() {
  oddmatrix = make_matrix(odd);
  evenmatrix = make_matrix(even);
  oddres = make_matrix(odd);
  oddtemp = make_matrix(odd);
  evenres = make_matrix(even);
  eventemp = make_matrix(even);
  Matrix temp1 = make_matrix(base);
  Matrix temp2 = make_matrix(base);
  for (int i = 0; i < base; i++) {
    for (int j = 0; j < base; j++) {
      if (i - j == 1 || j - i == 1) temp1[i][j] = 1;
      else temp1[i][j] = 0;
    }
  }
  multiply(temp1, temp1, temp2, base);
  for (int i = 0; i < odd; i++) {
    for (int j = 0; j < odd; j++) {
      oddmatrix[i][j] = temp2[2*i+1][2*j+1];
    }
  }
  for (int i = 0; i < even; i++) {
    for (int j = 0; j < even; j++) {
      evenmatrix[i][j] = temp2[2*i][2*j];
    }
  }

  oddPows[0][0] =  make_matrix(odd);
  evenPows[0][0] = make_matrix(even);
  for (int i = 1; i < PGROUP; i++) {
    Matrix m1 = make_matrix(odd);
    multiply_odd(oddPows[0][i-1], oddmatrix, m1);
    oddPows[0][i] = m1;
    m1 = make_matrix(even);
    multiply_even(evenPows[0][i-1], evenmatrix, m1);
    evenPows[0][i] = m1;
  }

  Matrix a = make_matrix(odd);
  multiply_odd(oddPows[0][PGROUP - 1], oddmatrix, a);
  Matrix b = make_matrix(even);
  multiply_even(evenPows[0][PGROUP - 1], evenmatrix, b);

  for (int i = 1; i < GROUP; i++) {
    oddPows[i][0] = oddPows[0][0];
    evenPows[i][0] = evenPows[0][0];
    for (int j = 1; j < PGROUP; j++) {
      Matrix m1 = make_matrix(odd);
      multiply_odd(oddPows[i-1][j], a, m1);
      oddPows[i][j] = m1;
      m1 = make_matrix(even);
      multiply_even(evenPows[i-1][j], b, m1);
      evenPows[i][j] = m1;
    }
  }


}

void pow_odd(gribovLong n, Matrix& res) {
  int k = n & (PGROUP - 1);
  for (int i = 0; i < odd; i++) {
    for (int j = 0; j < odd; j++) {
      res[i][j] = oddPows[0][k][i][j];
    }
  }

  for (int i = 1; i < GROUP; i++) {
    n >>= GROUP;
    if (n == 0) break;
    k = n & (PGROUP - 1);
    if (k == 0) continue;
    multiply_odd(res, oddPows[i][k], oddtemp);
    {
      Matrix tmp = res;
      res = oddtemp;
      oddtemp = tmp;
    }
  }

}

void pow_even(gribovLong n, Matrix& res) {
  int k = n & (PGROUP - 1);

  for (int i = 0; i < even; i++) {
    for (int j = 0; j < even; j++) {
      res[i][j] = evenPows[0][k][i][j];
    }
  }

  for (int i = 1; i < GROUP; i++) {
    n >>= GROUP;
    if (n == 0) break;
    k = n & (PGROUP - 1);
    if (k == 0) continue;
    multiply_even(res, evenPows[i][k], eventemp);
    {
      Matrix tmp = res;
      res = eventemp;
      eventemp = tmp;
    }
  }

}

gribovInt get(gribovLong n) {
  if (n & 1) 
    return 0;
  //if (n == 18446744073709551614ULL) {
  //  return 1980637122;
  //}
  gribovLong ret = 0;
  pow_odd(n/2, oddres);
  for (int i = 0; i < odd; i++) ret += oddres[i][i];
  pow_even(n/2, evenres);
  for (int i = 1; i < even; i++) ret += evenres[i][i];
  ret %= MOD;
  return (gribovInt)ret;
}

int main() {
#ifndef ONLINE_JUDGE
  freopen("in.txt", "r", stdin);
//  long long start = GetTickCount();
  freopen("out.txt", "w", stdout);
#endif
  init();
  int tc;
  scanf("%d",&tc);
  gribovLong n;
  for (int i = 0; i < tc; i++) {
    scanf("%llu", &n);
//    gribovLong res = get(n);
    printf("%u\n", get(n));
  }
//  printf("%lld\n", GetTickCount() - start);

  return 0;
}