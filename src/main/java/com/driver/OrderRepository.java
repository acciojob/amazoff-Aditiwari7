package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class OrderRepository {
    Map<String,Order> orderMap;
    Map<String, Integer> unassignedOrders;
    Map<String, List<String>> pair;

    public OrderRepository(){
        this.orderMap = new HashMap<>();
        this.unassignedOrders = new HashMap<>();
        this.pair = new HashMap<>();
    }

    public void addOrder(Order order){
        orderMap.put(order.getId(), order);
        unassignedOrders.put(order.getId(), 0);
    }

    public void addPartner(String partnerId){
        List<String> list = new ArrayList<>();
        pair.put(partnerId, list);
    }

    public Order getOrderById(String orderId){
        return orderMap.get(orderId);
    }

    public DeliveryPartner getPartnerById(String Id){
        DeliveryPartner deliveryPartner = new DeliveryPartner(Id);
        deliveryPartner.setNumberOfOrders(pair.get(Id).size());
        return deliveryPartner;
    }

    public int getOrderCountByPartnerId(String Id){
        return pair.get(Id).size();
    }

    public void deleteOrderById(String id){
        orderMap.remove(id);
    }

    public void deletePartnerById(String id){
        pair.remove(id);
    }

    public void addOrderPartnerPair(String orderId, String patnerId){
        if ( unassignedOrders.get(orderId) == 0) {
            List<String> listOfOrders = pair.get(patnerId);
            listOfOrders.add(orderId);
            pair.replace(patnerId, listOfOrders);

            unassignedOrders.replace(orderId, 1);
        }
    }

    public List<String> getOrdersByPartnerId(String id) {
        List<String> ordersOfPatner= pair.get(id);
        return ordersOfPatner;
    }

    public List<String> getAllOrders() {
        List<String> orders=new ArrayList<>();
        for(String key: orderMap.keySet())
        {
            orders.add(orderMap.get(key).getId());
        }
        return orders;
    }

    public int getCountOfUnassignedOrders() {
        int count=0;
        if(!orderMap.isEmpty()) {
            for (String key : orderMap.keySet()) {
                if (unassignedOrders.get(key) == 0) {
                    count++;
                }
            }
        }
        return  count;
    }

    public String getLastDeliveryTimeByPartnerId(String patnerId) {
        String lastDeliveryTime="";
        List<String> allOrdersOfPartner=pair.get(patnerId);

        for (int i=0; i<allOrdersOfPartner.size(); i++){
            Order order=orderMap.get(allOrdersOfPartner.get(i));

            int deliveryTime=order.getDeliveryTime();  //which is in int -> we have to convert in into string;
            int hour=deliveryTime/60;
            int minute=deliveryTime % 60;

            lastDeliveryTime=""+hour+":";
            if (minute < 10){
                lastDeliveryTime=lastDeliveryTime+"0"+minute;
            }
            else {
                lastDeliveryTime=lastDeliveryTime+minute;
            }
        }
        return lastDeliveryTime;
    }

    public int getOrdersLeftAfterGivenTimeByPartnerId(String time,String patnerId) {
        int hour=0;
        int minute=0;
        for (int i=0; i<2; i++) {
            hour=(hour*10)+ (int)time.charAt(i) -'0';
        }
        for (int i=3; i<5; i++) {
            minute=(minute*10)+ (int)time.charAt(i) -'0';
        }
        System.out.println(hour+" "+minute);

        int currentTime=hour*60 + minute;
        System.out.println(currentTime);

        List<String> listOfOrders=pair.get(patnerId);

        int count=0;
        //traverse throung list of orders
        for (int i=0; i<listOfOrders.size(); i++) {
            Order order=orderMap.get(listOfOrders.get(i));
            System.out.println(order.getDeliveryTime()+" "+currentTime);
            if (order.getDeliveryTime()> currentTime) {
                count++;
            }
        }
        System.out.println(count);
        return  count;
    }
}
