package phonebook;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.stream.Collectors;

public class PhoneBook {
    private Map<String, Number> phoneBook = new TreeMap<>();
    private List<Pattern> patterns = new ArrayList<>();
    private int count = 0;

    public Map<String, Number> getPhoneBook() {
        return phoneBook;
    }

    public List<Pattern> getPatterns() {
        return patterns;
    }

    public int getCount() {
        return count;
    }

    public PhoneBook() {
        patterns.add(Pattern.compile("(\\+359)(87|88|89)([2-9])([0-9]{6})"));
        patterns.add(Pattern.compile("(0)(87|88|89)([2-9])([0-9]{6})"));
        patterns.add(Pattern.compile("(00359)(87|88|89)([2-9])([0-9]{6})"));
    }

    public boolean addNewPair(String name, String phone) {
        for (Pattern pattern : patterns) {
            Matcher matcher = pattern.matcher(phone);
            if (matcher.matches()) {
                if (!matcher.group(1).equals("+359")) {
                    phone = phone.replaceFirst(matcher.group(1), "+359");
                }
                phoneBook.put(name, new Number(phone));
                return true;
            }
        }
        return false;
    }

    public boolean deletePair(String name) {
        if (phoneBook.containsKey(name)) {
            phoneBook.remove(name);
            return true;
        } else {
            return false;
        }
    }

    public String accessPhoneNumber(String name) {
        Number phoneNumber = phoneBook.get(name);
        if (phoneNumber == null) {
            return null;
        }
        phoneNumber.setCount(phoneNumber.getCount() + 1);
        count++;
        return phoneNumber.getPhoneNumber();
    }

    public void printAllSorted() {
        for (Map.Entry<String, Number> entry : phoneBook.entrySet()) {
            System.out.printf("Name: %20s |  Phone: %13s \n", entry.getKey(), entry.getValue().getPhoneNumber());
        }
        System.out.println("Count of outgoing calls: " + count);
    }

    public boolean readFromFile(String path) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                try {
                    String[] pair = line.split(", ");
                    if (pair.length == 2) {
                        addNewPair(pair[0], pair[1]);
                    }
                } catch (PatternSyntaxException e) {
                    System.out.println("There is incorrect record!");
                }
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void getTopFive() {
        List<Number> phoneNumbers = new ArrayList<>(phoneBook.values());
        phoneNumbers.sort(Number::compareTo);

        List<Number> topFive = phoneNumbers.subList(Math.max(phoneNumbers.size() - 5, 0), phoneNumbers.size());
        System.out.println("Top five outgoing call numbers: ");
        for (Number phoneNumber : topFive) {
            System.out.printf("Name: %20s  | Phone: %13s  | Count: %d \n", phoneBook.entrySet()
                            .stream()
                            .filter(entry -> Objects.equals(entry.getValue(), phoneNumber))
                            .map(Map.Entry::getKey)
                            .collect(Collectors.toList()).get(0),
                    phoneNumber.getPhoneNumber(),
                    phoneNumber.getCount());
        }
        System.out.println();
    }
}


