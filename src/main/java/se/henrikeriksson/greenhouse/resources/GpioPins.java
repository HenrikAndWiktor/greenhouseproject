package se.henrikeriksson.greenhouse.resources;

import com.pi4j.io.gpio.Pin;

import static com.pi4j.io.gpio.RaspiPin.*;

/**
 * Created by henrikeriksson on 2017-06-06.
 */
public class GpioPins {
    public static final Pin PIN_3 = GPIO_08;
    public static final Pin PIN_5 = GPIO_09;
    public static final Pin PIN_7 = GPIO_07;
    public static final Pin PIN_8 = GPIO_15;
    public static final Pin PIN_10 = GPIO_16;
    public static final Pin PIN_11 = GPIO_00;
    public static final Pin PIN_12 = GPIO_01;
    public static final Pin PIN_13 = GPIO_02;
    public static final Pin PIN_15 = GPIO_03;
    public static final Pin PIN_16 = GPIO_04;
    public static final Pin PIN_18 = GPIO_05;
    public static final Pin PIN_19 = GPIO_12;
    public static final Pin PIN_21 = GPIO_13;
    public static final Pin PIN_22 = GPIO_06;
    public static final Pin PIN_23 = GPIO_14;
    public static final Pin PIN_24 = GPIO_10;
    public static final Pin PIN_26 = GPIO_11;
    public static final Pin PIN_27 = GPIO_30;
    public static final Pin PIN_28 = GPIO_31;
    public static final Pin PIN_29 = GPIO_21;
    public static final Pin PIN_31 = GPIO_22;
    public static final Pin PIN_32 = GPIO_26;
    public static final Pin PIN_33 = GPIO_23;
    public static final Pin PIN_35 = GPIO_24;
    public static final Pin PIN_36 = GPIO_27;
    public static final Pin PIN_37 = GPIO_25;
    public static final Pin PIN_38 = GPIO_28;
    public static final Pin PIN_40 = GPIO_29;
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
