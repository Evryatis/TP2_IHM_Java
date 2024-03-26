package vue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import modele.CalendrierDuMois;
import modele.ConstantesCalendrier;
import modele.DateCalendrier;

import java.util.List;

public class VBoxRoot extends VBox implements ConstantesCalendrier {
    public VBoxRoot() {
        DateCalendrier today = new DateCalendrier();

        //
        Label labelMois = new Label(MOIS[today.getMois()-1]+ " " + today.getAnnee());
        this.getChildren().add(labelMois);

        //
        StackPane stackPaneMois = new StackPane(); // création de l'objet stackpane
        VBox.setMargin(stackPaneMois, new Insets(4)); // on y crée une légère marge

        for (int mois = 1; mois <= 12; mois++) { // pour chaque mois de l'année
            CalendrierDuMois monthCalendar = new CalendrierDuMois(mois, today.getAnnee()); // création du calendrier pour le mois en cours
            VBox boiteDates = new VBox();

            // init du nom du mois psk trop compliqué à faire sinon
            String nomMois = MOIS[mois - 1];

            // Iterator pour parcourir les dates du mois
            for (DateCalendrier date : monthCalendar.getDates()) { // boucle spéciale qui récupère les dates du calendrier du mois
                Label labelDate = new Label(date.toString());
                if (date.getMois() != monthCalendar.getMois()) {
                    labelDate.setId("dateHorsMois");
                }

                VBox.setMargin(labelDate, new Insets(8));
                boiteDates.getChildren().add(labelDate);
            }

            ScrollPane scrollPane = new ScrollPane(); // création d'un ScrollPane pour chaque mois
            scrollPane.setContent(boiteDates);
            scrollPane.setAccessibleText(nomMois); // Associer le nom du mois au ScrollPane

            stackPaneMois.getChildren().add(scrollPane); // Ajout du ScrollPane dans le StackPane
        }
        // Ajout du StackPane dans le VBoxRoot
        this.getChildren().add (stackPaneMois);

        List<Node> listMonthCalendars = stackPaneMois.getChildren();
        final int lastIndice = listMonthCalendars.size()-1;
        Node premierMois = listMonthCalendars.get(0);
        Node dernierMois = listMonthCalendars.get(lastIndice);


        // placer le mois courant en haut de la pile
        //System.out.println(MOIS[today.getMois()-1]);
        while(!listMonthCalendars.get(lastIndice).getAccessibleText().equals(MOIS[today.getMois()-1])) {
            listMonthCalendars.get(lastIndice).toBack();
        }

        // Initialisation des b
        Button boutonSuivant = new Button();
        Button boutonFin = new Button();
        Button boutonPrecedant = new Button();
        Button boutonDebut = new Button();
        boutonSuivant.setText(">");
        boutonFin.setText(">>");
        boutonPrecedant.setText("<");
        boutonDebut.setText("<<");
        boutonSuivant.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                listMonthCalendars.get(0).toFront();
            }
        });

        boutonPrecedant.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                listMonthCalendars.get(lastIndice).toBack();
            }
        });

        boutonFin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                for(int i = 0 ; i < 13 ; i ++){
                    if (listMonthCalendars.get(lastIndice).getAccessibleText().equals("decembre")) {
                        break;
                    }
                listMonthCalendars.get(0).toFront();
                }

            }
        });

        boutonDebut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                for(int i = 0 ; i < 13 ; i ++){
                    if (listMonthCalendars.get(lastIndice).getAccessibleText().equals("janvier")) {
                        break;
                    }
                    listMonthCalendars.get(0).toFront();
                }

            }
        });
        this.getChildren().add(boutonSuivant);
        this.getChildren().add(boutonFin);
        this.getChildren().add(boutonPrecedant);
        this.getChildren().add(boutonDebut);

    }
}
