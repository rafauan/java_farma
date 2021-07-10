package Animals;

public class Horse extends Animal {
    public static String type = "horse";
    public static double buycost = 4000;
    public static double kgs_week = 8;
    public static int weeks_adult = 25;
    public static int eat_per_week = 8;
    public static String[] eat = {"wheat", "beetroot", "apple"};
    public static double reproductionChance = 0.05;
    public static int earnings_per_week = 300;
    public static int money_per_kg = 30;

    public Horse() {
        super("horse", 4000, 8, 25, 8, new String[]{"wheat", "beetroot", "apple"}, 0.05, 300, 30);
    }
}
