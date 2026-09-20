/*
 * Die Klasse HangmanApp enthält die Main-Methode und startet das Multi-Hangman-Spiel.
 * 
 * Schrittweise Verfeinerung des Hauptproblems "Multi-Hangman Spiel durchführen":
 * 
 * 1. Hauptproblem zerlegen:
 *    - Spielinstanz erzeugen und starten.
 * 
 * 2. Spielablauf in HangmanGame zerlegen:
 *    - Eingabe: Anzahl der Wörter und Wörter abfragen.
 *    - Hauptschleife (solange nicht GameOver):
 *        a. Status aller Wörter ausgeben.
 *        b. Buchstabe einlesen.
 *        c. Gültige Wortnummer einlesen (Validierung).
 *        d. Zug auf dem gewählten Wort durchführen.
 *    - Endergebnis ausgeben.
 * 
 * 3. Wort-Ebene in Hangman zerlegen:
 *    - Zustand verwalten (Buchstaben-Array, verbleibende Leben).
 *    - Tipp auswerten (Buchstabe im Wort suchen; falls neu -> aufdecken, sonst Leben - 1).
 *    - Status prüfen (gelöst / verloren).
 */
public class Hangmanapp {

    public static void main(String[] args) {
        Hangmangame game = new Hangmangame();
        game.run();
    }
}