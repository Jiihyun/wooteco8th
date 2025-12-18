package vendingmachine.domain;

public class Customer {

    private int userMoney;

    public Customer(int userMoney) {
        this.userMoney = userMoney;
    }

    public int purchase(Product product) {
        return userMoney -= product.getPrice();
    }
}
