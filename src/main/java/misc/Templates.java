package misc;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.LinkedHashMap;
import java.util.Locale;

public class Templates {

    public LinkedHashMap<String, String> tableTemplate() {
        //LinkedHashMap потому что сохраняет порядок, в котором добавлялись элементы в отличие от HashMap
        LinkedHashMap<String, String> columns = new LinkedHashMap<>();
        columns.put("id", "%-5s");
        columns.put("type", "%-10s");
        columns.put("name", "%-20s");
        columns.put("age", "%-3s");
        columns.put("weight", "%-7s");
        columns.put("color", "%-7s");

        return columns;
    }

    public String floatTemplate(float number) {
        //используем US локаль, чтоб разделителем десятичным была точка, а не русская запятая
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.US);
        // настройка шаблона на 3 знака после запятой
        DecimalFormat df = new DecimalFormat("#.###", otherSymbols);
        // лучше, чем String.format("%.3f", weight), хотя бы потому что отрезает лишние нули
        return df.format(number);
    }
}
