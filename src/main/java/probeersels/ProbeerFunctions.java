package probeersels;

public class ProbeerFunctions {

    public static void main(String[] args) {

        double resultaatSqrt = FunctionService.sqrt(-9.0);
        System.out.println("Wortel  is : "+resultaatSqrt);

        double resultaatPow = FunctionService.pow(-8,-2);
        System.out.println("macht is  van  is : "+resultaatPow);

        double resultaatPI = FunctionService.pi();
        System.out.println("Het getal PI is : "+resultaatPI);

        double resultaatExp = FunctionService .exp(-0.2);
        System.out.println("Getal van Euler  is : "+resultaatExp);

        double resultaatEuler = FunctionService.euler();
        System.out.println("Het getal Euler is : "+resultaatEuler);


    }
}
