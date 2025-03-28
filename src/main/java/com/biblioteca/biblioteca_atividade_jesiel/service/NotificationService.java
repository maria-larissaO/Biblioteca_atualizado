
package com.biblioteca.biblioteca_atividade_jesiel.service;

import com.biblioteca.biblioteca_atividade_jesiel.domain.emprestimo.Emprestimo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class NotificationService {

    public void notificarEmprestimoCriado(Emprestimo emprestimo) {
        System.out.println("📚 Notificação: Empréstimo criado para o usuário " + emprestimo.getUsuario().getNome()
                + " com vencimento em " + emprestimo.getDataDevolucao());
    }

    public void notificarVencimentoProximo(Emprestimo emprestimo) {
        System.out.println("⚠️ Notificação: O empréstimo do livro " + emprestimo.getLivro().getTitulo()
                + " do usuário " + emprestimo.getUsuario().getNome()
                + " vence em breve (" + emprestimo.getDataDevolucao() + ")");
    }

    public void notificarVencimentoHoje(Emprestimo emprestimo) {
        System.out.println("⏰ Notificação: O empréstimo do livro \"" + emprestimo.getLivro().getTitulo()
                + "\" do usuário " + emprestimo.getUsuario().getNome()
                + " vence hoje (" + LocalDate.now() + ")");
    }
}
