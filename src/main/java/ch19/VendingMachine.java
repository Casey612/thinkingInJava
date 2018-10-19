package ch19;

import ch15.Generator;
import ch18.TextFile;

import java.util.EnumMap;
import java.util.Iterator;

/**
 * @author yuzhe
 * @since 10/19/18
 */

enum Category {
    MONEY(Input.NICKEL, Input.DIME, Input.QUARTER, Input.DOLLAR),
    ITEM_SELECTION(Input.TOOTHPASTE, Input.CHIPS, Input.SODA, Input.SOAP),
    QUIT_TRANSACTION(Input.ABORT_TRANSACTION),
    SHUN_DOWN(Input.STOP);

    private Input[] values;

    Category(Input... vals) {
        this.values = vals;
    }

    private static EnumMap<Input, Category> categories = new EnumMap<Input, Category>(Input.class);

    static {
        for (Category c : Category.class.getEnumConstants()) {
            for (Input type : c.values) {
                categories.put(type, c);
            }
        }
    }

    public static Category categorize(Input input) {
        return categories.get(input);
    }
}

public class VendingMachine {

    enum StateDuration {
        TRANSIENT
    }

    private static State state = State.RESTING;
    private static int amount = 0;
    private static Input selection = null;

    enum State {
        RESTING {
            @Override
            void next(Input input) {
                switch (Category.categorize(input)) {
                    case MONEY:
                        amount += input.amount();
                        state = ADDING_MONTY;
                        break;
                    case SHUN_DOWN:
                        state = TERMINAL;
                    default:
                }
            }
        },
        ADDING_MONTY {
            @Override
            void next(Input input) {
                switch (Category.categorize(input)) {
                    case MONEY:
                        amount += input.amount();
                        break;
                    case ITEM_SELECTION:
                        selection = input;
                        if (amount < selection.amount()) {
                            System.out.println("insufficient money for " + selection);
                        } else {
                            state = DISPENSING;
                        }
                        break;
                    case QUIT_TRANSACTION:
                        state = GIVING_CHANGE;
                        break;
                    case SHUN_DOWN:
                        state = TERMINAL;
                    default:
                }
            }
        },
        DISPENSING() {
            @Override
            void next() {
                System.out.println("here is your " + selection);
                amount -= selection.amount();
                state = GIVING_CHANGE;
            }
        },
        GIVING_CHANGE(StateDuration.TRANSIENT) {
            @Override
            void next() {
                if (amount > 0) {
                    System.out.println("your change:" + amount);
                    amount = 0;
                }
                state = RESTING;
            }
        },
        TERMINAL {
            @Override
            void output() {
                System.out.println("halted");
            }
        };
        private boolean isTransient = false;

        State() {
        }

        State(StateDuration trans) {
            isTransient = true;
        }

        void next(Input input) {
            throw new RuntimeException("only call next(Input input) for non-transient states");
        }

        void next() {
            throw new RuntimeException("only call next() for StateDuration.TRANSIENT states");
        }

        void output() {
            System.out.println(amount);
        }
    }

    static void run(Generator<Input> gen){
        while(state != State.TERMINAL){
            state.next(gen.next());
            while(state.isTransient){
                state.next();
            }
            state.output();
        }
    }


    public static void main(String[] args) {
        Generator<Input> gen = new RandomInputGenerator();
        if(args.length == 1){
            gen = new FileInputGenerator(args[0]);
        }
        run(gen);
    }

}


class RandomInputGenerator implements Generator<Input> {

    @Override
    public Input next() {
        return Input.randomSelection();
    }
}

class FileInputGenerator implements Generator<Input> {

    private Iterator<String> input;

    public FileInputGenerator(String fileName) {
        input = new TextFile(fileName, ",").iterator();
    }

    @Override
    public Input next() {
        if (!input.hasNext()) {
            return null;
        } else {
            return Enum.valueOf(Input.class, input.next().trim());
        }
    }
}