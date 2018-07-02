package br.com.bmf.bmfmobile;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.bmf.bmfmobile.persistence.Ativo;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void capturePetrobras_isCorrect() {
        try {
            Document doc = Jsoup.connect("https://br.advfn.com/").get();

//            Elements select = doc.select("h3:contains(Maior Volume)+table>tbody>tr[class=odd first]>td:eq(1)");

            Elements select = doc.select("h3:contains(Maior Volume)+table>tbody>tr");

            List<Ativo> ativos = new ArrayList<>();

            for (Element element : select) {
                String codigo = element.selectFirst("td:eq(1)>a").text();
                String valor = element.selectFirst("td:eq(3)").text();

                ativos.add(new Ativo(codigo, Float.parseFloat(valor.replace(",", "."))));
            }

            if (ativos.isEmpty()) {
                fail();
            }

        } catch (IOException e) {
            fail(e.getMessage());
        }

    }
}