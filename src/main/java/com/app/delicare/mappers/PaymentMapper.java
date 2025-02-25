package com.app.delicare.mappers;

import com.app.delicare.dtos.category.PaymentDTO;
import com.app.delicare.entitys.category.Payment;
import com.app.delicare.mappers.base.BaseMapper;
import com.app.delicare.repositories.user.UserRepository;
import com.app.delicare.responses.category.PaymentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
@RequiredArgsConstructor
public class PaymentMapper extends BaseMapper {
    private final UserRepository userRepository;

    public Payment mapEntityToModel(PaymentDTO paymentDTO){
        Payment menu = Payment.builder()
                .paymentCode(paymentDTO.getPaymentCode())
                .paymentName(paymentDTO.getPaymentName())
                .build();
        menu.setStatus(paymentDTO.getStatus());
        return menu;
    }

    public PaymentResponse mapResponseToEntity(Payment payment){
        PaymentResponse paymentResponse = PaymentResponse.builder()
                .id(payment.getId())
                .paymentCode(payment.getPaymentCode())
                .paymentName(payment.getPaymentName())
                .build();

        Optional.ofNullable(payment)
                .map(Payment::getCreatedByUser)
                .ifPresent(user -> {
                    paymentResponse.setCreateByUserResponse(mapCreateByUserResponseToEntity(payment.getCreatedByUser()));
                });
        Optional.ofNullable(payment)
                .map(Payment::getModifiedByUser)
                .ifPresent(user -> {
                    paymentResponse.setModifiedByUserResponse(mapModifiedByUserResponseToEntity(payment.getModifiedByUser()));
                });
        paymentResponse.setDeleted(payment.getDeleted());
        paymentResponse.setDescription(payment.getDescription());
        paymentResponse.setCreatedAt(payment.getCreatedAt());
        paymentResponse.setCreatedAt(payment.getCreatedAt());
        paymentResponse.setModifiedAt(payment.getModifiedAt());
        paymentResponse.setStatus(payment.getStatus());
        return paymentResponse;
    }
}
