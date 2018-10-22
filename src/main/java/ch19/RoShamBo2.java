package ch19;

import java.util.Random;

/**
 * @author yuzhe
 * @since 10/22/18
 */
public enum RoShamBo2 implements Competitor<RoShamBo2> {
    PAPER(Outcome.DRAW, Outcome.LOSE, Outcome.WIN),
    SCISSORS(Outcome.WIN, Outcome.DRAW, Outcome.LOSE),
    ROCK(Outcome.LOSE, Outcome.WIN, Outcome.DRAW);

    private Outcome vPaper, vScissors, vRock;

    RoShamBo2(Outcome paper, Outcome scissors, Outcome rock) {
        this.vPaper = paper;
        this.vScissors = scissors;
        this.vRock = rock;
    }

    public Outcome compete(RoShamBo2 it) {
        switch (it) {
            default:
            case PAPER:
                return vPaper;
            case SCISSORS:
                return vScissors;
            case ROCK:
                return vRock;
        }
    }

    public static void match(RoShamBo2 a, RoShamBo2 b) {
        System.out.println(a + " vs. " + b + ": " + a.compete(b));
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            match(newItem(), newItem());
        }
        System.out.println("================= method 2 =================");
        RoShamBo.play(RoShamBo2.class, 20);

    }

    private static Random random = new Random(47);

    private static RoShamBo2 newItem() {
        switch (random.nextInt(3)) {
            case 0:
                return PAPER;
            case 1:
                return SCISSORS;
            default:
                return ROCK;
        }
    }
}
