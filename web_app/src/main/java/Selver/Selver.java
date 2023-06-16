package Selver;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.*;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class Selver {
    ElementsCollection productNames = $$("h3 .ProductCard__link");
    ElementsCollection productPrice = $$(".ProductPrices:not(:nth-child(2))");
    ElementsCollection pages = $$(".sf-link.sf-pagination__item");
    SelenideElement searchField = $(".Search__input ");
    SelenideElement searchButton = $(".Search__button");
    SelenideElement searchInit = $(".Search__initialiser");

    public List<String> getProductNames() {
        List<String> output = new ArrayList<>();
        for (var i = 0; i < productNames.size(); i++) {
            SelenideElement element = productNames.get(i);
            output.add(element.getText().trim());
        }
        output.removeIf(Objects::isNull);
        output.removeIf(String::isEmpty);
        return output;
    }

    public List<String> getProductPrice() {
        List<String> output = new ArrayList<>();
        for (var i = 0; i < productPrice.size(); i++) {
            SelenideElement element = productPrice.get(i);

            output.add(element.getText().trim());

        }
        output.removeIf(Objects::isNull);
        output.removeIf(String::isEmpty);
        return output;
    }

    public Map<String, String> joinNameAsKey_PriceAsValue(List<String> name, List<String> price) {
        Map<String, String> output = new HashMap<>();
        if (name != null && price != null && name.size() == price.size()) {
            for (int i = 0; i < name.size(); i++) {
                output.put(name.get(i), price.get(i));
            }
        }
        return output;
    }

    public Integer getHighestPage() {
        return Integer.parseInt(pages.get(5).getText());
    }

    public void toggleAllProductsBySearch() {
        searchInit.click();
        searchField.val("*");
        searchButton.click();
    }

}
