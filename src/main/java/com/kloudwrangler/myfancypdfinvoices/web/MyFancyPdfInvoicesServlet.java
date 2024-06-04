package com.kloudwrangler.myfancypdfinvoices.web;

import com.kloudwrangler.myfancypdfinvoices.context.MyFancyPdfInvoicesApplicationConfiguration;
import com.kloudwrangler.myfancypdfinvoices.model.Invoice;
import com.kloudwrangler.myfancypdfinvoices.service.InvoiceService;
import com.kloudwrangler.myfancypdfinvoices.service.UserService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.util.List;

public class MyFancyPdfInvoicesServlet extends HttpServlet {
    private ObjectMapper objectMapper;
    private InvoiceService invoiceService;
    private UserService userService;

    @Override
    public void init() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MyFancyPdfInvoicesApplicationConfiguration.class);

        ctx.registerShutdownHook();

        this.userService = ctx.getBean(UserService.class);
        this.objectMapper = ctx.getBean(ObjectMapper.class);
        this.invoiceService = ctx.getBean(InvoiceService.class);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getRequestURI().equalsIgnoreCase("/")) {
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().print(
                    """
                            <html>
                            <body>
                            <h1>Hello World</h1>
                            <p>This is my very first, embedded Tomcat, HTML Page!</p>
                            </body>
                            </html>""");
        }
        else if (request.getRequestURI().equalsIgnoreCase("/invoices")){
            response.setContentType("application/json");
            List<Invoice> invoices = invoiceService.findAll();
            response.getWriter().print(objectMapper.writeValueAsString(invoices));
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getRequestURI().equalsIgnoreCase("/invoices")) {
            String userId = request.getParameter("user_id");
            Integer amount = Integer.valueOf(request.getParameter("amount"));
            Invoice invoice = invoiceService.create(userId, amount);
            response.setContentType("application/json");
            String json = objectMapper.writeValueAsString(invoice);
            response.getWriter().print(json);

        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}