package ch19;

import java.util.EnumMap;
import java.util.Iterator;

/**
 * @author yuzhe
 * @since 10/19/18
 */
class Mail {
    enum GeneralDelivery {
        YES, NO1, NO2, NO3, NO4, NO5;
    }

    enum Scannability {
        UNSCANNABLE, YES1, YES2, YES3, YES4;
    }

    enum Readability {
        ILLEGIBLE, YES1, YES2, YES3, YES4;
    }

    enum Address {
        INCORRECT, OK1, OK2, OK3, OK4, OK5, OK6;
    }

    enum ReturnAdress {
        MISSING, OK1, OK2, OK3, OK4, OK5;
    }

    GeneralDelivery generalDelivery;
    Scannability scannability;
    Readability readability;
    Address address;
    ReturnAdress returnAdress;

    static long counter = 0;
    long id = counter++;

    @Override
    public String toString() {
        return "mail " + id;
    }

    public String details() {
        return toString() + ", GeneralDelivery: " + generalDelivery +
                ", Scannability: " + scannability +
                ", Readability: " + readability +
                ", Address: " + address +
                ", ReturnAdress: " + returnAdress;
    }

    public static Mail randomMail() {
        Mail m = new Mail();
        m.returnAdress = Enums.random(ReturnAdress.class);
        m.address = Enums.random(Address.class);
        m.readability = Enums.random(Readability.class);
        m.scannability = Enums.random(Scannability.class);
        m.generalDelivery = Enums.random(GeneralDelivery.class);
        return m;
    }

    public static Iterable<Mail> generator(final int count) {
        return new Iterable<Mail>() {
            int n = count;

            @Override
            public Iterator<Mail> iterator() {
                return new Iterator<Mail>() {
                    @Override
                    public boolean hasNext() {
                        return n-- > 0;
                    }

                    @Override
                    public Mail next() {
                        return randomMail();
                    }
                };
            }
        };
    }

}

public class PostOffice {

    interface Handler{
        boolean handle(Mail m);
    }

    enum MailHandler implements Handler{
        GENERAL_DELIVERY {
            @Override
            public boolean handle(Mail m) {
                switch (m.generalDelivery) {
                    case YES:
                        System.out.println("using general delivery for " + m);
                        return true;
                    default:
                        return false;
                }
            }
        },
        MACHINE_SCAN {
            @Override
            public boolean handle(Mail m) {
                switch (m.scannability) {
                    case UNSCANNABLE:
                        return false;
                    default:
                        switch (m.address) {
                            case INCORRECT:
                                return false;
                            default:
                                System.out.println("delivery " + m + " normally");
                                return true;
                        }
                }
            }
        },
        VISUAL_INSPECTION {
            @Override
            public boolean handle(Mail m) {
                switch (m.readability) {
                    case ILLEGIBLE:
                        return false;
                    default:
                        switch (m.address) {
                            case INCORRECT:
                                return false;
                            default:
                                System.out.println("delivery " + m + " normally");
                                return true;
                        }
                }
            }
        },
        RETURN_TO_SENDER {
            @Override
            public boolean handle(Mail m) {
                switch (m.returnAdress) {
                    case MISSING:
                        return false;
                    default:
                        System.out.println("returning " + m + " to sender");
                        return true;
                }
            }
        };

    }

    static void handle(Mail m){
        for(MailHandler handler : MailHandler.values()){
            if(handler.handle(m)){
                return;
            }
        }
        System.out.println(m + " is a dead mail.");
    }

    static void hanle2(Mail d){
        EnumMap<MailHandler, Handler> map = new EnumMap<MailHandler, Handler>(MailHandler.class);
        //枚举类可不实现Handler，将处理方法逐一放入map中，便利map，来处理邮件
    }

    public static void main(String[] args) {
        for(Mail mail : Mail.generator(10)){
            System.out.println(mail);
            handle(mail);
            System.out.println("==================");
        }
    }

}
