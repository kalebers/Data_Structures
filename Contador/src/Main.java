import java.io.File;

public class Main {

    public static void main(String[] args){

        Contador contador = new Contador();
        File diretorio = new File("C:\\Users\\andre\\Desktop\\uni\\problemas estruturados\\Arquivos");
        contador.contar_palavra(diretorio);


    }
}
