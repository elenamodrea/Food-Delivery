package BusinessLayer;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {
    private int idOrder;
    private int idClient;
    private Date date;

    public Order(int idOrder, int idClient, Date date) {
        this.idOrder = idOrder;
        this.idClient = idClient;
        this.date = date;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
