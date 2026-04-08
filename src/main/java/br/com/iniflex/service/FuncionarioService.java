package br.com.iniflex.service;

import br.com.iniflex.model.Funcionario;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public final class FuncionarioService {

    public static final BigDecimal SALARIO_MINIMO_REFERENCIA = new BigDecimal("1212.00");

    private static final BigDecimal FATOR_REAJUSTE_10_PORCENTO = new BigDecimal("1.10");
    private static final int ESCALA_MONETARIA = 2;
    private static final RoundingMode ARREDONDAMENTO_PADRAO = RoundingMode.HALF_UP;

    private FuncionarioService() {
    }

    public static List<Funcionario> criarFuncionariosIniciais() {
        List<Funcionario> lista = new ArrayList<>();
        lista.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), bd("2009.44"), "Operador"));
        lista.add(new Funcionario("João", LocalDate.of(1990, 5, 12), bd("2284.38"), "Operador"));
        lista.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), bd("9836.14"), "Coordenador"));
        lista.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), bd("19119.88"), "Diretor"));
        lista.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), bd("2234.68"), "Recepcionista"));
        lista.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), bd("1582.72"), "Operador"));
        lista.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), bd("4071.84"), "Contador"));
        lista.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), bd("3017.45"), "Gerente"));
        lista.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), bd("1606.85"), "Eletricista"));
        lista.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), bd("2799.93"), "Gerente"));
        return lista;
    }

    private static BigDecimal bd(String v) {
        return new BigDecimal(v);
    }

    public static void removerPorNome(List<Funcionario> funcionarios, String nome) {
        funcionarios.removeIf(f -> nome.equals(f.getNome()));
    }

    public static void aplicarReajusteDezPorcento(List<Funcionario> funcionarios) {
        for (Funcionario f : funcionarios) {
            BigDecimal novo = f.getSalario()
                    .multiply(FATOR_REAJUSTE_10_PORCENTO)
                    .setScale(ESCALA_MONETARIA, ARREDONDAMENTO_PADRAO);
            f.setSalario(novo);
        }
    }

    public static Map<String, List<Funcionario>> agruparPorFuncao(List<Funcionario> funcionarios) {
        return funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao, LinkedHashMap::new, Collectors.toList()));
    }

    public static List<Funcionario> aniversariantesOutubroOuDezembro(List<Funcionario> funcionarios) {
        return funcionarios.stream()
                .filter(f -> {
                    Month m = f.getDataNascimento().getMonth();
                    return m == Month.OCTOBER || m == Month.DECEMBER;
                })
                .toList();
    }

    public static Optional<Funcionario> funcionarioMaisVelho(List<Funcionario> funcionarios) {
        return funcionarios.stream()
                .min(Comparator.comparing(Funcionario::getDataNascimento));
    }

    public static int calcularIdade(LocalDate dataNascimento, LocalDate dataReferencia) {
        return Period.between(dataNascimento, dataReferencia).getYears();
    }

    public static List<Funcionario> ordenarAlfabeticamentePorNome(List<Funcionario> funcionarios) {
        return funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome, String.CASE_INSENSITIVE_ORDER))
                .toList();
    }

    public static BigDecimal somaSalarios(List<Funcionario> funcionarios) {
        return funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(ESCALA_MONETARIA, ARREDONDAMENTO_PADRAO);
    }

    public static BigDecimal quantidadeSalariosMinimos(BigDecimal salario, BigDecimal salarioMinimo) {
        return salario.divide(salarioMinimo, 2, ARREDONDAMENTO_PADRAO);
    }
}
