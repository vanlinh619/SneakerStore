package com.ale.sneakerstoreapi.mapper.input;

import com.ale.sneakerstoreapi.entity.Order;
import com.ale.sneakerstoreapi.entity.OrderDetail;
import com.ale.sneakerstoreapi.entity.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderInput {
    @Valid
    @NotEmpty
    private List<OrderDetailInput> orderDetails;
    @NotBlank
    private String userId;

    public Order toOrder(ModelMapper mapper){
        Order order = new Order();
        order.setOrderDetails(orderDetails.stream()
                .map(orderDetailInput -> {
                    OrderDetail orderDetail = orderDetailInput.toOrderDetail(mapper);
                    orderDetail.setOrder(order);
                    return orderDetail;
                })
                .toList()
        );
        order.setUser(new User().builder().uuid(UUID.fromString(userId)).build());
        return order;
    }
}
