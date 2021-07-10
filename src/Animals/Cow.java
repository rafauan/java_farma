package Animals;

public class Cow extends Animal {
    public static String type = "cow";
    public static double buycost = 1500;
    public static double kgs_week = 4;
    public static int weeks_adult = 17;
    public static int eat_per_week = 5;
    public static String[] eat = {"wheat", "beetroot", "apple"};
    public static double reproductionChance = 0.1;
    public static int earnings_per_week = 100;
    public static int money_per_kg = 45;

    public Cow() {
        super("cow", 1500, 4, 17, 5, new String[]{"wheat", "beetroot", "apple"}, 0.1, 100, 45);
    }
}
