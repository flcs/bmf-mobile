package br.com.bmf.bmfmobile;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.bmf.bmfmobile.persistence.Ativo;

public class AtivosFromURLUtil {

    public static List<Ativo> get() throws IOException {
        Document doc = Jsoup.connect("https://br.advfn.com/").get();

        Elements select = doc.select("h3:contains(Maior Volume)+table>tbody>tr");

        List<Ativo> ativos = new ArrayList<>();

        for (Element element : select) {
            String codigo = element.selectFirst("td:eq(1)>a").text();
            String valor = element.selectFirst("td:eq(3)").text();

            ativos.add(new Ativo(codigo, Float.parseFloat(valor.replace(",", "."))));
        }

        return ativos;
    }
}
