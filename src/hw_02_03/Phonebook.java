package hw_02_03;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Phonebook {
    final private Map<String, List<String>> phones=new TreeMap<>();

    public void add(String name, String phone) {
        List<String> e = get(name);
        e.add(phone);
        phones.putIfAbsent(name,e);
    }

    public List<String> get(String name) {
        return phones.getOrDefault(name, new ArrayList<>());
    }
}
