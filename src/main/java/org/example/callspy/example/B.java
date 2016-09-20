package org.example.callspy.example;

/**
 * Created by shelajev on 27/04/15.
 */
class B extends A {

  public void op(String name) {
    super.op("B is for BCN! (" + name + ")");
//    System.out.println("B: " + name);
  }
}
