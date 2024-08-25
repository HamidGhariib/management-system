package com.managementsystem.util.messagingrabbitmq;

import java.util.concurrent.CountDownLatch;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class Receiver {

    private CountDownLatch latch = new CountDownLatch(1);

    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
        latch.countDown();
    }

}
