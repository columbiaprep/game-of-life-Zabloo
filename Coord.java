public class Coord {
    private long x, y;

    public Coord(long x, long y) {
        this.x = x;
        this.y = y;
    }

    // array length 8
    public Coord[] getAdjacentCoords() {
        return new Coord[] {
                new Coord(x + 1, y),
                new Coord(x + 1, y + 1),
                new Coord(x, y + 1),
                new Coord(x - 1, y + 1),
                new Coord(x - 1, y),
                new Coord(x - 1, y - 1),
                new Coord(x, y - 1),
                new Coord(x + 1, y - 1)
        };
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

    @Override
    public String toString() {
        return "( " + x + ", " + y + ")";
    }

    public long getX() {
        return x;
    }

    public long getY() {
        return y;
    }
}