/*
 * QUESTION 1 Part-A
 */

public class Rect extends GeometricObject {
    private double x1;
    private double x2;
    private double y1;
    private double y2;
    private double x;
    private double y;

    public Rect() {
        this.x1 = 0;
        this.y1 = 1;
        this.x2 = 1;
        this.y2 = 0;
        this.x = (this.x1 + this.x2) / 2;
        this.y = (this.y1 + this.y2) / 2;
    }

    public Rect(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x = (this.x1 + this.x2) / 2;
        this.y = (this.y1 + this.y2) / 2;
    }

    public double getX1() {
        return this.x1;
    }

    public double getX2() {
        return this.x2;
    }

    public double getY1() {
        return this.y1;
    }

    public double getY2() {
        return this.y2;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getWidth() {
        return Math.abs(this.x2 - this.x1);
    }

    public double getHeight() {
        return Math.abs(this.y1 - this.y2);
    }

    @Override
    public double getPerimeter() {
        return 2 * (this.getWidth() + this.getHeight());
    }

    @Override
    public double getArea() {
        return this.getWidth() * this.getHeight();
    }
}
