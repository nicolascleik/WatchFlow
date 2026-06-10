package com.watchflow.WatchFlow.core.domain.usuario;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Usuario {
    
    private final UUID id;
    private String nome;
    private String email;
    private String senha;
    private String cidade;
    private String estado;
    
    private Set<UUID> amigosIds;
    private Set<UUID> categoriasFavoritasIds;
    private Set<UUID> filmesAssistidosIds;
    private Set<UUID> episodiosAssistidosIds;

    // Construtor privado para forçar o uso da Factory de Domínio
    private Usuario(UUID id, String nome, String email, String senha, String cidade, String estado) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cidade = cidade;
        this.estado = estado;
        this.amigosIds = new HashSet<>();
        this.categoriasFavoritasIds = new HashSet<>();
        this.filmesAssistidosIds = new HashSet<>();
        this.episodiosAssistidosIds = new HashSet<>();
    }

    // FÁBRICA: Usada quando um novo usuário se cadastra no sistema
    public static Usuario criar(String nome, String email, String senhaHasheada, String cidade, String estado) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Formato de e-mail inválido.");
        }
        return new Usuario(UUID.randomUUID(), nome, email, senhaHasheada, cidade, estado);
    }

    // FÁBRICA: Usada pela camada de Infrastructure (Mapper) para remontar a entidade vinda do DB
    public static Usuario reconstruir(UUID id, String nome, String email, String senha, String cidade, String estado,
                                      Set<UUID> amigosIds, Set<UUID> categoriasFavoritasIds,
                                      Set<UUID> filmesAssistidosIds, Set<UUID> episodiosAssistidosIds) {
        Usuario usuario = new Usuario(id, nome, email, senha, cidade, estado);
        usuario.amigosIds = amigosIds != null ? new HashSet<>(amigosIds) : new HashSet<>();
        usuario.categoriasFavoritasIds = categoriasFavoritasIds != null ? new HashSet<>(categoriasFavoritasIds) : new HashSet<>();
        usuario.filmesAssistidosIds = filmesAssistidosIds != null ? new HashSet<>(filmesAssistidosIds) : new HashSet<>();
        usuario.episodiosAssistidosIds = episodiosAssistidosIds != null ? new HashSet<>(episodiosAssistidosIds) : new HashSet<>();
        return usuario;
    }

    // MÉTODOS DE NEGÓCIO (Comportamento)
    
    public void atualizarPerfil(String nome, String cidade, String estado) {
        this.nome = nome;
        this.cidade = cidade;
        this.estado = estado;
    }

    public void adicionarAmigo(UUID amigoId) {
        this.amigosIds.add(amigoId);
    }

    public void removerAmigo(UUID amigoId) {
        this.amigosIds.remove(amigoId);
    }

    public void registrarFilmeAssistido(UUID filmeId) {
        this.filmesAssistidosIds.add(filmeId);
    }

    public void registrarEpisodioAssistido(UUID episodioId) {
        this.episodiosAssistidosIds.add(episodioId);
    }

    public void atualizarCategorias(Set<UUID> categorias) {
        this.categoriasFavoritasIds = new HashSet<>(categorias);
    }

    // GETTERS (Apenas leitura para exportar dados para a Infra ou Web)
    
    public UUID getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getSenha() { return senha; }
    public String getCidade() { return cidade; }
    public String getEstado() { return estado; }

    // Retorna cópias imutáveis para evitar modificação externa acidental das listas
    public Set<UUID> getAmigosIds() { return Collections.unmodifiableSet(amigosIds); }
    public Set<UUID> getCategoriasFavoritasIds() { return Collections.unmodifiableSet(categoriasFavoritasIds); }
    public Set<UUID> getFilmesAssistidosIds() { return Collections.unmodifiableSet(filmesAssistidosIds); }
    public Set<UUID> getEpisodiosAssistidosIds() { return Collections.unmodifiableSet(episodiosAssistidosIds); }
}