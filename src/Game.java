import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {

  private final AdjacencyLists adj;
  private final List<GamePoint> initPoints = new ArrayList<GamePoint>();
  private final List<GamePoint> points = new ArrayList<GamePoint>();
  private Random rand;

  public Game(AdjacencyLists adj) {
    this(adj, false, true, false, true);
  }

  public Game(AdjacencyLists adj, boolean TL, boolean TR, boolean BR, boolean BL) {
    this.adj = adj;
    rand = new Random();
    if (TL) {
      initPoints.add(new GamePoint(0));
    }
    if (TR) {
      initPoints.add(new GamePoint(adj.getColumnCount() - 1));
    }
    if (BR) {
      initPoints.add(new GamePoint(adj.getVertexCount() - 1));
    }
    if (BL) {
      boolean isLastRowLong = (adj.getRowsCount() - 1) % 2 == 0;
      if (isLastRowLong) {
        initPoints.add(new GamePoint(adj.getVertexCount() - adj.getColumnCount()));
      } else {
        initPoints.add(new GamePoint(adj.getVertexCount() - adj.getColumnCount() + 1));
      }
    }
  }

  public int play() {
    points.clear();
    for (GamePoint point : initPoints) {
      points.add(new GamePoint(point.getPosition()));
    }

    int result = 0;
    int randCell;
    while (true) {
      for (GamePoint point : points) {
        randCell = rand.nextInt(adj.getCountForVertex(point.getPosition()));
        point.setPosition(adj.getNewVertexNumber(point.getPosition(), randCell));
      }
      result++;
      if (isFinish(points)){
        break;
      }
    }
    return result;
  }

  // TODO: change to general
  private boolean isFinish(List<GamePoint> points) {
    if (points.get(0).getPosition() == points.get(1).getPosition()){
      return true;
    } else {
      return false;
    }
  }

}
