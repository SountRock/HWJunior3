package hw.juinior.lesson3.taskwh1;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        Student student = new Student("Aile", 24, 78);

        //Serialization
        try(FileOutputStream fileOutput = new FileOutputStream("stu1.stu");
            ObjectOutputStream out = new ObjectOutputStream(fileOutput)) {
            out.writeObject(student);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Deserialization
        try(FileInputStream fileInput = new FileInputStream("stu1.stu");
        ObjectInputStream in = new ObjectInputStream(fileInput)) {
            Student student1 = (Student) in.readObject();
            System.out.println(student1);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        //GPA не серилизуется потому что мы указали, что при серилизации это поле нужно игнорировать с помощью transient
    }
}
