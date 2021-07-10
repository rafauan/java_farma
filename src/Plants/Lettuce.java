package Plants;

public class Lettuce extends Plant {
    public static String type = "lettuce";
    public static double plant_cost = 200;
    public static double protect_cost = 100;
    public static int efficiency = 25;
    public static int grow_time = 12;
    public static int collect_cost = 50;
    public static double buy_price = 22;
    public static double sell_price = 20;

    public Lettuce() {
        super("lettuce", 200, 100, 25, 12, 50, 22, 20);
    }
}
