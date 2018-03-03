package se.henrikeriksson.greenhouse.resources;

public class GpioPins {
    private static final com.pi4j.io.gpio.Pin PIN_3 = com.pi4j.io.gpio.RaspiPin.GPIO_08;
    private static final com.pi4j.io.gpio.Pin PIN_5 = com.pi4j.io.gpio.RaspiPin.GPIO_09;
    private static final com.pi4j.io.gpio.Pin PIN_7 = com.pi4j.io.gpio.RaspiPin.GPIO_07;
    private static final com.pi4j.io.gpio.Pin PIN_8 = com.pi4j.io.gpio.RaspiPin.GPIO_15;
    private static final com.pi4j.io.gpio.Pin PIN_10 = com.pi4j.io.gpio.RaspiPin.GPIO_16;
    private static final com.pi4j.io.gpio.Pin PIN_11 = com.pi4j.io.gpio.RaspiPin.GPIO_00;
    private static final com.pi4j.io.gpio.Pin PIN_12 = com.pi4j.io.gpio.RaspiPin.GPIO_01;
    private static final com.pi4j.io.gpio.Pin PIN_13 = com.pi4j.io.gpio.RaspiPin.GPIO_02;
    private static final com.pi4j.io.gpio.Pin PIN_15 = com.pi4j.io.gpio.RaspiPin.GPIO_03;
    private static final com.pi4j.io.gpio.Pin PIN_16 = com.pi4j.io.gpio.RaspiPin.GPIO_04;
    private static final com.pi4j.io.gpio.Pin PIN_18 = com.pi4j.io.gpio.RaspiPin.GPIO_05;
    private static final com.pi4j.io.gpio.Pin PIN_19 = com.pi4j.io.gpio.RaspiPin.GPIO_12;
    private static final com.pi4j.io.gpio.Pin PIN_21 = com.pi4j.io.gpio.RaspiPin.GPIO_13;
    private static final com.pi4j.io.gpio.Pin PIN_22 = com.pi4j.io.gpio.RaspiPin.GPIO_06;
    private static final com.pi4j.io.gpio.Pin PIN_23 = com.pi4j.io.gpio.RaspiPin.GPIO_14;
    private static final com.pi4j.io.gpio.Pin PIN_24 = com.pi4j.io.gpio.RaspiPin.GPIO_10;
    private static final com.pi4j.io.gpio.Pin PIN_26 = com.pi4j.io.gpio.RaspiPin.GPIO_11;
    private static final com.pi4j.io.gpio.Pin PIN_27 = com.pi4j.io.gpio.RaspiPin.GPIO_30;
    private static final com.pi4j.io.gpio.Pin PIN_28 = com.pi4j.io.gpio.RaspiPin.GPIO_31;
    private static final com.pi4j.io.gpio.Pin PIN_29 = com.pi4j.io.gpio.RaspiPin.GPIO_21;
    private static final com.pi4j.io.gpio.Pin PIN_31 = com.pi4j.io.gpio.RaspiPin.GPIO_22;
    private static final com.pi4j.io.gpio.Pin PIN_32 = com.pi4j.io.gpio.RaspiPin.GPIO_26;
    private static final com.pi4j.io.gpio.Pin PIN_33 = com.pi4j.io.gpio.RaspiPin.GPIO_23;
    private static final com.pi4j.io.gpio.Pin PIN_35 = com.pi4j.io.gpio.RaspiPin.GPIO_24;
    private static final com.pi4j.io.gpio.Pin PIN_36 = com.pi4j.io.gpio.RaspiPin.GPIO_27;
    private static final com.pi4j.io.gpio.Pin PIN_37 = com.pi4j.io.gpio.RaspiPin.GPIO_25;
    private static final com.pi4j.io.gpio.Pin PIN_38 = com.pi4j.io.gpio.RaspiPin.GPIO_28;
    private static final com.pi4j.io.gpio.Pin PIN_40 = com.pi4j.io.gpio.RaspiPin.GPIO_29;
    private static com.pi4j.io.gpio.Pin[] pfnBoard = new com.pi4j.io.gpio.Pin[40];

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
    public static com.pi4j.io.gpio.Pin getPinFromBOARD(int boardId) {
        if (pfnBoard[boardId-1]==null){
            throw new IllegalArgumentException("Board ID is ground or 3.3/5V port",
                    new NullPointerException("pfn["+(boardId-1)+"] is null"));
        }
        return pfnBoard[boardId-1];
    }
}
