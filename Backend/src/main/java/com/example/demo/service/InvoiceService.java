package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import com.example.demo.service.ProductService;
@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService product;
    @Autowired
    private ProductService productService;
    private Date today = new Date();




    // Hàm tạo hóa đơn
    public InvoiceResponse createInvoice(InvoiceRequest invoiceRequest) {

        // Tìm nhân viên từ ID
        Employee employee = employeeRepository.findById(invoiceRequest.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Optional<Invoice> existingInvoice = invoiceRepository.findByPhone(
                 invoiceRequest.getPhone());

        int purchaseCount = existingInvoice.map(Invoice::getPurchaseCount).orElse(0) + 1;
        Invoice invoice = new Invoice();
        invoice.setCustomerName(invoiceRequest.getCustomerName());
        invoice.setPhone(invoiceRequest.getPhone());
        invoice.setAddress(invoiceRequest.getAddress());
        invoice.setPurchaseDate(invoiceRequest.getPurchaseDate());
        invoice.setEmployee(employee);
        invoice.setPurchaseCount(purchaseCount);

        // Chuyển đổi từ InvoiceRequest sang Invoice

        // Chuyển đổi danh sách sản phẩm từ ProductDetail
        Invoice finalInvoice = invoice;
        List<InvoiceItem> items = invoiceRequest.getProducts().stream()
                .map(productDetail -> {

                    productService.reduceStock(productDetail.getName(), productDetail.getQuantity());
                    productService.checkFoodExpiry(productDetail.getName(), today);
                    // Tạo InvoiceItem
                    Product product = productRepository.findByName(productDetail.getName())
                            .orElseThrow(() -> new RuntimeException("Product not found"));
                    InvoiceItem item = new InvoiceItem();
                    item.setProduct(productRepository.findByName(productDetail.getName())
                            .orElseThrow(() -> new RuntimeException("Product not found")));
                    item.setQuantity(productDetail.getQuantity());
                    item.setInvoice(finalInvoice);







                    return item;
                })
                .collect(Collectors.toList());

        // Gắn các sản phẩm vào hóa đơn
        invoice.setItems(items);

        // Tính tổng tiền hóa đơn
        invoice.setTotalAmount(invoice.getTotalAmount());

        // Lưu hóa đơn vào cơ sở dữ liệu
        invoice = invoiceRepository.save(invoice);

        // Chuyển hóa đơn thành InvoiceResponse
        InvoiceResponse response = new InvoiceResponse(
                invoice.getId(),
                invoice.getEmployee().getName(),
                invoice.getCustomerName(),
                invoice.getPurchaseDate(),
                items.stream()
                        .map(item -> new ProductDetail(item.getProduct().getName(), item.getQuantity(), item.getProduct().getPrice()))
                        .collect(Collectors.toList()),
                invoice.getTotalAmount(),
                invoice.getPhone(),
                invoice.getAddress(),
                invoice.getPurchaseCount()
        );

        return response;
    }



    public InvoiceResponse getInvoice(Long invoiceId) {
        // Tìm hóa đơn theo ID
        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));

        // Tạo và trả về InvoiceResponse
        return new InvoiceResponse(
                invoice.getId(),
                invoice.getEmployee().getName(),
                invoice.getCustomerName(),
                invoice.getPurchaseDate(),
                invoice.getItems().stream()
                        .map(item -> new ProductDetail(item.getProduct().getName(), item.getQuantity(), item.getProduct().getPrice()))
                        .collect(Collectors.toList()),
                invoice.getTotalAmount(),

                invoice.getPhone(),
                invoice.getAddress(),
                invoice.getPurchaseCount()
        );
    }
    public List<InvoiceResponse> getAllInvoices() {
        // Lấy tất cả các hóa đơn từ repository
        List<Invoice> invoices = invoiceRepository.findAll();

        // Chuyển đổi danh sách hóa đơn thành danh sách InvoiceResponse
        return invoices.stream()
                .map(invoice -> new InvoiceResponse(
                        invoice.getId(),
                        invoice.getEmployee().getName(),
                        invoice.getCustomerName(),
                        invoice.getPurchaseDate(),
                        invoice.getItems().stream()
                                .map(item -> new ProductDetail(item.getProduct().getName(), item.getQuantity(), item.getProduct().getPrice()))
                                .collect(Collectors.toList()),
                        invoice.getTotalAmount(),
                        invoice.getPhone(),
                        invoice.getAddress(),
                        invoice.getPurchaseCount())



                )


                .collect(Collectors.toList());
    }


}