package com.taoz27.heavyweapontest;

public enum  PlaneImage {
    bigbomber(0),
    blimp(1),
    bomber(2),
    deflector(3),
    deltabomber(4),
    deltajet(5),
    jetfighter(6),
    pupcopter(7),
    smalljet(8);

    int value;
    PlaneImage(int value){
        this.value=value;
    }

    public int getValue(){
        return value;
    }
}
