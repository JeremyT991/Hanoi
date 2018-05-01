import helper.*;

import java.util.*;

public class Hanoi {

  static Stack tower1;
  static Stack tower2;
  static Stack tower3;
  static int size;

  Hanoi() { // Initialize towers
    tower1 = new Stack();
    tower2 = new Stack();
    tower3 = new Stack();
    run();
  }

  public static void main(String[] args) {
    Hanoi towers = new Hanoi();
  }

  public void run() { //prompt for tower size, build tower, and solve
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

  public void solver(int count, Stack a, Stack b, Stack c) { //solve recursively
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

  public void move1(Stack a, Stack b) { // move from tower a to tower b
    int A = 0;
    int B = 0;

    // get top disc on tower A, default is max + 1
    try {
      A = (int) a.peek();
    } catch (EmptyStackException e) {
      A = size + 1;
    }

    // get top disc on tower B, default is max + 1
    try {
      B = (int) b.peek();
    } catch (EmptyStackException e) {
      B = size + 1;
    }

    // check if move is legal
    try {
      if (A > B)
        throw new Monk("Can't put a bigger value on a smaller one!");
      b.push(a.pop());
    } catch (Monk e) {

    } 
  }

  public void build() { //stacks / builds the discs onto tower 1
    for (int i = 0; i < size; i++) {
      tower1.push(size - i);
    }
  }

  public void print() { // print all three towers
    // make a copy of each
    Stack copy1 = (Stack) tower1.clone();
    Stack copy2 = (Stack) tower2.clone();
    Stack copy3 = (Stack) tower3.clone();

    // reverse the ordering of each
    copy1 = reverse(copy1);
    copy2 = reverse(copy2);
    copy3 = reverse(copy3);

    //build the output in a stack by combining all towers starting at base value
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
    
    // print final output
    for (int i = 0; i < size; i++) {
      System.out.println(toPrint.pop());
    }
    System.out.println();
  }

  public Stack reverse(Stack toReverse) {//reverse a given stack
    Stack reversed = new Stack();
    
    try {
      while (toReverse.peek() != null) 
        reversed.push(toReverse.pop());
    } catch (EmptyStackException e) {

    }

    return reversed;
  }
}
