import java.lang.reflect.Array;
import java.util.ArrayList;

public class State {
    int price;
    ArrayList<String> legal_values = new ArrayList<>();
    public State(){
        legal_values.add("+");
        legal_values.add("-");
        legal_values.add("@");
    }
}
