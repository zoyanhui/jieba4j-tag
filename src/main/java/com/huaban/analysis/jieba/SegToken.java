package com.huaban.analysis.jieba;

import com.huaban.analysis.jieba.word.PartOfSpeech;

public class SegToken {
    public String word;

    public int startOffset;

    public int endOffset;

    public PartOfSpeech partOfSpeech;


    public SegToken(String word, int startOffset, int endOffset) {
        this.word = word;
        this.startOffset = startOffset;
        this.endOffset = endOffset;
    }

    public SegToken(String word, int startOffset, int endOffset, PartOfSpeech partOfSpeech) {
        this.word = word;
        this.startOffset = startOffset;
        this.endOffset = endOffset;
        this.partOfSpeech = partOfSpeech;
    }


    @Override
    public String toString() {
        if(partOfSpeech != null) {
            return "[" + word + ", " + startOffset + ", " + endOffset + ", " + partOfSpeech.name() + "]";
        }else{
            return "[" + word + ", " + startOffset + ", " + endOffset + "]";
        }
    }

}
