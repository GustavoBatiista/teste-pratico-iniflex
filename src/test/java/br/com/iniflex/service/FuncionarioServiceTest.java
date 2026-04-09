package br.com.iniflex.service;

import br.com.iniflex.model.Funcionario;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FuncionarioServiceTest {

    @Test
    void removerPorNomeRemoveJoao() {
        List<Funcionario> lista = new ArrayList<>(FuncionarioService.criarFuncionariosIniciais());
        assertEquals(10, lista.size());
        FuncionarioService.removerPorNome(lista, "João");
        assertEquals(9, lista.size());
        assertTrue(lista.stream().noneMatch(f -> "João".equals(f.getNome())));
    }

    @Test
    void reajusteDezPorcentoAtualizaSalarios() {
        Funcionario f = new Funcionario("Teste", LocalDate.of(1990, 1, 1), new BigDecimal("1000.00"), "X");
        List<Funcionario> lista = new ArrayList<>(List.of(f));
        FuncionarioService.aplicarReajusteDezPorcento(lista);
        assertEquals(new BigDecimal("1100.00"), f.getSalario());
    }

    @Test
    void agruparPorFuncao() {
        List<Funcionario> lista = new ArrayList<>(FuncionarioService.criarFuncionariosIniciais());
        FuncionarioService.removerPorNome(lista, "João");
        Map<String, List<Funcionario>> map = FuncionarioService.agruparPorFuncao(lista);
        assertTrue(map.containsKey("Operador"));
        assertEquals(2, map.get("Operador").size());
    }

    @Test
    void funcionarioMaisVelhoECaio() {
        List<Funcionario> lista = new ArrayList<>(FuncionarioService.criarFuncionariosIniciais());
        FuncionarioService.removerPorNome(lista, "João");
        Optional<Funcionario> velho = FuncionarioService.funcionarioMaisVelho(lista);
        assertTrue(velho.isPresent());
        assertEquals("Caio", velho.get().getNome());
    }

    @Test
    void aniversariantesOutubroIncluiMariaEMiguel() {
        List<Funcionario> lista = new ArrayList<>(FuncionarioService.criarFuncionariosIniciais());
        FuncionarioService.removerPorNome(lista, "João");
        List<Funcionario> aniv = FuncionarioService.aniversariantesOutubroOuDezembro(lista);
        assertEquals(2, aniv.size());
        assertTrue(aniv.stream().anyMatch(f -> "Maria".equals(f.getNome())));
        assertTrue(aniv.stream().anyMatch(f -> "Miguel".equals(f.getNome())));
    }

    @Test
    void somaSalariosConsistente() {
        List<Funcionario> lista = new ArrayList<>();
        lista.add(new Funcionario("A", LocalDate.of(2000, 1, 1), new BigDecimal("100.00"), "F"));
        lista.add(new Funcionario("B", LocalDate.of(2000, 1, 1), new BigDecimal("200.50"), "F"));
        BigDecimal soma = FuncionarioService.somaSalarios(lista);
        assertEquals(new BigDecimal("300.50"), soma);
    }

    @Test
    void quantidadeSalariosMinimos() {
        BigDecimal sm = FuncionarioService.SALARIO_MINIMO_REFERENCIA;
        BigDecimal dois = FuncionarioService.quantidadeSalariosMinimos(new BigDecimal("2424.00"), sm);
        assertEquals(new BigDecimal("2.00"), dois);
    }

    @Test
    void ordenacaoAlfabeticaIgnoraJoaoRemovido() {
        List<Funcionario> lista = new ArrayList<>(FuncionarioService.criarFuncionariosIniciais());
        FuncionarioService.removerPorNome(lista, "João");
        List<Funcionario> ord = FuncionarioService.ordenarAlfabeticamentePorNome(lista);
        assertFalse(ord.isEmpty());
        assertEquals("Alice", ord.get(0).getNome());
    }
}
