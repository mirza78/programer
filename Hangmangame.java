/*
 * Die Klasse HangmanGame verwaltet das Gesamtspiel mit mehreren Wörter-Hangmen.
 * Sie kümmert sich um die Abfrage der Wörter, die Spielschleife und die Validierung der Benutzereingaben.
 */
public class Hangmangame{

    private Hangman[] hangmen;

    /*
     * Startet das Spiel: Liest die Anzahl der Wörter sowie die Wörter selbst ein
     * und führt die Hauptspielschleife aus.
     */
    public void run() {
        Out.print("How many words do you want to play? ");
        int count = In.readInt();
        while (count <= 0) {
            Out.print("How many words do you want to play? ");
            count = In.readInt();
        }

        hangmen = new Hangman[count];

        for (int i = 0; i < count; i++) {
            Out.print("Which word do you want to have on hangman #" + (i + 1) + "? ");
            String w = In.readWord();
            hangmen[i] = new Hangman(w);
        }

        gameLoop();
    }

    /*
     * Führt die Schleife durch, bis das Spiel gewonnen oder verloren ist.
     */
    private void gameLoop() {
        while (!isGameOver()) {
            printAllHangmen();

            Out.print("Which letter do you want to guess? ");
            char letter = In.readChar();

            int wordIndex = readValidWordIndex();
            hangmen[wordIndex].makeGuess(letter);
        }

        printAllHangmen();
        printEndResult();
    }

    /*
     * Liest eine gültige Wortnummer (1-basiert) vom Benutzer ein und gibt den korrekten Array-Index (0-basiert) zurück.
     * Rückgabewert: int
     */
    private int readValidWordIndex() {
        Out.print("Which word (number) do you want to play? ");
        int idx = In.readInt();

        while (idx < 1 || idx > hangmen.length) {
            Out.print("Word number not valid. Which word (number) do you want to play? ");
            idx = In.readInt();
        }

        return idx - 1;
    }

    /*
     * Gibt den aktuellen Status aller Hangmen aus.
     */
    private void printAllHangmen() {
        for (int i = 0; i < hangmen.length; i++) {
            Out.println("Word " + (i + 1) + ":");
            hangmen[i].printState();
        }
    }

    /*
     * Überprüft, ob das Spiel zu Ende ist.
     * Ein Spiel endet, wenn alle Wörter gelöst sind oder mindestens ein Wort verloren wurde.
     * Rückgabewert: boolean
     */
    private boolean isGameOver() {
        if (hasAnyLost()) {
            return true;
        }
        return areAllSolved();
    }

    /*
     * Prüft, ob mindestens ein Wort verloren wurde.
     * Rückgabewert: boolean
     */
    private boolean hasAnyLost() {
        for (Hangman h : hangmen) {
            if (h.isLost()) {
                return true;
            }
        }
        return false;
    }

    /*
     * Prüft, ob alle Wörter gelöst wurden.
     * Rückgabewert: boolean
     */
    private boolean areAllSolved() {
        for (Hangman h : hangmen) {
            if (!h.isSolved()) {
                return false;
            }
        }
        return true;
    }

    /*
     * Gibt das Spielergebnis (Gewonnen/Verloren) sowie die Anzahl gelöster Wörter am Spielende aus.
     */
    private void printEndResult() {
        int solvedCount = 0;
        for (Hangman h : hangmen) {
            if (h.isSolved()) {
                solvedCount++;
            }
        }

        if (hasAnyLost()) {
            Out.println("You lost! You solved " + solvedCount + " word(s)");
        } else {
            Out.println("You won! You solved " + solvedCount + " word(s)");
        }
    }
}