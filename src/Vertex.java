public class Vertex {

  private final int number;

  public Vertex(int number) {
    this.number = number;
  }

  public int getNumber() {
    return number;
  }

  @Override
  public String toString() {
    return "Vertex{" +
        "number=" + number +
        '}';
  }
}
