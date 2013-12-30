import java.util.HashMap;
import java.util.Map;

public class Main {

  public static void main(String[] args) {
    AdjacencyLists adj = new AdjacencyLists(3, 3);
    Game game = new Game(adj);
    Map<Integer, Integer> stat = new HashMap<Integer, Integer>();
    for (int i = 0; i < 1000000; i++) {
      Integer result = game.play();
      Integer valueFromMap = stat.get(result);
      if (valueFromMap == null) {
        stat.put(result, 1);
      } else {
        stat.put(result, valueFromMap + 1);
      }
    }
    stat = MapUtil.sortByValue(stat);
    System.out.println("results: " + stat);
  }
}
