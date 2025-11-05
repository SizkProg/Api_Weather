import java.text.NumberFormat;
import java.text.ParseException;

public class ExceptionTest {
    public static void main(String[] args) {
        System.out.println("Метод Main успешно запущен");
        method1();
        System.out.println("Метод Main заканчивает свою работу");
    }
    static void method1() {
        System.out.println("Первый метод передает привет!");
        method2();
    }
    static void method2() {
        int x = 10;
        int y = 0;
        double z = x / y;
        System.out.println( z );
        System.out.println("Второй метод");
    }

}
