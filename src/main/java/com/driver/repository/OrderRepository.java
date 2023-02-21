package com.driver.repository;

import com.driver.model.DeliveryPartner;
import com.driver.model.Order;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class OrderRepository {

    private Map<DeliveryPartner,List<Order>> orderDeliveryPartnerPair = new HashMap<>();
    private ArrayList<DeliveryPartner> partners = new ArrayList<>();
    private ArrayList<Order> orders = new ArrayList<>();
    private ArrayList<Order> assignedOrders = new ArrayList<>();


}
