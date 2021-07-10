package Animals;

public class Pig extends Animal {
    public static String type = "pig";
    public static double buycost = 700;
    public static double kgs_week = 3;
    public static int weeks_adult = 12;
    public static int eat_per_week = 3;
    public static String[] eat = {"wheat", "beetroot", "apple"};
    public static double reproductionChance = 0.15;
    public static int earnings_per_week = 0;
    public static int money_per_kg = 35;

    public Pig() {
        super("pig", 700, 3, 12, 3, new String[]{"wheat", "beetroot", "apple"}, 0.15, 0, 35);
    }
}
