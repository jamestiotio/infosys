/*
 * QUESTION 2 Part-A For Testing
 * 
 * In Vocareum:
 * - Click BUILD to compile
 * - RUN to execute the main function
 * - SUBMIT to submit your answer
 */

import java.util.Collections;
import java.util.LinkedList;

public class TestCompare {
    public static void main(String[] args) {
        LinkedList<LineSegment> s = new LinkedList<>();
        s.add(new LineSegment(0, 0, 1, 1));
        s.add(new LineSegment(2, 1, 1, 3));
        s.add(new LineSegment(0, 0, 5, 1));
        Collections.sort(s);
        assert s.toString()
                .equals("[(0.0,0.0):(5.0,1.0), (0.0,0.0):(1.0,1.0), (2.0,1.0):(1.0,3.0)]");
    }
}
