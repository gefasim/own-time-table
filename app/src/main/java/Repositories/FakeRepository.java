package Repositories;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 9/4/16.
 */
public class FakeRepository implements IRepository {
    private List<String> card = new ArrayList<>();

    public FakeRepository(){
        this.card.add("1 FakeLol");
        this.card.add("2 FakeLol");
        this.card.add("3 FakeLol");
        this.card.add("4 FakeLol");
    }

    public List<String> getCard(){
        return this.card;
    }
}
