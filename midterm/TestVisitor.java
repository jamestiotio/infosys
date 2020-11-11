/*
 * QUESTION 2 Part-B
 * 
 * In Vocareum:
 * - Click BUILD to compile
 * - RUN to execute the main function
 * - SUBMIT to submit your answer
 */

import java.util.ArrayList;

public class TestVisitor {
    public static void main(String[] args) {
        ArrayList<Visitable> items = new ArrayList<>();

        AreaVisitor areaVisitor = new AreaVisitor();
        PerimeterVisitor perimeterVisitor = new PerimeterVisitor();

        items.add(new Circle(1));
        items.add(new Square(2));

        for (Visitable item : items) {
            item.accept(areaVisitor);
            item.accept(perimeterVisitor);
        }

        assert Math.ceil(areaVisitor.getTotal()) == 8.0;
        assert Math.ceil(perimeterVisitor.getTotal()) == 15.0;

        items = new ArrayList<>();
        items.add(new Rectangle(2, 4));
        items.add(new Circle(2));
        items.add(new Circle(5));
        items.add(new Square(4));

        for (Visitable item : items) {
            item.accept(areaVisitor);
            item.accept(perimeterVisitor);
        }

        assert Math.ceil(areaVisitor.getTotal()) == 123.0;
        assert Math.ceil(perimeterVisitor.getTotal()) == 87.0;
    }
}


interface Visitable {
    public void accept(Visitor v);
}


interface Visitor {
    public void visit(Rectangle r);

    public void visit(Circle c);

    public void visit(Square s);
}


class AreaVisitor implements Visitor {
    private double total = 0;

    public double getTotal() {
        return total;
    }

    @Override
    public void visit(Rectangle r) {
        total += r.getArea();
    }

    @Override
    public void visit(Circle c) {
        total += c.getArea();
    }

    @Override
    public void visit(Square s) {
        total += s.getArea();
    }
}


class PerimeterVisitor implements Visitor {
    private double total = 0;

    public double getTotal() {
        return total;
    }

    @Override
    public void visit(Rectangle r) {
        total += r.getPerimeter();
    }

    @Override
    public void visit(Circle c) {
        total += c.getPerimeter();
    }

    @Override
    public void visit(Square s) {
        total += s.getPerimeter();
    }
}


class Circle implements Visitable {
    private double radius;

    Circle(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public double getPerimeter() {
        return 2 * Math.PI * this.radius;
    }

    public double getArea() {
        return Math.PI * Math.pow(this.radius, 2);
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}


class Rectangle implements Visitable {
    private double width;
    private double height;

    Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getPerimeter() {
        return 2 * (this.getWidth() + this.getHeight());
    }

    public double getArea() {
        return this.getWidth() * this.getHeight();
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}


class Square implements Visitable {
    private double lengthOfSide;

    Square(double lengthOfSide) {
        this.lengthOfSide = lengthOfSide;
    }

    public double getLengthOfSide() {
        return lengthOfSide;
    }

    public double getPerimeter() {
        return 4 * this.getLengthOfSide();
    }

    public double getArea() {
        return Math.pow(this.getLengthOfSide(), 2);
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
