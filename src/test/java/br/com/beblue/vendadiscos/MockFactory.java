package br.com.beblue.vendadiscos;

import br.com.beblue.vendadiscos.domain.model.*;
import br.com.beblue.vendadiscos.domain.model.dto.ItemDTO;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MockFactory {

    public static List<Disco> montarListaDiscos(int tamanhoPagina) {

        List<Disco> discos = new ArrayList<>();
        for (int i = 1; i <= tamanhoPagina; i++) {
            Disco disco = montarDisco(i);
            discos.add(disco);
        }
        return discos;
    }


    public static Disco montarDisco(long id) {
        Disco disco = new Disco();
        disco.setId(id);
        disco.setPreco(new BigDecimal(10));
        Artista artista = new Artista();
        Genero genero = new Genero();
        genero.setCashback(montarCashback());
        artista.adicionarGenero(genero);
        disco.adicionarArtista(artista);
        return disco;
    }

    private static List<Cashback> montarCashback() {

        return Arrays.asList(
                new Cashback(DayOfWeek.SUNDAY, new BigDecimal(1)),
                new Cashback(DayOfWeek.MONDAY, new BigDecimal(2)),
                new Cashback(DayOfWeek.TUESDAY, new BigDecimal(3)),
                new Cashback(DayOfWeek.WEDNESDAY, new BigDecimal(4)),
                new Cashback(DayOfWeek.THURSDAY, new BigDecimal(5)),
                new Cashback(DayOfWeek.FRIDAY, new BigDecimal(6)),
                new Cashback(DayOfWeek.SATURDAY, new BigDecimal(7)));
    }


    public static List<Venda> montarListaVendas(int tamanhoPagina) {

        List<Venda> vendas = new ArrayList<>();
        for (int i = 1; i <= tamanhoPagina; i++) {
            Venda disco = montarVenda(i);
            vendas.add(disco);
        }
        return vendas;
    }

    public static Venda montarVenda(long i) {

        Venda venda = new Venda();
        venda.setId(i);
        Item item = new Item();
        item.setQuantidade(1);
        Disco disco = new Disco();
        disco.setPreco(new BigDecimal(100));
        item.setDisco(disco);
        venda.setItens(Collections.singletonList(item));
        return venda;
    }


    public static ItemDTO montarItemDTO() {

        return new ItemDTO(montarItem());
    }


    private static Item montarItem() {

        Item item = new Item();
        item.setQuantidade(1);
        item.setDisco(montarDisco(1L));
        return item;
    }
}
