package ch19;

/**
 * @author yuzhe
 * @since 10/22/18
 */
public interface Competitor<T extends Competitor<T>> {

    Outcome compete(T competitor);
}
