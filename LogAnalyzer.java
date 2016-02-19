/**
 * Read web server data and analyse
 * hourly access patterns.
 * 
 * @author David J. Barnes and Michael KÃ¶lling.
 * @version 2011.07.31
 */
public class LogAnalyzer
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;

    /**
     * Create an object to analyze hourly web accesses.
     * Crear un objeto para analizar los accesos web por hora.
     */
    public LogAnalyzer()
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        // Create the reader to obtain the data.
        reader = new LogfileReader();
    }

    /**
     * Analyze the hourly access data from the log file.
     * Analizar los datos de acceso por hora desde el archivo de registro.
     */
    public void analyzeHourlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();//--se guarda un objeto LogEntry en la VL entry
            int hour = entry.getHour();    // --guardamos la hora de entrada en la VL hour ----------------------0069
            hourCounts[hour]++;          //la posición del Array suma 1, para saber la hora a la que ha habido una entrada
        }
    }

    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printHourlyCounts()
    {
        System.out.println("Hr: Count");
        //         for(int hour = 0; hour < hourCounts.length; hour++) {
        //             System.out.println(hour + ": " + hourCounts[hour]);
        //         }
        int hour = 0;
        while(hour < hourCounts.length ){
            System.out.println(hour + ": " + hourCounts[hour]);
            hour ++;
        }
    }

    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();  //imprime todas las líneas del archivo------------------------------------------------- 0069
    }
}
