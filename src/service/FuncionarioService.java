package service;

import model.Funcionario;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;
import java.util.List;
import java.util.Map;       
import java.util.stream.Collectors;


public class FuncionarioService {
    
    public void aplicarAumento(List<Funcionario> funcionarios, BigDecimal percentual) {
        BigDecimal fator = BigDecimal.ONE.add(percentual);

        funcionarios.forEach(f ->
            f.setSalario(
                f.getSalario().multiply(fator).setScale(2, BigDecimal.ROUND_HALF_UP)
            )
        );
    }

    public Map<String, List<Funcionario>> agruparPorFuncao(List<Funcionario> funcionarios){
        return funcionarios.stream()
            .collect(Collectors.groupingBy(Funcionario::getFuncao));    
    }

    public List<Funcionario> aniversariantes(List<Funcionario> funcionarios){
        return funcionarios.stream()
            .filter(f -> {
                int mes = f.getDataNascimento().getMonthValue();
                return mes == 10 || mes == 12;
            }) 
            .toList();
    }

    public Funcionario maisVelho(List<Funcionario> funcionarios){
        return Collections.min(funcionarios,
           Comparator.comparing(Funcionario::getDataNascimento));
    }

    public int calcularIdade(Funcionario f){
        return Period.between(f.getDataNascimento(), LocalDate.now()).getYears();   
    }

    public List<Funcionario> ordenarPorNome(List<Funcionario> funcionarios){
        return funcionarios.stream()
            .sorted(Comparator.comparing(Funcionario::getNome))
            .toList();
    }

    public BigDecimal calcularTotalSalarios(List<Funcionario> funcionarios){
        return funcionarios.stream()
            .map(Funcionario::igetSalario)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal calcularSalariosMinimos(BigDecimal salario){
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        return salario.divide(salarioMinimo,2, RoundingMode.HALF_UP);
    }
    
}
