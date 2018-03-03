package se.henrikeriksson.greenhouse.resources;

import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;

public class GpioPins {
    private static final Pin PIN_3 = RaspiPin.GPIO_08;
    private static final Pin PIN_5 = RaspiPin.GPIO_09;
    private static final Pin PIN_7 = RaspiPin.GPIO_07;
    private static final Pin PIN_8 = RaspiPin.GPIO_15;
    private static final Pin PIN_10 = RaspiPin.GPIO_16;
    private static final Pin PIN_11 = RaspiPin.GPIO_00;
    private static final Pin PIN_12 = RaspiPin.GPIO_01;
    private static final Pin PIN_13 = RaspiPin.GPIO_02;
    private static final Pin PIN_15 = RaspiPin.GPIO_03;
    private static final Pin PIN_16 = RaspiPin.GPIO_04;
    private static final Pin PIN_18 = RaspiPin.GPIO_05;
    private static final Pin PIN_19 = RaspiPin.GPIO_12;
    private static final Pin PIN_21 = RaspiPin.GPIO_13;
    private static final Pin PIN_22 = RaspiPin.GPIO_06;
    private static final Pin PIN_23 = RaspiPin.GPIO_14;
    private static final Pin PIN_24 = RaspiPin.GPIO_10;
    private static final Pin PIN_26 = RaspiPin.GPIO_11;
    private static final Pin PIN_27 = RaspiPin.GPIO_30;
    private static final Pin PIN_28 = RaspiPin.GPIO_31;
    private static final Pin PIN_29 = RaspiPin.GPIO_21;
    private static final Pin PIN_31 = RaspiPin.GPIO_22;
    private static final Pin PIN_32 = RaspiPin.GPIO_26;
    private static final Pin PIN_33 = RaspiPin.GPIO_23;
    private static final Pin PIN_35 = RaspiPin.GPIO_24;
    private static final Pin PIN_36 = RaspiPin.GPIO_27;
    private static final Pin PIN_37 = RaspiPin.GPIO_25;
    private static final Pin PIN_38 = RaspiPin.GPIO_28;
    private static final Pin PIN_40 = RaspiPin.GPIO_29;
    private static Pin[] pfnBoard = new Pin[40];

    static {
        pfnBoard[2] = PIN_3;
        pfnBoard[4] = PIN_5;
        pfnBoard[6] = PIN_7;
        pfnBoard[7] = PIN_8;
        pfnBoard[9] = PIN_10;
        pfnBoard[10] = PIN_11;
        pfnBoard[11] = PIN_12;
        pfnBoard[12] = PIN_13;
        pfnBoard[14] = PIN_15;
        pfnBoard[15] = PIN_16;
        pfnBoard[17] = PIN_18;
        pfnBoard[18] = PIN_19;
        pfnBoard[20] = PIN_21;
        pfnBoard[21] = PIN_22;
        pfnBoard[22] = PIN_23;
        pfnBoard[23] = PIN_24;
        pfnBoard[25] = PIN_26;
        pfnBoard[26] = PIN_27;
        pfnBoard[27] = PIN_28;
        pfnBoard[28] = PIN_29;
        pfnBoard[30] = PIN_31;
        pfnBoard[31] = PIN_32;
        pfnBoard[32] = PIN_33;
        pfnBoard[34] = PIN_35;
        pfnBoard[35] = PIN_36;
        pfnBoard[36] = PIN_37;
        pfnBoard[37] = PIN_38;
        pfnBoard[39] = PIN_40;
    }
    public static Pin getPinFromBOARD(int boardId) {
        if (pfnBoard[boardId-1]==null){
            throw new IllegalArgumentException("Board ID is ground or 3.3/5V port",
                    new NullPointerException("pfn["+(boardId-1)+"] is null"));
        }
        return pfnBoard[boardId-1];
    }
}
