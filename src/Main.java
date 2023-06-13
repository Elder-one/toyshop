import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException, RuntimeException {
        Toy toy1 = new Toy("d101", "plastic doll", 70.0, 200);
        Toy toy2 = new Toy("l101", "lego city", 10.0, 30);
        Toy toy3 = new Toy("c101", "cube", 20.0, 50);
        ArrayList<Toy> toyList = new ArrayList<>();
        toyList.add(toy1);
        toyList.add(toy2);
        toyList.add(toy3);
        ToyProbTable table = new ToyProbTable(toyList);
        ToyQueue toyQue = new ToyQueue(table, 10, "toyQueue1.txt");
        try {
            toyQue.Proccess(100);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(table);
    }
}