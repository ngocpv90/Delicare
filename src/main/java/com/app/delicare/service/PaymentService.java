package com.app.delicare.service;

import com.app.delicare.dtos.PaymentDTO;
import com.app.delicare.entitys.Payment;
import com.app.delicare.entitys.User;
import com.app.delicare.mappers.PaymentMapper;
import com.app.delicare.repositories.PaymentRepository;
import com.app.delicare.repositories.UserRepository;
import com.app.delicare.responses.PaymentResponse;
import com.app.delicare.service.implement.IPaymentService;
import com.app.delicare.specification.PaymentSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class PaymentService implements IPaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final UserRepository userRepository;

    @Override
    public PaymentResponse createPayment(PaymentDTO paymentDTO) {
        try{
            Payment payment = paymentMapper.mapEntityToModel(paymentDTO);
            paymentRepository.save(payment);
            paymentRepository.flush();
            return paymentMapper.mapResponseToEntity(payment);
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public List<PaymentResponse> getAllPayment() {
        Specification<Payment> specification = Specification.where(PaymentSpecification.isNotDeleted());
        return paymentRepository.findAll(specification).stream().map(payment -> {
                    return  paymentMapper.mapResponseToEntity(payment);
                })
                .toList();
    }

    @Override
    public Page<PaymentResponse> getListPayment(PageRequest pageRequest) {
        Specification<Payment> specification = Specification.where(PaymentSpecification.isNotDeleted());
        return paymentRepository.findAll(specification, pageRequest).map(payment -> {
            return paymentMapper.mapResponseToEntity(payment);
        });
    }

    @Override
    public PaymentResponse updatePayment(Long id, PaymentDTO paymentDTO) {
        User userModified = userRepository.getReferenceById(paymentDTO.getModifiedById());
        Payment payment = paymentMapper.mapEntityToModel(paymentDTO);
        payment.setId(id);
        payment.setModifiedByUser(userModified);
        paymentRepository.save(payment);
        return paymentMapper.mapResponseToEntity(payment);
    }

    @Override
    public PaymentResponse getPaymentById(Long id) {
        Payment payment = paymentRepository.getReferenceById(id);
        return paymentMapper.mapResponseToEntity(payment);
    }

    @Override
    public void deletePayment(Long id) {

    }
}
