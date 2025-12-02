package br.com.rcl.screenmatch.exercicio;

@FunctionalInterface
interface Primo{
    boolean verificaPrimo(int numero);
}

public class ExercicioPrimo {
    public static void main(String[] args) {
        Primo primo = n -> {
            if(n <= 1) return false;
            for(int i = 2; i < Math.sqrt(n); i++){
                if(n % i == 0) return false;
            }
            return true;
        };
        System.out.println(primo.verificaPrimo(11));
        System.out.println(primo.verificaPrimo(111));
    }
}
