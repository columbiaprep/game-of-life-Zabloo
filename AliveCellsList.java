import java.util.ArrayList;

public class AliveCellsList extends ArrayList<Coord> {
  // extrema
  private Coord rightMost;
  private Coord topMost;
  private Coord leftMost;
  private Coord bottomMost;


  public void addCell(Coord c) throws Exception {
    // first ensure that there are no duplicates
    if (findIndexOfCoord(c) == -1) {
      // then loop through list and insert it in appropriate position (if using binary search method)
      if (rightMost == null && topMost == null && leftMost == null && bottomMost == null) {
        if (size() == 0) {
          rightMost = topMost = leftMost = bottomMost = c;
        }
        else throw new Exception("Must call refreshExtrema in between removing a cell and adding another one!");
      }
      testForExtrema(c);
      add(c);
    }
  }


  public void removeCell(Coord c) { // should always call refreshExtrema() after removing 1 or more cells
    try {
      remove(findIndexOfCoord(c));
      rightMost = topMost = leftMost = bottomMost = null; // to show that these are invalid until refreshExtrema is called
    } catch (Exception e) {
      // System.out.println("Could not remove cell that is not in list, attempted to input coord of " + c.toString());
      // e.printStackTrace();
    }
  }


  public void refreshExtrema() { // should always be called after calling removeCell()
    for (Coord a : this)
      testForExtrema(a);
  }


  // checks if a coordinate is an extrema and sets fields appropriately
  public void testForExtrema(Coord c) {
    if (c.getX() > rightMost.getX())
      rightMost = c;
    if (c.getY() > topMost.getY())
      topMost = c;
    if (c.getX() < leftMost.getX())
      leftMost = c;
    if (c.getY() < bottomMost.getY())
      bottomMost = c;
  }


  // returns index of given coordinate, -1 if nonexistant
  public int findIndexOfCoord(Coord c) {
    for (int a = 0; a < size(); a++) {
      Coord temp = get(a);
      if (temp.equals(c))
        return a;
    }
    return -1;
  }


  public Coord getRightMost() {
    return rightMost;
  }

  public Coord getTopMost() {
    return topMost;
  }

  public Coord getLeftMost() {
    return leftMost;
  }

  public Coord getBottomMost() {
    return bottomMost;
  }
}