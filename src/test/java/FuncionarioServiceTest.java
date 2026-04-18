

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import model.Funcionario;
import service.FuncionarioService;

public class FuncionarioServiceTest {
    private final FuncionarioService service = new FuncionarioService();

    @Test
    void deveAplicarAumento(){
        Funcionario f = new Funcionario("Teste", null, new BigDecimal("1000.00"), "Dev");
        List<Funcionario> lista = Arrays.asList(f);

        service.aplicarAumento(lista, new BigDecimal("0.10"));

        assertEquals(0, new BigDecimal("1100.00").compareTo(f.getSalario())); 
    }
   

    @Test 
    void deveCalcularTotalSalarios(){
        List<Funcionario> lista = Arrays.asList(
            new Funcionario("Teste1", null, new BigDecimal("2000.00"), "Dev"),
            new Funcionario("Teste2", null, new BigDecimal("3000.00"), "Dev")
        );

        BigDecimal total = service.calcularTotalSalarios(lista);
        assertEquals(0, new BigDecimal("5000.00").compareTo(total));

    }

    @Test
    void deveAgruparPorFuncao(){
        List<Funcionario> lista = Arrays.asList(
            new Funcionario("Teste1", null, new BigDecimal("2000.00"), "Dev"),
            new Funcionario("Teste2", null, new BigDecimal("3000.00"), "Dev"),
            new Funcionario("Teste3", null, new BigDecimal("4000.00"), "Gerente")
        );

        Map<String, List<Funcionario>> agrupados = service.agruparPorFuncao(lista);
        assertEquals(2, agrupados.get("Dev").size());
        assertEquals(1, agrupados.get("Gerente").size());
    }
    
}
