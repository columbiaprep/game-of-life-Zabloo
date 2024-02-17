import java.util.ArrayList;

public class Board {
  private AliveCellsList aliveCells = new AliveCellsList();

  public Board() {
    try {
      aliveCells.addCell(new Coord(0, 0));
      aliveCells.addCell(new Coord(0, 1));
      aliveCells.addCell(new Coord(1, 0));
      aliveCells.addCell(new Coord(5, 6));
      aliveCells.addCell(new Coord(-5, -3));
      aliveCells.addCell(new Coord(7, 0));
      aliveCells.addCell(new Coord(-7, 0));
    } catch (Exception e) {
      e.printStackTrace();
    }
    // System.out.println(aliveCells.getTopMost());
  }



  public void createNewGeneration() {
    ArrayList<Coord> cellsToDie = new ArrayList<Coord>();
    ArrayList<Coord> cellsToAdd = new ArrayList<Coord>();

    for (Coord a : aliveCells) {
      int aliveCellNeighbors = 0;
      // yes indeed some dead cells will be looked at twice or more, this is fine as aliveCellsList does duplicate checking
      for (Coord b : a.getAdjacentCoords()) { // check all cells around the alive cell
        if (aliveCells.findIndexOfCoord(b) == -1) { // make sure this cell is dead
          int deadCellNeighbors = 0;
          for (Coord c : b.getAdjacentCoords()) { // check neighbors around this dead cell
            if (aliveCells.findIndexOfCoord(c) != -1) // if this neighbor is alive, keep track
              deadCellNeighbors++;
          }
          if (conwayLogicCell(false, deadCellNeighbors)) // now check if our neighbor should be made alive
            cellsToAdd.add(b);
        } else aliveCellNeighbors++;

        // check if our alive cell should be made dead
        if (!conwayLogicCell(true, aliveCellNeighbors))
          cellsToDie.add(a);
      }
    }

    for (Coord a : cellsToDie)
      aliveCells.removeCell(a);
    for (Coord a : cellsToAdd) {
      try {
        aliveCells.addCell(a);
      } catch (Exception e) { // should never happen, only exception addCell would throw is if refreshExtrema was not called
        e.printStackTrace();
      }
    }
    aliveCells.refreshExtrema();
  }



  // use extrema to define a rectangle that contains all alive cells and print it
  public void display() {
    // start from top left corner, work down and right
    System.out.println("From " + aliveCells.getTopMost().getY() + " to " + aliveCells.getBottomMost().getY());
    System.out.println("From " + aliveCells.getLeftMost().getX() + " to " + aliveCells.getRightMost().getX());
    for (long y = aliveCells.getTopMost().getY(); y >= aliveCells.getBottomMost().getY(); y--) {
      for (long x = aliveCells.getLeftMost().getX(); x <= aliveCells.getRightMost().getX(); x++) {
        if (aliveCells.findIndexOfCoord(new Coord(x, y)) != -1) // check if cell is alive
          System.out.print("🦠");
        else
          System.out.print("🔲");
      }
      System.out.println();
    }
  }



  // takes in a cell, whether it is alive and number of neighbors and determines whether it should be made dead or alive next gen
  public boolean conwayLogicCell(boolean isAlive, int neighbors) {
    if (isAlive)
      return neighbors == 2 || neighbors == 3;
    // if dead
    return neighbors == 3;
  }
}