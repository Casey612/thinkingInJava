package java.ch09.filter;

/**
 * @author yuzhe
 * @since 8/15/18
 */
public class BandPass extends Filter{


    private double cutHighOff;
    private double cutLowOff;

    BandPass(double low, double high){
        this.cutHighOff = high;
        this.cutLowOff = low;
    }

    @Override
    public WaveForm process(WaveForm in){
        return in;
    }

}
