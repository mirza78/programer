/*
 * Die Klasse Hangman repräsentiert ein einzelnes Hangman-Wort im Spiel.
 * Sie speichert das Zielwort, den aktuellen Erratungszustand sowie die verbleibenden Leben.
 */
public class Hangman {

    private String word;
    private char[] guessedChars;
    private int lives;

    /*
     * Konstruktor zur Initialisierung eines Hangman-Objekts.
     * Erhält das eingegebene Wort, wandelt es in Großbuchstaben um und setzt die Startleben auf 4.
     * Eingangsparameter: String word
     */
    public Hangman(String word) {
        this.word = word.toUpperCase();
        this.lives = 4;
        this.guessedChars = new char[this.word.length()];
        for (int i = 0; i < this.guessedChars.length; i++) {
            this.guessedChars[i] = '.';
        }
    }

    /*
     * Prüft, ob ein übergebener Buchstabe im Wort vorkommt.
     * Wenn ja, werden alle Vorkommen im guessedChars-Array aufgedeckt.
     * Wenn nein oder wenn der Buchstabe bereits komplett aufgedeckt war, wird ein Leben abgezogen.
     * Eingangsparameter: char letter
     * Rückgabewert: boolean (true, wenn mindestens ein verdeckter Buchstabe aufgedeckt wurde)
     */
    public boolean makeGuess(char letter) {
        letter = Character.toUpperCase(letter);
        boolean foundNew = false;

        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == letter && guessedChars[i] == '.') {
                guessedChars[i] = letter;
                foundNew = true;
            }
        }

        if (!foundNew) {
            lives--;
        }

        return foundNew;
    }

    /*
     * Gibt zurück, ob das Wort komplett erraten wurde.
     * Rückgabewert: boolean
     */
    public boolean isSolved() {
        for (char c : guessedChars) {
            if (c == '.') {
                return false;
            }
        }
        return true;
    }

    /*
     * Gibt zurück, ob das Wort verloren wurde (keine Leben mehr).
     * Rückgabewert: boolean
     */
    public boolean isLost() {
        return lives <= 0;
    }

    /*
     * Gibt die Anzahl der verbleibenden Leben zurück.
     * Rückgabewert: int
     */
    public int getLives() {
        return lives;
    }

    /*
     * Gibt den aktuellen Spielstand des Wortes sowie die Galgenzeichnung aus.
     */
   public void printState() {
        String currentStr = new String(guessedChars);
        Out.println(currentStr + " (" + word.length() + ")");
        if (lives < 4) {
            printHanger(lives);
        }
        Out.println("Remaining lives: " + lives);
    }
    /*
     * Gegebene Methode zur Ausgabe der Galgenzeichnung.
     * Eingangsparameter: int lives
     */
    private static void printHanger(int lives) {
    switch (lives) {
      case 3:
        Out.println("---------");
        Out.println(" |      |");
        Out.println(" |      O");
        Out.println(" |");
        Out.println(" |");
        Out.println(" |");
        Out.println("_|_");
        break;
      case 2:
      case 1:
        Out.println("---------");
        Out.println(" |      |");
        Out.println(" |      O");
        Out.println(" |     /|\\");
        Out.println(" |");
        Out.println(" |");
        Out.println("_|_");
        break;
      case 0:
        Out.println("---------");
        Out.println(" |      |");
        Out.println(" |      O");
        Out.println(" |     /|\\");
        Out.println(" |     / \\");
        Out.println(" |");
        Out.println("_|_");
        break;
      default:
        break;
    }
  }
}