package BusinessLayer;

import java.io.Serializable;
import java.util.List;

public class CompositeProduct extends MenuItem implements Serializable {
private String name;
private List<BaseProduct> products;

    public CompositeProduct(String name, List<BaseProduct> products) {
        this.name = name;
        this.products = products;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BaseProduct> getProducts() {
        return products;
    }

    public void setProducts(List<BaseProduct> products) {
        this.products = products;
    }

    @Override
    public String getTitle() {
        return name;
    }

    @Override
    public double getRating() {
        double rating=0.0;
        for (BaseProduct t: products) {
            rating+=t.getRating();
        }
        return rating;
    }

    @Override
    public int getCalories() {
       int calories=0;
       for(BaseProduct t:products){
           calories+=t.getCalories();
       }
       return calories;
    }

    @Override
    public int getProtein() {
        int protein=0;
        for (BaseProduct t: products) {
            protein +=t.getProtein();
        }
        return protein;
    }

    @Override
    public int getFat() {
        int fat=0;
        for(BaseProduct t:products){
            fat+=t.getFat();
        }
        return fat;
    }

    @Override
    public int getSodium() {
        int sodium=0;
        for (BaseProduct t: products) {
            sodium+=t.getSodium();
        }
        return sodium;
    }
    @Override
    public int getPrice() {
        int price = 0;
        for (BaseProduct t: products) {
            price+=t.getPrice();
        }
        return price;
    }
}
