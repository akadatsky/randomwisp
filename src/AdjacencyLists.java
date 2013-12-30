import java.util.ArrayList;
import java.util.List;

public class AdjacencyLists {

  final private int rowsCount;
  final private int columnCount;

  private final List<List<Vertex>> adj;
  private int vertexCount;

  public AdjacencyLists(int rowsCount, int columnCount) {
    this.rowsCount = rowsCount;
    this.columnCount = columnCount;

    vertexCount = getVertexCount(rowsCount, columnCount);
    adj = new ArrayList<List<Vertex>>(vertexCount);
    initAdjacencyList();
  }

  private void initAdjacencyList() {
    int listIndex = 0;
    for (int i = 0; i < rowsCount; i++) {
      boolean isRowLong = i % 2 == 0;
      for (int j = 0; j < columnCount; j++) {
        if (j == columnCount - 1 && !isRowLong) {
          break;
        }
        List<Vertex> currentVertex = new ArrayList<Vertex>();
        if (!isFirstRow(i)) {
          currentVertex.addAll(getEdges(j, listIndex, isRowLong, false));
        }
        if (!isLastRow(i)) {
          currentVertex.addAll(getEdges(j, listIndex, isRowLong, true));
        }
        adj.add(currentVertex);
        listIndex++;
      }
    }
  }

  private List<Vertex> getEdges(int posInRow, int number, boolean bigRow, boolean next) {
    List<Vertex> result = new ArrayList<Vertex>();

    int dif1 = columnCount;
    int dif2 = columnCount - 1;
    if (next) {
      dif1 = columnCount - 1;
      dif2 = columnCount;
    }

    if (!bigRow || !isFirstColumn(posInRow)) {
      result.add(getVertex(number, dif1, next));
    }
    if (!bigRow || !isLastColumn(posInRow)) {
      result.add(getVertex(number, dif2, next));
    }
    return result;
  }

  private Vertex getVertex(int number, int diff, boolean isInc) {
    if (isInc) {
      return new Vertex(number + diff);
    } else {
      return new Vertex(number - diff);
    }
  }

  private boolean isLastRow(int rowIndex) {
    return rowIndex == rowsCount - 1;
  }

  private boolean isFirstRow(int rowIndex) {
    return rowIndex == 0;
  }

  private boolean isLastColumn(int posInRow) {
    return posInRow == columnCount - 1;
  }

  private boolean isFirstColumn(int posInRow) {
    return posInRow == 0;
  }

  private int getVertexCount(int rowsCount, int columnCount) {
    int result = rowsCount * columnCount - rowsCount / 2;
    return result;
  }

  public int getCountForVertex(int oldNumber) {
    return adj.get(oldNumber).size();
  }

  public int getNewVertexNumber(int oldNumber, int randValue) {
    return adj.get(oldNumber).get(randValue).getNumber();
  }

  public int getRowsCount() {
    return rowsCount;
  }

  public int getColumnCount() {
    return columnCount;
  }

  public int getVertexCount() {
    return vertexCount;
  }
}
