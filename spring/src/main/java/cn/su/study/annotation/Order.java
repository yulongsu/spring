package cn.su.study.annotation;

import java.math.BigDecimal;


public class Order {
    @NotNull("orderId cannot null")
    private String orderId;

    @NotNull("amount cannot null")
    private BigDecimal amount;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
