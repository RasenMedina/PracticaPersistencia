import input.InputReader;
import view.Menus;
import view.Vista;

public class Main {

    public static void main(String[] args) {

        Vista.titol("APLICACIÓ ESCALADA");

        int opcio;

        do {
            Menus.menuPrincipal();
            opcio = InputReader.llegirOpcio("Escull opció", 0, 4);

            switch (opcio) {
                case 1:
                    Vista.info("Gestió escoles...");
                    break;
                case 2:
                    Vista.info("Gestió sectors...");
                    break;
                case 3:
                    Vista.info("Gestió vies...");
                    break;
                case 4:
                    Vista.info("Gestió escaladors...");
                    break;
                case 0:
                    Vista.info("Sortint...");
                    break;
            }

        } while (opcio != 0);

        Vista.ok("Programa finalitzat");
    }
}