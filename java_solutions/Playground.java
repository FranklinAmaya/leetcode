public class Playground {
  public double myPow(double x, int n) {
    // base case
    if (n == 0)
      return 1;

    if (n > 0)
      return x * myPow(x, n - 1);

    return x / myPow(x, n++);
  }
}

class Main {
  public static void main(String[] args) {
    Playground playground = new Playground();

    System.out.println(playground.myPow(2.0, -2));
  }
}
