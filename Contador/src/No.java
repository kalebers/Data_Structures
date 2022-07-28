public class No {
    private int altura;
    private DATA chave;
    private No esquerda, setDireita;

    public No (DATA info){
        this(info, null, null);
    }

    public No(DATA info, No esquerda, No direita){
        this.chave = info;
        this.esquerda = esquerda;
        this.setDireita = direita;
        this.altura = 0;
    }

    public int getAltura(){
        return this.altura;
    }

    public DATA getChave(){
        return this.chave;
    }

    public No getEsquerda(){
        return this.esquerda;
    }

    public No getDireita(){
        return this.setDireita;
    }

    public void setEsquerda(No esquerda){
        this.esquerda = esquerda;
    }

    public void setDireita(No setDireita) {
        this.setDireita = setDireita;
    }

    public void setChave(DATA chave) {
        this.chave = chave;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

}
