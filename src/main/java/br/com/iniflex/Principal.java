package br.com.iniflex;

import br.com.iniflex.model.Funcionario;
import br.com.iniflex.service.FuncionarioService;
import br.com.iniflex.util.Formatadores;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public final class Principal {

    private static final LocalDate DATA_REFERENCIA_IDADE = LocalDate.now();

    private Principal() {
    }

    public static void main(String[] args) {
        
        List<Funcionario> funcionarios = new ArrayList<>(FuncionarioService.criarFuncionariosIniciais());

        
        FuncionarioService.removerPorNome(funcionarios, "João");


        System.out.println("=== 3.3 — Todos os funcionários (após remoção do João, antes do reajuste) ===");
        imprimirTodos(funcionarios);

        FuncionarioService.aplicarReajusteDezPorcento(funcionarios);

 
        Map<String, List<Funcionario>> porFuncao = FuncionarioService.agruparPorFuncao(funcionarios);
        System.out.println();
        System.out.println("=== 3.6 — Funcionários agrupados por função (após reajuste) ===");
        imprimirAgrupados(porFuncao);


        List<Funcionario> aniv = FuncionarioService.aniversariantesOutubroOuDezembro(funcionarios);
        System.out.println();
        System.out.println("=== 3.8 — Aniversariantes dos meses 10 (outubro) e 12 (dezembro) ===");
        if (aniv.isEmpty()) {
            System.out.println("(nenhum)");
        } else {
            imprimirTodos(aniv);
        }


        Optional<Funcionario> maisVelho = FuncionarioService.funcionarioMaisVelho(funcionarios);
        System.out.println();
        System.out.println("=== 3.9 — Funcionário mais velho ===");
        maisVelho.ifPresentOrElse(
                f -> {
                    int idade = FuncionarioService.calcularIdade(f.getDataNascimento(), DATA_REFERENCIA_IDADE);
                    System.out.println("Nome: " + f.getNome());
                    System.out.println("Idade: " + idade + " anos");
                },
                () -> System.out.println("(lista vazia)"));

        List<Funcionario> ordenados = FuncionarioService.ordenarAlfabeticamentePorNome(funcionarios);
        System.out.println();
        System.out.println("=== 3.10 — Funcionários em ordem alfabética (nome) ===");
        imprimirTodos(ordenados);

        BigDecimal total = FuncionarioService.somaSalarios(funcionarios);
        System.out.println();
        System.out.println("=== 3.11 — Soma dos salários ===");
        System.out.println(Formatadores.formatarMoeda(total));


        System.out.println();
        System.out.println("=== 3.12 — Salários mínimos por funcionário (SM = R$ 1.212,00) ===");
        for (Funcionario f : ordenados) {
            BigDecimal qtdSm = FuncionarioService.quantidadeSalariosMinimos(
                    f.getSalario(), FuncionarioService.SALARIO_MINIMO_REFERENCIA);
            System.out.println(f.getNome() + ": " + qtdSm + " SM");
        }
    }

    private static void imprimirTodos(List<Funcionario> lista) {
        for (Funcionario f : lista) {
            System.out.println(formatarLinhaFuncionario(f));
        }
    }

    private static String formatarLinhaFuncionario(Funcionario f) {
        return String.join(" | ",
                "Nome: " + f.getNome(),
                "Nascimento: " + Formatadores.formatarData(f.getDataNascimento()),
                "Salário: " + Formatadores.formatarMoeda(f.getSalario()),
                "Função: " + f.getFuncao());
    }

    private static void imprimirAgrupados(Map<String, List<Funcionario>> porFuncao) {
        for (Map.Entry<String, List<Funcionario>> e : porFuncao.entrySet()) {
            System.out.println();
            System.out.println("Função: " + e.getKey());
            for (Funcionario f : e.getValue()) {
                System.out.println("  " + formatarLinhaFuncionario(f));
            }
        }
    }
}
