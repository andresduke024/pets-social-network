package com.pruebasan.android_cesde_social_network.utils;

import com.pruebasan.android_cesde_social_network.R;
import com.pruebasan.android_cesde_social_network.models.enums.AvatarType;

public class AvatarResourcesFactory {
    public static int getResourceName(AvatarType type) {
        switch (type) {
            case BEAR: return R.drawable.bear;
            case CAT: return R.drawable.cat;
            case CHICKEN: return R.drawable.chicken;
            case DOG: return R.drawable.dog;
            case DOG2: return R.drawable.dog2;
            case GIRAFFE: return R.drawable.giraffe;
            case ELK: return R.drawable.jackalope;
            case KOALA: return R.drawable.koala;
            case PANDA: return R.drawable.panda;
            case RABBIT: return R.drawable.rabbit;
            default: return R.drawable.ic_launcher_background;
        }
    }

}
