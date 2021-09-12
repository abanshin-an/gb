package hw_03_05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Race {
    private final ArrayList<Stage> stages;

    public List<Stage> getStages() {
        return stages;
    }

    public Race(Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }

}
