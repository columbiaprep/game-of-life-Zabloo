public class Coord {
    private long x, y;

    public Coord(long x, long y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object other) {
        Coord otherC;
        try {
            otherC = (Coord) other;
        } catch (Exception e) {
            return false;
        }
        return x == otherC.x && y == otherC.y;
    }

    public long getX() {
        return x;
    }

    public long getY() {
        return y;
    }
}