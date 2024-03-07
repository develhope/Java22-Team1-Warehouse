package UserAndOperatorEnums;

public enum MenuOptionsUser {
    VISUALIZZA_TUTTI_PRODOTTI("Visualizza tutti i prodotti"),
    RICERCA_PER_TIPO_DISPOSITIVO("Ricerca per tipo di dispositivo"),
    RICERCA_PER_PRODUTTORE("Ricerca per produttore"),
    RICERCA_PER_MODELLO("Ricerca per modello"),
    RICERCA_PER_PREZZO_DI_VENDITA("Ricerca per prezzo di vendita"),
    RICERCA_PER_RANGE_DI_VENDITA("Ricerca per range di vendita"),
    AGGIUNGI_AL_CARRELLO("Aggiungi al carrello"),
    RIMUOVI_DAL_CARRELLO("Rimuovi dal carrello"),
    CALCOLARE_IL_TOTALE("Calcola il totale"),
    VISUALIZZA_IL_CARRELLO("Visualizza il carrello"),
    ACQUISTA("Acquista"),
    FINE("Fine"),
    UNKNOWN("");

    private String string;

    MenuOptionsUser(String s) {
        this.string = s;
    }

    public String getStringa() {
        return string;
    }
}