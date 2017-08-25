package com.huaban.analysis.jieba.word;

import com.huaban.analysis.jieba.SegToken;
import com.huaban.analysis.jieba.WordDictionary;
import org.apache.commons.collections4.Trie;
import org.apache.commons.collections4.trie.PatriciaTrie;

import java.io.*;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Locale;

/**
 * 词性标注
 * <p>
 * Created by zoyanhui on 17-8-14.
 */
public class PartOfSpeechTagging {
    private final Trie<String, PartOfSpeech> wordTagTrie;

    private static final String DEFAULT_TAG_FILE = "/word/default_part_of_speech.txt";
    private static final String CUSTOM_TAG_FILE = "/word/custom_part_of_speech.txt";

    private static PartOfSpeechTagging singleton;

    public static PartOfSpeechTagging getInstance() {
        if (singleton == null) {
            synchronized (WordDictionary.class) {
                if (singleton == null) {
                    singleton = new PartOfSpeechTagging();
                    return singleton;
                }
            }
        }
        return singleton;
    }

    private PartOfSpeechTagging() {
        wordTagTrie = new PatriciaTrie<PartOfSpeech>();
        try {
            int count = load(DEFAULT_TAG_FILE, wordTagTrie);
            System.out.println(String.format(Locale.getDefault(),
                    "load default word tags from %s complete, size %d", DEFAULT_TAG_FILE, count));
            if (new File(CUSTOM_TAG_FILE).exists()) {
                count = load(CUSTOM_TAG_FILE, wordTagTrie);
                System.out.println(String.format(Locale.getDefault(),
                        "load custom word tags from %s complete, size %d", CUSTOM_TAG_FILE, count));
            } else {
                System.out.println(String.format(Locale.getDefault(),
                        "custom word tags file not exists %s", CUSTOM_TAG_FILE));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private int load(String path, Trie<String, PartOfSpeech> trie) throws IOException {
        int count = 0;
        InputStream is = this.getClass().getResourceAsStream(path);
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            long s = System.currentTimeMillis();
            while (br.ready()) {
                String line = br.readLine();
                if (line.startsWith("#")) {
                    continue;
                }
                count++;
                String[] attr = line.trim().split(":");
                trie.put(attr[0], PartOfSpeech.valueOf(attr[1]));
            }
            System.out.println(String.format(Locale.getDefault(), "%s partOfSpeech dict load finished, time elapsed %d ms",
                    path,
                    System.currentTimeMillis() - s));
        } catch (IOException e) {
            System.err.println(String.format(Locale.getDefault(), "%s load failure!", path));
        } finally {
            try {
                if (null != is)
                    is.close();
            } catch (IOException e) {
                System.err.println(String.format(Locale.getDefault(), "%s close failure!", path));
            }
        }
        return count;
    }

    public PartOfSpeech process(String wordText) {
        PartOfSpeech pos = wordTagTrie.get(wordText);
        if (pos == null) {
            pos = PartOfSpeech.i; // 未识别的词性
            //识别英文
            if (RecognitionTool.isEnglish(wordText)) {
                pos = PartOfSpeech.w;
            }
            //识别数字
            if (RecognitionTool.isNumber(wordText)) {
                pos = PartOfSpeech.m;
            }
            //中文数字
            if (RecognitionTool.isChineseNumber(wordText)) {
                pos = PartOfSpeech.mh;
            }
            //识别小数和分数
            if (RecognitionTool.isFraction(wordText)) {
                if (wordText.contains(".") || wordText.contains("．") || wordText.contains("·")) {
                    pos = PartOfSpeech.mx;
                }
                if (wordText.contains("/") || wordText.contains("／")) {
                    pos = PartOfSpeech.mf;
                }
            }
            //识别数量词
            if (RecognitionTool.isQuantifier(wordText)) {
                //分数
                if (wordText.contains("‰") || wordText.contains("%") || wordText.contains("％")) {
                    pos = PartOfSpeech.mf;
                }
                //时间量词
                else if (wordText.contains("时") || wordText.contains("分") || wordText.contains("秒")) {
                    pos = PartOfSpeech.tq;
                }
                //日期量词
                else if (wordText.contains("年") || wordText.contains("月") || wordText.contains("日")
                        || wordText.contains("天") || wordText.contains("号")) {
                    pos = PartOfSpeech.tdq;
                }
                //数量词
                else {
                    pos = PartOfSpeech.mq;
                }
            }
        }
        return pos;
    }


    public void process(List<SegToken> words) {
        for (SegToken segToken : words) {
            if (segToken.partOfSpeech != null) {
                continue;
            }
            segToken.partOfSpeech = process(segToken.word);
        }
    }
}
