import Animals.*;
import Plants.Plant;

import java.util.ArrayList;
import java.util.Arrays;


public class Player {

    //Każdy gracz ma na start drobne możliwości zbierania zwierzyny i nasion
    String name = "";
    double money = 0;
    int pigspace = 2; // one pigsty 10 places
    int cowspace = 1;   // one cowshed 10 places
    int chickenspace = 2;   // one cote 10 places
    int horsespace = 0;
    int rabbitspace = 5;
    int[] stash = {0, 0, 0, 0, 0, 0};// 0 for arugula , 1 for lettuce ...
    int warehouse_place = 20;  // one warehouse 100 kg's
    int hectares = 0;
    ArrayList<Animal> animals = new ArrayList<>();
    ArrayList<Plant> plants = new ArrayList<>();
    int[] farms_own = {0, 0, 0, 0, 0, 0, 0, 0};
    boolean Winner = false;
    boolean namedetected = false;


    public void status_check() {
        System.out.println("Posiadanych zwierząt - " + animals.size());
        System.out.println("ilość miejsca dla świnek - " + pigspace);
        System.out.println("ilość miejsca dla krów - " + cowspace);
        System.out.println("ilość miejsca dla kurczaków - " + chickenspace);
        System.out.println("ilość miejsca dla koni - " + horsespace);
        System.out.println("ilość miejsca dla królików - " + rabbitspace);
        System.out.println("Posiadanych roślin - " + plants.size());
        System.out.println("Miejsce w magazynie - " + warehouse_place);
        System.out.println("Posiadane Hektary - " + hectares);
        System.out.println("Wolna ziemia - " + hecsToUse() + "\n\n");
    }

    public boolean placeCheck(int addingval) {
        int fullstash = 0;
        for (int i = 0; i < stash.length; i++) {
            stash[i] += fullstash;
        }
        return fullstash + addingval <= this.warehouse_place;
    }

    public int hecsToUse() {
        int planted = 0;
        for (Plant p : plants) {
            if (p.isPlanted) planted++;
        }
        return this.hectares - planted;
    }

    public void animalsInfo() {
        for (Animal a : animals) {
            System.out.println(a.presentation());
        }
    }

    public void plantsInfo() {
        for (Plant p : plants) {
            System.out.println(p.presentation());
        }
    }

    public int howManyTypesAnimals() {
        boolean[] have = new boolean[5];
        int trues = 0;
        Arrays.fill(have, Boolean.FALSE);
        for (Animal a : animals) {
            switch (a.type) {
                case "cow" -> have[0] = true;
                case "pig" -> have[1] = true;
                case "chicken" -> have[2] = true;
                case "horse" -> have[3] = true;
                case "rabbit" -> have[4] = true;
            }
        }
        for (Boolean b : have) {
            if (b) trues++;
        }
        return trues;
    }

    public int howManyTypesPlants() {
        boolean[] have = new boolean[6];
        int trues = 0;
        Arrays.fill(have, Boolean.FALSE);
        for (Plant p : plants) {
            switch (p.type) {
                case "arugula" -> have[0] = true;
                case "lettuce" -> have[1] = true;
                case "beetroot" -> have[2] = true;
                case "wheat" -> have[3] = true;
                case "apple" -> have[4] = true;
                case "pear" -> have[5] = true;
            }
        }
        for (Boolean b : have) {
            if (b) trues++;
        }
        return trues;
    }

    public int[] animalsAmount() {
        int[] amount = {0, 0, 0, 0, 0};
        for (Animal an : animals) {
            switch (an.type) {
                case "cow" -> amount[0]++;
                case "pig" -> amount[1]++;
                case "chicken" -> amount[2]++;
                case "horse" -> amount[3]++;
                case "rabbit" -> amount[4]++;
            }
        }
        return amount;
    }

    public boolean foodForYear() {
        int[] amount = this.animalsAmount();
        //sa zwierzeta ktore zjedza tylko jeden typ jedzenia - krolik i kura
        int onetypeoffood = amount[2] * Chicken.eat_per_week * 60 + amount[4] * Rabbit.eat_per_week * 60;//wheat inneed
        if (onetypeoffood > stash[3]) return false;
        int kgsleft = stash[2] + stash[3] + stash[4] - onetypeoffood;
        return kgsleft > amount[0] * Cow.eat_per_week * 60 + amount[1] * Pig.eat_per_week * 60 + amount[3] * Horse.eat_per_week * 60;
    }

    public boolean isWinner() {
        return (this.hectares >= 20 && howManyTypesAnimals() >= 5 && howManyTypesPlants() >= 5 && foodForYear());
    }

    public void stashInfo() {
        System.out.println("Twój stan magazynu (wyrośniętych plonów) - ");
        System.out.println("Rukola - " + stash[0] + " kg ");
        System.out.println("Sałata - " + stash[1] + " kg ");
        System.out.println("Burak - " + stash[2] + " kg ");
        System.out.println("Pszenica - " + stash[3] + " kg ");
        System.out.println("Jabłko - " + stash[4] + " kg ");
        System.out.println("Gruszka - " + stash[5] + " kg ");
    }
}

