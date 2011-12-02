package Maths;
import static java.util.Arrays.*;

import java.util.*;

public class Polynoms {
	static class Monom {
		int coef;
		int[] pows;

		public Monom(int coef, int[] pows) {
			this.coef = coef;
			this.pows = pows;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + Arrays.hashCode(pows);
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Monom other = (Monom) obj;
			if (!Arrays.equals(pows, other.pows))
				return false;
			return true;
		}

		@Override
		public String toString() {
			if (coef == 0) {
				return "0";
			}
			boolean zero = true;
			for (int i = 0; i < 26; i++) {
				if (pows[i] != 0) {
					zero = false;
					break;
				}
			}
			if (zero) {
				return "" + coef;
			}
			StringBuilder sb = new StringBuilder();
			if (coef == -1) {
				sb.append('-');
			} else if (coef != 1) {
				sb.append(coef).append('*');
			}
			for (int i = 0; i < 26; i++) {
				if (pows[i] == 0) {
					continue;
				}
				sb.append((char) (i + 'a'));
				if (pows[i] != 1) {
					sb.append('^').append(pows[i]);
				}
				sb.append('*');
			}
			sb.setLength(sb.length() - 1);
			return sb.toString();
		}

		public long evaluate(int[] vals) {
			long ans = coef;
			for (int i = 0; i < 26; i++) {
				for (int j = 0; j < pows[i]; j++) {
					ans *= vals[i];
				}
			}
			return ans;
		}
	}

	static class Polynom {
		Monom[] a;

		public Polynom(Monom[] a) {
			this.a = a;
		}

		public Polynom(Collection<Monom> a) {
			this.a = a.toArray(new Monom[a.size()]);
		}

		Polynom multiply(Polynom p) {
			List<Monom> list = new ArrayList<Monom>();
			int[] e = new int[26];
			for (Monom m1 : a) {
				for (Monom m2 : p.a) {
					fill(e, 0);
					for (int i = 0; i < 26; i++) {
						e[i] = m1.pows[i] + m2.pows[i];
					}
					boolean found = false;
					for (Monom m3 : list) {
						if (Arrays.equals(m3.pows, e)) {
							m3.coef += m1.coef * m2.coef;
							found = true;
							break;
						}
					}
					if (!found) {
						list.add(new Monom(m1.coef * m2.coef, e.clone()));
					}
				}
			}
			List<Monom> ret = new ArrayList<Monom>();
			for (Monom c : list) {
				if (c.coef != 0) {
					ret.add(c);
				}
			}
			return new Polynom(ret);
		}

		@Override
		public String toString() {
			sort(a, new Comparator<Monom>() {
				@Override
				public int compare(Monom o1, Monom o2) {
					int sum1 = 0;
					int sum2 = 0;
					for (int i = 0; i < 26; i++) {
						sum1 += o1.pows[i];
						sum2 += o2.pows[i];
					}
					if (sum1 != sum2) {
						return sum2 - sum1;
					}
					for (int i = 0; i < 26; i++) {
						int d = o1.pows[i] - o2.pows[i];
						if (d != 0) {
							return -d;
						}
					}
					return 0;
				}
			});
			if (a.length == 0) {
				return "0";
			}
			StringBuilder ret = new StringBuilder();
			for (int i = 0; i < a.length; i++) {
				if (i > 0) {
					ret.append(' ').append(a[i].coef < 0 ? '-' : '+')
							.append(' ');
				} else {
					if (a[i].coef < 0) {
						ret.append('-');
					}
				}
				String e = a[i].toString();
				if (a[i].coef < 0) {
					e = e.substring(1);
				}
				ret.append(e);
			}
			return ret.toString();
		}

		public long evaluate(int[] vals) {
			long ret = 0;
			for (Monom m : a) {
				ret += m.evaluate(vals);
			}
			return ret;
		}
	}

	static Monom readMonom(String s) {
		int coef = 1;
		if (Character.isDigit(s.charAt(0))) {
			int p = s.indexOf('*');
			if (p < 0) {
				return new Monom(Integer.parseInt(s), new int[26]);
			}
			coef = Integer.parseInt(s.substring(0, p));
			s = s.substring(p + 1);
		}
		int[] pows = new int[26];
		while (!s.isEmpty()) {
			int c = s.charAt(0) - 'a';
			int p = s.indexOf('*');
			if (p < 0) {
				p = s.length();
			}
			String d = s.substring(0, p);
			int add = 1;
			if (d.length() > 1) {
				d = d.substring(2);
				add = Integer.parseInt(d);
			}
			pows[c] += add;
			if (p == s.length()) {
				break;
			}
			s = s.substring(p + 1);
		}
		return new Monom(coef, pows);
	}

	static Polynom readPolynom(String s) {
		s = s.replace(" ", "");
		List<Monom> list = new ArrayList<Monom>();
		while (!s.isEmpty()) {
			boolean neg = false;
			if (s.charAt(0) == '-') {
				s = s.substring(1);
				neg = true;
			}
			int pos1 = s.indexOf('+');
			int pos2 = s.indexOf('-');
			if (pos1 < 0) {
				pos1 = Integer.MAX_VALUE;
			}
			if (pos2 < 0) {
				pos2 = Integer.MAX_VALUE;
			}
			String d = null;
			if (pos1 < pos2) {
				d = s.substring(0, pos1);
				s = s.substring(pos1 + 1);
			} else if (pos2 == Integer.MAX_VALUE) {
				d = s;
				s = "";
			} else {
				d = s.substring(0, pos2);
				s = s.substring(pos2);
			}
			Monom m = readMonom(d);
			if (neg) {
				m.coef = -m.coef;
			}
			list.add(m);
		}
		return new Polynom(list);
	}
}
