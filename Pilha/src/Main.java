public class Main {


    public static void main(String[] args){
        /*
        Pilha p = new Pilha(10);

        p.empilha(1);
        p.empilha('a');
        p.empilha('B');
        while(p.vazia() == false){
            System.out.println(p.desempilha());
        }

        System.out.println(p.topo());
        System.out.println(p.cheia());
        System.out.println(p.vazia());
        */
        Expressao expressao = new Expressao();

        System.out.println(expressao.verificar_expressao("([55+1] * 2)"));
    }

}
