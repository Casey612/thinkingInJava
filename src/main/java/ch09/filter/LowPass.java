package java.ch09.filter;

/**
 * @author yuzhe
 * @since 8/15/18
 */
public class LowPass extends Filter{

    private double cutoff;

    LowPass(double cutoff){
        this.cutoff = cutoff;
    }

    @Override
    public WaveForm process(WaveForm in){
        return in;
    }


}
