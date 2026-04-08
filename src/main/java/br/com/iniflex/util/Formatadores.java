package br.com.iniflex.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


public final class Formatadores {

    private static final DateTimeFormatter DATA_BR = DateTimeFormatter.ofPattern("dd/MM/uuuu");
    private static final DecimalFormat FORMATO_MOEDA_BR;

    static {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.forLanguageTag("pt-BR"));
        symbols.setGroupingSeparator('.');
        symbols.setDecimalSeparator(',');
        FORMATO_MOEDA_BR = new DecimalFormat("#,##0.00", symbols);
        FORMATO_MOEDA_BR.setParseBigDecimal(true);
    }

    private Formatadores() {
    }

    public static String formatarData(LocalDate data) {
        return DATA_BR.format(data);
    }

    public static String formatarMoeda(BigDecimal valor) {
        return FORMATO_MOEDA_BR.format(valor);
    }
}
