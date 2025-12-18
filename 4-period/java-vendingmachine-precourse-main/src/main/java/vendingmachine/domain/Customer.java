package vendingmachine.domain;

import vendingmachine.exception.ExceptionMessage;

public class Customer {

    private int userMoney;

    public Customer(int userMoney) {
        validateMoney(userMoney);
        this.userMoney = userMoney;
    }

    private void validateMoney(int userMoney) {
        if (userMoney <= 0) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_USER_MONEY.getMessage());
        }
    }

    public void purchase(Product product) {
        userMoney -= product.getPrice();
    }

    public int getUserMoney() {
        return userMoney;
    }
}
