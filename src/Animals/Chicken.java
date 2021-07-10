package Animals;

public class Chicken extends Animal {
    public static String type = "chicken";
    public static double buycost = 150;
    public static double kgs_week = 0.7;
    public static int weeks_adult = 10;
    public static int eat_per_week = 2;
    public static String[] eat = {"wheat"};
    public static double reproductionChance = 0.15;
    public static int earnings_per_week = 25;
    public static int money_per_kg = 40;

    public Chicken() {
        super("chicken", 150, 0.7, 10, 2, new String[]{"wheat"}, 0.15, 25, 40);
    }
}
