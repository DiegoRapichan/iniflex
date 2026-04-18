import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import model.Funcionario;
import service.FuncionarioService;
import util.FuncionarioMock;

public class Principal {

    public static void main(String[] args) {

        FuncionarioService service = new FuncionarioService();

        List<Funcionario> funcionarios = new ArrayList<>(FuncionarioMock.getFuncionarios());

        // Removendo João
        funcionarios.removeIf(f -> f.getNome().equalsIgnoreCase("João"));

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

        // Imprimindo
        System.out.println("--- Funcionários ---");
        funcionarios.forEach(f ->
                System.out.println(f.getNome() + " | " +
                        f.getDataNascimento().format(dtf) + " | " +
                        nf.format(f.getSalario()) + " | " +
                        f.getFuncao())
        );

        // Aumento
        service.aplicarAumento(funcionarios, new BigDecimal("0.10"));

        // Agrupados
        System.out.println("\n--- Agrupados ---");
        service.agruparPorFuncao(funcionarios)
                .forEach((funcao, lista) -> {
                    System.out.println("Função: " + funcao);
                    lista.forEach(f -> System.out.println(" - " + f.getNome()));
                });

        // Aniversariantes
        System.out.println("\n--- Aniversariantes ---");
        service.aniversariantes(funcionarios)
                .forEach(f -> System.out.println(f.getNome()));

        // Mais velho
        Funcionario maisVelho = service.maisVelho(funcionarios);
        int idade = service.calcularIdade(maisVelho);
        System.out.println("\nMais velho: " + maisVelho.getNome() + " - " + idade + " anos");

        // Ordem alfabética
        System.out.println("\n--- Ordem Alfabética ---");
        service.ordenarPorNome(funcionarios)
                .forEach(f -> System.out.println(f.getNome()));

        // Total salários
        System.out.println("\nTotal salários: " +
                nf.format(service.calcularTotalSalarios(funcionarios)));

        // Salários mínimos
        System.out.println("\n--- Salários mínimos ---");
        funcionarios.forEach(f ->
                System.out.println(f.getNome() + ": " +
                        service.calcularSalariosMinimos(f.getSalario()))
        );
    }
}