import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayDeque;
import java.util.Random;
import java.util.Set;


public class ToyQueue {
    private String pathToFile;
    private Integer queSize;
    private ArrayDeque<Toy> toyQue;
    private ToyProbTable table;


    public ToyQueue(ToyProbTable table, Integer size, String path) {
        this.queSize = size;
        this.pathToFile = path;
        this.table = table;
        this.toyQue = new ArrayDeque<>();
    }


    public void Proccess(Integer iterations) throws IOException {
        Random rnd = new Random();
        // Если игрушек недостаточно для заполнения очереди
        // оповестим об этом пользователя
        if (this.table.getTotalCount() < this.queSize) {
            System.out.println("Your probability table doesn't have enough elements");
            return;
        }
        // Заполняем очередь размером queSize
        // если в очереди недостаточно елементов
        while (this.toyQue.size() < this.queSize) {
            Toy toy = table.getRandomToy(rnd);
            this.toyQue.add(toy);
        }
        File file = new File(this.pathToFile);
        int iter = 0;
        while (iter < iterations && !this.toyQue.isEmpty()) {

            Toy toyOut = this.toyQue.pollFirst();
            FileOutputStream fostream = new FileOutputStream(file, true);
            fostream.write(toyOut.toString().getBytes());
            fostream.close();

            Toy toyIn = this.table.getRandomToy(rnd);
            if (toyIn != ToyProbTable.zeroToy) {
                this.toyQue.add(toyIn);
            }
            iter++;
        }
        if (iter < iterations) {
            System.out.println("All toys have been sold");
        }
    }
}
