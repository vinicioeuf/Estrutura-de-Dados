package q1;

public class ListaEncadeada {
	private No inicio, fim;
	private int tamanho;
	
	public ListaEncadeada() {
		this.inicio = null;
		this.fim = null;
		this.tamanho = 0;
	}
	
	public void adicionaNoInicio(Object elemento) {
		No novo = new No(elemento, this.inicio);
		this.inicio = novo;
		if(this.tamanho==0) {
			this.fim = novo;
		}
		this.tamanho++;
	}
	
	
	
	public void adiciona(int posicao, Object elemento) {
		if(posicao==0) {
			this.adicionaNoInicio(elemento);
		}else{
			No anterior = this.pegaNo(posicao-1);
			No novo = new No(elemento, anterior.getProximo());
			anterior.setProximo(novo);
			this.tamanho++;
		}
	}
	
	private boolean posicaoValida(int posicao) {
		return posicao >= 0 && posicao < this.tamanho;
	}
	
	private No pegaNo (int posicao) {
		if(!this.posicaoValida(posicao)) {
			throw new IllegalArgumentException("Posição Inválida!");
		}
		No atual = this.inicio;
		for(int i = 0; i < posicao; i++) {
			atual = atual.getProximo();
		}
		return atual;
	}
	

//	public Object achaKEsimo(int k) {
//		No atual = this.inicio;
//		for(int i = 0; i < this.tamanho-1; i++) {
//			atual = atual.getProximo();
//		}
//		
//		
//	}
	
	
	public Object pega(int posicao) {
		return this.pegaNo(posicao).getElemento();
	}
	
	public void removeInicio() {
		if(this.tamanho > 0) {
			this.inicio = this.inicio.getProximo();
			this.tamanho--;
			if(this.tamanho == 0) this.fim = null;
		}else {
			System.out.println("A lista não possui nenhum elemento.");
		}
		
	}
	
	public void remove(int pos) {
		if(((pos == 0) && (pos < this.tamanho))) {
			if(pos == 0) {
				this.removeInicio();
			}else if(pos == this.tamanho-1) {
				this.removeFinal();
			}else {
				No anterior = this.pegaNo(pos-1);
				No atual = anterior.getProximo();
				No proximo = atual.getProximo();
				anterior.setProximo(proximo);
				this.tamanho--;
			}
		}
	}
	
	public void removeFinal() {
		if(this.tamanho == 0) {
			System.out.println("A lista não possui nenhum elemento.");
		}else if(this.tamanho == 1){
			this.removeInicio();
		}else {
			No anterior = this.pegaNo(this.tamanho-2);
			this.fim = anterior;
			anterior.setProximo(null);
			this.tamanho--;
		}
	}
	
	public ListaEncadeada concatena(ListaEncadeada l1, ListaEncadeada l2) {
		l1.fim.setProximo(l2.inicio);
		l1.fim = l2.fim;
		l1.tamanho = l1.tamanho + l2.tamanho;
		return l1;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public String toString() {
		if(this.tamanho==0) {
			return "[]";
		}
		StringBuilder builder = new StringBuilder("[");
		No atual = this.inicio;
		for(int i=0; i<this.tamanho-1; i++) {
			builder.append(atual.getElemento());
			builder.append(", ");
			atual = atual.getProximo();
		}
		builder.append(atual.getElemento());
		builder.append("]");
		return builder.toString();
	}
	
	
}
