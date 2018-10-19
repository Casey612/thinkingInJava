package ch19;

import java.util.Random;

/**
 * @author yuzhe
 * @since 10/19/18
 */
public enum Input {

    NICKEL(5), DIME(10), QUARTER(25), DOLLAR(100),
    TOOTHPASTE(200), CHIPS(75), SODA(100), SOAP(50),
    ABORT_TRANSACTION {
        @Override
        int amount() {
            throw new RuntimeException("abort transaction");
        }
    },
    STOP {
        @Override
        int amount() {
            throw new RuntimeException("stop");
        }
    };


    int value;

    Input() {
    }

    Input(int v) {
        this.value = v;
    }

    int amount() {
        return value;
    }

    static Random random = new Random(47);

    public static Input randomSelection() {
        return values()[random.nextInt(values().length - 1)];
    }
}
