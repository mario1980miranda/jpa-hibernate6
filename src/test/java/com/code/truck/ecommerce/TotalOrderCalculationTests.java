package com.code.truck.ecommerce;

import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TotalOrderCalculationTests {

    final BigDecimal BIG = new BigDecimal("42.42");
    final Integer INT = 10;
    final BigDecimal EXPECTED = new BigDecimal("424.2");

    @Test
    public void multiplyBigDecimalToIntegerShouldResultInANewBigDecimal() {

        BigDecimal result = BIG.multiply(BigDecimal.valueOf(INT));

        Assertions.assertEquals(0, EXPECTED.compareTo(result));
    }

    @Test
    public void multiplyListOfBigDecimalToIntegerShouldWork() {

        final BigDecimal DUMMY_ORDER_TOTAL_RESULT_EXPECTED = new BigDecimal("35.00");

        DummyOrder order = new DummyOrder();

        DummyOrderItem firstOrderItem = new DummyOrderItem();
        firstOrderItem.setPrice(new BigDecimal("10.00"));
        firstOrderItem.setQuantity(2);

        order.addItem(firstOrderItem);

        DummyOrderItem secondOrderItem = new DummyOrderItem();
        secondOrderItem.setPrice(new BigDecimal("3.00"));
        secondOrderItem.setQuantity(5);

        order.addItem(secondOrderItem);

        BigDecimal result = order.getItems()
                .stream()
                .map(i -> i.getPrice().multiply(BigDecimal.valueOf(i.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Assertions.assertEquals(0, DUMMY_ORDER_TOTAL_RESULT_EXPECTED.compareTo(result));
    }

}

@Getter
class DummyOrder {
    private List<DummyOrderItem> items = new ArrayList<>();

    public void addItem(DummyOrderItem item) {
        items.add(item);
    }
}

@Setter
@Getter
class DummyOrderItem {
    private BigDecimal price;
    private Integer quantity;
}
