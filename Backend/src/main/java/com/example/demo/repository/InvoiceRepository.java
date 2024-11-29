package com.example.demo.repository;

import com.example.demo.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    Optional<Invoice> findByPhone( String phone);


}
