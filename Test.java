import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Test {
    
  public static String Request(String data) {
        String requesPart = "select * from students WHERE ";
        StringBuilder request = new StringBuilder();
        request.append(requesPart);
        data = data.replace("{", "");
        data = data.replace("}", "");
        data = data.replace("\"", "");
        data = data.replace(" ", "");
        String[] dataIn = data.split(",");
        for (int i = 0; i < dataIn.length; i++) {
            if (dataIn[i].contains("null") != true) {
                request.append(dataIn[i] + " AND ");
            }
        }
        request.delete(request.length() - 5, request.length());
        return request.toString();
    }
    
  public static void PrintArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
        }
        System.out.println();
    }
    
  public static void arrayToFile(int[] array){
        try (FileWriter writer = new FileWriter("sort.txt", true)) {
            for(int i = 0; i < array.length; i++) {
                writer.write("" + array[i]);
            }
            writer.write("\n");
        }
        catch (Exception e) {
            System.out.println("Что то пошло не так");
        }
    }
    
  public static void BubbleSort(int[] array) {
        int temp = 0;
        boolean flag = true;
        while (flag == true) {
            flag = false;
            arrayToFile(array);
            for (int i = 1; i < array.length; i++) {
                if (array[i] < array[i - 1]) {
                    temp = array[i - 1];
                    array[i - 1] = array[i];
                    array[i] = temp;
                    flag = true;
                }
            }
        }
    }
    
  public static String ReadFromFile() {
        File file = new File("school.txt");
        StringBuilder text = new StringBuilder();
        try{
            FileReader fileReader = new FileReader(file);
            char[] arr = new char[(int) file.length()];
            fileReader.read(arr);
            for (char c : arr) {
                text.append(c);
            }
            fileReader.close();
        }
        catch(Exception e){
            System.out.println("Что то пошло не так");
        }
        return text.toString();
    }
    
  public static void Students() {
        String dataOut = ReadFromFile(); //считывание этой строки из файла

        dataOut = dataOut.replace("[", ""); //убрали из строки боковые скобки
        dataOut = dataOut.replace("]", "");
        String[] data = dataOut.split("},"); //почему-то не работала запись String[] data = dataOut.split("},{"); с добавлением другой скобки, не поняла суть ошибки, поэтому обошла ее
        Map<String,String> dictionary = new HashMap<String,String>();

        for (int i = 0; i < data.length; i++) {
            data[i] = data[i].replace("{", "");
            data[i] = data[i].replace("}", ""); //на выходе массив из трех строк типа "фамилия":"Иванов","оценка":"5","предмет":"Математика"
            String[] student = data[i].split(","); //массив из трех строк типа "фамилия":"Иванов"
            for (int j = 0; j < student.length; j++) {
                student[j] = student[j].replace("\"", "");
                String[] forDict = student[j].split(":"); //разбили строки на пары ключ-значение и запишем в словарь
                String key = forDict[0];
                String value = forDict[1];
                dictionary.put(key, value);
            }
            StringBuilder result = new StringBuilder();
            result.append("Студент " + dictionary.get("фамилия") + " " + "получил " + dictionary.get("оценка") + " " + "по предмету " + dictionary.get("предмет"));
            System.out.println(result);
        }
    }
    
  public static void Calc(){
        System.out.print("Введите математическое выражение через пробелы: ");
        Scanner sc = new Scanner(System.in);
        String math = sc.nextLine();
        sc.close();
        String[] m = math.split(" ");
        int rezult;
        boolean flag = false;
        try (FileWriter writer = new FileWriter("calc.txt", true)) {
            if (math.contains("+")) {
                rezult = Integer.parseInt(m[0]) + Integer.parseInt(m[2]);
                System.out.printf("%s = %s", math, rezult);
                writer.write(math + " = " + rezult);
                writer.write("\n");
                flag = true;
            }
            if (math.contains("-")) {
                rezult = Integer.parseInt(m[0]) - Integer.parseInt(m[2]);
                System.out.printf("%s = %s", math, rezult);
                writer.write(math + " = " + rezult);
                writer.write("\n");
                flag = true;
            }
            if (math.contains("*")) {
                rezult = Integer.parseInt(m[0]) * Integer.parseInt(m[2]);
                System.out.printf("%s = %s", math, rezult);
                writer.write(math + " = " + rezult);
                writer.write("\n");
                flag = true;
            }
            if (math.contains("/")) {
                rezult = Integer.parseInt(m[0]) / Integer.parseInt(m[2]);
                System.out.printf("%s = %s", math, rezult);
                writer.write(math + " = " + rezult);
                writer.write("\n");
                flag = true;
            }
        }
        catch (Exception e) {
            System.out.println("Что то пошло не так");
        }
        if (flag == false) System.out.println("Неверный математический оператор");
    }

    
  public static void main(String[] args) {
        //Task1
        String dataInput = "{\"name\":\"Ivanov\", \"country\":\"Russia\", \"city\":\"Moscow\", \"age\":\"null\"}";
        System.out.println(Request(dataInput));

        //Task2
        int[] array = {3, 4, 6, 8, 1};
        BubbleSort(array);

        //Task3
        Students();

        //Task4
        Calc();
    }
}