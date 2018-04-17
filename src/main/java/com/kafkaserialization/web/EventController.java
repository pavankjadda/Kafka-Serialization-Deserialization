package com.kafkaserialization.web;

import com.kafkaserialization.model.Order;
import com.kafkaserialization.service.SendEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Random;
import java.util.TimeZone;
import java.util.concurrent.ExecutionException;

@RestController
public class EventController
{
    private SendEvent sendEvent;

    @Autowired
    public EventController(SendEvent    sendEvent)
    {
        this.sendEvent=sendEvent;
    }

    @GetMapping("/sendorder")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Order sendOrderEvent() throws ExecutionException, InterruptedException
    {
        Random random=new Random(1);
        //Send Order Event
        Order order=new Order();
        order.setOrderId("ORD"+random.nextInt());
        //order.setOrderId("ORD1001");
        order.setCustomerId("CU1001");
        order.setOrderItemName("Reebok Shoes");
        order.setOrderPlace("NewYork,NY");
        order.setOrderPurchaseTime(getCurrentTime());
        sendEvent.sendOrderEvent(order);
        return order;
    }

    public String getCurrentTime()
    {
        Calendar calendar=Calendar.getInstance(TimeZone.getDefault());
        return calendar.getTime().toString();
    }
}
