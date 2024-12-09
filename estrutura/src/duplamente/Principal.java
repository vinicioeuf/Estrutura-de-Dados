package duplamente;

public class Principal {
	public static void main(String[] args) {
        ListaDuplamenteEncadeada lista = new ListaDuplamenteEncadeada();
        ListaDuplamenteEncadeada subLista1 = new ListaDuplamenteEncadeada();




        lista.adiciona(0, "Amor");// Posição 0
        lista.adiciona(1, "Maior");// Posição 1
        lista.adiciona(2, "Tabaco");// Posição 2

        //Temos elementos duplicados nos indices [0, 2, 3, 4, 5]
        System.out.println(lista);
        System.out.println("---------------------------");

        lista.removeCaracter("a");
        System.out.println(lista);

        // ELEMENTO 4



    }
}
