package ro.tuc.tp.BusinessLogic;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Clasa Order modeleaza o comanda care se poate plasa
 * @author Pop Crina-Maria
 */
public class Order implements Serializable {
    private int orderId;
    private String clientId;
    private LocalDateTime orderDate;
    private int price;

    /**
     * Constructorul cu parametrii care seteaza proprietatile acestei clase
     */
    public Order(int idOrder, String idClient, LocalDateTime date, int price) {
        this.orderId = idOrder;
        this.clientId = idClient;
        this.orderDate = date;
        this.price = price;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return orderId == order.orderId && clientId == order.getClientId()
                && orderDate.equals(order.orderDate) && price == order.price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, clientId, orderDate);
    }

    public String toString() {
        return "Order no. " + orderId + "\nClient: " + clientId + "\nDate: " + orderDate;
    }
}
