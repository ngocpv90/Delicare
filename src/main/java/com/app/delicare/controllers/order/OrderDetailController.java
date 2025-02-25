package com.app.delicare.controllers.order;

import com.app.delicare.common.enums.EAction;
import com.app.delicare.common.enums.EFunction;
import com.app.delicare.component.MessageUtils;
import com.app.delicare.dtos.order.OrderDTO;
import com.app.delicare.dtos.order.OrderDetailDTO;
import com.app.delicare.responses.base.SystemResponse;
import com.app.delicare.responses.order.OrderDetailResponse;
import com.app.delicare.responses.user.UserResponse;
import com.app.delicare.service.OrderDetailService;
import com.app.delicare.service.common.CommonService;
import com.app.delicare.utils.MessageString;
import com.app.delicare.utils.WebUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/orderDetail")
public class OrderDetailController {
    private final CommonService commonService;
    private final MessageUtils messageUtils;
    private final OrderDetailService orderDetailService;

    @GetMapping("/listPage")
    public ResponseEntity<?> getListPageOrderDetail(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
    ){
        try {
            if(!commonService.hasAccessPermission("", EFunction.ORDER_DETAIL.getValue(), EAction.READ.getValue())){
                return ResponseEntity.badRequest().body(SystemResponse.builder()
                        .message(messageUtils.getLocalizationMessage(MessageString.SYSTEM_PERMISSION))
                        .build());
            }

            PageRequest pageRequest = PageRequest.of(
                    page, limit,
                    Sort.by("createdAt").descending());
            Page<OrderDetailResponse> orderDetailResponses = orderDetailService.getListOrderDetail(pageRequest);
            return ResponseEntity.ok(SystemResponse.builder()
                    .data(orderDetailResponses.getContent())
                    .totalRow(orderDetailResponses.getTotalElements())
                    .totalPages(orderDetailResponses.getTotalPages())
                    .build());
        }catch (Exception e){
            return ResponseEntity.badRequest()
                    .body(messageUtils.getLocalizationMessage(MessageString.SYSTEM_DATA_NOT_FOUND));
        }
    }

    @GetMapping("/listAll")
    public ResponseEntity<?> getListAllOrderDetail(@RequestBody OrderDTO orderDTO){
        try {
            if(!commonService.hasAccessPermission("", EFunction.ORDER_DETAIL.getValue(), EAction.READ.getValue())){
                return ResponseEntity.badRequest().body(SystemResponse.builder()
                        .message(messageUtils.getLocalizationMessage(MessageString.SYSTEM_PERMISSION))
                        .build());
            }

            List<OrderDetailResponse> orderDetailResponses = orderDetailService.getAllOrderDetail();
            return ResponseEntity.ok(SystemResponse.builder()
                    .data(orderDetailResponses)
                    .totalRow(orderDetailResponses.stream().count())
                    .totalPages(0)
                    .build());
        }catch (Exception e){
            return ResponseEntity.badRequest()
                    .body(messageUtils.getLocalizationMessage(MessageString.SYSTEM_DATA_NOT_FOUND));
        }
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getOrderDetailById(@PathVariable Long id){
        try {
            if(!commonService.hasAccessPermission("", EFunction.ORDER_DETAIL.getValue(), EAction.READ.getValue())){
                return ResponseEntity.badRequest().body(SystemResponse.builder()
                        .message(messageUtils.getLocalizationMessage(MessageString.SYSTEM_PERMISSION))
                        .build());
            }
            return ResponseEntity.ok(orderDetailService.getOrderDetailById(id));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(messageUtils.getLocalizationMessage(MessageString.SYSTEM_DATA_NOT_FOUND));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createOrderDetail(
            @Valid @RequestBody OrderDetailDTO orderDetailDTO,
            BindingResult result
    ){
        try{
            if(result.hasErrors()){
                List<String> errorMessage = result.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                return ResponseEntity.badRequest().body(errorMessage);
            }

            if(!commonService.hasAccessPermission("", EFunction.ORDER_DETAIL.getValue(), EAction.CREATE.getValue())){
                return ResponseEntity.badRequest().body(messageUtils.getLocalizationMessage(MessageString.SYSTEM_PERMISSION));
            }

            UserResponse userAuthentication = commonService.getUserLogin(WebUtils.getAuthentication());
            if(userAuthentication == null){
                return ResponseEntity.badRequest().body(messageUtils.getLocalizationMessage(MessageString.SYSTEM_PERMISSION));
            }
            orderDetailDTO.setCreatedById(userAuthentication.getId());
            OrderDetailResponse orderDetailResponse = orderDetailService.createOrderDetail(orderDetailDTO);
            return ResponseEntity.ok(SystemResponse.builder()
                    .message(messageUtils.getLocalizationMessage(MessageString.DEPARTMENT_CREATE_SUCCESSFULLY))
                    .id(orderDetailResponse.getId())
                    .build()
            );
        } catch(Exception e){
            return ResponseEntity.badRequest().body(messageUtils.getLocalizationMessage(MessageString.DEPARTMENT_CREATE_FAILED));
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateOrderDetail(@PathVariable Long id, OrderDetailDTO orderDetailDTO){
        try {
            if(commonService.hasAccessPermission("", EFunction.ORDER_DETAIL.getValue(), EAction.UPDATE.getValue())){
                return ResponseEntity.badRequest().body(SystemResponse.builder()
                        .message(messageUtils.getLocalizationMessage(MessageString.SYSTEM_PERMISSION))
                        .build());
            }

            UserResponse userAuthentication = commonService.getUserLogin(WebUtils.getAuthentication());
            if(userAuthentication == null){
                return ResponseEntity.badRequest().body(messageUtils.getLocalizationMessage(MessageString.SYSTEM_PERMISSION));
            }
            orderDetailDTO.setModifiedById(userAuthentication.getId());

            orderDetailService.updateOrderDetail(id, orderDetailDTO);
            return ResponseEntity.ok(messageUtils.getLocalizationMessage(MessageString.SYSTEM_UPDATE_SUCCESSFULLY));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(messageUtils.getLocalizationMessage(MessageString.SYSTEM_UPDATE_FAILED));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteOrderDetail(@PathVariable Long id){
        try{
            orderDetailService.deleteOrderDetail(id);
            return ResponseEntity.ok(messageUtils.getLocalizationMessage(MessageString.SYSTEM_DELETE_SUCCESSFULLY));
        } catch (Exception e){
            return ResponseEntity.badRequest()
                    .body(messageUtils.getLocalizationMessage(MessageString.SYSTEM_UPDATE_FAILED));
        }
    }
}
