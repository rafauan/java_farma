import Animals.*;
import Plants.Plant;
import Plants.Wheat;

import java.util.Random;

public class Sumbit {

    public static void submit(Player p, int flucts) {
        Random r = new Random();
        p.isWinner();
        if (p.Winner) System.out.println(p.name + " wygrał");

        for (Plant plant : p.plants) {
            if (plant.isPlanted) plant.grow();

            if (plant.protect_cost > p.money) {
                if (r.nextDouble() < 0.5) {
                    p.plants.remove(plant);
                    int floop = r.nextInt(3);
                    switch (floop) {
                        case 0 -> System.out.println("Przykro mi , część twoich plonów została zalana");
                        case 1 -> System.out.println("Przykro mi , część twoich plonów została zniszczona przez robale");
                        case 2 -> System.out.println("Przykro mi , susza zniszczyła część twoich plonów");
                    }
                }
            } else {

                p.money -= plant.protect_cost;
                if (r.nextDouble() < 0.1) {
                    p.plants.remove(plant);
                    int floop = r.nextInt(3);
                    switch (floop) {
                        case 0 -> System.out.println("Przykro mi , część twoich plonów została zalana");
                        case 1 -> System.out.println("Przykro mi , część twoich plonów została zniszczona przez robale");
                        case 2 -> System.out.println("Przykro mi , susza zniszczyła część twoich plonów");
                    }
                }
            }
        }
        for (Animal an : p.animals) {
            if (!(an.isAdult())) an.grow();
            if (an.type.equals("chicken") || an.type.equals("rabbit")) {
                if (p.stash[3] > an.eat_per_week) p.stash[3] -= an.eat_per_week;
                else p.money -= Wheat.buy_price * an.eat_per_week * (100 + flucts) / 100;
            } else {
                if (p.stash[2] > an.eat_per_week) p.stash[2] -= an.eat_per_week;
                else if (p.stash[3] > an.eat_per_week) p.stash[3] -= an.eat_per_week;
                else if (p.stash[4] > an.eat_per_week) p.stash[4] -= an.eat_per_week;
                else if (p.money > Wheat.buy_price * an.eat_per_week * (100 + flucts) / 100)
                    p.money -= Wheat.buy_price * an.eat_per_week * (100 + flucts) / 100;//Wheat as default food
                else if (r.nextDouble() < 0.1) an = null;// jesli nie ma jedzenia 10% szans , ze zwierze umrze
            }
            assert an != null;
            if (an.isAdult()) {
                p.money += an.earnings_per_week;
            }
        }

        int[] amOfAnimals = p.animalsAmount();
        int rep = 0;
        for (int i = 0; i < amOfAnimals.length; i++) {
            for (int j = 0; j < Math.floor((double) amOfAnimals[i] / 2); j++) {
                switch (i) {
                    case 0 -> {
                        if (r.nextDouble() < Cow.reproductionChance) {
                            p.animals.add(new Cow());
                            rep++;
                        }
                    }
                    case 1 -> {
                        if (r.nextDouble() < Pig.reproductionChance) {
                            p.animals.add(new Pig());
                            rep++;
                        }
                    }
                    case 2 -> {
                        if (r.nextDouble() < Chicken.reproductionChance) {
                            p.animals.add(new Chicken());
                            rep++;
                        }
                    }
                    case 3 -> {
                        if (r.nextDouble() < Horse.reproductionChance) {
                            p.animals.add(new Horse());
                            rep++;
                        }
                    }
                    case 4 -> {
                        if (r.nextDouble() < Rabbit.reproductionChance) {
                            p.animals.add(new Rabbit());
                            rep++;
                        }
                    }
                }
            }
        }
        System.out.println("zwierzęta rozmnożyły się " + rep + " razy");
    }
}
