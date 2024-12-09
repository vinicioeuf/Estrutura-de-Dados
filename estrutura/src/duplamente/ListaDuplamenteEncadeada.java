import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
public class ListaDuplamenteEncadeada {
    private No inicio;
    private No fim;
    private int totalDeElementos;

    private boolean posicaoValida(int posicao) {
        return posicao >= 0 && posicao < this.totalDeElementos;
    }

    private No pegaNo (int posicao) {
        if(!this.posicaoValida(posicao)) {
            throw new IllegalArgumentException("Posição Inálida!");
        }
        No atual = this.inicio;
        for(int i = 0; i < posicao; i++) {
            atual = atual.getProximo();
        }
        return atual;
    }

    public void adicionaNoInicio(Object elemento) {
        if(this.totalDeElementos == 0) {
            No novo = new No(elemento);
            this.inicio = novo;
            this.fim = novo;
        }
        else {
            No novo = new No(elemento, this.inicio);
            this.inicio.setAnterior(novo);
            this.inicio = novo;
        }
        this.totalDeElementos++;
    }

    public void adicionaNoFinal(Object elemento) {
        if(this.totalDeElementos == 0) {
            this.adicionaNoInicio(elemento);
        }
        else {
            No novo = new No(elemento);
            this.fim.setProximo(novo);
            novo.setAnterior(this.fim);
            this.fim = novo;
            this.totalDeElementos++;
        }
    }

    public void adiciona(int posicao, Object elemento) {
        if(this.posicaoValida(posicao)) {
            if(posicao == 0) {
                this.adicionaNoInicio(elemento);
            }else {
                No anterior = this.pegaNo(posicao-1);
                No proximo = anterior.getProximo();
                No novo = new No(elemento, proximo);
                anterior.setProximo(novo);
                proximo.setAnterior(novo);
                novo.setAnterior(anterior);

                this.totalDeElementos++;

            }
        }else if(posicao == this.totalDeElementos){
            this.adicionaNoFinal(elemento);
        }else{
            System.out.println("Posição inválida!");
        }
    }

    public void removeDoInicio() {
        if(this.totalDeElementos > 1) {
            this.inicio = this.inicio.getProximo();
            this.inicio.setAnterior(null);
        }else {
            this.inicio = null;
            this.fim = null;
        }
        this.totalDeElementos--;
    }

    public void removeDoFinal() {
        if(this.totalDeElementos > 1) {
            No NovoFim = this.fim.getAnterior();
            NovoFim.setProximo(null);
            this.fim = this.fim.getAnterior();
            this.totalDeElementos--;
        }
        else {
            this.removeDoInicio();
        }
    }

    public void remove(int posicao) {
        if(this.posicaoValida(posicao)) {
            if(posicao == 0) {
                this.removeDoInicio();
            }else if(posicao == this.totalDeElementos-1) {
                this.removeDoFinal();
            }else {
                No no = this.pegaNo(posicao);
                No anterior = no.getAnterior();
                No proximo = no.getProximo();
                anterior.setProximo(proximo);
                proximo.setAnterior(anterior);
                this.totalDeElementos--;
            }
        }else{
            System.out.println("Posição inválida!");
        }
    }

    public int encontraImpar() {
        No atual = this.inicio;
        int soma = 0;
        for(int i = 0; i < this.totalDeElementos; i++) {
            if((int) atual.getElemento() % 2 != 0) {
                soma += (int) atual.getElemento();

            }
            atual = atual.getProximo();
        }

        return soma;
    }

    public void removePreenchida(int a, int b) {
        No atual = this.inicio;
        for(int i = 0; i < this.totalDeElementos; i++) {
            if((int) atual.getElemento() >= a && (int) atual.getElemento() <= b) {
                No anterior = atual.getAnterior();
                No proximo = atual.getProximo();

                anterior.setAnterior(anterior);
                proximo.setProximo(proximo);
                this.totalDeElementos--;

            }
            atual = atual.getProximo();
        }
    }

    public boolean recebeVetor(int[] listaEstatica) {
        No atual = this.inicio;

        for(int i = 0; i < this.totalDeElementos; i++) {
            if((int) atual.getElemento() != listaEstatica[i]) {
                return false;
            }
            atual = atual.getProximo();
        }
        return true;

    }

//1. Inserção em Ordem Crescente
//Escreva um método para uma lista duplamente encadeada que insere um novo
//elemento mantendo a ordem crescente dos elementos já presentes na lista.

    public boolean adicionaOrdenado(Object elemento) {
        if(this.totalDeElementos == 0){
            this.adicionaNoFinal(elemento);
        }else if((int) elemento <= (int) this.inicio.getElemento()){
            this.adicionaNoInicio(elemento);
        }else if((int) elemento >= (int) this.fim.getElemento()){
            this.adicionaNoFinal(elemento);
        }else{
            No atual = this.inicio.getProximo();
            for(int i = 1; i < this.totalDeElementos-1; i++) {
                if((int) elemento >= (int) atual.getElemento() && (int) elemento <= (int) atual.getProximo().getElemento()){
                    this.adiciona(i+1, elemento);
                    return true;
                }
                atual = atual.getProximo();
            }
        }
        return false;




    }

    /*
    2-
    Implemente um método que inverte uma lista duplamente encadeada, ou seja, o primeiro elemento deve se tornar o
    último, o segundo deve ser o penúltimo, e assim por diante.
    1 2 3 4
    4 3 2 1
    */

    public void inverterLista() {
        No atual = this.inicio;
        No fim = this.fim;
        No anterior = atual.getAnterior();
        No proximo = atual.getProximo();
        for(int i = 0; i < this.totalDeElementos; i++) {
            fim.setAnterior(atual.getProximo());
            fim.setProximo(atual.getAnterior());
        }



    }
//Versão 2 da segunda
    public void inverteLista2(){

        No atual = this.fim.getAnterior();

        for(int i = totalDeElementos-2; i >0;i--){


            atual.setProximo(atual.getAnterior());
            atual.setAnterior(atual.getProximo());

            atual = atual.getAnterior();
        }

        No primeiro = this.inicio;
        No ultimo = this.fim;

        this.inicio = ultimo;
        this.fim = primeiro;

        this.inicio.setProximo(this.inicio.getAnterior());
        this.fim.setAnterior(this.fim.getProximo());
        this.inicio.setAnterior(null);
        this.fim.setProximo(null);


    }

    // 3- Remove duplicado
    //Crie um método que percorra uma lista duplamente encadeada e remova todos os elementos duplicados, mantendo apenas a primeira ocorrência de cada valor.
    public void removeDuplicado()
    {
        if(this.totalDeElementos == 0){
            this.removeDoInicio();
        }
        No atual = this.inicio;
        int duplicatas = 1;
        int indice = 0;
        while(atual != null)
        {
            //Temos elementos duplicados nos indices [0, 2, 3, 4, 5]
            if(atual.getProximo() == null) break;

            if(atual.getElemento() == atual.getProximo().getElemento())
            {
                duplicatas++;
                indice++;

            }
            System.out.println("Indices: " + indice);


            atual = atual.getProximo();
        }
        System.out.println("Duplicados: " + duplicatas);
    }

    //Versão GPT
    public void removeDuplicatas() {

        if (this.inicio == null) {
            return; // Lista vazia, nada para remover
        }

        No atual = this.inicio;

        while (atual != null) {
            No comparador = atual.getProximo();
            No anterior = atual;

            while (comparador != null) {

                if (atual.getElemento().equals(comparador.getElemento())) {
                    // Elemento duplicado encontrado; remove o nó comparador
                    anterior.setProximo(comparador.getProximo());
                    if (comparador.getProximo() != null) {
                        comparador.getProximo().setAnterior(anterior);
                    }
                    if (comparador == this.fim) {
                        this.fim = anterior; // Atualiza o fim da lista, se necessário
                    }
                    this.totalDeElementos--;
                }else {
                    anterior = comparador;
                }
                comparador = comparador.getProximo();
            }
            atual = atual.getProximo();
        }
    }

    //=-=-=-=-==-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-

    //4- Escreva um método que verifica se uma lista duplamente encadeada representa um palíndromo, ou seja, se os elementos da lista lidos de
    // frente para trás são iguais aos lidos de trás para frente.

    public boolean palindromo(){
        No inicio = this.inicio;
        No fim = this.fim;
        for(int i = 0; i < this.totalDeElementos; i++)
        {
            if(inicio.getElemento() != fim.getElemento()){
                return false;
            }
            inicio = inicio.getProximo();
            fim = fim.getAnterior();
        }
        return true;
    }

    // 5-
    public void rotaciona(int qnt){

        No atual = this.inicio;
        for(int i =0; i < qnt; i++){

            adiciona(this.totalDeElementos - i, atual.getElemento());
            remove(0);
            atual = atual.getProximo();

        }
    }

// 6 -
    public ListaDuplamenteEncadeada intersecaoDeListas(ListaDuplamenteEncadeada lista1, ListaDuplamenteEncadeada lista2) {

        ListaDuplamenteEncadeada listaNova = new ListaDuplamenteEncadeada();
        No atual = lista1.getInicio();

        for (int i = 0; i < lista1.totalDeElementos; i++) {
            if (lista2.contem(atual.getElemento())) {

                listaNova.adicionaNoFinal(atual.getElemento());
            }

            atual = atual.getProximo();
        }

        listaNova.removeDuplicatas();
        return listaNova;
    }

    // 7 - Escreva um método que receba uma lista duplamente encadeada e retorne o késimo menor elemento da lista (não uma cópia, mas o próprio elemento da lista).
    //Sem biblioteca
    public No kesimoMenor(int kesimo){

        ListaDuplamenteEncadeada listaNova = new ListaDuplamenteEncadeada();
        int contador = 0;

        while(contador < this.totalDeElementos){

            No atual = this.inicio;
            No menor = null;

            for(int i = 0; i < this.totalDeElementos; i++){

                if(!listaNova.contem(atual.getElemento())){

                    if(menor == null || (int)atual.getElemento() < (int)menor.getElemento()){
                        menor = atual;

                    }

                }
                atual = atual.getProximo();
            }

            listaNova.adicionaNoFinal(menor.getElemento());


            contador++;
        }

        No atualNovo = listaNova.getInicio();
        for(int i = 0; i < kesimo - 1;i++){
            atualNovo = atualNovo.getProximo();
        }

        return atualNovo;

    }
    //COM BIBLIOTECA
    public int encontrarKesimoMenor(ListaDuplamenteEncadeada lista, int k) {
        ArrayList<Integer> elementos = new ArrayList<>();
        No atual = lista.getInicio();


        while (atual != null) {
            elementos.add((int)atual.getElemento());
            atual = atual.getProximo();
        }

        Collections.sort(elementos);
        return elementos.get(k - 1);
    }

    //8- Implemente um método que recebe duas listas duplamente encadeadas ordenadas e retorna uma nova lista
    // também ordenada, contendo todos os elementos das duas listas originais.

    public void mergeListas(ListaDuplamenteEncadeada lista2){

        No atual = lista2.getInicio();

        for(int i = 0; i <lista2.getTotalDeElementos(); i++){

            this.adiciona(i, atual.getElemento());

            atual = atual.getProximo();
        }

        System.out.println(this);

        this.ordenarCrescente();

    }


    //9- Escreva um método que divida uma lista duplamente encadeada em duas sublistas: uma contendo os elementos
    // menores que um valor dado e outra contendo os elementos maiores ou iguais a esse valor.
    public void divideSubListasMaiorMenor(int num){

        ListaDuplamenteEncadeada listaMenores = new ListaDuplamenteEncadeada();
        ListaDuplamenteEncadeada listamaiores = new ListaDuplamenteEncadeada();

        No atual = this.inicio;

        for(int i = 0; i <this.totalDeElementos; i++){

            if((int)atual.getElemento() < num){
                listaMenores.adicionaNoFinal(atual.getElemento());
            }else{
                listamaiores.adicionaNoFinal(atual.getElemento());
            }

            atual = atual.getProximo();
        }

        System.out.println("Lista de menores:" + listaMenores);
        System.out.println("Lista de maiores:" + listamaiores);
    }


    //10- Implemente um método que busca um valor específico em uma lista duplamente encadeada e, caso encontrado, substitui-o por outro valor fornecido.

    public boolean buscaTroca(Object valorBuscado, Object valorFornecido){
        No atual = this.inicio;
        for(int i = 0; i < this.totalDeElementos; i++)
        {
            if(atual.getElemento().equals(valorBuscado))
            {
                atual.setElemento(valorFornecido);
                No anterior = atual.getAnterior();
                No proximo = atual.getProximo();
                return true;
            }
            atual = atual.getProximo();
        }
        return false;
    }

    //-----------------------------------------------------------------------------------------------------------------------

    //Treino pt 2



    //1- Inserção em Ordem Decrescente:
    //Escreva um método que insira um elemento em uma lista duplamente encadeada mantendo os elementos em ordem decrescente.

    public boolean adicionaDecrecente(Object elemento)
    {
        if(this.totalDeElementos == 0){
            this.adicionaNoFinal(elemento);
        }else if((int) elemento >= (int) this.inicio.getElemento()){
            this.adicionaNoInicio(elemento);
        }else if((int) elemento <= (int) this.fim.getElemento()){
            this.adicionaNoFinal(elemento);
        }else{
            No atual = this.inicio.getProximo();
            for(int i = 1; i < this.totalDeElementos-1; i++) {
                if((int) elemento <= (int) atual.getElemento() && (int) elemento >= (int) atual.getProximo().getElemento()){
                    this.adiciona(i+1, elemento);
                    return true;
                }
                atual = atual.getProximo();
            }
        }
        return false;
    }

    //2- Intercalação Alternada:
    // Questão 9: Divisão por valor médio
    //Dada uma lista duplamente encadeada, implemente uma função que divida a lista em duas listas separadas: uma contendo os nós com valores menores
    // que a média dos valores da lista original e outra com valores iguais ou maiores que a média.
    //

    public double pegaMedia()
    {
        ListaDuplamenteEncadeada listaMenores = new ListaDuplamenteEncadeada();
        ListaDuplamenteEncadeada listamaioresIguais = new ListaDuplamenteEncadeada();

        No atual = this.inicio;
        double media = 0;
        int cont = 0;
        for(int i = 0; i <this.totalDeElementos; i++)
        {
            media += (int)atual.getElemento();
            cont++;
            atual = atual.getProximo();
        }

        No atual2 = this.inicio;
        media /= cont;
        for(int i = 0; i <this.totalDeElementos; i++){
            if((int)atual2.getElemento() < media){
                System.out.println("if " + atual2.getElemento());
                listaMenores.adicionaNoFinal(atual2.getElemento());
            }else{
                System.out.println("else " + atual2.getElemento());
                listamaioresIguais.adicionaNoFinal(atual2.getElemento());
            }

            atual2 = atual2.getProximo();
        }

        System.out.println("Lista de menores:" + listaMenores);
        System.out.println("Lista de maiores:" + listamaioresIguais);
        return media;
    }

    //3- Tenho uma lista ["Amor", "Maior", "Menor"] quero remover uma lista especifica de cada uma das palavras

    public void removeCaracter(String caracter)
    {
        No atual = this.inicio;
        caracter = caracter.toLowerCase();

        for (int i = 0; i < this.totalDeElementos; i++)
        {
            String elementoAtual = atual.getElemento().toString();
            String elementoAtualNormalizado = elementoAtual.toLowerCase();

            if (elementoAtualNormalizado.contains(caracter))
            {
                elementoAtual = elementoAtual.replaceAll("(?i)" + caracter, "");
                atual.setElemento(elementoAtual);
            }
            atual = atual.getProximo();
        }
    }








    public void ordenarCrescente() {
        if (this.inicio == null || this.inicio.getProximo() == null) {
            return; // Lista vazia ou com apenas um elemento, já está ordenada
        }

        boolean trocou;
        do {
            trocou = false;
            No atual = this.inicio;

            while (atual.getProximo() != null) {
                No proximo = atual.getProximo();
                if ((int) atual.getElemento() > (int) proximo.getElemento()) {
                    // Troca os elementos
                    Object temp = atual.getElemento();
                    atual.setElemento(proximo.getElemento());
                    proximo.setElemento(temp);
                    trocou = true;
                }
                atual = atual.getProximo();
            }
        } while (trocou);
}
    

    public boolean contem(Object elemento) {
        No atual = this.inicio;
        for(int i=0; i<this.totalDeElementos;i++) {
            if(atual.getElemento().equals(elemento)) return true;
            atual = atual.getProximo();
        }
        return false;
    }
    public void concatenaLista(ListaDuplamenteEncadeada listaJunta) {

        if (this.inicio == null)
        {
            this.inicio = listaJunta.getInicio();
            this.totalDeElementos = listaJunta.totalDeElementos;
            return;
        }
        No atual = this.inicio;

        while (atual.getProximo() != null)
        {
            atual = atual.getProximo();
        }

        atual.setProximo(listaJunta.getInicio());
        this.totalDeElementos += listaJunta.totalDeElementos;
    }

    @Override
    public String toString() {
        if(this.totalDeElementos==0) {
            return "[]";
        }
        StringBuilder builder = new StringBuilder("[");
        No atual = this.inicio;
        for(int i=0; i<this.totalDeElementos-1; i++) {
            builder.append(atual.getElemento());
            builder.append(", ");
            atual = atual.getProximo();
        }
        builder.append(atual.getElemento());
        builder.append("]");

        System.out.println("Início: "+this.inicio.getElemento());
        System.out.println("Fim: "+this.fim.getElemento());
        return builder.toString();
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

    public int getTotalDeElementos() {
        return totalDeElementos;
    }


}










