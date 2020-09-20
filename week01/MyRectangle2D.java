public class MyRectangle2D {

  private double x;
  private double y;

  private double width;
  private double height;

  MyRectangle2D() {
    this.x = 0;
    this.y = 0;
    this.width = 1;
    this.height = 1;
  }

  MyRectangle2D(double sX, double sY, double sWidth, double sHeight) {
    this.x = sX;
    this.y = sY;
    this.width = sWidth;
    this.height = sHeight;
  }

  public double getX() {
    return this.x;
  }

  public double getY() {
    return this.y;
  }

  public double getWidth() {
    return this.width;
  }

  public double getHeight() {
    return this.height;
  }

  public void setX(double newX) {
    this.x = newX;
  }

  public void setY(double newY) {
    this.y = newY;
  }

  public void setWidth(double newWidth) {
    this.width = newWidth;
  }

  public void setHeight(double newHeight) {
    this.height = newHeight;
  }

  public double getArea() {
    return this.height * this.width;
  }

  public double getPerimeter() {
    return 2 * (this.height + this.width);
  }

  public boolean contains(double cX, double cY) {
    return (((cX > this.x - 0.5*this.width) && (cX < this.x + 0.5*this.width)) && ((cY > this.y - 0.5*this.height) && (cY < this.y + 0.5*this.height)));
  }

  public boolean contains(MyRectangle2D r) {
    return ((((r.x - 0.5*r.width) > (this.x - 0.5*this.width)) && ((r.x + 0.5*r.width) < (this.x + 0.5*this.width))) && (((r.y - 0.5*r.height) > (this.y - 0.5*this.height)) && ((r.y + 0.5*r.height) < (this.y + 0.5*this.height))));
  }

  public boolean overlaps(MyRectangle2D r) {
    return !(((r.x - 0.5*r.width) > (this.x + 0.5*this.width)) || ((r.x + 0.5*r.width) < (this.x - 0.5*this.width)) || ((r.y - 0.5*r.height) > (this.y + 0.5*this.height)) || ((r.y + 0.5*r.height) < (this.y - 0.5*this.height)));
  }

}