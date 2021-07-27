package hw_02_03;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        // 1 ===========================================================================================================
        List<String> cats =  new ArrayList<>(Arrays.asList(
                "тигр","кот","леопард","пума","ирбис","лев","ягуар","манул","рысь","оцелот",
                "пантера","каракал","тигр","пума","лев","ягуар","пантера","ирбис","тигр","кот","лев"));
        Map<String,Integer> catsReport = new TreeMap<>();
        for (String cat:cats)
        {
            catsReport.put(cat,catsReport.getOrDefault(cat,0)+1);
        }
        catsReport.entrySet().forEach( e -> System.out.printf("Word %-10s meets %3s times\n",e.getKey(),e.getValue()));
        // 2 ===========================================================================================================
        Phonebook b = new Phonebook();
        b.add("Иванов","11-22-33");
        b.add("Петров","22-33-44");
        b.add("Сидоров","33-44-55");
        b.add("Иванов","55-66-77");
        b.add("Сидоров","77-88-99");
        b.add("Иванов","99-88-77");
        b.add("Смирнов","88-77-66");
        System.out.println("Иванов -> "+b.get("Иванов"));
        System.out.println("Сидоров -> "+b.get("Сидоров"));
        System.out.println("Смирнов -> "+b.get("Смирнов"));
        System.out.println("Кузнецов -> "+b.get("Кузнецов"));
    }
}
