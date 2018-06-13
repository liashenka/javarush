package com.javarush.task.task21.task2109;

import java.util.Objects;

/*
Запретить клонирование
*/
public class Solution {
    public static class A implements Cloneable {
        private int i;
        private int j;

        public A(int i, int j) {
            this.i = i;
            this.j = j;
        }

        public int getI() {
            return i;
        }

        public int getJ() {
            return j;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof A)) return false;
            if (o == null || getClass() != o.getClass()) return false;
            A a = (A) o;
            return i == a.i &&
                    j == a.j;
        }

        @Override
        public int hashCode() {

            return Objects.hash(i, j);
        }

        protected A clone() throws CloneNotSupportedException {
            int i = this.i;
            int j = this.j;
            A a = new A(i, j);
            return a;
        }
    }

    public static class B extends A {
        private String name;

        public B(int i, int j, String name) {
            super(i, j);
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        protected B clone() throws CloneNotSupportedException {
            throw new CloneNotSupportedException();    //Die Klonmethode in Klasse B muss neu definiert werden, sodass beim Versuch,
            // ein Objekt der Klasse B zu klonen, eine CloneNotSupportedException ausgelöst wird.
        }
    }

    public static class C extends B implements Cloneable {  //Klonobjekte der Klasse C müssen erfolgreich sein.
        public C(int i, int j, String name) {
            super(i, j, name);
        }

        @Override
        protected C clone() throws CloneNotSupportedException {
            int i = this.getI();
            int j = this.getJ();
            String name1 = this.getName();
            C c = new C(i, j, name1);
            return c;
        }
    }

    public static void main(String[] args) {

    }
}
