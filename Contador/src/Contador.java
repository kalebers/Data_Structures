import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Contador {
    private Arvore arvore = new Arvore();
    private String files;
    private String[] arquivos;


    public Contador(){
    }


    public void contar_palavra(File diretorio){
        criarArvore(diretorio);


        System.out.println("Entre com um termo a ser pesquisado: ");
        Scanner sc = new Scanner(System.in);
        String palavra = sc.nextLine();
        DATA word = new DATA(palavra, 0);


        total_ocorrencias(word);


        for (String txt : arquivos){
            ocorrencias_arquivo(diretorio + "\\" + txt, palavra, txt);
        }

    }

    private void ocorrencias_arquivo(String texto, String palavra, String txt){
        try {
            String proxima;
            int count = 0;
            FileReader text = new FileReader(texto);
            Scanner scanner = new Scanner(text);
            while (scanner.hasNext()){
                proxima = scanner.next();
                if (proxima.equals(palavra)){
                    count++;
                }
            }

            System.out.println("Arquivo " + '"' + txt + '"' + ':' + count);
        }

        catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }



    private void total_ocorrencias(DATA palavra){
        if (arvore.existe_elemento(palavra)){
            System.out.println("Total de ocorrências de " + '"' + palavra.getInfo() + '"' + ':' + arvore.recupera_elemento(palavra).getChave().getCount());
        }
        else {
            System.out.println("Total de ocorrências de " + '"' + palavra.getInfo() + '"' + ':' + "0");
        }
    }


    private void criarArvore (File diretorio){
        Scanner sc;
        FileReader reader;
        String temp;
        DATA palavra;

        leitorDeArquivos(diretorio);

        for (String txt : arquivos){
            try {
                reader = new FileReader(diretorio +"\\"+ txt);
                sc = new Scanner(reader);

                while (sc.hasNext()) {
                    temp = sc.next();
                    palavra = new DATA(temp, 1);

                    if (!arvore.existe_elemento(palavra)) {
                        arvore.insere_elemento(palavra);
                    } else {
                        DATA aux = new DATA(palavra.getInfo(), arvore.recupera_elemento(palavra).getChave().getCount());
                        arvore.remove_elemento(palavra);
                        aux.addCount();
                        arvore.insere_elemento(aux);
                    }
                }
                reader.close();
            }
            catch(FileNotFoundException e){
                    e.printStackTrace();
            }
            catch(IOException f){
                    f.printStackTrace();
            }
        }
    }


    private void leitorDeArquivos(File folder){
        for (File fileEntry : folder.listFiles()){
            if (fileEntry.isDirectory()){
                leitorDeArquivos(fileEntry);

            }
            else {
                files += fileEntry.getName()+ '/';
            }
        }
        files = files.replaceAll("null", "");
        arquivos = files.split("/");
    }
}

