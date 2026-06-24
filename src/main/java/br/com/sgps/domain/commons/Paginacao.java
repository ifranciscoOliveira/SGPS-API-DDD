package br.com.sgps.domain.commons;

public record Paginacao(int pagina, int tamanho, String ordenadoPor, DirecaoOrdenacao direcao) {
}
