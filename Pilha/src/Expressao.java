public class Expressao {

    public Expressao() {


    }

    public boolean verificar_expressao(String caracteres) {
        Pilha p = new Pilha(caracteres.length());
        for (int i = 0; i < caracteres.length(); i++) {
            if (caracteres.charAt(i) == '(' || caracteres.charAt(i) == '[' || caracteres.charAt(i) == '{') {
                p.empilha(caracteres.charAt(i));
            }

            else if (caracteres.charAt(i) == ')' || caracteres.charAt(i) == ']' || caracteres.charAt(i) == '}') {

                if (p.vazia()) {
                    return false;
                }

                else if (caracteres.charAt(i) == ')' && p.topo().equals('(')) {
                    p.desempilha();
                }

                else if (caracteres.charAt(i) == ']' && p.topo().equals('[')) {
                    p.desempilha();
                }

                else if (caracteres.charAt(i) == '}' && p.topo().equals('{')) {
                    p.desempilha();
                }
            }
        }
        return p.vazia();
    }
}



