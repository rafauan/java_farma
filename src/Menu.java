import Plants.Plant;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    static Scanner s = new Scanner(System.in);

    public static boolean selectionMenu(Player p, int week, int flucts) {
        Helper.waitForEnter();
        Helper.bump();
        String choice;
        System.out.println("1. Sklep");
        System.out.println("2. Sadzenie nasion");
        System.out.println("3. Zbiory");
        System.out.println("4. Sprawdź swoje zasoby");
        System.out.println("5. Informacje o posiadanych zwierzętach");
        System.out.println("6. Informacje o posiadanych roślinach");
        System.out.println("7. Sprawdź stan zapasów");
        System.out.println("8. Kolejny tydzien... / Kolejny Gracz ...");
        System.out.print("Twój wybór : ");
        choice = s.nextLine();
        System.out.println();
        switch (choice) {
            case "1" -> Shop.shop(p, flucts);
            case "2" -> {
                int choice2 = 0;
                ArrayList<Plant> notPlantedYet = new ArrayList<>();
                if (p.plants.isEmpty()) {
                    System.out.println("Nie posiadasz żadnych Roślin");
                    break;
                }
                for (int i = 0; i < p.plants.size(); i++) {
                    if (!(p.plants.get(i).isPlanted)) notPlantedYet.add(p.plants.get(i));
                }
                if (notPlantedYet.isEmpty()) System.out.println("Wszystkie roślinki są już posadzone");
                else {
                    System.out.println("Którą roślinkę chcesz posadzić  : ");
                    System.out.print("Twój wybór : ");

                    for (int i = 0; i < notPlantedYet.size(); i++) {
                        System.out.println(i + ". " + notPlantedYet.get(i).type);
                    }
                    while (choice2 >= notPlantedYet.size()) {
                        System.out.println("Wybierz ponownie , zly wybór...");
                        System.out.print("Twój wybór : ");
                        choice2 = Integer.parseInt(s.nextLine());
                    }
                    Plant tmp = notPlantedYet.get(choice2);
                    if (tmp.checkSeason(week))
                        System.out.println(" Pomyślnie zasadziłeś " + notPlantedYet.get(choice2).type);
                }

            }
            case "3" -> {
                int choice2 = 0;
                ArrayList<Plant> readytocollect = new ArrayList<>();
                if (p.plants.isEmpty()) {
                    System.out.println("Nie posiadasz żadnych Roślin");
                    break;
                }
                for (int i = 0; i < p.plants.size(); i++) {
                    if (p.plants.get(i).age >= p.plants.get(i).grow_time) readytocollect.add(p.plants.get(i));
                }
                if (readytocollect.isEmpty()) System.out.println("Nie masz jeszcze w pełni wyrośniętych roślinek");
                else {
                    System.out.println("Którą roślinkę chcesz zebrać  : ");
                    System.out.print("Twój wybór : ");

                    for (int i = 0; i < readytocollect.size(); i++) {
                        System.out.println(i + ". " + readytocollect.get(i).type);
                    }
                    while (choice2 >= readytocollect.size()) {
                        System.out.println("Wybierz ponownie , zly wybór...");
                        System.out.print("Twój wybór : ");
                        choice2 = Integer.parseInt(s.nextLine());
                    }
                    Plant tmp = readytocollect.get(choice2);
                    System.out.println(" Pomyślnie zebrałeś " + tmp.type);
                    if (p.placeCheck(tmp.efficiency)) {
                        switch (tmp.type) {
                            case "basil" -> {
                                p.stash[0] += tmp.efficiency;
                                p.plants.remove(tmp);
                            }
                            case "lettuce" -> {
                                p.stash[1] += tmp.efficiency;
                                p.plants.remove(tmp);
                            }
                            case "beetroot" -> {
                                p.stash[2] += tmp.efficiency;
                                p.plants.remove(tmp);

                            }
                            case "wheat" -> {
                                p.stash[3] += tmp.efficiency;
                                p.plants.remove(tmp);
                            }
                            case "apple" -> {
                                p.stash[4] += tmp.efficiency;
                                p.plants.remove(tmp);
                            }
                            case "cherry" -> {
                                p.stash[5] += tmp.efficiency;
                                p.plants.remove(tmp);
                            }
                        }
                    }
                }
            }
            case "4" -> p.status_check();
            case "5" -> p.animalsInfo();
            case "6" -> p.plantsInfo();
            case "7" -> p.stashInfo();
            case "8" -> {
                return true;
            }
            default -> selectionMenu(p, week, flucts);

        }

        return false;
    }
}
