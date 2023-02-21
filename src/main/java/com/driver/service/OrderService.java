package com.driver.service;

import com.driver.model.DeliveryPartner;
import com.driver.model.Order;
import com.driver.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    public void addOrder(Order order){
        orderRepository.getOrders().add(order);
    }

    public void addPartner(String partnerId) {
        orderRepository.getPartners().add(new DeliveryPartner(partnerId));
    }

    public Order getOrderById(String orderId) {
        for (Order order : orderRepository.getOrders()) {
            if (order.getId().equals(orderId)) {
                return order;
            }
        }
        return null;
    }

    public DeliveryPartner getDeliveryPartnerById(String id){
        for(DeliveryPartner deliveryPartner: orderRepository.getPartners()){
            if(Objects.equals(deliveryPartner.getId(), id)){
                return deliveryPartner;
            }
        }
        return null;
    }

    public void addOrderPartnerPair (String orderId, String partnerId) {
            Order order = getOrderById(orderId);
            DeliveryPartner deliveryPartner = getDeliveryPartnerById(partnerId);
            deliveryPartner.setNumberOfOrders(deliveryPartner.getNumberOfOrders()+1);
            if(!orderRepository.getOrderDeliveryPartnerPair().containsKey(deliveryPartner)){
                ArrayList<Order> orders = new ArrayList<>();
                orders.add(order);
                orderRepository.getOrderDeliveryPartnerPair().put(deliveryPartner,orders);
            }
            orderRepository.getOrderDeliveryPartnerPair().get(deliveryPartner).add(order);
            orderRepository.getAssignedOrders().add(order);
        }

    public Integer getOrderCountByPartnerId(String partnerId) {
        return getDeliveryPartnerById(partnerId).getNumberOfOrders();
    }

    public List<String> getOrdersByPartnerId(String partnerId) {
        List<String> orders = new ArrayList<>();
        for(Order order: orderRepository.getOrderDeliveryPartnerPair().get(getDeliveryPartnerById(partnerId))){
            orders.add(order.getId());
        }
        return orders;
    }

    public List<String> getAllOrders() {
        List<String> orders = new ArrayList<>();
        for(Order order: orderRepository.getOrders()){
            orders.add(order.getId());
        }
        return orders;
    }

    public Integer getCountOfUnassignedOrders() {
        return orderRepository.getOrders().size() - orderRepository.getAssignedOrders().size();
//        return Math.toIntExact(orderRepository.getOrders().stream().filter(x -> !orderRepository.getAssignedOrders().contains(x)).count());
    }
}

