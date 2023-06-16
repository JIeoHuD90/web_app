package SelverTest;

import Selver.Selver;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import java.util.HashMap;
import java.util.Map;

import static SelverTest.MapToFileExample.writeMapToFile;
import static com.codeborne.selenide.Selenide.*;

public class SelverTest {
    @BeforeAll
    public static void ConfigureSelenide() {
        Configuration.browser = "firefox";
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://www.selver.ee";

    }

    @BeforeEach
    public void openSearchPage() {
        open("https://www.selver.ee");
        Selver selver = new Selver();
        selver.toggleAllProductsBySearch();
        refresh();
    }

    @Test
    public void getAllProductsShouldReturn_ListWithKeyProductName_ValueAsPrice() {
        Selver selverProducts = new Selver();
        int highestPage = selverProducts.getHighestPage();
        List<String> productNames = selverProducts.getProductNames();
        List<String> productPrice = selverProducts.getProductPrice();
        Map<String, String> joined = selverProducts.joinNameAsKey_PriceAsValue(productNames, productPrice);
        for (int i = 1; i < highestPage - 242; i++) {
            open("https://www.selver.ee/search?q=%2a&page=" + i);
            sleep(2000);
            productNames.addAll(selverProducts.getProductNames());
            productPrice.addAll(selverProducts.getProductPrice());

            Map<String, String> result = selverProducts.joinNameAsKey_PriceAsValue(productNames, productPrice);
            joined.putAll(result);

        }
        System.out.println(selverProducts.getHighestPage());
        System.out.println(joined);
        System.out.println(joined.size());

        Assertions.assertThat(joined.size()).isGreaterThan(0);

        /*String filePath = "C:\\Users\\whoops\\Desktop\\AutomatedTesting\\web_app\\output.txt";
        writeMapToFile(output, filePath);
        File outputMap = new File(filePath);
        Assertions.assertThat(outputMap.exists());
        Assertions.assertThat(outputMap.length()!=0);*/
    }

    @Test
    public void testFunc() {
        Selver selverProducts = new Selver();
        List<String> names = Arrays.asList("Apple", "Banana", "Orange");
        List<String> prices = Arrays.asList("1.00", "0.50", "0.75");

        Map<String, String> result = selverProducts.joinNameAsKey_PriceAsValue(names, prices);

        // Verify the result
        Assertions.assertThat(result).isNotEmpty();
    }
}