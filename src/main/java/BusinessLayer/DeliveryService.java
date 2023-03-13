package BusinessLayer;

import Serialization.Serialization;

import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeliveryService implements Serializable,IDeliveryServiceProcessing {
    private List<BaseProduct> baseProductList;
    Serialization s=new Serialization();
    ReadFromFile r=new ReadFromFile();
    public DefaultTableModel tableModel() throws IllegalAccessException, IOException, ClassNotFoundException {
        List<MenuItem> list=s.listDeserialization();
        DefaultTableModel table = new DefaultTableModel();
       String[] colomns=new String[7];
        colomns[0]="title";colomns[1]="rating";colomns[2]="calories";colomns[3]="protein";colomns[4]="fat";colomns[5]="sodium";colomns[6]="price";
        for (int i = 0; i < 7; i++) {
            table.addColumn(colomns[i]);
        }

        for (MenuItem t : list) {
            Object[] linie = new Object[7];
            for(int i=0;i<7;i++)
                linie[i] = new Object();
            linie[0] = t.getTitle();linie[1] = t.getRating();linie[2] = t.getCalories();linie[3] = t.getProtein();linie[4] = t.getFat();linie[5] = t.getSodium();linie[6] = t.getPrice();
            table.addRow(linie);
        }
        return table;
    }
    public List<MenuItem>modify(int i,String title,String ratingS,String caloriesS,String proteinS,String fatS,String sodiumS,String priceS) throws IOException, ClassNotFoundException {
        List<MenuItem> list=s.listDeserialization();
        MenuItem product=list.get(i);
        double rating;
        int calories;
        int protein;
        int fat;
        int sodium;
        int price;
        String titleF = null;
        if(ratingS.isEmpty())
            rating=product.getRating();
        else rating=Double.parseDouble(ratingS);
        if(caloriesS.isEmpty())
            calories= product.getCalories();
        else calories=Integer.parseInt(caloriesS);
        if(proteinS.isEmpty())
            protein= product.getProtein();
        else protein=Integer.parseInt(proteinS);
        if(fatS.isEmpty())
            fat= product.getFat();
        else fat=Integer.parseInt(fatS);
        if(sodiumS.isEmpty())
            sodium= product.getSodium();
        else sodium=Integer.parseInt(sodiumS);
        if(priceS.isEmpty())
            price=product.getPrice();
        else price=Integer.parseInt(priceS);
        if(title.isEmpty())
            titleF=product.getTitle();
        BaseProduct baseProduct=new BaseProduct(titleF,rating,calories,protein,fat,sodium,price);
        list.set(i,baseProduct);
        s.listSerialization(list);
        return list;
    }
    public List<MenuItem> delete(int i) throws IOException, ClassNotFoundException {
        List<MenuItem> list= s.listDeserialization();
        list.remove(i);
        s.listSerialization(list);
        return list;
    }
    public List<MenuItem> add(String title,String ratingS,String caloriesS,String proteinS,String fatS,String sodiumS,String priceS) throws IOException, ClassNotFoundException {
        List<MenuItem> list =s.listDeserialization();
        double rating;
        int calories;
        int protein;
        int fat;
        int sodium;
        int price;
        rating=Double.parseDouble(ratingS);
        calories=Integer.parseInt(caloriesS);
        protein=Integer.parseInt(proteinS);
        fat=Integer.parseInt(fatS);
        sodium=Integer.parseInt(sodiumS);
        price=Integer.parseInt(priceS);
        BaseProduct baseProduct=new BaseProduct(title,rating,calories,protein,fat,sodium,price);
        list.add(baseProduct);
        s.listSerialization(list);
        return list;
    }
    public List<User> userInit(User user) throws IOException, ClassNotFoundException {
     List<User> list=s.listUsersDeserialization();
     list.add(user);
     s.listUsersSerialization(list);
     return list;
      }
      public List<BaseProduct> addToComposedList(int i,List<BaseProduct> itemList) throws IOException, ClassNotFoundException {
        List<MenuItem> list=s.listDeserialization();
        BaseProduct item= (BaseProduct) list.get(i);
        itemList.add(item);
        s.listSerialization(list);
        return itemList;
    }
    public List<MenuItem> addToOrderList(int i,List<MenuItem> itemList) throws IOException, ClassNotFoundException {
        List<MenuItem> list=s.listDeserialization();
        MenuItem item= list.get(i);
        itemList.add(item);
        s.listSerialization(list);
        return itemList;
    }
    public Map<Order,List<MenuItem>> createOrder(Order order, List<MenuItem> menuItems) throws IOException, ClassNotFoundException {
        Map<Order,List<MenuItem>> map=s.MapDeserialization();
        map.put(order,menuItems);
        s.MapSerialization(map);
        return map;
    }
    public int totalPrice(List<MenuItem> list ){
        int price=0;
        for (MenuItem t: list) {
            price+=t.getPrice();
        }
        return price;
    }
    public void createBill(Order order, List<MenuItem> list){
        try {
            FileWriter myWriter = new FileWriter("Bil.txt");

            String o="idClient idOrder date \n";

            o+=order.getIdClient()+" "+ order.getIdOrder()+" "+order.getDate()+"\n";
            o+="list of products:"+"\n";
            for (MenuItem t: list) {
                o+=t.getTitle()+" "+t.getCalories()+" "+t.getFat()+" "+t.getProtein()+" "+t.getRating()+" "+t.getSodium()+" "+t.getPrice()+"\n";

            }
            o+= "Total price is: "+ totalPrice(list);
            myWriter.write(o);
            myWriter.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public DefaultTableModel searchByKeyword(String keyword) throws IOException, ClassNotFoundException {
        List<MenuItem> list=s.listDeserialization();
        DefaultTableModel table = new DefaultTableModel();
        String[] colomns=new String[7];
        colomns[0]="title";colomns[1]="rating";colomns[2]="calories";colomns[3]="protein";colomns[4]="fat";colomns[5]="sodium";colomns[6]="price";
        for (int i = 0; i < 7; i++) {
            table.addColumn(colomns[i]);
        }

        list.forEach(t-> {
            if(t.getTitle().contains(keyword))
            {Object[] linie = new Object[7];
            for(int i=0;i<7;i++)
                linie[i] = new Object();
            linie[0] = t.getTitle();linie[1] = t.getRating();linie[2] = t.getCalories();linie[3] = t.getProtein();linie[4] = t.getFat();linie[5] = t.getSodium();linie[6] = t.getPrice();
            table.addRow(linie);}});

        return table;
    }
    public DefaultTableModel searchByPrice(int price) throws IOException, ClassNotFoundException {
        List<MenuItem> list=s.listDeserialization();
        DefaultTableModel table = new DefaultTableModel();
        String[] colomns=new String[7];
        colomns[0]="title";colomns[1]="rating";colomns[2]="calories";colomns[3]="protein";colomns[4]="fat";colomns[5]="sodium";colomns[6]="price";
        for (int i = 0; i < 7; i++) {
            table.addColumn(colomns[i]);
        }

        list.forEach(t->{
            if(t.getPrice()==price)
            {Object[] linie = new Object[7];
                for(int i=0;i<7;i++)
                    linie[i] = new Object();
                linie[0] = t.getTitle();linie[1] = t.getRating();linie[2] = t.getCalories();linie[3] = t.getProtein();linie[4] = t.getFat();linie[5] = t.getSodium();linie[6] = t.getPrice();
                table.addRow(linie);}});

        return table;
    }
    public DefaultTableModel searchByCalories(int calories) throws IOException, ClassNotFoundException {
        List<MenuItem> list=s.listDeserialization();
        DefaultTableModel table = new DefaultTableModel();
        String[] colomns=new String[7];
        colomns[0]="title";colomns[1]="rating";colomns[2]="calories";colomns[3]="protein";colomns[4]="fat";colomns[5]="sodium";colomns[6]="price";
        for (int i = 0; i < 7; i++) {
            table.addColumn(colomns[i]);
        }

        list.forEach(t->{
            if(t.getCalories()==calories)
            {Object[] linie = new Object[7];
                for(int i=0;i<7;i++)
                    linie[i] = new Object();
                linie[0] = t.getTitle();linie[1] = t.getRating();linie[2] = t.getCalories();linie[3] = t.getProtein();linie[4] = t.getFat();linie[5] = t.getSodium();linie[6] = t.getPrice();
                table.addRow(linie);}});

        return table;
    }
    public DefaultTableModel searchByFats(int fats) throws IOException, ClassNotFoundException {
        List<MenuItem> list=s.listDeserialization();
        DefaultTableModel table = new DefaultTableModel();
        String[] colomns=new String[7];
        colomns[0]="title";colomns[1]="rating";colomns[2]="calories";colomns[3]="protein";colomns[4]="fat";colomns[5]="sodium";colomns[6]="price";
        for (int i = 0; i < 7; i++) {
            table.addColumn(colomns[i]);
        }

        list.forEach(t->{
            if(t.getFat()==fats)
            {Object[] linie = new Object[7];
                for(int i=0;i<7;i++)
                    linie[i] = new Object();
                linie[0] = t.getTitle();linie[1] = t.getRating();linie[2] = t.getCalories();linie[3] = t.getProtein();linie[4] = t.getFat();linie[5] = t.getSodium();linie[6] = t.getPrice();
                table.addRow(linie);}});

        return table;
    }
    public DefaultTableModel searchByProteins(int proteins) throws IOException, ClassNotFoundException {
        List<MenuItem> list=s.listDeserialization();
        DefaultTableModel table = new DefaultTableModel();
        String[] colomns=new String[7];
        colomns[0]="title";colomns[1]="rating";colomns[2]="calories";colomns[3]="protein";colomns[4]="fat";colomns[5]="sodium";colomns[6]="price";
        for (int i = 0; i < 7; i++) {
            table.addColumn(colomns[i]);
        }

        list.forEach(t->{
            if(t.getProtein()==proteins)
            {Object[] linie = new Object[7];
                for(int i=0;i<7;i++)
                    linie[i] = new Object();
                linie[0] = t.getTitle();linie[1] = t.getRating();linie[2] = t.getCalories();linie[3] = t.getProtein();linie[4] = t.getFat();linie[5] = t.getSodium();linie[6] = t.getPrice();
                table.addRow(linie);}});

        return table;
    }
    public DefaultTableModel searchByRating(double rating) throws IOException, ClassNotFoundException {
        List<MenuItem> list=s.listDeserialization();
        DefaultTableModel table = new DefaultTableModel();
        String[] colomns=new String[7];
        colomns[0]="title";colomns[1]="rating";colomns[2]="calories";colomns[3]="protein";colomns[4]="fat";colomns[5]="sodium";colomns[6]="price";
        for (int i = 0; i < 7; i++) {
            table.addColumn(colomns[i]);
        }

        list.forEach(t->{
            if(t.getRating()==rating)
            {Object[] linie = new Object[7];
                for(int i=0;i<7;i++)
                    linie[i] = new Object();
                linie[0] = t.getTitle();linie[1] = t.getRating();linie[2] = t.getCalories();linie[3] = t.getProtein();linie[4] = t.getFat();linie[5] = t.getSodium();linie[6] = t.getPrice();
                table.addRow(linie);}});

        return table;
    }
    public DefaultTableModel searchBySodium(int sodium) throws IOException, ClassNotFoundException {
        List<MenuItem> list=s.listDeserialization();
        DefaultTableModel table = new DefaultTableModel();
        String[] colomns=new String[7];
        colomns[0]="title";colomns[1]="rating";colomns[2]="calories";colomns[3]="protein";colomns[4]="fat";colomns[5]="sodium";colomns[6]="price";
        for (int i = 0; i < 7; i++) {
            table.addColumn(colomns[i]);
        }

        list.forEach(t->{
            if(t.getSodium()==sodium)
            {Object[] linie = new Object[7];
                for(int i=0;i<7;i++)
                    linie[i] = new Object();
                linie[0] = t.getTitle();linie[1] = t.getRating();linie[2] = t.getCalories();linie[3] = t.getProtein();linie[4] = t.getFat();linie[5] = t.getSodium();linie[6] = t.getPrice();
                table.addRow(linie);}});

        return table;
    }



    }

