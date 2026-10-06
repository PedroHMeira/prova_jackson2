public class Robo {
    public int codigo;
    public String nome;
    public int ataque = 0;
    public int defesa = 0;
    public int energia_atual = 100;
    public int vitorias = 0;
    public int derrotas = 0;
    public int empates = 0;
    public int pontos = 0;

    public Robo(int codigo, String nome, int ataque, int defesa) {
        this.codigo = codigo;
        this.nome = nome;
        this.ataque = ataque;
        this.defesa = defesa;
        this.energia_atual = 100;
        this.vitorias = 0;
        this.derrotas = 0;
        this.empates = 0;
        this.pontos = 0;

    }


    public String situacaoRobo() {
        if(this.energia_atual >= 30) {
            return "DISPONIVEL";

        } else {
            return "EM RECUPERAÇÃO";
        }
    }

    public void consultar() {
        System.out.println("+-----------------+-----------------+");
        System.out.printf("| %-15s | %15s |%n", "DADOS", "VALORES");
        System.out.println("+-----------------+-----------------+");
        System.out.printf("| %-15s | %15s |%n", "Código", this.codigo);
        System.out.println("+-----------------+-----------------+");
        System.out.printf("| %-15s | %15s |%n", "Nome", this.nome);
        System.out.println("+-----------------+-----------------+");
        System.out.printf("| %-15s | %15d |%n", "Ataque", this.ataque);
        System.out.println("+-----------------+-----------------+");
        System.out.printf("| %-15s | %15d |%n", "Defesa", this.defesa);
        System.out.println("+-----------------+-----------------+");
        System.out.printf("| %-15s | %15d |%n", "Energia total", this.energia_atual);
        System.out.println("+-----------------+-----------------+");
        System.out.printf("| %-15s | %15s |%n", "Situação", this.situacaoRobo());
        System.out.println("+-----------------+-----------------+");
        System.out.printf("| %-15s | %15d |%n", "Vitorias", this.vitorias);
        System.out.println("+-----------------+-----------------+");
        System.out.printf("| %-15s | %15d |%n", "Derrotas", this.derrotas);
        System.out.println("+-----------------+-----------------+");
        System.out.printf("| %-15s | %15d |%n", "Empates", this.empates);
        System.out.println("+-----------------+-----------------+");
        System.out.printf("| %-15s | %15d |%n", "Pontos", this.pontos);
        System.out.println("+-----------------+-----------------+");
    }
}