/*
 * QUESTION 1 Part-C
 */
public class Overlap {
    public static boolean overlap(Circle c, Rect r) {
        // Calculate distance between centre of circle and centre of axis-aligned rectangle
        double distanceBetweenCentresX = Math.abs(c.getX() - r.getX());
        double distanceBetweenCentresY = Math.abs(c.getY() - r.getY());

        // First case: circle is far enough away from axis-aligned rectangle
        if (distanceBetweenCentresX > ((r.getWidth() / 2) + c.getRadius()))
            return false;
        if (distanceBetweenCentresY > ((r.getHeight() / 2) + c.getRadius()))
            return false;

        // Second case: circle is close enough to axis-aligned rectangle
        if (distanceBetweenCentresX <= (r.getWidth() / 2))
            return true;
        if (distanceBetweenCentresY <= (r.getHeight() / 2))
            return true;

        // Third case: circle intersects corner of axis-aligned rectangle
        return ((Math.pow(distanceBetweenCentresX - (r.getWidth() / 2), 2)
                + Math.pow(distanceBetweenCentresY - (r.getHeight() / 2), 2)) <= Math
                        .pow(c.getRadius(), 2));
    }
}
