package service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import model.Funcionario;

public class FuncionarioService {

    private BigDecimal valorSalarioMinimo = new BigDecimal("1212.00");

    public void setValorSalarioMinimo(BigDecimal valor) {
        this.valorSalarioMinimo = valor;
    }

    public String formatarSalariosMinimos(BigDecimal salarioFuncionario) {
        BigDecimal resultado = salarioFuncionario.divide(valorSalarioMinimo, 2, RoundingMode.HALF_UP);
     
        return String.format("%.2f", resultado).replace(".", ",");
    }

    public void aplicarAumento(List<Funcionario> funcionarios, BigDecimal percentual) {
        BigDecimal fator = BigDecimal.ONE.add(percentual);
        funcionarios.forEach(f -> {
            BigDecimal novoSalario = f.getSalario().multiply(fator).setScale(2, RoundingMode.HALF_UP);
            f.setSalario(novoSalario);
        });
    }

    public Map<String, List<Funcionario>> agruparPorFuncao(List<Funcionario> funcionarios) {
        return funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));
    }

    public List<Funcionario> aniversariantes(List<Funcionario> funcionarios) {
        return funcionarios.stream()
                .filter(f -> {
                    int mes = f.getDataNascimento().getMonthValue();
                    return mes == 10 || mes == 12;
                })
                .collect(Collectors.toList());
    }

    public Funcionario maisVelho(List<Funcionario> funcionarios) {
        return funcionarios.stream()
                .min(Comparator.comparing(Funcionario::getDataNascimento))
                .orElse(null);
    }

    public int calcularIdade(Funcionario f) {
        if (f == null) return 0;
        return Period.between(f.getDataNascimento(), LocalDate.now()).getYears();
    }

    public List<Funcionario> ordenarPorNome(List<Funcionario> funcionarios) {
        return funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .collect(Collectors.toList());
    }

    public BigDecimal calcularTotalSalarios(List<Funcionario> funcionarios) {
        return funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

  
    public String calcularSalariosMinimos(BigDecimal salario) {
        BigDecimal qtd = salario.divide(valorSalarioMinimo, 2, RoundingMode.HALF_UP);
        
        DecimalFormat df = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));
        return df.format(qtd);
    }
}