package hw.juinior.lesson3.taskhw2;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import hw.juinior.lesson3.taskwh1.Student;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        Student student = new Student("Aile", 24, 78);

        Kryo kryo = new Kryo();
        kryo.register(Student.class);
        //Serialization
        try(Output output = new Output(new FileOutputStream("stu2.stu"))) {
            kryo.writeClassAndObject(output, student);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        //Deserialization
        try(Input input = new Input(new FileInputStream("stu2.stu"))) {
            student = (Student) kryo.readClassAndObject(input);
            System.out.println(student);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
