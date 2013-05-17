package coding;

public class BirthNumbersValidator {
    public String[] validate(String[] test) {
        String[] answer = new String[test.length];
        for (int i = 0; i < test.length; i++) {
            answer[i] = isValid(test[i]) ? "YES" : "NO";
        }
        return answer;
    }

    static boolean isValid(String s) {
        int year = Integer.parseInt(s.substring(0, 2));
        if (year < 7) year += 2000; else year += 1900;
        int month = Integer.parseInt(s.substring(2, 4));
        if (month >= 50) {
            month -= 50;
        }
        if (month < 1 || month > 12) {
            return false;
        }
        int day = Integer.parseInt(s.substring(4, 6));
        int[] dm = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (isLeap(year)) {
            dm[1]++;
        }
        if (day < 1 || day > dm[month - 1]) {
            return false;
        }
        if (Long.parseLong(s) % 11 != 0) {
            return false;
        }
        return true;
    }
    static boolean isLeap(int y) {
        return y % 400 == 0 || (y % 100 != 0 && y % 4 == 0);
    }
}
