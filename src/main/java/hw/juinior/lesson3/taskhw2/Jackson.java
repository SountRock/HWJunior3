package hw.juinior.lesson3.taskhw2;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import hw.juinior.lesson3.taskwh1.Student;

import java.io.File;
import java.io.IOException;

public class Jackson {
    public static void main(String[] args) {
        Student student = new Student("Vent", 23, 79);

        XmlMapper xml = new XmlMapper();

        //Для того, чтобы не игнорировать transient-ы!!!
        //Установка видимости полей
        xml.setVisibilityChecker(xml.getVisibilityChecker()
                .withFieldVisibility(JsonAutoDetect.Visibility.ANY) //Видимость полей для кажого поля
                .withGetterVisibility(JsonAutoDetect.Visibility.NONE)); //Но игнорируя геттеры

        //Serialization
        try {
            xml.configure(SerializationFeature.INDENT_OUTPUT, true);
            xml.writeValue(new File("stuXml.xml"), student);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Deserialization
        try {
            student = xml.readValue(new File("stuXml.xml"), Student.class);
            System.out.println(student);
        } catch (StreamReadException e) {
            throw new RuntimeException(e);
        } catch (DatabindException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
