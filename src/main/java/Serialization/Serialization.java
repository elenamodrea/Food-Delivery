package Serialization;

import BusinessLayer.*;
import BusinessLayer.MenuItem;

import java.awt.*;
import java.io.*;
import java.util.List;
import java.util.Map;

public class Serialization {
    public void listSerialization(List<MenuItem> list) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("BaseProduct.bin"));
       objectOutputStream.writeObject(list);
    }
    public List<MenuItem> listDeserialization() throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream=new ObjectInputStream(new FileInputStream("BaseProduct.bin"));
        List<MenuItem> menuItems=(List<MenuItem>) objectInputStream.readObject();
        return menuItems;
    }
    public void listUsersSerialization(List<User> list) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("User.bin"));
        objectOutputStream.writeObject(list);
    }
    public List<User> listUsersDeserialization() throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream=new ObjectInputStream(new FileInputStream("User.bin"));
        List<User> users=(List<User>) objectInputStream.readObject();
        return users;
    }
    public void MapSerialization(Map<Order,List<MenuItem>> list) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("map.bin"));
        objectOutputStream.writeObject(list);
    }
    public Map<Order,List<MenuItem>> MapDeserialization() throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream=new ObjectInputStream(new FileInputStream("map.bin"));
        Map<Order,List<MenuItem>> listMap=(Map<Order,List<MenuItem>>) objectInputStream.readObject();
        return listMap;
    }
}
