package modele;

import java.util.Calendar;

public class DateCalendrier extends Date implements ConstantesCalendrier {
    private int chJourSemaine;

    public DateCalendrier() {
        super();

        Calendar today = Calendar.getInstance();

        chJourSemaine = today.get(Calendar.DAY_OF_WEEK);

        if (chJourSemaine == 1) {
            chJourSemaine = 7;
        }

        else {
            chJourSemaine -= 1;
        }
    }

    public DateCalendrier(int parJour, int parMois, int parAnnee) {
        super(parJour, parMois, parAnnee);

        Calendar date = Calendar.getInstance();
        date.set(parAnnee, parMois - 1, parJour);

        chJourSemaine = date.get(Calendar.DAY_OF_WEEK);

        if (chJourSemaine == 1) {
            chJourSemaine = 7;
        }

        else {
            chJourSemaine -= 1;
        }
    }

    public DateCalendrier dateDuLendemain() {
        Date date = super.dateDuLendemain();

        return new DateCalendrier(date.chJour, date.chMois, date.chAnnee);
    }

    public DateCalendrier dateDeLaVeille() {
        Date date = super.dateDeLaVeille();

        return new DateCalendrier(date.chJour, date.chMois, date.chAnnee);
    }

    public String toString() {
        return JOURS_SEMAINE[chJourSemaine - 1] + " " + chJour + " " + MOIS[chMois - 1] + " " + chAnnee;
    }

    public int getJourSemaine() {
        return chJourSemaine;
    }
}