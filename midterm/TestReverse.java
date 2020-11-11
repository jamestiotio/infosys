/*
 * QUESTION 3 Part-A
 * 
 * In Vocareum:
 * - Click BUILD to compile
 * - RUN to execute the main function
 * - SUBMIT to submit your answer
 */

public class TestReverse {
    public static void main(String[] args) {
        String str0 = "man ate fish";
        assert reverse(str0).equals("fish ate man");
    }

    public static String reverse(String str) {
        int id = str.indexOf(' ');

        // Base case
        if (id == -1) {
            return str;
        }

        // Recursive case
        return reverse(str.substring(id + 1)) + ' ' + str.substring(0, id);
    }
}
