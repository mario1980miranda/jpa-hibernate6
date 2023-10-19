package com.code.truck.ecommerce.listeners;

import com.code.truck.ecommerce.model.Order;
import com.code.truck.ecommerce.services.InvoiceService;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

public class InvoiceListener {

    private final InvoiceService invoiceService = new InvoiceService();

    @PrePersist
    @PreUpdate
    public void generate(Order order) {
        if (order.isPaid() && order.getInvoice() == null) {
            invoiceService.generate(order);
        }
    }
}
