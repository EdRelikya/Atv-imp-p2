import java.util.Scanner;

class Pessoa{
    String nome;
    int idade;
    double altura, peso;
}

public class QuestoesProva2 {
    public static Scanner sc = new Scanner(System.in);
    public static final int TAM = 3;

    public static void main(String[] args) {

        Pessoa[] pessoas = new Pessoa[TAM];
        int qtd = 0;

        qtd = cadastrarPessoa(pessoas, qtd);
        qtd = cadastrarPessoa(pessoas, qtd);
        qtd = cadastrarPessoa(pessoas, qtd);

        insertionSortPorNome(pessoas, qtd);
        imprimirPessoas(pessoas, qtd);



        int pos = maisVelhaImcMagreza(pessoas, qtd);

        if (pos != -1) {
            System.out.printf("A pessoa mais velha com magreza está na posição %d: ", pos + 1);
            imprimirPessoaCompacto(pessoas[pos]);
        } else {
            System.out.println("Nenhuma pessoa com IMC de magreza encontrada!");
        }
        
    }

    public static int buscaSequencial(Pessoa[] v, int qtd, String nome){
        for(int i = 0; i < qtd; i++){
            if (v[i].nome.equalsIgnoreCase(nome)) {
                return i; 
            }
        }
        return -1;
    }

    public static int cadastrarPessoa(Pessoa[] v, int qtd){
        if (qtd == TAM) {
            System.out.println("Vetor Cheio!");
            return qtd;
        }

        Pessoa p = new Pessoa();
        System.out.println("Digite nome: ");
        p.nome = sc.nextLine();

        while (buscaSequencial(v, qtd, p.nome) != -1) {
            System.out.println("Já cadastrado, Digite outro nome!");
            p.nome = sc.nextLine();
        }

        System.out.println("Digite idade: ");
        p.idade = sc.nextInt();
        System.out.println("Digite peso: ");
        p.peso = sc.nextDouble();
        System.out.println("Digite altura: ");
        p.altura = sc.nextDouble();
        sc.nextLine();

        v[qtd] = p;
        return qtd + 1;
    }


    public static void imprimirPessoas(Pessoa[] v, int qtd) {
        for (int i = 0; i < qtd; i++) {
            System.out.printf("[%d] ", i + 1);
            imprimirPessoaCompacto(v[i]);
        }
    }

    public static void imprimirPessoaCompacto(Pessoa p) {
        System.out.printf("[%10s, %3d anos, %.2f kg, %.2f m, imc = %.2f]\n",
                p.nome, p.idade, p.peso, p.altura, imc(p));
    }

    public static double imc(Pessoa p){
        return p.peso / (p.altura * p.altura);
    }


    public static int maisVelhaImcMagreza(Pessoa[] v, int qtd) {
        int iMaior = -1;
        for (int i = 0; i < qtd; i++) {
            if (imc(v[i]) < 18.5) {
                if (iMaior == -1 || v[i].idade > v[iMaior].idade) {
                    iMaior = i;
                }
            }
        }
        return iMaior;
    }


    public static void insertionSortPorNome(Pessoa[] v, int qtd) {
        for (int i = 1; i < qtd; i++) {
            Pessoa chave = v[i];
            int j = i - 1;
            while (j >= 0 && v[j].nome.compareToIgnoreCase(chave.nome) > 0) {
                v[j + 1] = v[j];
                j--;
            }
            v[j + 1] = chave;
        }
    }
    
}
