package BusinessLayer;

import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface IDeliveryServiceProcessing {
    public DefaultTableModel tableModel()  throws IllegalAccessException, IOException, ClassNotFoundException;
    public List<MenuItem> modify(int i, String title, String ratingS, String caloriesS, String proteinS, String fatS, String sodiumS, String priceS) throws IOException, ClassNotFoundException;
    public List<MenuItem> delete(int i)throws IOException, ClassNotFoundException;
    public List<MenuItem> add(String title,String ratingS,String caloriesS,String proteinS,String fatS,String sodiumS,String priceS) throws IOException, ClassNotFoundException;
    public List<User> userInit(User user) throws IOException, ClassNotFoundException;
    public List<BaseProduct> addToComposedList(int i,List<BaseProduct> itemList) throws IOException, ClassNotFoundException;
    public List<MenuItem> addToOrderList(int i,List<MenuItem> itemList) throws IOException, ClassNotFoundException;
    public Map<Order,List<MenuItem>> createOrder(Order order, List<MenuItem> menuItems) throws IOException, ClassNotFoundException;
    public int totalPrice(List<MenuItem> list );
    public void createBill(Order order, List<MenuItem> list);
    public DefaultTableModel searchByKeyword(String keyword) throws IOException, ClassNotFoundException;
    public DefaultTableModel searchByPrice(int price) throws IOException, ClassNotFoundException;
    public DefaultTableModel searchByCalories(int calories) throws IOException, ClassNotFoundException;
    public DefaultTableModel searchByFats(int fats) throws IOException, ClassNotFoundException;
    public DefaultTableModel searchByProteins(int proteins) throws IOException, ClassNotFoundException;
    public DefaultTableModel searchByRating(double rating) throws IOException, ClassNotFoundException;
    public DefaultTableModel searchBySodium(int sodium) throws IOException, ClassNotFoundException;

}
