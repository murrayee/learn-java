package demo22;

import java.util.List;

public class Producer {

    private List<Integer> list;
    private int max;

    public Producer(String name, int max, List<Integer> list) {
        super();
        this.list = list;
        this.max = max;
    }
}
