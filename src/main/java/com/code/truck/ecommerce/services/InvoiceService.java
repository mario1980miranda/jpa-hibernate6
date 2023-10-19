package com.code.truck.ecommerce.services;

import com.code.truck.ecommerce.model.Order;

public class InvoiceService {

    public void generate(Order order) {
        System.out.println("**** Invoice generated for Order: " + order.getId());
    }
}
