import java.util.ArrayList;

public class Magazzino {
  private ArrayList<ClasseDispositivi> dispositivi = new ArrayList<>();

  public Magazzino() {
    this.dispositivi = new ArrayList<>();
  };

  public void aggiungi(ClasseDispositivi dispositivo) {
    dispositivi.add(dispositivo);
  }

  public void rimuovi(int index) {
    if(index >=0 && index < dispositivi.size()) {
      dispositivi.remove(index);
    } else {
      System.out.println("Indice non valido");
    }
  }

  public void stampa() {
    for(int i = 0; i < dispositivi.size(); i++) {
      System.out.println(i + " : " + dispositivi.get(i));
    }
  }

  public ClasseDispositivi getPerModello(String modello) {
    String modelloLowerCase = modello.toLowerCase();

    for(int i = 0; i < dispositivi.size(); i++) {
      if(modelloLowerCase.equals(dispositivi.get(i).getModel())) {
        return dispositivi.get(i);
      }
    }
    return null;
  }

  public ArrayList<ClasseDispositivi> getPerDispositivo(String input) {
    String inputLowerCase = input.toLowerCase();
     ArrayList<ClasseDispositivi> dispositiviCompatibili = new ArrayList<>();

    for(int i = 0; i < dispositivi.size(); i++) {
      String deviceLowerCase = dispositivi.get(i).getDevice().toLowerCase();

      if(inputLowerCase.equals(deviceLowerCase)) {
        dispositiviCompatibili.add(dispositivi.get(i));
      }
    }
    return dispositiviCompatibili;
  }

  public ClasseDispositivi getIndiceDispositivo(int indice) {
    if(indice >= 0 && indice < dispositivi.size()) {
      return dispositivi.get(indice);
    } else {
      System.out.println("Indice non valido");
      return null;
    }
  }
}
