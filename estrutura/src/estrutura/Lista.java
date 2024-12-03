package estrutura;

public class Lista {
	private No inicio;
	private No fim;
	private int tamanho;
	private String[] elementos;
	
	public Lista() {
		super();
		this.inicio = null;
		this.fim = null;
		this.tamanho = 0;
	}
	
	public void adicionaNoInicio(Object elemento) {
		No novo = new No(elemento, this.inicio);
		this.inicio = novo;
		if(this.tamanho == 0) this.fim = novo;
		this.tamanho++;
	}
	public void adicionaNoFinal(Object elemento) {
		No novo = new No(elemento, null);
		this.fim.setProximo(novo);
		if(this.tamanho == 0) this.fim = novo;
		this.tamanho++;
	}
	public No getInicio() {
		return inicio;
	}
	public void setInicio(No inicio) {
		this.inicio = inicio;
	}
	public No getFim() {
		return fim;
	}
	public void setFim(No fim) {
		this.fim = fim;
	}
	public int tamanho() {
		return this.tamanho;
	}
	
	public void adiciona(String elemento) {
		if(this.tamanho<this.elementos.length) {
			this.elementos[this.tamanho] = elemento;
		}else {
			
			this.elementos[this.tamanho] = elemento;
		}
		this.tamanho++;
	}
	
	public boolean adicionaPos(String elemento, int pos) {
		if(!((pos >= 0) && (pos <= this.tamanho)) || this.tamanho() >= this.elementos.length) {
			return false;
		}
		for(int i = this.tamanho; i >= pos; i--) {
			this.elementos[i+1] = this.elementos[i];
		}
		this.elementos[pos] = elemento;
		this.tamanho++;
		return true;
	}

	@Override
	public String toString() {
		return "";
	}
}
