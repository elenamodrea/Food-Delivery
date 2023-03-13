package Interface;

import BusinessLayer.MenuItem;
import BusinessLayer.Order;
import BusinessLayer.ReadFromFile;
import Serialization.Serialization;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {
    public static void main(String[] args) {

        Serialization s = new Serialization();
        ReadFromFile r=new ReadFromFile();
         Map<Order, List<MenuItem>> orderMap = new HashMap<>();
        try {
            s.MapSerialization(orderMap);
            s.listSerialization(r.readFromFile());
            s.listUsersSerialization(r.readUserFromFile());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }



}
