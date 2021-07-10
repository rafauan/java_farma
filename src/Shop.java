import Animals.*;
import Plants.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Shop {
    static Scanner s = new Scanner(System.in);

    public static void shop(Player p, int flucts) {
        String choice;
        System.out.println("Witaj w sklepie. Chcesz coś sprzedać czy kupic ?");
        System.out.println("1. Kupić");
        System.out.println("2. Sprzedać");
        System.out.println("3.Wyjdź ze sklepu..");
        System.out.print("Twój wybór : ");
        choice = s.nextLine();
        System.out.println();
        switch (choice) {
            case "1" -> buyShop(p, flucts);
            case "2" -> sellShop(p, flucts);
            case "3" -> {
            }
            default -> System.out.println("Została wybrana nieprawidłowa opcja...");
        }
    }

    public static void buyShop(Player p, int flucts) {
        String choice;
        System.out.println("Co dokładnie chciałbyś kupić ?");
        System.out.println("1. Nasiona");
        System.out.println("2. Zwierzęta");
        System.out.println("3. Farmę");
        System.out.println("4. Zlecić budowę budynku");
        System.out.println("5. Ziemię(5000 za hektar)");
        System.out.println("6. Jedzenie dla zwierząt");
        System.out.println("7. Wyjdz ze sklepu ...");
        System.out.print("Twój wybór : ");
        choice = s.nextLine();
        System.out.println();
        switch (choice) {

            case "1" -> seedsShop(p, flucts);
            case "2" -> animalsShop(p, flucts);
            case "3" -> farmShop(p);
            case "4" -> buildSmthing(p);
            case "5" -> {
                if (p.money < 5000) {
                    System.out.println("Nie masz wystarczającej gotówki");
                } else {
                    p.money -= 5000;
                    System.out.println("Zakupiłeś Hektar ziemii");
                    p.hectares++;
                    Helper.waitForEnter();
                }
                buyShop(p, flucts);
            }
            case "6" -> foodShop(p, flucts);
            case "7" -> {
            }
            default -> System.out.println("Została wybrana nieprawidłowa opcja ...");
        }
    }

    public static void foodShop(Player p, int flucts) {
        String choice;
        System.out.println("Który typ jedzenia ciebie interesuje ?");
        System.out.println("Twoje środki wynoszą " + p.money);
        System.out.println("1. Pszenica (" + Wheat.buy_price * (100 + flucts) / 100 + "za kg) ");
        System.out.println("2. Burak (" + Beetroot.buy_price * (100 + flucts) / 100 + "za kg) ");
        System.out.println("3. Jabłko (" + Apple.buy_price * (100 + flucts) / 100 + "za kg) ");
        System.out.println("4. Wyjdz ze sklepu ...");
        System.out.print("Twój wybór : ");
        int kgs = 0;
        choice = s.nextLine();
        System.out.println();
        switch (choice) {

            case "1" -> {
                System.out.print("Ile kg chciałbyś kupic ?  ");
                while (kgs < 1) {
                    try {
                        kgs = Integer.parseInt(s.nextLine());
                    } catch (NumberFormatException ignored) {
                    }
                }
                if (p.warehouse_place >= kgs) {
                    if (p.money < Wheat.buy_price * (100 + flucts) / 100 * kgs) {
                        System.out.println("Nie maszwystarczającej ilości gotówki lub miejsca..");
                    } else {
                        p.money -= Wheat.buy_price * (100 + flucts) / 100 * kgs;
                        p.stash[3] += kgs;
                        p.warehouse_place -= kgs;
                        System.out.println("Zakup przebiegł pomyślnie !");
                        Helper.waitForEnter();
                    }
                } else System.out.println("Nie masz wystarczająco miejsca");
            }
            case "2" -> {
                System.out.print("Ile kg chciałbyś kupic ?  ");
                while (kgs < 1) {
                    try {
                        kgs = Integer.parseInt(s.nextLine());
                    } catch (NumberFormatException ignored) {
                    }
                }
                if (p.warehouse_place >= kgs) {
                    if (p.money < Beetroot.buy_price * (100 + flucts) / 100) {
                        System.out.println("Nie maszwystarczającej ilości gotówki lub miejsca..");
                    } else {
                        p.money -= Beetroot.buy_price * (100 + flucts) / 100;
                        p.stash[2] += kgs;
                        p.warehouse_place -= kgs;
                        System.out.println("Zakup przebiegł pomyślnie !");
                        Helper.waitForEnter();
                    }
                } else System.out.println("Nie wasz wystarczająco miejsca");
            }

            case "3" -> {

                System.out.print("Ile kg chciałbyś kupic ?  ");
                while (kgs < 1) {
                    try {
                        kgs = Integer.parseInt(s.nextLine());
                    } catch (NumberFormatException ignored) {
                    }
                }
                if (p.warehouse_place >= kgs) {
                    if (p.money < Apple.buy_price * (100 + flucts) / 100) {
                        System.out.println("Nie maszwystarczającej ilości gotówki lub miejsca..");
                    } else {
                        p.money -= Apple.buy_price * (100 + flucts) / 100;
                        p.stash[4] += kgs;
                        p.warehouse_place -= kgs;
                        System.out.println("Zakup przebiegł pomyślnie !");
                        Helper.waitForEnter();
                    }
                } else System.out.println("Nie wasz wystarczająco miejsca");
            }
            case "4" -> {
            }
            default -> System.out.println("Została wybrana nieprawidłowa opcja ...");
        }
    }

    public static void farmShop(Player p) {
        String choice;
        System.out.println("Wybierz farmę , którą chciałbyś kupić (każda farma ma swoją wielkość oraz może posiadać dodatkowe budynki)");
        System.out.println("Farmy są do wyboru w kolejności od najmniejszej do największej...");
        System.out.println("Twoje środki wynoszą " + p.money);
        System.out.println("1. Pierwsza Farma (4500) - 1ha");
        System.out.println("2. Druga Farma (12000) - 3ha");
        System.out.println("3. Trzecia Farma (21000) - 5ha i skład");
        System.out.println("4. Czwarta farma (27000) - 8ha i skład ");
        System.out.println("5. Piąta Farma (32000) - 10ha i skład");
        System.out.println("6. Szósta Farma (40000) - 13ha, skład i kurnik");
        System.out.println("7. Siódma Farma (50000) - 17ha, skład, kurnik i chlew");
        System.out.println("8. Ósma farma (70000) - 20ha, skład, kurnik i obora");
        System.out.println("9. Wyjdz ze sklepu ...");
        System.out.print("Twój wybór : ");
        choice = s.nextLine();
        System.out.println();
        switch (choice) {

            case "1" -> {
                if (p.farms_own[0] == 0) {
                    if (p.money < 4500) {
                        System.out.println("Nie maszwystarczającej ilości gotówki lub miejsca..");
                    } else {
                        p.money -= 4500;
                        p.hectares += 1;
                        p.farms_own[0] = 1;
                        System.out.println("Zakup przebiegł pomyślnie !");
                        System.out.println("Posiadasz teraz nową farmę oraz 1ha ziemi więcej !");
                        Helper.waitForEnter();
                    }
                } else System.out.println("Posiadasz już tą farmę !");
            }
            case "2" -> {
                if (p.farms_own[1] == 0) {
                    if (p.money < 12000) {
                        System.out.println("Nie maszwystarczającej ilości gotówki lub miejsca..");
                    } else {
                        p.money -= 12000;
                        p.hectares += 3;
                        p.farms_own[1] = 1;
                        System.out.println("Zakup przebiegł pomyślnie !");
                        System.out.println("Posiadasz teraz nową farmę oraz 3ha ziemi więcej !");
                        Helper.waitForEnter();
                    }
                } else System.out.println("Posiadasz już tą farmę !");
            }
            case "3" -> {
                if (p.farms_own[2] == 0) {
                    if (p.money < 21000) {
                        System.out.println("Nie maszwystarczającej ilości gotówki lub miejsca..");
                    } else {
                        p.money -= 21000;
                        p.hectares += 5;
                        p.warehouse_place += 100;
                        p.farms_own[2] = 1;
                        System.out.println("Zakup przebiegł pomyślnie !");
                        System.out.println("Posiadasz teraz nową farmę , 5ha ziemi więcej oraz dodatkowy skład (100 kg warzyw i owoców) !");
                        Helper.waitForEnter();
                    }
                } else System.out.println("Posiadasz już tą farmę !");
            }
            case "4" -> {
                if (p.farms_own[3] == 0) {
                    if (p.money < 27000) {
                        System.out.println("Nie maszwystarczającej ilości gotówki lub miejsca..");
                    } else {
                        p.money -= 27000;
                        p.hectares += 8;
                        p.warehouse_place += 100;
                        p.farms_own[3] = 1;
                        System.out.println("Zakup przebiegł pomyślnie !");
                        System.out.println("Posiadasz teraz nową farmę , 8ha ziemi więcej oraz dodatkowy skład (100 kg warzyw i owoców) !");
                        Helper.waitForEnter();
                    }
                } else System.out.println("Posiadasz już tą farmę !");
            }
            case "5" -> {
                if (p.farms_own[4] == 0) {
                    if (p.money < 32000) {
                        System.out.println("Nie maszwystarczającej ilości gotówki lub miejsca..");
                    } else {
                        p.money -= 32000;
                        p.hectares += 10;
                        p.warehouse_place += 100;
                        p.farms_own[4] = 1;
                        System.out.println("Zakup przebiegł pomyślnie !");
                        System.out.println("Posiadasz teraz nową farmę , 10ha ziemi więcej oraz dodatkowy skład (100 kg warzyw i owoców) !");
                        Helper.waitForEnter();
                    }
                } else System.out.println("Posiadasz już tą farmę !");
            }
            case "6" -> {
                if (p.farms_own[5] == 0) {
                    if (p.money < 40000) {
                        System.out.println("Nie maszwystarczającej ilości gotówki lub miejsca..");
                    } else {
                        p.money -= 40000;
                        p.hectares += 13;
                        p.warehouse_place += 100;
                        p.chickenspace = +20;
                        p.farms_own[5] = 1;
                        System.out.println("Zakup przebiegł pomyślnie !");
                        System.out.println("Posiadasz teraz nową farmę , 13ha ziemi więcej, dodatkowy skład (100 kg warzyw i owoców) oraz nowy kurnik (20 miejsc dla kurczaków) !");
                        Helper.waitForEnter();
                    }
                } else System.out.println("Posiadasz już tą farmę !");
            }
            case "7" -> {
                if (p.farms_own[6] == 0) {
                    if (p.money < 50000) {
                        System.out.println("Nie maszwystarczającej ilości gotówki lub miejsca..");
                    } else {
                        p.money -= 50000;
                        p.hectares += 17;
                        p.warehouse_place += 100;
                        p.chickenspace += 20;
                        p.pigspace += 10;
                        p.farms_own[6] = 1;
                        System.out.println("Zakup przebiegł pomyślnie !");
                        System.out.println("Posiadasz teraz nową farmę , 17ha ziemi więcej, dodatkowy skład (100 kg warzyw i owoców), kurnik oraz chlew !");
                        Helper.waitForEnter();
                    }
                } else System.out.println("Posiadasz już tą farmę !");
            }
            case "8" -> {
                if (p.farms_own[7] == 0) {
                    if (p.money < 70000) {
                        System.out.println("Nie maszwystarczającej ilości gotówki lub miejsca..");
                    } else {
                        p.money -= 70000;
                        p.hectares += 20;
                        p.warehouse_place += 100;
                        p.chickenspace += 20;
                        p.cowspace += 10;
                        p.farms_own[7] = 1;
                        System.out.println("Zakup przebiegł pomyślnie !");
                        System.out.println("Posiadasz teraz nową farmę , 20ha ziemi więcej, dodatkowy skład (100 kg warzyw i owoców), kurnik oraz oborę !");
                        Helper.waitForEnter();
                    }
                } else System.out.println("Posiadasz już tą farmę !");
            }
            case "9" -> {
            }
            default -> System.out.println("Została wybrana nieprawidłowa opcja ...");
        }
    }


    public static void buildSmthing(Player p) {
        String choice;
        System.out.println("Co dokładnie chciałbyś wybudować ?");
        System.out.println("Twoje środki wynoszą " + p.money);
        System.out.println("1. Obora (10000)");
        System.out.println("2. Chlew (8000)");
        System.out.println("3. Kurnik (8000)");
        System.out.println("4. Skład (6000)");
        System.out.println("5. Stajnia (15000)");
        System.out.println("6. Klatki dla królików (10000)");
        System.out.println("7. Wyjdz ze sklepu ...");
        System.out.print("Twój wybór : ");
        choice = s.nextLine();
        System.out.println();
        switch (choice) {

            case "1" -> {
                if (p.money < 10000 && p.hectares < 2) {
                    System.out.println("Nie maszwystarczającej ilości gotówki lub miejsca..");
                } else {
                    p.money -= 10000;
                    p.hectares -= 2;
                    p.cowspace += 10;
                    System.out.println("Budowa przebiegła pomyślnie !");
                    System.out.println("Teraz możesz chodować 10 krów więcej !");
                    Helper.waitForEnter();
                }
            }
            case "2" -> {
                if (p.money < 8000 && p.hectares < 2) {
                    System.out.println("Nie maszwystarczającej ilości gotówki lub miejsca..");
                } else {
                    p.money -= 8000;
                    p.hectares -= 2;
                    p.pigspace += 10;
                    System.out.println("Budowa przebiegła pomyślnie !");
                    System.out.println("Teraz możesz chodować 10 świń więcej !");
                    Helper.waitForEnter();
                }
            }
            case "3" -> {
                if (p.money < 8000 || p.hectares < 1) {
                    System.out.println("Nie maszwystarczającej ilości gotówki lub miejsca..");
                } else {
                    p.money -= 8000;
                    p.hectares -= 1;
                    p.chickenspace += 10;
                    System.out.println("Budowa przebiegła pomyślnie !");
                    System.out.println("Teraz możesz chodować 20 kur więcej !");
                    Helper.waitForEnter();
                }
            }
            case "4" -> {
                if (p.money < 6000 && p.hectares < 2) {
                    System.out.println("Nie maszwystarczającej ilości gotówki lub miejsca..");
                } else {
                    p.money -= 6000;
                    p.hectares -= 2;
                    p.warehouse_place += 100;
                    System.out.println("Budowa przebiegła pomyślnie !");
                    System.out.println("Teraz możesz chodować 100 kg owoców i warzyw więcej  !");
                    Helper.waitForEnter();
                }
            }
            case "5" -> {
                if (p.money < 15000 && p.hectares < 2) {
                    System.out.println("Nie maszwystarczającej ilości gotówki lub miejsca..");
                } else {
                    p.money -= 15000;
                    p.hectares -= 2;
                    p.horsespace += 8;
                    System.out.println("Budowa przebiegła pomyślnie !");
                    System.out.println("Teraz możesz chodować 8 Koni więcej !");
                    Helper.waitForEnter();
                }
            }
            case "6" -> {
                if (p.money < 10000 && p.hectares < 2) {
                    System.out.println("Nie maszwystarczającej ilości gotówki lub miejsca..");
                } else {
                    p.money -= 10000;
                    p.hectares -= 2;
                    p.rabbitspace += 30;
                    System.out.println("Budowa przebiegła pomyślnie !");
                    System.out.println("Teraz możesz chodować 30 królików więcej !");
                    Helper.waitForEnter();
                }
            }
            case "7" -> {
            }
            default -> System.out.println("Została wybrana nieprawidłowa opcja ...");
        }
    }

    public static void seedsShop(Player p, int flucts) {
        String choice;
        System.out.println("Nasionami której rośliny jesteś zainteresowany ? (cena za nasiona na hektar)");
        System.out.println("Twoje środki wynoszą " + p.money);
        System.out.println("1. Bazylia  " + Basil.plant_cost * (100 + flucts) / 100);
        System.out.println("2. Sałata  " + Lettuce.plant_cost * (100 + flucts) / 100);
        System.out.println("3. Burak  " + Beetroot.plant_cost * (100 + flucts) / 100);
        System.out.println("4. Pszenica  " + Wheat.plant_cost * (100 + flucts) / 100);
        System.out.println("5. Jabłko  " + Apple.plant_cost * (100 + flucts) / 100);
        System.out.println("6. Wiśnia  " + Cherry.plant_cost * (100 + flucts) / 100);
        System.out.println("7. Wyjdz ze sklepu ...");
        System.out.print("Twój wybór : ");
        choice = s.nextLine();
        System.out.println();
        switch (choice) {
            case "1" -> {
                if (p.money < Basil.plant_cost * (100 + flucts) / 100) {
                    System.out.println("Nie masz wystarczającej ilości gotówki ... ");
                } else {
                    p.money -= Basil.plant_cost * (100 + flucts) / 100;
                    p.plants.add(new Basil());
                    System.out.println("Zakup przebiegł pomyślnie");
                    Helper.waitForEnter();
                }
            }
            case "2" -> {
                if (p.money < Lettuce.plant_cost * (100 + flucts) / 100) {
                    System.out.println("Nie masz wystarczającej ilości gotówki ... ");
                } else {
                    p.money -= Lettuce.plant_cost * (100 + flucts) / 100;
                    p.plants.add(new Lettuce());
                    System.out.println("Zakup przebiegł pomyślnie");
                    Helper.waitForEnter();
                }
            }
            case "3" -> {
                if (p.money < Beetroot.plant_cost * (100 + flucts) / 100) {
                    System.out.println("Nie masz wystarczającej ilości gotówki ... ");
                } else {
                    p.money -= Beetroot.plant_cost * (100 + flucts) / 100;
                    p.plants.add(new Beetroot());
                    System.out.println("Zakup przebiegł pomyślnie");
                    Helper.waitForEnter();
                }
            }
            case "4" -> {
                if (p.money < Wheat.plant_cost * (100 + flucts) / 100) {
                    System.out.println("Nie masz wystarczającej ilości gotówki ... ");
                } else {
                    p.money -= Wheat.plant_cost * (100 + flucts) / 100;
                    p.plants.add(new Wheat());
                    System.out.println("Zakup przebiegł pomyślnie");
                    Helper.waitForEnter();
                }
            }
            case "5" -> {
                if (p.money < Apple.plant_cost * (100 + flucts) / 100) {
                    System.out.println("Nie masz wystarczającej ilości gotówki ... ");
                } else {
                    p.money -= Apple.plant_cost * (100 + flucts) / 100;
                    p.plants.add(new Apple());
                    System.out.println("Zakup przebiegł pomyślnie");
                    Helper.waitForEnter();
                }
            }
            case "6" -> {
                if (p.money < Cherry.plant_cost * (100 + flucts) / 100) {
                    System.out.println("Nie masz wystarczającej ilości gotówki ... ");
                } else {
                    p.money -= Cherry.plant_cost * (100 + flucts) / 100;
                    p.plants.add(new Cherry());
                    System.out.println("Zakup przebiegł pomyślnie");
                    Helper.waitForEnter();
                }
            }
            case "7" -> {
            }
            default -> System.out.println("Została wybrana nieprawidłowa opcja ...");
        }
    }

    public static void animalsShop(Player p, int flucts) {
        String choice;
        System.out.println("Którym zwierzęciem jesteś zainteresowany ? ");
        System.out.println("Twoje środki wynoszą " + p.money);
        System.out.println("1. Krowa  " + Cow.buycost * (100 + flucts) / 100);
        System.out.println("2. Swinia  " + Pig.buycost * (100 + flucts) / 100);
        System.out.println("3. Kura  " + Chicken.buycost * (100 + flucts) / 100);
        System.out.println("4. Koń  " + Horse.buycost * (100 + flucts) / 100);
        System.out.println("5. Królik  " + Rabbit.buycost * (100 + flucts) / 100);
        System.out.println("6. Wyjdz ze sklepu ...");
        System.out.print("Twój wybór : ");
        choice = s.nextLine();
        System.out.println();
        switch (choice) {
            case "1" -> {
                if (p.money < Cow.buycost * (100 + flucts) / 100 || p.cowspace < 1) {
                    System.out.println("Nie masz wystarczającej ilości gotówki lub miejsca ... ");
                } else {
                    p.money -= Cow.buycost * (100 + flucts) / 100;
                    p.cowspace--;
                    p.animals.add(new Cow());
                    System.out.println("Zakup przebiegł pomyślnie");
                    Helper.waitForEnter();
                }
            }
            case "2" -> {
                if (p.money < Pig.buycost * (100 + flucts) / 100 || p.pigspace < 1) {
                    System.out.println("Nie masz wystarczającej ilości gotówki lub miejsca... ");

                } else {
                    p.money -= Pig.buycost * (100 + flucts) / 100;
                    p.pigspace--;
                    p.animals.add(new Pig());
                    System.out.println("Zakup przebiegł pomyślnie");
                    Helper.waitForEnter();
                }
            }
            case "3" -> {
                if (p.money < Chicken.buycost * (100 + flucts) / 100 || p.chickenspace < 1) {
                    System.out.println("Nie masz wystarczającej ilości gotówki lub miejsca ... ");

                } else {
                    p.money -= Chicken.buycost * (100 + flucts) / 100;
                    p.chickenspace--;
                    p.animals.add(new Chicken());
                    System.out.println("Zakup przebiegł pomyślnie");
                    Helper.waitForEnter();
                }
            }
            case "4" -> {
                if (p.money < Horse.buycost * (100 + flucts) / 100 || p.horsespace < 1) {
                    System.out.println("Nie masz wystarczającej ilości gotówki lub miejsca ... ");

                } else {
                    p.money -= Horse.buycost * (100 + flucts) / 100;
                    p.horsespace--;
                    p.animals.add(new Horse());
                    System.out.println("Zakup przebiegł pomyślnie");
                    Helper.waitForEnter();
                }
            }
            case "5" -> {
                if (p.money < Rabbit.buycost * (100 + flucts) / 100 || p.rabbitspace < 1) {
                    System.out.println("Nie masz wystarczającej ilości gotówki lub miejsca ... ");

                } else {
                    p.money -= Rabbit.buycost * (100 + flucts) / 100;
                    p.rabbitspace--;
                    p.animals.add(new Rabbit());
                    System.out.println("Zakup przebiegł pomyślnie");
                    Helper.waitForEnter();
                }
            }
            case "6" -> {}
            default -> System.out.println("Została wybrana nieprawidłowa opcja ...");
        }
    }


    public static void sellShop(Player p, int flucts) {
        String choice;
        System.out.println("Co dokładnie chciałbyś sprzedać ?");
        System.out.println("1. Mięso");
        System.out.println("2. Plony");
        System.out.println("3. Ziemię");
        System.out.println("4. Wyjdz ze sklepu ...");
        System.out.print("Twój wybór : ");
        choice = s.nextLine();
        System.out.println();
        switch (choice) {
            case "1" -> sellAnimals(p, flucts);
            case "2" -> sellcrops(p, flucts);
            case "3" -> {
                if (p.hectares == 0) System.out.println("Nie masz ziemii na sprzedaż");
                else {
                    p.hectares--;
                    p.money += 4000;
                }
            }
            case "4" -> {
            }
            default -> System.out.println("Została wybrana nieprawidłowa opcja ...");
        }
    }

    public static void sellAnimals(Player p, int flucts) {
        ArrayList<Animal> tosell = new ArrayList<>();
        Animal tmp;
        int choice2 = p.animals.size() + 1;
        for (Animal an : p.animals) {
            if (an.isAdult()) tosell.add(an);
        }
        if (tosell.isEmpty()) {
            System.out.println("Nie posiadasz zwierząt na sprzedaż");
        }
        System.out.println("Wszystkie dorosłe zwierzęta na sprzedaż  -");
        for (int i = 0; i < tosell.size(); i++) {
            tmp = tosell.get(i);
            System.out.println(i + ". Rodzaj zwierzęcia - " + tmp.type + ", cena za kg miesa - " + tmp.money_per_kg + ", cena za sprzedaż - " + tmp.money_per_kg * (100 + flucts) / 100 * tmp.kgs_week * tmp.age);
        }
        System.out.println(tosell.size() + ". Wyjdz ze sklepu...");
        System.out.println("Wybierz, które zwierze chcesz sprzedać");
        while (choice2 < 0 || choice2 > tosell.size()) {
            choice2 = Integer.parseInt(s.nextLine());
        }
        if (choice2 != tosell.size()) {
            tmp = tosell.get(choice2);
            p.money += tmp.money_per_kg * (100 + flucts) / 100 * tmp.kgs_week * tmp.age;
            p.animals.remove(tmp);
            System.out.println("Pomyślnie sprzedano zwierze!");
        }
    }

    public static void sellcrops(Player p, int flucts) {
        String choice;
        int kgs = 0;
        System.out.println("Posiadasz : ");
        System.out.println("1. " + p.stash[0] + "kg Bazylii ( cena za kg - " + Basil.sell_price * (100 + flucts) / 100 + " )");
        System.out.println("2. " + p.stash[1] + "kg Sałaty ( cena za kg - " + Lettuce.sell_price * (100 + flucts) / 100 + " )");
        System.out.println("3. " + p.stash[2] + "kg Buraków ( cena za kg - " + Beetroot.sell_price * (100 + flucts) / 100 + " )\"");
        System.out.println("4. " + p.stash[3] + "kg Pszenicy ( cena za kg - " + Wheat.sell_price * (100 + flucts) / 100 + " )\"");
        System.out.println("5. " + p.stash[4] + "kg Jabłek ( cena za kg - " + Apple.sell_price * (100 + flucts) / 100 + " )\"");
        System.out.println("6. " + p.stash[5] + "kg Wiśni ( cena za kg - " + Cherry.sell_price * (100 + flucts) / 100 + " )\"");
        System.out.println("7. Wyjdz ze sklepu ...");
        System.out.println("Które z nich chciałbyś sprzedać ? ");
        choice = s.nextLine();
        System.out.println("Ile kilogramów chcesz sprzedać? ");
        while (kgs < 1) {
            try {
                kgs = Integer.parseInt(s.nextLine());
            } catch (NumberFormatException ignored) {
            }
        }
        switch (choice) {
            case "1" -> {
                if (p.stash[0] < kgs) System.out.println("Nie masz wystarczającej ilości zapasów do sprzedaży");
                else {
                    p.money += kgs * Basil.sell_price * (100 + flucts) / 100;
                    p.stash[0] -= kgs;
                    p.warehouse_place += kgs;
                }
            }
            case "2" -> {
                if (p.stash[1] < kgs) System.out.println("Nie masz wystarczającej ilości zapasów do sprzedaży");
                else {
                    p.money += kgs * Lettuce.sell_price * (100 + flucts) / 100;
                    p.stash[1] -= kgs;
                    p.warehouse_place += kgs;
                }
            }
            case "3" -> {
                if (p.stash[2] < kgs) System.out.println("Nie masz wystarczającej ilości zapasów do sprzedaży");
                else {
                    p.money += kgs * Beetroot.sell_price * (100 + flucts) / 100;
                    p.stash[2] -= kgs;
                    p.warehouse_place += kgs;
                }
            }
            case "4" -> {
                if (p.stash[3] < kgs) System.out.println("Nie masz wystarczającej ilości zapasów do sprzedaży");
                else {
                    p.money += kgs * Wheat.sell_price * (100 + flucts) / 100;
                    p.stash[3] -= kgs;
                    p.warehouse_place += kgs;
                }
            }
            case "5" -> {
                if (p.stash[4] < kgs) System.out.println("Nie masz wystarczającej ilości zapasów do sprzedaży");
                else {
                    p.money += kgs * Apple.sell_price * (100 + flucts) / 100;
                    p.stash[4] -= kgs;
                    p.warehouse_place += kgs;
                }
            }
            case "6" -> {
                if (p.stash[5] < kgs) System.out.println("Nie masz wystarczającej ilości zapasów do sprzedaży");
                else {
                    p.money += kgs * Cherry.sell_price * (100 + flucts) / 100;
                    p.stash[5] -= kgs;
                    p.warehouse_place += kgs;
                }
            }
            case "7" -> {
            }
            default -> System.out.println("Błędny wybór...");
        }
    }
}
