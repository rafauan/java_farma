package Plants;

public class Beetroot extends Plant {
    public static String type = "beetroot";
    public static double plant_cost = 400;
    public static double protect_cost = 100;
    public static int efficiency = 35;
    public static int grow_time = 30;
    public static int collect_cost = 80;
    public static double buy_price = 28;
    public static double sell_price = 25;

    public Beetroot() {
        super("beetroot", 400, 100, 35, 30, 80, 28, 25);
    }


}
