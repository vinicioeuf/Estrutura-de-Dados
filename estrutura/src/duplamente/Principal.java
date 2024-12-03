package duplamente;

public class Principal {
	public static void main(String[] args) {
		ListaDuplamenteEncadeada lista = new ListaDuplamenteEncadeada();	
		lista.adicionaNoInicio(2);
		lista.adicionaNoInicio(3);
		lista.adicionaNoInicio(5);
		lista.adicionaNoInicio(1);
		
		System.out.println(lista);
		System.out.println("---------------------------");
		int listaEstatica[] = new int[4];
		listaEstatica[0] = 1;
		listaEstatica[1] = 5;
		listaEstatica[2] = 3;
		listaEstatica[3] = 2;
		
		System.out.println(lista.recebeVetor(listaEstatica));
		
	}
}
