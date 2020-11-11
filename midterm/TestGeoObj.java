/*
 * QUESTION 1 For Testing
 * 
 * In Vocareum:
 * - Click BUILD to compile
 * - RUN to execute the main function
 * - SUBMIT to submit your answer
 */

public class TestGeoObj {
    public static void main(String[] args) {
        Circle c1 = new Circle();
        assert c1.getArea() == 3.141592653589793;

        Circle c2 = new Circle(0.5, -0.5, 4);
        assert c2.getPerimeter() == 25.132741228718345;

        Rect r1 = new Rect();
        assert r1.getArea() == 1.0;

        r1 = new Rect(-4, 4, -3, 2);
        assert r1.getPerimeter() == 6.0;

        Rect r2;
        Circle cN;

        r2 = new Rect(-4, 4, -3, 2);
        cN = new Circle(-3, 2, 1);
        assert Overlap.overlap(cN, r2);

        r2 = new Rect(-4, 4, -3, 2);
        cN = new Circle(8, 9, 1);
        assert !Overlap.overlap(cN, r2);
    }
}
