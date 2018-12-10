package org.example.simple;

import org.example.simple.package1.Class4;

public class Class1 {

    public static void main(String... args) {
        Class1 class1 = new Class1();
        class1.method11();
    }

    public void method11() {
        method12();
        method14();
    }

    private void method12() {
        Class2 class2 = new Class2();
        class2.method21(1, true, "");
    }

    void method13() {

    }

    public void method14() {
        Class4 class4 = new Class4();
        class4.method41();
    }
}
