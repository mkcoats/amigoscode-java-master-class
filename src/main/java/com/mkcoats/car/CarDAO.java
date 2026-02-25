package com.mkcoats.car;

import java.math.BigDecimal;
import java.util.UUID;

public class CarDAO {
    private static final Car[] cars;

    static {
        cars = new Car[]{
                new Car(UUID.fromString("ba87b9e8-8ebe-4365-b50c-b659e1a02215"),"123", new BigDecimal("1.00"), Brand.AUDI, false),
                new Car(UUID.fromString("0cc8d22b-9e47-4408-aab5-eed54b91934d"),"234", new BigDecimal("2.00"), Brand.MERCEDES, false),
                new Car(UUID.fromString("787284eb-4e83-46f3-b784-55c73c6c4de9"),"345", new BigDecimal("3.00"), Brand.TESLA, true),
                new Car(UUID.fromString("e8404107-2246-4b6b-8505-883a45b66a0b"),"456", new BigDecimal("4.00"), Brand.TOYOTA, false),
                new Car(UUID.fromString("ed6b2a9c-5a53-4895-a762-679918836ccb"),"567", new BigDecimal("5.00"), Brand.TOYOTA, true)
        };
    }

    public Car[] getCars() {
        return cars;
    }
}
