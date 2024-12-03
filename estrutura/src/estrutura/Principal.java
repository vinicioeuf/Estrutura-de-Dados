package estrutura;

public class Principal {
	public static void main(String[] args) {
		Lista l = new Lista();
		l.adicionaNoInicio("Salgueiro");
		System.out.println("Inicio da lista: " + l.getInicio().getElemento());
		
	}
}
