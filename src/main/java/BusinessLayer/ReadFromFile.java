package BusinessLayer;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

public class ReadFromFile {


        public List<MenuItem> readFromFile() throws IOException
        {
            Path path = Paths.get("D:\\PT\\PT2022_30223_Modrea_Elena_Assignment_4\\products.csv");
          List<MenuItem> baseProductList=  Files.lines(path)
                    .skip(1)
                    .map(line->{
                        String[] fields = line.split(",");
                        return new BaseProduct(fields[0],Double.parseDouble(fields[1]),Integer.parseInt(fields[2]),Integer.parseInt(fields[3]),Integer.parseInt(fields[4]),Integer.parseInt(fields[5]),(int)Double.parseDouble(fields[6]));
                    }).collect(Collectors.toList());
            List<MenuItem> unique = baseProductList.stream()
                    .collect(collectingAndThen(toCollection(() -> new TreeSet<>(comparing(MenuItem::getTitle))),
                            ArrayList::new));
            return unique;
        }
        public List<User> readUserFromFile() throws IOException {
            Path path = Paths.get("D:\\PT\\PT2022_30223_Modrea_Elena_Assignment_4\\usersLogin.txt");
            List<User> users=  Files.lines(path)
                    .map(line->{
                        String[] fields = line.split(",");

                        return new User(Integer.parseInt(fields[0]),fields[1],fields[2],fields[3]);
                    }).collect(Collectors.toList());
            return users;
        }
    }

