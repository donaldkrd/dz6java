public class NoteBook {
    String brand;
    String model;
    String processor;
    int ram;
    int rom;
    String os;
    int price;

    @Override
    public String toString() {
        return String.format("Марка: %s, Модель: %s, Процессор: %s, Память: %d, Жесткий диск: %d, ОС: %s, Цена: %d\n", 
        brand, model, processor, ram, rom, os, price);
    }
}
