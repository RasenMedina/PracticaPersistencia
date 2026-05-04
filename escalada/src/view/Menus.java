package view;

public class Menus {

    public static void menuPrincipal() {

        String[] opcions = {
                "Gestionar escoles",
                "Gestionar sectors",
                "Gestionar vies",
                "Gestionar escaladors",
                "Sortir"
        };

        Vista.titol("MENU PRINCIPAL");
        Vista.mostrarMenu(opcions);
    }
}
