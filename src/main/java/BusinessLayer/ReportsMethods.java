package BusinessLayer;

import Serialization.Serialization;

import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class ReportsMethods implements Serializable {
    Serialization s=new Serialization();
    DeliveryService d=new DeliveryService();
    public int hour(Date date){
      int h=0;
        String s=date.toString();
      String[] strings=s.split(" ");
      String[] hs=strings[3].split(":");
      h=Integer.parseInt(hs[0]);

        return h;
    }
    public String dayDate(Date date){
        String s=date.toString();
        String[] strings=s.split(" ");

        return strings[0];
    }
    public void writeReport1(List<Order> orders, int startHour, int endHour){
        try {
            FileWriter myWriter = new FileWriter("Report1.txt");

            String o="orders performed between "+startHour+" and  "+endHour+" regardless the date:"+"\n";

            for (Order t: orders) {
                o+=t.getIdClient()+" "+t.getIdOrder()+" "+t.getDate()+" "+"\n";

            }
            myWriter.write(o);
            myWriter.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void generateRaport1(int startHour, int endHour) throws IOException, ClassNotFoundException {
        Map<Order, List<MenuItem>> map=s.MapDeserialization();
        List<Order> list=new ArrayList<>();
        int h;
        map.forEach((u,v)->{
         if((hour(u.getDate())>=startHour)&&(hour(u.getDate())<=endHour))
             list.add(u);
        });
     writeReport1(list,startHour,endHour);

    }
    public void writeReport2(List<MenuItem> items, int number){
        try {
            FileWriter myWriter = new FileWriter("Report2.txt");

            String o=" products ordered more than "+number+" time so far"+"\n";

            for (MenuItem t: items) {
                o+=t.getTitle()+" "+t.getCalories()+" "+t.getFat()+" "+t.getProtein()+" "+t.getRating()+" "+t.getSodium()+" "+t.getPrice()+"\n";
            }
            myWriter.write(o);
            myWriter.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void generateReport2(int number) throws IOException, ClassNotFoundException {
        Map<Order, List<MenuItem>> map=s.MapDeserialization();
        List<MenuItem> allItems=new ArrayList<>();
        List<MenuItem> list=new ArrayList<>();
        map.forEach((u,v)->{
            v.forEach(t-> {
                allItems.addAll(v);

            });
        });

       TreeSet ts= allItems.stream()
                .collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(MenuItem::getTitle))));
       List<MenuItem> unique=new ArrayList<MenuItem>(ts);

        unique.forEach(t->{
            if(Collections.frequency(allItems,t)>=number)
                list.add(t);
        });
        System.out.println(list);
        writeReport2(list,number);
    }
    public void writeReport3(List<User> users, int number,int amount){
        try {
            FileWriter myWriter = new FileWriter("Report3.txt");

            String o=" the clients that have ordered more than "+number+" of times so far and the " +
                    "value of the order was higher than "+amount+"\n";

            for (User t: users) {
                o+=t.getUsername()+" "+t.getUserId()+"\n";
            }
            myWriter.write(o);
            myWriter.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void generateReport3(int number,int amount) throws IOException, ClassNotFoundException {
        Map<Order, List<MenuItem>> map=s.MapDeserialization();
        List<Integer> list=new ArrayList<>();
        map.forEach((u,v)->{
            list.add(u.getIdClient());
        });
        int k;
        LinkedHashSet<Integer> hashSet = new LinkedHashSet<>(list);
        ArrayList<Integer> listWithoutDuplicates = new ArrayList<>(hashSet);
        List<Integer> amountlist=new ArrayList<>();
        List<Integer> finalClients=new ArrayList<>();
        for(int t: listWithoutDuplicates){
            if(Collections.frequency(list,t)>=number)
            {
                List<Integer> finalAmountlist = amountlist;
                map.forEach((u, v)-> {
                if (u.getIdClient() == t)
                    if (d.totalPrice(v) < amount)
                      finalAmountlist.add(d.totalPrice(v));

             });
            if(amountlist.isEmpty())
            {
                finalClients.add(t);
            }
            else {
                amountlist=new ArrayList<>();
            }
            }
        }
        List<User> users=s.listUsersDeserialization();
        List<User> usersFinal=new ArrayList<>();
        finalClients.forEach(p->{
                users.forEach(t->{
                    if(t.getUserId()==p)
                        usersFinal.add(t);
                });
        });
      writeReport3(usersFinal,number,amount);

    }
    public void writeReport4(Map<MenuItem,Integer> map, String day){
        try {
            FileWriter myWriter = new FileWriter("Report4.txt");

            String o="the products ordered on  "+day +"\n";

            for (Map.Entry<MenuItem,Integer> entry : map.entrySet())
                    o+=entry.getKey().getTitle()+" "+entry.getKey().getCalories()+" "+entry.getKey().getFat()+" "+entry.getKey().getProtein()+" "+entry.getKey().getRating()+" "+entry.getKey().getSodium()+" "+entry.getKey().getPrice()+" and the number of times: "+entry.getValue()+"\n";
            myWriter.write(o);
            myWriter.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void generateReport4(String day) throws IOException, ClassNotFoundException {
        Map<Order, List<MenuItem>> map=s.MapDeserialization();
        Map<MenuItem,Integer> finalMap=new HashMap<>();
        List<MenuItem> list=new ArrayList<>();
        List<MenuItem> allItems=new ArrayList<>();
       /* map.forEach((u,v)->{
            if(dayDate(u.getDate()).equals(day))
                list.addAll(v);
        });

        TreeSet ts= list.stream()
                .collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(MenuItem::getTitle))));
        List<MenuItem> unique=new ArrayList<MenuItem>(ts);
        System.out.println(list);
        for (MenuItem t: unique)
        {System.out.println( Collections.frequency(list,t));
            finalMap.put(t,Collections.frequency(list,t));
        }*/
        map.forEach((u,v)->{
            v.forEach(t-> {
                if(dayDate(u.getDate()).equals(day))
                allItems.addAll(v);

            });
        });

        TreeSet ts= allItems.stream()
                .collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(MenuItem::getTitle))));
        List<MenuItem> unique=new ArrayList<MenuItem>(ts);

        unique.forEach(t->{
            System.out.println( Collections.frequency(allItems,t));
            finalMap.put(t,Collections.frequency(allItems,t));
        });
   writeReport4(finalMap,day);
    }
}
