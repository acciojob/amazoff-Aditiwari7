package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderRepository repository;

    public void addOrder(Order order){
        repository.addOrder(order);
    }

    public void addPartner(String partnerId){
        repository.addPartner(partnerId);
    }

    public Order getOrderById(String orderId){
        return repository.getOrderById(orderId);
    }

    public DeliveryPartner getPartnerById(String id){
        return repository.getPartnerById(id);
    }

    public int getOrderCountByPartnerId(String id){
        return repository.getOrderCountByPartnerId(id);
    }

    public void deleteOrderById(String id){
        repository.deleteOrderById(id);
    }

    public void deletePartnerById(String id){
        repository.deletePartnerById(id);
    }

    public void addOrderPartnerPair(String orderId, String partnerId){
        repository.addOrderPartnerPair(orderId, partnerId);
    }

    public List<String> getOrdersByPartnerId(String Id){
        return repository.getOrdersByPartnerId(Id);
    }

    public List<String> getAllOrders(){
        return repository.getAllOrders();
    }

    public String getLastDeliveryTimeByPartnerId(String id){
        return repository.getLastDeliveryTimeByPartnerId(id);
    }

    public int getOrdersLeftAfterGivenTimeByPartnerId(String time, String partnerId){
        return repository.getOrdersLeftAfterGivenTimeByPartnerId(time, partnerId);
    }

    public int getCountOfUnassignedOrders(){
        return repository.getCountOfUnassignedOrders();
    }
}
