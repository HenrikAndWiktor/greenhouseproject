package se.henrikeriksson.greenhouse.resources;

import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;

public class GpioPins {
    @NotNull private static final Pin PIN_3 = RaspiPin.GPIO_08;
    @NotNull private static final Pin PIN_5 = RaspiPin.GPIO_09;
    @NotNull private static final Pin PIN_7 = RaspiPin.GPIO_07;
    @NotNull private static final Pin PIN_8 = RaspiPin.GPIO_15;
    @NotNull private static final Pin PIN_10 = RaspiPin.GPIO_16;
    @NotNull private static final Pin PIN_11 = RaspiPin.GPIO_00;
    @NotNull private static final Pin PIN_12 = RaspiPin.GPIO_01;
    @NotNull private static final Pin PIN_13 = RaspiPin.GPIO_02;
    @NotNull private static final Pin PIN_15 = RaspiPin.GPIO_03;
    @NotNull private static final Pin PIN_16 = RaspiPin.GPIO_04;
    @NotNull private static final Pin PIN_18 = RaspiPin.GPIO_05;
    @NotNull private static final Pin PIN_19 = RaspiPin.GPIO_12;
    @NotNull private static final Pin PIN_21 = RaspiPin.GPIO_13;
    @NotNull private static final Pin PIN_22 = RaspiPin.GPIO_06;
    @NotNull private static final Pin PIN_23 = RaspiPin.GPIO_14;
    @NotNull private static final Pin PIN_24 = RaspiPin.GPIO_10;
    @NotNull private static final Pin PIN_26 = RaspiPin.GPIO_11;
    @NotNull private static final Pin PIN_27 = RaspiPin.GPIO_30;
    @NotNull private static final Pin PIN_28 = RaspiPin.GPIO_31;
    @NotNull private static final Pin PIN_29 = RaspiPin.GPIO_21;
    @NotNull private static final Pin PIN_31 = RaspiPin.GPIO_22;
    @NotNull private static final Pin PIN_32 = RaspiPin.GPIO_26;
    @NotNull private static final Pin PIN_33 = RaspiPin.GPIO_23;
    @NotNull private static final Pin PIN_35 = RaspiPin.GPIO_24;
    @NotNull private static final Pin PIN_36 = RaspiPin.GPIO_27;
    @NotNull private static final Pin PIN_37 = RaspiPin.GPIO_25;
    @NotNull private static final Pin PIN_38 = RaspiPin.GPIO_28;
    @NotNull private static final Pin PIN_40 = RaspiPin.GPIO_29;

    private GpioPins(){}

    @NotNull
    public static Pin getPinFromBOARD(int boardId) {
        Field f;
        Pin p=null;
        try {
            f = GpioPins.class.getDeclaredField("PIN_"+boardId);
            f.setAccessible(true);
            p = (Pin) f.get(new GpioPins());
        } catch (NoSuchFieldException e) {
            throw new IllegalArgumentException("Board ID is ground or 3.3/5V port",e);
        } catch (IllegalAccessException ignore) {}
        if (p==null) throw new RuntimeException();
        return p;
    }
}
