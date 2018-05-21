package com.javarush.task.task20.task2002;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/* 
Читаем и пишем в файл: JavaRush
*/
public class Solution {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or fix outputStream/inputStream according to your real file location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
            File your_file_name = new File("C:\\Users\\maria\\1.txt");
            OutputStream outputStream = new FileOutputStream(your_file_name);
            InputStream inputStream = new FileInputStream(your_file_name);

            JavaRush javaRush = new JavaRush();
            //initialize users field for the javaRush object here -
            // инициализируйте поле users для объекта javaRush// тут
            User user = new User();
            user.setFirstName("Rubi");
            user.setLastName("Rail");
            user.setBirthDate(new Date(1508944516168L));
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MM yyyy", Locale.ENGLISH);
            Date formatDate = simpleDateFormat.parse("25 10 2017");
           // user.setBirthDate(formatDate);
            user.setMale(true);
            user.setCountry(User.Country.OTHER);
            javaRush.users.add(user);


            User user1 = new User();
            user1.setFirstName("Vasa1");
            user1.setLastName("Peta1");
           // user1.setBirthDate(new Date(1508944516163L));
            formatDate = simpleDateFormat.parse("20 10 2017");
            user1.setBirthDate(formatDate);
            user1.setMale(true);
            user1.setCountry(User.Country.RUSSIA);
            javaRush.users.add(user1);

            User user3 = new User();
            user3.setFirstName("Solo");
            user3.setLastName("Han");
          //  user3.setBirthDate(new Date(1508944516169L));
            formatDate = simpleDateFormat.parse("21 10 2017");
            user3.setBirthDate(formatDate);
            user3.setMale(true);
            user3.setCountry(User.Country.UKRAINE);
            javaRush.users.add(user3);

            javaRush.save(outputStream);
            outputStream.flush();


            JavaRush loadedObject = new JavaRush();
      /*      if(javaRush.users.size()!=loadedObject.users.size()) {
                for (int i = 0; i <javaRush.users.size() ; i++) {
                    loadedObject.users.add(new User());
                }
            }
      */
            loadedObject.load(inputStream);

            //check here that javaRush object equals to loadedObject object -
            // проверьте тут, что javaRush и loadedObject равны
            for (User u : loadedObject.users) {
                System.out.println(u.getFirstName() + " " + u.getLastName() + " " + u.getCountry() + " " + u.getBirthDate() + " " + u.isMale());
            }

            System.out.println(javaRush.equals(loadedObject));

            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }

    public static class JavaRush {
        public List<User> users = new ArrayList<>(); // users null

        public void save(OutputStream outputStream) throws Exception {
            //implement this method - реализуйте этот метод

            PrintWriter writer = new PrintWriter(outputStream);
            for (User user : users) {
                writer.println(user.getFirstName());
                writer.println(user.getLastName());
                writer.println(user.getBirthDate());
                writer.println(user.isMale());
                writer.println(user.getCountry());
            }

            writer.flush();
            writer.close();

        }

        public void load(InputStream inputStream) throws Exception {
            //implement this method - реализуйте этот метод
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));


                while (reader.ready()) {
                    User user = new User();
                    user.setFirstName(reader.readLine());
                    user.setLastName(reader.readLine());
                  //  user.setBirthDate(new Date(Long.parseLong(reader.readLine())));
                    SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
                    user.setBirthDate(sdf.parse(reader.readLine()));

                    user.setMale(Boolean.parseBoolean(reader.readLine()));

                    String str = reader.readLine();
                    if (str.equals("OTHER")) {
                        user.setCountry(User.Country.OTHER);
                    }
                    if (str.equals("RUSSIA")) {
                        user.setCountry(User.Country.RUSSIA);
                    }
                    if (str.equals("UKRAINE")) {
                        user.setCountry(User.Country.UKRAINE);
                    }

                    this.users.add(user);
                }
                reader.close();

        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            JavaRush javaRush = (JavaRush) o;

            return users != null ? users.equals(javaRush.users) : javaRush.users == null;

        }

        @Override
        public int hashCode() {
            return users != null ? users.hashCode() : 0;
        }
    }
}
