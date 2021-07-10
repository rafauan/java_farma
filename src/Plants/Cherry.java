package Plants;

public class Cherry extends Plant {
    public static String type = "cherry";
    public static double plant_cost = 1200;
    public static double protect_cost;
    public static int efficiency;
    public static int grow_time;
    public static int collect_cost;
    public static double buy_price;
    public static double sell_price;

    public Cherry() {
        super("cherry", 1200, 200, 150, 60, 80, 20, 18);
    }
}
