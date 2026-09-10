package br.com.EcoVolt;

import br.com.EcoVolt.conexao.ConnectionFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ExecutarScriptBanco {

    public static void main(String[] args) throws Exception {
        InputStream in = ExecutarScriptBanco.class.getClassLoader().getResourceAsStream("database.sql");
        if (in == null) {
            throw new IllegalStateException("Arquivo database.sql nao encontrado em resources.");
        }

        String conteudo = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));

        List<String> comandos = separarComandos(conteudo);
        System.out.println("Executando " + comandos.size() + " comandos SQL...");

        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement()) {
            for (String comando : comandos) {
                String sql = comando.trim();
                if (sql.isEmpty() || sql.startsWith("--")) {
                    continue;
                }
                try {
                    stmt.execute(sql);
                    System.out.println("OK: " + resumir(sql));
                } catch (Exception e) {
                    String msg = e.getMessage() == null ? "" : e.getMessage();
                    // Ignora drop de objeto inexistente
                    if (msg.contains("ORA-00942") || msg.contains("ORA-02289") || msg.contains("ORA-04043")) {
                        System.out.println("IGNORADO: " + resumir(sql) + " -> " + msg.split("\n")[0]);
                    } else {
                        System.out.println("ERRO: " + resumir(sql));
                        throw e;
                    }
                }
            }
        }

        System.out.println("Script finalizado.");
    }

    private static List<String> separarComandos(String conteudo) {
        List<String> comandos = new ArrayList<>();
        StringBuilder atual = new StringBuilder();
        for (String linha : conteudo.split("\n")) {
            String limpa = linha.trim();
            if (limpa.startsWith("--")) {
                continue;
            }
            atual.append(linha).append('\n');
            if (limpa.endsWith(";")) {
                String cmd = atual.toString().trim();
                if (cmd.endsWith(";")) {
                    cmd = cmd.substring(0, cmd.length() - 1).trim();
                }
                if (!cmd.isEmpty()) {
                    comandos.add(cmd);
                }
                atual.setLength(0);
            }
        }
        return comandos;
    }

    private static String resumir(String sql) {
        String uma = sql.replaceAll("\\s+", " ");
        return uma.length() > 80 ? uma.substring(0, 80) + "..." : uma;
    }
}
