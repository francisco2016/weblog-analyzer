import java.util.Scanner;

/**
 * Break up line from a web server log file into
 * ----------Romper la l�nea de un archivo de registro del servidor web en
 * its separate fields.---------� * Sus campos separados.
 * Currently, the log file is assumed to contain simply
 * ---------------En la actualidad, el archivo de registro se supone que contienen simplemente -----------
 * ----------------Fecha y hora del n�mero entero.
 * @author David J. Barnes and Michael Kolling.
 * @version 2008.03.30
 */
public class LoglineTokenizer
{
    /**
     * Construct a LogLineAnalyzer
     */
    public LoglineTokenizer()
    {
    }

    /**
     * Tokenize a log line. Place the integer values from
     * it into an array. The number of tokens on the line
     * must be sufficient to fill the array.
     *
     * @param logline The line to be tokenized.
     * @param dataLine Where to store the values.
     */
    public void tokenize(String logline, int[] dataLine)
    {
        try {
            // Scan the logline for integers.
            Scanner tokenizer = new Scanner(logline);
            for(int i = 0; i < dataLine.length; i++) {
                dataLine[i] = tokenizer.nextInt();
            }
        }
        catch(java.util.NoSuchElementException e) {
            System.out.println("Insuffient data items on log line: " + logline);
            throw e;
        }
    }
}
