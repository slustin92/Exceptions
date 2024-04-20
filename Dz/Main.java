package Dz;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException {


        Scanner scanner = new Scanner(System.in);
//
        System.out.print("Введите ваши данные через запятую (ФИО, № телефона, пол, дата рождения); ");
        String str = scanner.nextLine();
//        String str = " Петров Вася Сергеевич, 01.11.2015, 91531155, m";
        String[] arr = str.split(",");
        String fio = null;
        String dateRod = null;
        Integer telefon = 0;
        String pol = null;
        HashMap<String, String> map = new LinkedHashMap<>();

        if(arr.length != 4){
            throw new MySizeException();
        }else{


            for (int i = 0; i < arr.length; i ++) {
                if (fioss(arr[i].strip())) {
                    fio = arr[i];
                }if (dataRo(arr[i])) {
                    dateRod = arr[i];
                }if (isNumeric(arr[i].strip())) {
                    telefon = Integer.parseInt(arr[i].strip());
                }if (arr[i].strip().equals("m") || arr[i].strip().equals("f")) {
                    pol = arr[i];
                }

            }
            if(fio == null){
                throw new RuntimeException("Некорректное ФИО;");
            }if(dateRod == null){
                throw new RuntimeException("Некорректное Дата рождения;");
            }if(telefon == 0){
                throw new RuntimeException("Некорректное № телефона;");
            }if(pol == null){
                throw new RuntimeException("Некорректное пол;");
            }

        }
        map.put("ФИО ", fio);
        map.put("Дата рождения ", dateRod);
        map.put("Телефон № ", String.valueOf(telefon));
        map.put("Пол " , pol);


        StringBuilder out = new StringBuilder();
        for (String key: map.keySet()){
            out.append(key).append("-").append(map.get(key)).append(", ");
        }

        save(out);

}
public static void save(StringBuilder out){

    try {
        File myFile = new File("myfile.txt");
        FileWriter writer = new FileWriter(myFile, true);
        BufferedWriter bufferWriter = new BufferedWriter(writer);
        bufferWriter.write(String.valueOf(out));
        bufferWriter.write("\n");
        bufferWriter.close();
    }
    catch (IOException e) {
        System.out.println(e);
    }


}
public static boolean dataRo(String str) {
        try {
            SimpleDateFormat format = new SimpleDateFormat();
            format.applyPattern("dd.MM.yyyy");
            Date docDate = format.parse(str);
            return true;
        }catch (ParseException e){
            return false;
        }
    }


public static boolean fioss(String str){
        String[] st = str.split(" ");
        if (st.length == 3){
            return true;
        }else {
            return false;
        }
}

public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){

            return false;
        }
    }
    static class MySizeException extends RuntimeException{
        public MySizeException(){
            super("Вы ввели меньше и больше данных, чем требуется.");

        }
        }
}
