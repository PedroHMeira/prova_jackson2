import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    static ArrayList<Robo> robos = new ArrayList<>();
    static Scanner entrada = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;

        do {
            System.out.println("+-------------------------------+");
            System.out.printf("| %-29s |%n", "OPÇÕES");
            System.out.println("+-------------------------------+");
            System.out.printf("| %-29s |%n", "1- CADASTRAR ROBÔ");
            System.out.println("+-------------------------------+");
            System.out.printf("| %-29s |%n", "2- CONSULTAR TODOS OS ROBÔS");
            System.out.println("+-------------------------------+");
            System.out.printf("| %-29s |%n", "3- CONSULTAR ROBÔ POR CÓDIGO");
            System.out.println("+-------------------------------+");
            System.out.printf("| %-29s |%n", "4- COMBATE");
            System.out.println("+-------------------------------+");
            System.out.printf("| %-29s |%n", "0- SAIR");
            System.out.println("+-------------------------------+");
            System.out.println("Digite a opção que deseja: ");
            opcao = entrada.nextInt();

            switch (opcao) {
                case 1: {
                    System.out.println("Digite o código do seu robô: ");
                    int codigo = entrada.nextInt();
                    entrada.nextLine();
                    if (codigo <= 0) {
                        System.out.println("Código inválido. Deve ser maior que zero.");
                        break;
                    }

                    boolean temCodigo = false;
                    for (Robo r : robos) {
                        if (r.codigo == codigo) {
                            temCodigo = true;
                            break;
                        }
                    }
                    if (temCodigo) {
                        System.out.println("Código já existe, digite novamente!");
                        break;
                    }

                    System.out.println("Digite o nome do seu robô: ");
                    String nome = entrada.nextLine().trim();
                    if (nome.length() == 0) {
                        System.out.println("O campo nome não pode estar vazio.");
                        break;
                    }

                    System.out.println("Digite o ataque do seu robô: ");
                    int ataque = entrada.nextInt();
                    if (ataque < 10 || ataque > 30) {
                        System.out.println("O ataque deve estar entre 10 e 30.");
                        break;
                    }

                    System.out.println("Digite a defesa do seu robô: ");
                    int defesa = entrada.nextInt();
                    if (defesa < 0 || defesa > 20) {
                        System.out.println("A defesa deve estar entre 0 e 20.");
                        break;
                    }

                    Robo p = new Robo(codigo, nome, ataque, defesa);
                    robos.add(p);
                    System.out.println("Robô cadastrado com sucesso!");
                    break;
                }

                case 2: {
                    if (robos.isEmpty()) {
                        System.out.println("Nenhum robô cadastrado.");
                        break;
                    }
                    for (Robo r : robos) {
                        r.consultar();
                    }
                    break;
                }

                case 3: {
                    System.out.println("Digite o código do robô que quer encontrar: ");
                    int codigoBusca = entrada.nextInt();

                    Robo encontrado = null;
                    for (Robo r : robos) {
                        if (r.codigo == codigoBusca) {
                            encontrado = r;
                            break;
                        }
                    }

                    if (encontrado == null) {
                        System.out.println("Nenhum robô encontrado com esse código.");
                    } else {
                        encontrado.consultar();
                    }
                    break;
                }

                case 4: {
                    System.out.println("Digite o código do primeiro robô: ");
                    int codPrimeiro = entrada.nextInt();
                    System.out.println("Digite o código do segundo robô: ");
                    int codSegundo = entrada.nextInt();

                    if (codPrimeiro == codSegundo) {
                        System.out.println("Os robôs devem ser diferentes.");
                        break;
                    }

                    Robo primeiro = null;
                    Robo segundo = null;
                    for (Robo r : robos) {
                        if (r.codigo == codPrimeiro) {
                            primeiro = r;
                        }
                        if (r.codigo == codSegundo) {
                            segundo = r;
                        }
                    }

                    if (primeiro == null || segundo == null) {
                        System.out.println("Robô não encontrado.");
                        break;
                    }

                    if (primeiro.energia_atual < 30 || segundo.energia_atual < 30) {
                        System.out.println("Energia insuficiente para combater (mínimo 30).");
                        break;
                    }

                    Robo robo1 = null;
                    Robo robo2 = null;

                    if (primeiro.pontos < segundo.pontos) {
                       robo1 = primeiro;
                       robo2 = segundo;

                    } else if (primeiro.pontos > segundo.pontos) {
                        robo1 = segundo;
                        robo2 = primeiro;
                    } else if (primeiro.codigo < segundo.codigo) {
                        robo1 = primeiro;
                        robo2 = segundo;
                    } else if (primeiro.codigo > segundo.codigo) {
                        robo1 = segundo;
                        robo2 = primeiro;
                    }

                    int DanoRobo1;
                    int DanoRobo2;

                    for (int i = 1; i <= 5; i++){
                        DanoRobo1 = robo1.ataque - robo2.defesa;
                        if (DanoRobo1 < 5) {
                            DanoRobo1 = 5;
                        }
                        if (i % 2 == 0) {
                            DanoRobo1 = (DanoRobo1 + 5);
                        }
                        robo2.energia_atual = (robo2.energia_atual - DanoRobo1);
                        if (robo2.energia_atual < 0) {
                            robo2.energia_atual = 0;
                        }
                        System.out.printf("Robô %s atacou, ele deu %d de dano, o robô %s ficou com %d de energia%n", robo1.nome, DanoRobo1, robo2.nome, robo2.energia_atual);

                        if (robo2.energia_atual == 0) {
                            System.out.printf("A partida acabou pois a energia do robô: %s chegou a 0%n", robo2.nome);
                            break;
                        }

                        DanoRobo2 = robo2.ataque - robo1.defesa;
                        if (DanoRobo2 < 5) {
                            DanoRobo2 = 5;
                        }
                        if (i % 2 == 0) {
                            DanoRobo2 = (DanoRobo2 + 5);
                        }
                        robo1.energia_atual = (robo1.energia_atual - DanoRobo2);
                        if (robo1.energia_atual < 0) {
                            robo1.energia_atual = 0;
                        }
                        System.out.printf("Robô %s atacou, ele deu %d de dano, o robô %s ficou com %d de energia%n", robo2.nome, DanoRobo2, robo1.nome, robo1.energia_atual);

                        if (robo1.energia_atual == 0) {
                            System.out.printf("A partida acabou pois a energia do robô: %s chegou a 0%n", robo1.nome);
                            break;
                        }
                    }

                    if (robo1.energia_atual > 0 && robo2.energia_atual > 0) {
                        System.out.println("Os dois robôs resistiram a batalha.");
                    }

                    if (robo1.energia_atual > robo2.energia_atual) {
                        System.out.printf("%s ganhou a batalha.%n", robo1.nome);
                        robo1.pontos = (robo1.pontos + 3);
                        robo1.vitorias = (robo1.vitorias + 1);
                        robo2.derrotas = (robo2.derrotas + 1);
                    } else if (robo2.energia_atual > robo1.energia_atual) {
                        System.out.printf("%s ganhou a batalha.%n", robo2.nome);
                        robo2.pontos = (robo2.pontos + 3);
                        robo2.vitorias = (robo2.vitorias + 1);
                        robo1.derrotas = (robo1.derrotas + 1);
                    } else if (robo1.energia_atual == robo2.energia_atual) {
                        System.out.println("Houve um empate");
                        robo1.pontos = (robo1.pontos + 1);
                        robo2.pontos = (robo2.pontos + 1);
                        robo1.empates = (robo1.empates + 1);
                        robo2.empates = (robo2.empates + 1);
                    }
                    break;
                }

                case 0:
                    System.out.println("Encerrando o programa...");
                    break;

                default:
                    System.out.println("Opção inválida.");
                    break;
            }

        } while (opcao != 0);
    }
}