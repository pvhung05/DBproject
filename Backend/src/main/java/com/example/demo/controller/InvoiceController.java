package com.example.demo.controller;

import com.example.demo.dto.InvoiceRequest;
import com.example.demo.dto.InvoiceResponse;
import com.example.demo.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;



    // API để tạo hóa đơn mới
    @PostMapping
    public InvoiceResponse createInvoice(@RequestBody InvoiceRequest request) {
        return invoiceService.createInvoice(request);
    }
    @GetMapping("/{invoiceId}")
    public InvoiceResponse getInvoice(@PathVariable Long invoiceId) {
        return invoiceService.getInvoice(invoiceId);

    }
    @GetMapping
    public ResponseEntity<List<InvoiceResponse>> getAllInvoices() {
        List<InvoiceResponse> invoiceResponses = invoiceService.getAllInvoices();
        return ResponseEntity.ok(invoiceResponses);
    }



}
