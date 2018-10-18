package java.ch14;

/**
 * @author yuzhe
 * @since 9/3/18
 */
public class ClassCast {

    public static void main(String[] args) {
        Building building = new House();
        Class<House> houseClass = House.class;
        House house1 = houseClass.cast(building);
        House house2 = (House) building;

        Class<? extends Building> houseClass2 = House.class.asSubclass(Building.class);


        if (building instanceof Building){
            System.out.println("instanceof Building");
        }

        if(building instanceof House){
            System.out.println("instanceof House");
        }
    }

}

class Building {
}

class House extends Building {
}

