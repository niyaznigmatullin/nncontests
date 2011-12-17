import static java.lang.Math.abs;

import java.io.*;
import java.util.*;

public class A {

	static void solve() throws IOException {
		int tc = nextInt();
		test: for (int i = 1; i <= tc; i++) {
			String s = nextToken();
			int month = Integer.parseInt(s.substring(0, s.indexOf('/'))) - 1;
			s = s.substring(s.indexOf('/') + 1);
			int day = Integer.parseInt(s.substring(0, s.indexOf('/'))) - 1;
			s = s.substring(s.indexOf('/') + 1);
			int year = Integer.parseInt(s);
			Date start = new Date(year, month, day);
			s = nextToken();
			int monthTo = Integer.parseInt(s.substring(0, s.indexOf('/'))) - 1;
			int dayTo = Integer.parseInt(s.substring(s.indexOf('/') + 1)) - 1;
			out.print(i + " ");
			for (int yearTo = year - 1; yearTo <= year + 1; yearTo++) {
				Date finish = new Date(yearTo, monthTo, dayTo);
				int d = distance(start, finish);
				if (abs(d) > 7) {
					continue;
				}
				if (d < 0) {
					out.println(finish + " IS " + -d + " " + days(-d)
							+ " PRIOR");
				}
				if (d > 0) {
					out.println(finish + " IS " + d + " " + days(d) + " AFTER");
				}
				if (d == 0) {
					out.println("SAME DAY");
				}
				continue test;
			}
			out.println("OUT OF RANGE");
		}
	}

	static String days(int days) {
		return days == 1 ? "DAY" : "DAYS";
	}

	static int distance(Date d1, Date d2) {
		boolean inv = false;
		if (d2.compareTo(d1) < 0) {
			Date t = d2;
			d2 = d1;
			d1 = t;
			inv = true;
		}
		int dist = 0;
		while (d1.compareTo(d2) != 0 && dist <= 7) {
			d1 = d1.plusOne();
			++dist;
		}
		return inv ? -dist : dist;
	}

	static class Date implements Comparable<Date> {
		final int year, month, day;
		static int[] months = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		static int[] months_leap = { 31, 29, 31, 30, 31, 30, 31, 31, 30, 31,
				30, 31 };

		private Date(int year, int month, int day) {
			this.year = year;
			this.month = month;
			this.day = day;
		}

		static boolean isLeap(int year) {
			return year % 4 != 0 ? false : year % 100 != 0 ? true
					: year % 400 == 0 ? true : false;
		}

		static int days(int year, int month) {
			return isLeap(year) ? months_leap[month] : months[month];
		}

		Date plusOne() {
			int y = year;
			int m = month;
			int d = day + 1;
			if (d == days(year, month)) {
				d = 0;
				m++;
				if (m == 12) {
					m = 0;
					y++;
				}
			}
			return new Date(y, m, d);
		}

		@Override
		public int compareTo(Date o) {
			if (year != o.year) {
				return year - o.year;
			}
			if (month != o.month) {
				return month - o.month;
			}
			return day - o.day;
		}

		public String toString() {
			return (month + 1) + "/" + (day + 1) + "/" + year;
		}

	}

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new FileReader("bank.in"));
		out = new PrintWriter(System.out);
		solve();
		out.close();
	}

	static BufferedReader br;
	static StringTokenizer st;
	static PrintWriter out;

	static String nextToken() throws IOException {
		while (st == null || !st.hasMoreTokens()) {
			String line = br.readLine();
			if (line == null) {
				return null;
			}
			st = new StringTokenizer(line);
		}
		return st.nextToken();
	}

	static int nextInt() throws IOException {
		return Integer.parseInt(nextToken());
	}

	static long nextLong() throws IOException {
		return Long.parseLong(nextToken());
	}

	static double nextDouble() throws IOException {
		return Double.parseDouble(nextToken());
	}
}