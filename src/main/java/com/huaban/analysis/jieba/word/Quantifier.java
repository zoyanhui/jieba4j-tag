package com.huaban.analysis.jieba.word;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/**
 * Quantifier, 数量词识别
 *
 * Created by zoyanhui on 17-8-14.
 */
public class Quantifier {
    private static final String DEFAULT_QUANTIFIER_FILE = "/word/quantifier.txt";
    private static final Quantifier instance = new Quantifier();
    private final Set<Character> quantifiers;

    static Quantifier getInstance() {
        return instance;
    }

    private Quantifier() {
        quantifiers = new HashSet<>();
        try {
            reload();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void reload() throws IOException {
        InputStream is = this.getClass().getResourceAsStream(DEFAULT_QUANTIFIER_FILE);
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));

            long s = System.currentTimeMillis();
            while (br.ready()) {
                String line = br.readLine();
                if (line.startsWith("#") || line.length() != 1) {
                    continue;
                }
                char _char = line.charAt(0);
                quantifiers.add(_char);
            }
            System.out.println(String.format(Locale.getDefault(), "quantifier dict load finished, time elapsed %d ms",
                    System.currentTimeMillis() - s));
        } catch (IOException e) {
            System.err.println(String.format(Locale.getDefault(), "%s load failure!", DEFAULT_QUANTIFIER_FILE));
        } finally {
            try {
                if (null != is)
                    is.close();
            }
            catch (IOException e) {
                System.err.println(String.format(Locale.getDefault(), "%s close failure!", DEFAULT_QUANTIFIER_FILE));
            }
        }
    }


    public boolean is(char _char) {
        return quantifiers.contains(_char);
    }

}
