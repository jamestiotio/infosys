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
    return getHeight() * getWidth();
  }

  public double getPerimeter() {
    return 2 * (getHeight() + getWidth());
  }

  public boolean contains(double cX, double cY) {
    return (((cX > getX() - 0.5*getWidth()) && (cX < getX() + 0.5*getWidth())) && ((cY > getY() - 0.5*getHeight()) && (cY < getY() + 0.5*getHeight())));
  }

  public boolean contains(MyRectangle2D r) {
    return ((((r.getX() - 0.5*r.getWidth()) > (getX() - 0.5*getWidth())) && ((r.getX() + 0.5*r.getWidth()) < (getX() + 0.5*getWidth()))) && (((r.getY() - 0.5*r.getHeight()) > (getY() - 0.5*getHeight())) && ((r.getY() + 0.5*r.getHeight()) < (getY() + 0.5*getHeight()))));
  }

  public boolean overlaps(MyRectangle2D r) {
    return !(((r.getX() - 0.5*r.getWidth()) > (getX() + 0.5*getWidth())) || ((r.getX() + 0.5*r.getWidth()) < (getX() - 0.5*getWidth())) || ((r.getY() - 0.5*r.getHeight()) > (getY() + 0.5*getHeight())) || ((r.getY() + 0.5*r.getHeight()) < (getY() - 0.5*getHeight())));
  }

}