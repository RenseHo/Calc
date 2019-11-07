package net.houwing.service;
import java.lang.Math;

public class FunctionService {
    // Zie ook : https://www.slimleren.nl/onderwerpen/rekenen/12.248/Hoeken+berekenen+met+de+sinus%252C+cosinus+en+tangens

    public static double sin (double getal){                    // overstaande rechtshoekzijde / schuine zijde
        return Math.sin(getal);
    }
    public static double cos (double getal){                    // aanliggende  rechtshoekzijde / schuine zijde
        return Math.cos(getal);
    }
    public static double tan (double getal){                    // overstaande rechtshoekzijde / aanliggende zijde
        return Math.tan(getal);
    }
    public static double sqrt (double getal){                   // Worteltrekken...
        return Math.sqrt(getal);
    }
    public static double pow (int grondtal, int exponenet ){    // Machtverheffen...
        return Math.pow(grondtal,exponenet);
    }
    public static double exp (double getal){                    //Het getal van Euler.....
        return Math.exp(getal);
    }
    public static double pi (){                                 //Het getal PI.....
        return Math.PI;
    }

}
