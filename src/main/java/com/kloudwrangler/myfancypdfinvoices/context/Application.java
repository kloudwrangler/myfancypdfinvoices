package com.kloudwrangler.myfancypdfinvoices.context;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kloudwrangler.myfancypdfinvoices.service.InvoiceService;
import com.kloudwrangler.myfancypdfinvoices.service.UserService;

public class Application {
    public static final UserService userService = new UserService();
    public static final InvoiceService invoiceService = new InvoiceService(userService);
    public static final ObjectMapper objectMapper = new ObjectMapper();
}
