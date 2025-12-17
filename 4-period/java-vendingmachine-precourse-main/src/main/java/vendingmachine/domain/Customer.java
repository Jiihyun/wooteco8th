package vendingmachine.domain;

public class Customer {

    private int userMoney;

    public Customer(int userMoney) {
        this.userMoney = userMoney;
    }

    public void purchase(Product product) {
        userMoney -= product.getPrice();
    }

    public int getUserMoney() {
        return userMoney;
    }
}
