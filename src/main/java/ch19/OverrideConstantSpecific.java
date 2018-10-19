package ch19;

/**
 * @author yuzhe
 * @since 10/19/18
 */
public enum  OverrideConstantSpecific {

    NUT,
    BOLT,
    WASHER{
        @Override
        void f() {
            System.out.println("overridden method");
        }
    };

    void f(){
        System.out.println("default method");
    }

    public static void main(String[] args) {
        for(OverrideConstantSpecific o : OverrideConstantSpecific.values()){
            System.out.println(o + ":");
            o.f();
        }
    }

}
