import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import model.Funcionario;
import service.FuncionarioService;
import util.FuncionarioMock;

public class Principal {

    private static final FuncionarioService service = new FuncionarioService();
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

    public static void main(String[] args) {
        // 3.1 – Inserir todos os funcionários
        List<Funcionario> funcionarios = new ArrayList<>(FuncionarioMock.getFuncionarios());

        //Remover "João"
        funcionarios.removeIf(f -> f.getNome().equalsIgnoreCase("João"));

        // Imprimir todos os funcionários
        imprimirRelatorio(funcionarios, "LISTA DE FUNCIONARIOS");

        // Aumento de 10%
        service.aplicarAumento(funcionarios, new BigDecimal("0.10"));
        System.out.println("\n>>> Aumento de 10% aplicado com sucesso! <<<");

        // Agrupar e imprimir por função
        imprimirAgrupados(service.agruparPorFuncao(funcionarios));

        //Aniversariantes dos meses 10 e 12
        System.out.println("\n--- Aniversariantes (Outubro e Dezembro) ---");
        service.aniversariantes(funcionarios).forEach(f -> 
            System.out.printf("Aniversariante: %-15s | Data: %s%n", f.getNome(), f.getDataNascimento().format(dtf)));

        //Maior idade
        Funcionario maisVelho = service.maisVelho(funcionarios);
        System.out.printf("\nFuncionario mais velho: %s | Idade: %d anos%n", 
            maisVelho.getNome(), service.calcularIdade(maisVelho));

        // Ordem alfabética
        imprimirRelatorio(service.ordenarPorNome(funcionarios), "ORDEM ALFABETICA");

        //Total dos salários
        BigDecimal total = service.calcularTotalSalarios(funcionarios);
        System.out.printf("\nTOTAL DOS SALARIOS: %s%n", nf.format(total));

        // Quantos salários mínimos ganha cada um
        imprimirSalariosMinimos(funcionarios);
    }

    private static void imprimirRelatorio(List<Funcionario> lista, String titulo) {
        System.out.println("\n=== " + titulo + " ===");
        System.out.printf("%-20s | %-12s | %-15s | %-15s%n", "NOME", "NASCIMENTO", "SALARIO", "FUNCAO");
        System.out.println("-".repeat(70));
        lista.forEach(f -> System.out.printf("%-20s | %-12s | %-15s | %-15s%n", 
                f.getNome(), f.getDataNascimento().format(dtf), nf.format(f.getSalario()), f.getFuncao()));
    }

    private static void imprimirAgrupados(Map<String, List<Funcionario>> agrupados) {
        System.out.println("\n=== FUNCIONARIOS AGRUPADOS POR FUNCAO ===");
        agrupados.forEach((funcao, lista) -> {
            System.out.printf("%n[ %s ]%n", funcao.toUpperCase());
            lista.forEach(f -> System.out.printf("   * %s%n", f.getNome()));
        });
    }

    private static void imprimirSalariosMinimos(List<Funcionario> lista) {
        System.out.println("\n=== QTD DE SALARIOS MINIMOS (R$ 1.212,00) ===");
        System.out.printf("%-20s | %-10s%n", "NOME", "QTD MIN.");
        System.out.println("-".repeat(35));
        lista.forEach(f -> System.out.printf("%-20s | %s%n", 
                f.getNome(), service.formatarSalariosMinimos(f.getSalario())));
    }
}