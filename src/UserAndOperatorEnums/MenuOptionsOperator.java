package UserAndOperatorEnums;

public enum MenuOptionsOperator {
    VISUALIZZA_TUTTI_PRODOTTI("Visualizza tutti i prodotti"),
    RICERCA_PER_TIPO_DISPOSITIVO("Ricerca per tipo di dispositivo"),
    RICERCA_PER_PRODUTTORE("Ricerca per produttore"),
    RICERCA_PER_MODELLO("Ricerca per modello"),
    RICERCA_PER_PREZZO_DI_VENDITA("Ricerca per prezzo di vendita"),
    RICERCA_PER_PREZZO_DI_ACQUISTO("Ricerca per prezzo di acquisto"),
    RICERCA_PER_RANGE_DI_ACQUISTO("Ricerca per range di acquisto"),
    RICERCA_SPESA_MEDIA_DISPOSITIVO("Ricerca spesa media di un dispositivo"),
    AGGIUNGI_DISPOSITIVO_AL_MAGAZZINO("Aggiungi un dispositivo al magazzino"),
    RIMUOVI_DISPOSITIVO_DAL_MAGAZZINO("Rimuovi un dispositivo dal magazzino"),
    FINE("Fine"),
    UNKNOWN("");

    private String string;

    MenuOptionsOperator(String s) {
        this.string = s;
    }

    public String getStringa() {
        return string;
    }
}

