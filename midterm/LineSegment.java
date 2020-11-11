/*
 * QUESTION 2 Part-A
 */

public class LineSegment implements Comparable<LineSegment> {
    protected double x1, y1;
    protected double x2, y2;
    private double slope;

    public LineSegment() {
        this.x1 = 1;
        this.y1 = 1;
        this.x2 = 2;
        this.y2 = 2;
        this.slope = (this.y2 - this.y1) / (this.x2 - this.x1);
    }

    public LineSegment(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.slope = (this.y2 - this.y1) / (this.x2 - this.x1);
    }

    public double getSlope() {
        return this.slope;
    }

    @Override
    public int compareTo(LineSegment ls) {
        if (Math.abs(this.getSlope()) > Math.abs(ls.getSlope())) {
            return 1;
        } else if (Math.abs(this.getSlope()) == Math.abs(ls.getSlope())) {
            return 0;
        } else {
            return -1;
        }
    }

    public String toString() {
        return "(" + x1 + "," + y1 + "):(" + x2 + "," + y2 + ")";
    }
}
