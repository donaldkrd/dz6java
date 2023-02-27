import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

public class DZ6 {

    public static void main(String[] args) {
        NoteBook noteBook1 = new NoteBook();
        noteBook1.brand = "HUAWEI";
        noteBook1.model = "MateBook D 16";
        noteBook1.processor = "INTEL";
        noteBook1.ram = 16;
        noteBook1.rom = 512;
        noteBook1.os = "Windows";
        noteBook1.price = 86_999;

        NoteBook noteBook2 = new NoteBook();
        noteBook2.brand = "HUAWEI";
        noteBook2.model = "MateBook X Pro";
        noteBook2.processor = "INTEL";
        noteBook2.ram = 16;
        noteBook2.rom = 1024;
        noteBook2.os = "Windows";
        noteBook2.price = 125_999;

        NoteBook noteBook3 = new NoteBook();
        noteBook3.brand = "Apple";
        noteBook3.model = "MacBook Pro";
        noteBook3.processor = "Apple";
        noteBook3.ram = 16;
        noteBook3.rom = 512;
        noteBook3.os = "macOS";
        noteBook3.price = 191_399;

        NoteBook noteBook4 = new NoteBook();
        noteBook4.brand = "Apple";
        noteBook4.model = "MacBook Air";
        noteBook4.processor = "Apple";
        noteBook4.ram = 8;
        noteBook4.rom = 512;
        noteBook4.os = "macOS";
        noteBook4.price = 135_199;

        NoteBook noteBook5 = new NoteBook();
        noteBook5.brand = "ASUS";
        noteBook5.model = "VivoBook PRO";
        noteBook5.processor = "AMD";
        noteBook5.ram = 8;
        noteBook5.rom = 256;
        noteBook5.os = "No OS";
        noteBook5.price = 73_999;

        NoteBook noteBook6 = new NoteBook();
        noteBook6.brand = "MSI";
        noteBook6.model = "Pulse GL66";
        noteBook6.processor = "INTEL";
        noteBook6.ram = 8;
        noteBook6.rom = 512;
        noteBook6.os = "No OS";
        noteBook6.price = 136_999;

        var noteBooks = new HashSet<NoteBook>(
                Arrays.asList(noteBook1, noteBook2, noteBook3, noteBook4, noteBook5, noteBook6));

        Map<String, String> find = getRequest();

        printResult(noteBooks, find);
    }

    static void printResult(HashSet<NoteBook> noteBooks, Map<String, String> find) {
        boolean res = true;
        boolean print = false;
        if (find.isEmpty())
            System.out.println("Будет выведен весь список ноутбуков:");
        for (NoteBook noteBook : noteBooks) {
            if (find.containsKey("rom")) {
                if (!(noteBook.rom >= Integer.parseInt(find.get("rom")))) {
                    res = false;
                }
            }
            if (find.containsKey("ram")) {
                if (noteBook.ram < Integer.parseInt(find.get("ram"))) {
                    res = false;
                }
            }
            if (find.containsKey("processor")) {
                if (!(noteBook.processor.equals(find.get("processor")))) {
                    res = false;
                }
            }
            if (find.containsKey("os")) {
                if (!(noteBook.os.equals(find.get("os")))) {
                    res = false;
                }
            }
            if (res == true) {
                System.out.println(noteBook);
                print = true;
            } else {
                res = true;
            }
        }
        if (print == false)
            System.out.println("Нет ноутбуков удовлетворяющих вашим запросам.");
    }

    static Map<String, String> getRequest() {
        Map<String, String> request = new HashMap<>();
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите цифру, соответствующую критерию, за тем его параметр. По завершении, введите - 5.");
        while (true) {
            System.out.println(
                    "1 - ОЗУ(8/16), 2 - Объем ЖД(256/512/1024), 3 - ОС(macOS/Windows/No OS), 4 - Процессор(INTEL/AMD/Apple)");
            String[] criterion = scan.nextLine().split(" ");
            if (criterion[0].equals("5"))
                break;
            if (!check(criterion)) {
                continue;
            }
            int takeValue = Integer.parseInt(criterion[0]);
            switch (takeValue) {
                case 1:
                    String memory = "ram";
                    System.out.printf("Введите нужный объем(8/16): ");
                    String value = scan.nextLine();
                    if (!check2(value)) {
                        continue;
                    }
                    if (Integer.parseInt(value) < 8 || Integer.parseInt(value) > 16) {
                        System.out.println("Не правильный ВВОД, данные не сохренены. Повторите...");
                    } else {
                        request.put(memory, value);
                    }
                    break;
                case 2:
                    String memory2 = "rom";
                    System.out.println("Введите нужный объем(256/512/1024): ");
                    String value2 = scan.nextLine();
                    if (!check2(value2)) {
                        continue;
                    }
                    if (Integer.parseInt(value2) < 256 || Integer.parseInt(value2) > 1024) {
                        System.out.println("Не правильный ВВОД, данные не сохренены. Повторите...");
                    } else {
                        request.put(memory2, value2);
                    }
                    break;
                case 3:
                    String os = "os";
                    System.out.println("Введите операционную систему(macOS/Windows/No OS): ");
                    String value3 = scan.nextLine();
                    if (value3.equals("macOS") || value3.equals("Windows") || value3.equals("No OS")) {
                        request.put(os, value3);
                    } else {
                        System.out.println("Не правильный ВВОД, данные не сохренены. Повторите...");
                    }
                    break;
                case 4:
                    String processor = "processor";
                    System.out.println("Введите нужный процессор(INTEL/AMD/Apple): ");
                    String value4 = scan.nextLine();
                    if (value4.equals("INTEL") || value4.equals("AMD") || value4.equals("Apple")) {
                        request.put(processor, value4);
                    } else {
                        System.out.println("Не правильный ВВОД, данные не сохренены. Повторите...");
                    }
                    break;
                default:
                    System.out.println("Не правильный ВВОД. Нет критерия с таким номером.");
                    break;
            }
        }
        scan.close();
        if (request.isEmpty()) {
            System.out.println("Критерии фильтра не выбраны.");
        } else {
            System.out.print("\033[H\033[J");
            System.out.println("Запрос: " + request);
        }
        return request;
    }

    static boolean check(String[] criterion) {
        if (!criterion[0].matches("[1-5]+")) { // match - совпадение
            System.out.println("Не правильный ВВОД. Критерий не выбран. Повторите...");
            return false;
        }
        return true;
    }

    static boolean check2(String value) {
        if (!value.matches("[0-9]+")) { // match - совпадение
            System.out.println("Не правильный ВВОД. Критерий не выбран. Повторите...");
            return false;
        }
        return true;
    }
}