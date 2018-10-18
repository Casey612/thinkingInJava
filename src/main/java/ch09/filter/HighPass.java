package ch09.filter;

/**
 * @author yuzhe
 * @since 8/15/18
 */
public class HighPass extends Filter{

    private double cutoff;

    HighPass(double cutoff){
        this.cutoff = cutoff;
    }

    @Override
    public WaveForm process(WaveForm in){
        return in;
    }

}
