package probeersels;

public class probeerLambda {

    public static void main(String[] args) {

        Functional functional1 = new Functional() {
            @Override
            public String execute(String input) {
                // Doe nog wat.
                return "Hello "+input ;
            }
        };
        Functional functional2 = (String input) ->  {
            // Doe nog wat.
            return "Hello "+ input;
        };
        Functional functional3 = input -> "Hello "+input;

        System.out.println("1:  "+ functional1.execute("Een"));
        System.out.println("2 : "+ functional2.execute("Twee"));
        System.out.println("3 : "+ functional3.execute("Drie"));
    }
}
