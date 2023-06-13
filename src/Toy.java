public class Toy implements Comparable<Toy> {
    // Эти поля используются в других классах,
    // поэтому я решить сделать их public.
    public String id;
    public String name;
    public Double probWeight;
    public Integer count;


    public Toy(String id, String name, Double probWeight, Integer count) {
        this.id = id;
        this.name = name;
        this.probWeight = probWeight;
        this.count = count;
    }

    // Не уверен в актуальности этого метода так как это поле public
    public void SetProbWeight(Double weight) {
        this.probWeight = weight;
    }

    // Метод добавления количества к уже имеющимся игрушкам
    public void addAmount(Integer amount) {
        this.count += amount;
    }


    public Toy Copy() {
        return new Toy(this.id, this.name, this.probWeight, this.count);
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Toy other = (Toy)obj;
        return (other.id.equals(this.id) &&
                other.name.equals(this.name) &&
                other.probWeight == this.probWeight &&
                other.count == this.count);
    }


    @Override
    public int compareTo(Toy o) {
        int option = this.id.compareTo(o.id);
        if (option > 0) {
            return 1;
        }
        if (option < 0) {
            return -1;
        }
        return 0;
    }


    @Override
    public String toString() {
        return String.format("id: %8s; name: %15s; probability weight: %3.2f; count: %4d \n",
                this.id,
                this.name,
                this.probWeight,
                this.count);
    }
}
