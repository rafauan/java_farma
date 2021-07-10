package Plants;

public class Wheat extends Plant {
    public static String type = "wheat";
    public static double plant_cost = 500;
    public static double protect_cost = 100;
    public static int efficiency = 40;
    public static int grow_time = 30;
    public static int collect_cost = 80;
    public static double buy_price = 32;
    public static double sell_price = 30;

    public Wheat() {
        super("wheat", 500, 100, 40, 30, 80, 32, 30);
    }
}
