import helper.*;

import java.util.*;

public class Hanoi {

  static Stack tower1;
  static Stack tower2;
  static Stack tower3;
  static int size;

  Hanoi() {
    tower1 = new Stack();
    tower2 = new Stack();
    tower3 = new Stack();
    run();
  }

  public static void main(String[] args) {
    Hanoi towers = new Hanoi();
  }

  public void run() {
    Scanner scanner = new Scanner(System.in);
    boolean noNumber = true;
    String input = "";

    while (noNumber) {
      try {
        System.out.print("Enter tower size: ");
        input = scanner.nextLine();
        size = Integer.parseInt(input);
        noNumber = false;
      } catch (NumberFormatException e) {
          
      }
    }

    System.out.println("Number entered: " + size);

    build();
    print();
    solver(size, tower1, tower2, tower3);
  }

  public void solver(int count, Stack a, Stack b, Stack c) {
    if (count == 1) {
      move1(a, c);
      print();
    } else {
      solver(count - 1, a, c, b);
      move1(a, c);
      print();
      solver(count - 1, b, a, c);
    }
  }

  public void move1(Stack a, Stack b) {

    try {
      if ((int) a.peek() > (int) b.peek())
        throw new Monk("Can't put a bigger value on a smaller one!");
    } catch (EmptyStackException e) {

    } catch (Monk e) {

    } 

    b.push(a.pop());
   
  }

  public void build() {
    for (int i = 0; i < size; i++) {
      tower1.push(size - i);
    }
  }

  public void print() {
    Stack copy1 = (Stack) tower1.clone();
    Stack copy2 = (Stack) tower2.clone();
    Stack copy3 = (Stack) tower3.clone();

    copy1 = reverse(copy1);
    copy2 = reverse(copy2);
    copy3 = reverse(copy3);

    Stack toPrint = new Stack();
    String line = "";
   
    for (int i = 0; i < size; i++) {
      try {
        line += copy1.pop() + "\t";
      } catch (EmptyStackException e) {
        line += "-\t";
      }
      try {
        line += copy2.pop() + "\t";
      } catch (EmptyStackException e) {
        line += "-\t";
      }
      try {
        line += copy3.pop() + "\t";
      } catch (EmptyStackException e) {
        line += "-";
      }
      toPrint.push(line);
      line = "";
    }
    
    for (int i = 0; i < size; i++) {
      System.out.println(toPrint.pop());
    }
    System.out.println();
  }

  public Stack reverse(Stack toReverse) {
    Stack reversed = new Stack();
    
    try {
      while (toReverse.peek() != null) 
        reversed.push(toReverse.pop());
    } catch (EmptyStackException e) {

    }

    return reversed;
  }
}
