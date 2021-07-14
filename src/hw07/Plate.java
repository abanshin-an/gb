package hw07;

public class Plate {
    int food=0;
    public void putFood(int food){
        this.food+=food;
    };

    public int getFood() {
        return food;
    }

    public boolean takeFood(int appetite){
        if (food<appetite){
            return false;
        }
        food-=appetite;
        return true;
    }

    @Override
    public String toString() {
        return "Plate{" +
                "food=" + food +
                '}';
    }

}
