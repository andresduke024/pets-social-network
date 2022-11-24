package com.pruebasan.android_cesde_social_network.models.enums;

import java.util.Random;

public enum AvatarType {
    BEAR,
    CAT,
    CHICKEN,
    DOG,
    DOG2,
    GIRAFFE,
    ELK,
    KOALA,
    PANDA,
    RABBIT;

    public static AvatarType getRandom()  {
        Random random = new Random();
        AvatarType[] directions = values();
        return directions[random.nextInt(directions.length)];
    }
}
