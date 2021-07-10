package Plants;

public class Apple extends Plant {
    public static String type = "apple";
    public static double plant_cost = 1000;
    public static int protect_cost = 200;
    public static int efficiency = 150;
    public static int grow_time = 60;
    public static int collect_cost = 80;
    public static double buy_price = 18;
    public static double sell_price = 15;

    public Apple() {
        super("apple", 1000, 200, 150, 60, 80, 18, 15);
    }
}
