package com.app.delicare.service.implement;

import com.app.delicare.dtos.MenuDTO;
import com.app.delicare.dtos.PaymentDTO;
import com.app.delicare.responses.MenuResponse;
import com.app.delicare.responses.PaymentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface IPaymentService {
    PaymentResponse createPayment(PaymentDTO paymentDTO);
    List<PaymentResponse> getAllPayment();
    Page<PaymentResponse> getListPayment(PageRequest pageRequest);
    PaymentResponse updatePayment(Long id, PaymentDTO paymentDTO);
    PaymentResponse getPaymentById(Long id);
    void deletePayment(Long id);
}
