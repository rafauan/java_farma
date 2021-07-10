import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class MainClass {

    // One Year = 60 weeks

    static Random r = new Random();
    static Scanner s = new Scanner(System.in);
    public static int week = 1;

    public static void main(String[] args) {
        ArrayList<Player> players = new ArrayList<>();
        int flucts;
        int numofplayers = 0;
        boolean selectedPlayers = false;
        Player actplayer;
        boolean cont;
        System.out.println("Podaj ilość graczy : ");
        while (!(selectedPlayers)) {
            try {
                System.out.print("Twój wybór : ");
                numofplayers = Integer.parseInt(s.nextLine());
                if (numofplayers > 0) {
                    selectedPlayers = true;
                }
            } catch (NumberFormatException ignored) {
            }

        }
        for (int i = 0; i < numofplayers; i++) {
            players.add(new Player());
        }
        while (!(Helper.isAnyWinner(players))) {
            flucts = r.nextInt(20) - 10;
            for (int h = 0; h < numofplayers; h++) {
                actplayer = players.get(h);
                cont = false;
                if (!(actplayer.namedetected)) {
                    System.out.println("Podaj imie gracza : ");
                    System.out.print("Twoje imie : ");
                    actplayer.name = s.nextLine();
                    System.out.println("Witaj" + actplayer.name + "!");
                    Helper.waitForEnter();
                    actplayer.money = Math.round(10000 + 20000 * r.nextDouble());
                    System.out.println("Twoje środki na początek wynoszą " + actplayer.money);
                    System.out.println("Na początek musisz zakupić swoją pierwszą farmę. Do wyboru na start są 4 farmy");
                    System.out.println("To od ciebie zależy, która stanie się twoją pierwszą - ograniczają ciebie jedynie pieniądze");
                    String choice;
                    while (!cont) {
                        System.out.println("Farmy są do wyboru w kolejności od najmniejszej do największej...");
                        System.out.println("1. Pierwsza Farma (4500) - 1ha");
                        System.out.println("2. Druga Farma (12000) - 3ha");
                        System.out.println("3. Trzecia Farma (21000) - 5ha i skład");
                        System.out.println("4. Czwarta farma (27000) - 8ha i skład ");
                        System.out.print("Twój wybór : ");

                        choice = s.nextLine();
                        switch (choice) {

                            case "1" -> {
                                if (actplayer.money < 4500) {
                                    System.out.println("Nie maszwystarczającej ilości gotówki lub miejsca..");
                                } else {
                                    actplayer.money -= 4500;
                                    actplayer.hectares += 1;
                                    System.out.println("Zakup przebiegł pomyślnie !");
                                    actplayer.farms_own[0] = 1;
                                    System.out.println("Posiadasz teraz nową farmę oraz 1ha ziemi więcej !");
                                    cont = true;
                                    Helper.waitForEnter();
                                }
                            }
                            case "2" -> {

                                if (actplayer.money < 12000) {
                                    System.out.println("Nie maszwystarczającej ilości gotówki lub miejsca..");
                                } else {
                                    actplayer.money -= 12000;
                                    actplayer.hectares += 3;
                                    actplayer.farms_own[1] = 1;
                                    System.out.println("Zakup przebiegł pomyślnie !");
                                    System.out.println("Posiadasz teraz nową farmę oraz 3ha ziemi więcej !");
                                    cont = true;
                                    Helper.waitForEnter();
                                }
                            }
                            case "3" -> {
                                if (actplayer.money < 21000) {
                                    System.out.println("Nie maszwystarczającej ilości gotówki lub miejsca..");
                                } else {
                                    actplayer.money -= 21000;
                                    actplayer.hectares += 5;
                                    actplayer.farms_own[2] = 1;
                                    actplayer.warehouse_place += 100;
                                    System.out.println("Zakup przebiegł pomyślnie !");
                                    System.out.println("Posiadasz teraz nową farmę , 5ha ziemi więcej oraz dodatkowy skład (100 kg warzyw i owoców) !");
                                    cont = true;
                                    Helper.waitForEnter();
                                }
                            }
                            case "4" -> {
                                if (actplayer.money < 27000) {
                                    System.out.println("Nie maszwystarczającej ilości gotówki lub miejsca..");
                                } else {
                                    actplayer.money -= 27000;
                                    actplayer.hectares += 8;
                                    actplayer.farms_own[3] = 1;
                                    actplayer.warehouse_place += 100;
                                    System.out.println("Zakup przebiegł pomyślnie !");
                                    System.out.println("Posiadasz teraz nową farmę , 8ha ziemi więcej oraz dodatkowy skład (100 kg warzyw i owoców) !");
                                    cont = true;
                                    Helper.waitForEnter();
                                }
                            }
                            case "5" -> {
                            }
                            default -> System.out.println("Została wybrana nieprawidłowa opcja ...");
                        }
                    }
                    System.out.println("Poznajmy teraz twoje początkowe statystyki.");
                    actplayer.status_check();
                    actplayer.namedetected = true;
                }
                cont = false;
                System.out.println("Gracz " + (h + 1) + ". - " + actplayer.name);
                System.out.println("Obecnie mamy tydzien " + week);
                System.out.println("Oto lista rzeczy, którą możesz wykonać nieograniczoną ilość razy w tygodniu - ograniczeniami są jedynie miejsce i zasoby");
                System.out.println("Wybierz co chcesz robić dalej albo przejdź do kolejnego tygodnia ...");
                while (!cont) cont = Menu.selectionMenu(actplayer, week, flucts);
                Sumbit.submit(actplayer, flucts);
                if (h == numofplayers - 1) {
                    week++;
                    if (week == 60) {
                        week = 1;
                    }
                }
                Helper.bump();
                Helper.waitForEnter();
            }
        }
    }
}
