package com.kloudwrangler.myfancypdfinvoices.service;
import com.kloudwrangler.myfancypdfinvoices.model.Invoice;
import com.kloudwrangler.myfancypdfinvoices.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class InvoiceService {

    private final UserService userService;

    List<Invoice> invoices = new CopyOnWriteArrayList<>();

    public InvoiceService(UserService userService){
        this.userService = userService;
    }
    public List<Invoice> findAll() {
        return invoices;
    }

    public Invoice create(String userId, Integer amount) {
        // TODO real pdf creation and storing it on network server
        User user = userService.findById(userId);
        if (user == null){
            throw new IllegalStateException();
        }
        Invoice invoice =  new Invoice(userId, amount, "http://www.africau.edu/images/default/sample.pdf");
        invoices.add(invoice);
        return invoice;
    }
    public UserService getUserService() {
        return userService;
    }
}
