/**
 * Read web server data and analyse-------- Leer datos de servidor web y analizar
 * hourly access patterns.----------------- Patrones de acceso por hora
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
    //para guardar el nº de accesos al servidor web ------------------------------------------------------------------ 0073
    private int contadorEntradas;
    /**
     * Create an object to analyze hourly web accesses.
     * -----------Crear un objeto para analizar los accesos web por hora.
     */
    public LogAnalyzer()
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        // Create the reader to obtain the data.
        reader = new LogfileReader();
        contadorEntradas = 0;//-----------------para guardar el nº de accesos al servidor web---------------------------- 0073
    }

    /**
     * Añade a la clase LogAnalyzer un nuevo constructor. Este constructor tendrá un parámetro consistente en el nombre
     * del archivo de log a analizar. Usa la clase LogFileCreator para crear tu propio archivo de log y comprueba que puedes
     * analizarlo con la clase LogAnalyzer.
     * ------------------------------------------------------------------------------------------------------------------- 0070
     */
    public LogAnalyzer(String fileName){
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        // Create the reader to obtain the data.
        reader = new LogfileReader(fileName);
        contadorEntradas = 0; //------------------------------------------------------------------------------------ 0073
    }
    
    /**
     * se pued ejecutar después del método analyzeHourlyData y que devuelva la hora a la que el servidor estuvo menos
     * sobrecargado. Si hay empate devuelve la última de las horas.
     * Si no ha habido accesos informa del hecho por pantalla y devuelve -1.
     * -----------------------------------------------------------------------------------------------  --------       0705
     */
    public int quietestHour()   //------------------------------------------------------------------------------- 0705
    {
        //Guardamos la hoa con menor numero de peticiones
        int horaConMenosPeticiones = 0;
        //Guardamos aqui el menor numero de peticiones hasta el momento
        int menorNumeroDePeticiones = hourCounts[0];

        if (numberOfAccesses() == 0) {
            horaConMenosPeticiones = -1;
            System.out.println("No ha habido accesos");
        }
        else {
            //Recorro el array donde estan guardados los accesos por hora
            for(int hora = 1; hora < hourCounts.length; hora++) {
                //Por cada elemento, si el numero de peticiones de esa hora es menor
                //que el menor numero de peticiones que tenemos registrado...
                if (menorNumeroDePeticiones >= hourCounts[hora]) {
                    //Ponemos como menor numero de peticiones las de esta hora
                    menorNumeroDePeticiones = hourCounts[hora];
                    //Guardamos esta hora como la que menos peticiones ha tenido
                    horaConMenosPeticiones = hora;
                }
            }
        }
        return horaConMenosPeticiones;
    }     
    
    /**
     * Muestre por pantalla el período de dos horas consecutivas con más carga 
     * del día y devuelva un entero con la primera hora de dicho periodo. Si 
     * hay empate devuelve el último período. Si no ha habido accesos informa 
     * del hecho por pantalla y devuelve -1.
     */
    public int busiestTwoHour()
    {
      int horaInicialPeriodoConMasPeticiones = -1;
      int mayorNumeroDePeticiones = 0;
      
      for(int hora = 0; hora < hourCounts.length; hora++) {
        int	sumaDePeticionesDeDosHoras = hourCounts[hora] + hourCounts[(hora + 1) % 24];
      	if (mayorNumeroDePeticiones <= sumaDePeticionesDeDosHoras) {
      		mayorNumeroDePeticiones = sumaDePeticionesDeDosHoras;
          horaInicialPeriodoConMasPeticiones = hora;
        }
      }
      
      if (horaInicialPeriodoConMasPeticiones == -1) {
        System.out.println("No ha habido accesos");
      }
        
      return horaInicialPeriodoConMasPeticiones;      
    }   

    
    /**
     * se pueda ejecutar después del método analyzeHourlyData y que devuelva el número total de accesos al servidor web
     * registrados en el archivo de log-------------- -----------------------------------------------------------------   0073
     * 
     * teniendo en cuenta que el mt.analyzeHourlyData() analiza las entradas...
     */ 
    public int numberOfAccesses(){
        return contadorEntradas;  //---------------muestra el nº de accesos al servidor web------------------------------- 0073
    }
    //      ---------------------------otra solución más elegante es la siguiente.
    //     /**
    //      * Devuelve el numero total de accesos al servidor registrados
    //      * en el archivo de log
    //      */
    //     public int numberOfAccesses()
    //     {
    //         int numeroDeAccesos = 0;
    //         int index = 0;
    //         while(index < hourCounts.length)
    //         {
    //             numeroDeAccesos =numeroDeAccesos + hourCounts[index];
    //             index++;
    //         }
    //         return numeroDeAccesos;  
    //     }

    /**
     * Devuelve a qué hora el servidor tuvo que responder a más peticiones.
     * Si hay empate devuelve la última de las horas. Si no ha habido acceso,
     * informa del hecho por pantalla y devuelve -1
     */
    public int busiestHour()
    {
        //Empezamos suponiendo que 0 es el valor más alto de peticiones por hora
        int mayorNumeroPeticionesPorHora = 0;
        //Aqui registramos la hora con más peticiones...
        int horaConMasAccesos = -1;
      
        //Recorremos el array y, conforme vamos avanzando, si encontramos
        //una cantidad de accesos más alta que la que tenemos registrada
        //como más alta, pisamos el valor antiguo y actualizamos la variable que
        //nos informa de la hora con más accesos
        for (int index = 0; index < hourCounts.length; index++) {
            //Ponemos mayor O IGUAL porque queremos que si hay empate 
            //se quede registrada la última horahoraConMasAccesos
            if (hourCounts[index] >= mayorNumeroPeticionesPorHora) {
              horaConMasAccesos = index;
                mayorNumeroPeticionesPorHora = hourCounts[index];
            }        
        }
      
        //Si no ha habido accesos, se informa por pantalla
        if ( mayorNumeroPeticionesPorHora == 0) {
          System.out.println("No ha habido accesos");
          horaConMasAccesos = -1;
        }
      
        //Devolvemos la hora con más accesos
        return horaConMasAccesos;
    }
    
    /**
     * Analyze the hourly access data from the log file.
     * ------------Analizar los datos de acceso por hora desde el archivo de registro.
     */
    public void analyzeHourlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();//--se guarda un objeto LogEntry en la VL entry
            int hour = entry.getHour();    // --guardamos la hora de entrada en la VL hour ----------------------0069
            hourCounts[hour]++;          //la posición del Array suma 1, para saber la hora a la que ha habido una entrada
            contadorEntradas++;
        }
    }

    /**
     * Print the hourly counts.-------- -------------- Imprimir los recuentos por hora.
     * These should have been set with a prior -------   Estos deberían haber sido fijado con un
     * call to analyzeHourlyData. ---- ---------------   llamada antes de analizar Datos por hora
     * --------------------------------------------------------------------------------------------------------- 0070
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
