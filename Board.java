import java.util.ArrayList;

public class Board {
  private AliveCellsList aliveCells = new AliveCellsList();

  public Board() {
    aliveCells.add(new Coord(0, 0));
    aliveCells.add(new Coord(0, 1));
    aliveCells.add(new Coord(1, 0));
  }

  public void createNewGeneration() {

  }

  public void display() {

  }
}