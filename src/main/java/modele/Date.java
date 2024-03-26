package modele;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.Calendar;

public class Date implements Comparable <Date> {
    protected int chJour;
    protected int chMois;
    protected int chAnnee;

    public Date() {
        Calendar today = Calendar.getInstance();

        chAnnee = today.get(Calendar.YEAR);
        chMois = today.get(Calendar.MONTH) + 1;
        chJour = today.get(Calendar.DAY_OF_MONTH);
    }

    public Date(int aChAnnee) {
        chJour = 1;
        chMois = 1;
        chAnnee = aChAnnee;
    }

    public Date(int aChJour, int aChMois, int aChAnnee) {
        chJour = aChJour;
        chMois = aChMois;
        chAnnee = aChAnnee;
    }

    public static Date lireDate() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Entrez un jour : ");
        int jour = scanner.nextInt();

        System.out.print("Entrez un mois : ");
        int mois = scanner.nextInt();

        System.out.print("Entrez une année : ");
        int annee = scanner.nextInt();

        scanner.close();

        return new Date(jour, mois, annee);
    }

    public int getWeekOfYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(chAnnee, chMois - 1, chJour);

        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    public boolean estValide() {
        if (chMois > 0 && chMois < 13) {
            if (chJour > 0 && chJour <= dernierJourMois(chMois, chAnnee)) {
                return true;
            }
        }

        return false;
    }

    private static int dernierJourMois(int aMois, int aAnnee) {
        switch (aMois) {
            case 2:
                if (Date.estBissextile(aAnnee)) { return 29; }
                return 28;
            case 4: case 6: case 9: case 11:
                return 30;
            default:
                return 31;
        }
    }

    private static boolean estBissextile(int aAnnee) {
        if (aAnnee >= 1583) {
            if (aAnnee % 400 == 0 || (aAnnee % 4 == 0 && aAnnee % 100 != 0)) {
                return true;
            }
        }
        return false;
    }

    public int getMois() {
        return chMois;
    }

    public String toString() {
        return chJour + "/" + chMois + "/" + chAnnee;
    }


    public int compareTo(Date aDate) {
        if (this.chAnnee < aDate.chAnnee) {
            return -1;
        }

        else if (this.chAnnee > aDate.chAnnee) {
            return 1;
        }

        else {
            if (this.chMois < aDate.chMois) {
                return -1;
            }

            else if (this.chMois > aDate.chMois) {
                return 1;
            }

            else {
                if (this.chJour < aDate.chJour) {
                    return -1;
                }

                else if (this.chJour > aDate.chJour) {
                    return 1;
                }

                return 0;
            }
        }
    }

    /**
     * Retourne la date du jour suivant l'objet de type Date
     * sur lequel cette méthode est appelée.
     *
     * @return la date du lendemain
     */
    public Date dateDuLendemain() {
        if (chJour == dernierJourMois(chMois, chAnnee)) {
            if (chMois == 12) {
                return new Date(1, 1, chAnnee + 1);
            }

            return new Date(1, chMois + 1, chAnnee);
        }

        return new Date(chJour + 1, chMois, chAnnee);
    }

    /**
     * Retourne la date du jour précédant l'objet de type Date
     * sur lequel cette méthode est appelée.
     *
     * @return la date de la veille
     */
    public Date dateDeLaVeille() {
        if (chJour == 1) {
            if (chMois == 1) {
                return new Date(31, 12, chAnnee - 1);
            }

            return new Date(dernierJourMois(chMois - 1, chAnnee), chMois - 1, chAnnee);
        }

        return new Date(chJour - 1, chMois, chAnnee);
    }

    public String isToday() {

        LocalDate currentDate = LocalDate.now();
        String currentDateString = currentDate.toString();

        return currentDateString;

    }

    public int getAnnee() {
        return chAnnee;

    }
}