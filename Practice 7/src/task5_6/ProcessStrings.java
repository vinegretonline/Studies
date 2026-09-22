package task5_6;

public class ProcessStrings implements StringProcessable {

    public int countChars(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            count++;
        }
        return count;
    }

    public String oddPositions(String s) {
        String result = "";
        for (int i = 0; i < s.length(); i = i + 2) {
            result = result + s.charAt(i);
        }
        return result;
    }

    public String invert(String s) {
        String result = "";
        for (int i = s.length() - 1; i >= 0; i--) {
            result = result + s.charAt(i);
        }
        return result;
    }
}
