package ch12;

/**
 * @author yuzhe
 * @since 8/23/18
 */
public class MyException extends Exception{

    private String desc;

    public MyException(String s){
        super();
        this.desc = s;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
