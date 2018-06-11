package com.javarush.task.task21.task2107;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/*
Глубокое клонирование карты
*/
public class Solution implements Cloneable {
    protected Map<String, User> users = new LinkedHashMap();

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.users.put("Hubert", new User(172, "Hubert"));
        solution.users.put("Zapp", new User(41, "Zapp"));
        Solution clone = null;
        try {
            clone = (Solution) solution.clone();
            System.out.println(solution);
            System.out.println(clone);

            System.out.println(solution.users);
            System.out.println(clone.users);

        } catch (CloneNotSupportedException e) {
            e.printStackTrace(System.err);
        }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Solution newObj = (Solution) super.clone();
        newObj.users = new LinkedHashMap();
        for (Map.Entry<String, User> setEn:users.entrySet()){
            newObj.users.put(setEn.getKey(), (User) setEn.getValue().clone());
        }

        return newObj;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(!(o instanceof Solution)) return false;
        if (o == null || getClass() != o.getClass()) return false;
        Solution solution = (Solution) o;
        return Objects.equals(users, solution.users);
    }

    @Override
    public int hashCode() {

        return Objects.hash(users);
    }

    public static class User implements Cloneable {
        int age;
        String name;

        @Override
        protected Object clone() {
            return new User(this.age, this.name);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if(!(o instanceof User)) return false;
            if (o == null || getClass() != o.getClass()) return false;
            User user = (User) o;
            return age == user.age &&
                    Objects.equals(name, user.name);
        }

        @Override
        public int hashCode() {

            return Objects.hash(age, name);
        }

        public User(int age, String name) {
            this.age = age;
            this.name = name;
        }

    }
}
