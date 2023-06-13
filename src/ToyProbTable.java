import java.util.ArrayList;
import java.util.Random;
import java.util.TreeSet;

public class ToyProbTable {
    // Класс реализует таблицу интервалов и вероятностей для заданного множества игрушек.
    // Если сумма всех probWeight игрушек равна 100 то они совпадают с вероятностями
    // выпадения этих игрушек (в процентах)
    // Метод getRandomToy возвращает игрушку с соответсвующей вероятностью.
    // Метод getProb возвращает вероятность выпадения переданной игрушки для
    // заданного множества.
    // Метод addToy добавляет переданную в параметре игрушку
    private Double fullMass;
    private ArrayList<Double[]> intervals;
    private TreeSet<Toy> toySet;
    private ArrayList<Double> probs;
    // Заглушка zeroToy
    public static Toy zeroToy = new Toy("none", "none", 0.0, 0);


    public ToyProbTable(ArrayList<Toy> toyList) {
        this.toySet = new TreeSet<Toy>();
        for(Toy toy : toyList) {
            this.toySet.add(toy);
        }
        // Составим таблицу интервалов и вычислим общий вес множества игрушек
        this.fullMass = 0.0;
        this.intervals = new ArrayList<Double[]>();
        for(Toy toy : this.toySet) {
            Double[] borders = new Double[2];
            borders[0] = this.fullMass;
            borders[1] = this.fullMass + toy.probWeight;
            this.intervals.add(borders);
            this.fullMass += toy.probWeight;
        }
        // Составим таблицу вероятностей
        this.probs = new ArrayList<Double>();
        for(Toy toy : this.toySet) {
            this.probs.add(toy.probWeight / this.fullMass);
        }
    }


    public void addToy(Toy item) {
        // Метод добавления новой игрушки.
        // Работает так же, как конструктор
        this.toySet.add(item);

        this.fullMass = 0.0;
        this.intervals = new ArrayList<Double[]>();
        for(Toy toy : this.toySet) {
            Double[] borders = new Double[2];
            borders[0] = this.fullMass;
            borders[1] = this.fullMass + toy.probWeight;
            this.intervals.add(borders);
            this.fullMass += toy.probWeight;
        }

        this.probs = new ArrayList<Double>();
        for(Toy toy : this.toySet) {
            this.probs.add(toy.probWeight / this.fullMass);
        }
    }


    public int getTotalCount() {
        int result = 0;
        for(Toy toy : this.toySet) {
            result += toy.count;
        }
        return result;
    }


    public boolean toysAreSoldOut() {
        for(Toy toy : this.toySet) {
            if (toy.count > 0) {
                return false;
            }
        }
        return true;
    }

    // Метод получения игрушки по id
    // для дальнейшей обработки
    public Toy getToy(String id) {
        for (Toy toy : this.toySet) {
            if (toy.id.equals(id)) {
                return toy;
            }
        }
        // Игрушка не найдена, возвращаем заглушку
        return zeroToy;
    }


    public Toy getRandomToy(Random rnd) {
        // Если множество игрушек пустое, возвращаем заглушку
        if (this.toySet.isEmpty()) {
            return zeroToy;
        }
        // Пока все игрушки не распроданы
        // пытаемся выдать игрушку
        while (!this.toysAreSoldOut()) {
            Double value = this.fullMass * rnd.nextDouble();
            int i = 0;
            for (Toy toy : this.toySet) {
                if (this.intervals.get(i)[0] <= value &&
                        value <= this.intervals.get(i)[1] &&
                        toy.count > 0) {
                    toy.count--;
                    return toy.Copy();
                }
                i++;
            }
        }
        // Все игрушки выданы, возвращаем заглушку
        return zeroToy;
    }


    public Double getProb(Toy target) {
        int i = 0;
        for(Toy toy : this.toySet) {
            if (toy == target) {
                return this.probs.get(i);
            }
            i++;
        }
        // Если игрушка не была найдена в множестве, вероятность равна нулю
        return 0.0;
    }


    @Override
    public String toString() {
        StringBuilder strb = new StringBuilder();
        for(Toy toy : this.toySet) {
            strb.append(toy);
        }
        String result = strb.toString();
        return result;
    }
}
