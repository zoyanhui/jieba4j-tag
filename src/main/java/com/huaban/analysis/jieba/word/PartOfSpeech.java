package com.huaban.analysis.jieba.word;

import java.util.EnumSet;

/**
 * Created by zoyanhui on 17-8-14.
 *
 *
 * #在自己定义的词性中，不要使用i、w
 #i内置在代码中，表示未知词性
 #w内置在代码中，表示英文单词
 #1. 名词
 n=名词
 nr=人名
 ns=地名
 nt=团体机构名
 nz=其它专名

 #2. 动词
 v=动词
 vd=副动词
 vn=名动词
 vi=不及物动词
 vg=动词性语素

 #3. 形容词
 a=形容词
 ad=副形容词
 an=名形容词
 ag=形容词性语素

 #4. 数词
 m=数词
 mh=中文数词
 mb=百分数词
 mf=分数词
 mx=小数词
 mq=数量词

 #5. 量词
 q=量词

 #6. 代词
 r=代词
 rr=人称代词
 rz=指示代词
 rg=代词语素

 #7. 副词
 d=副词
 dg=副语素	副词性语素。副词代码为d，语素代码ｇ前面置以D。
 df=

 #8. 介词
 p=介词

 #9. 连词
 c=连词

 #10. 助词
 u=助词
 ul=  如: 了, 哦
 ud=副助词  得
 ug
 uj=形容助词
 uv=动助词。 地
 uz=语气助词

 #11. 拟声词
 o=拟声词

 #12. 叹词
 e=叹词

 #13. 时间词
 t=时间词
 tq=时间量词
 tdq=日期量词
 tg=时间语素

 #14. 处所词
 s=处所词

 #15. 方位词
 f=方位词

 #16. 区别词
 b=区别词

 #17. 语气词
 y=语气词

 #18. 状态词
 z=状态词
 zg=状态语素词

 #19. 词组
 l=词组

 #20. 英文单词
 w=英文单词

 #21. 语素
 g=语素. 绝大多数语素都能作为合成词的“词根”，取汉字“根”的声母

 21. 简略语
 j=简称略语	取汉字“简”的声母
 jn=简略名词

 22.
 x 字符串
 xx 非语素字
 xu 网址URL

 23.后缀
 k

 23.前缀
 h

 */
public enum PartOfSpeech {
    i, w,
    n, nr, ns, nt, nz, ng,
    v, vd, vn, vi, vg,
    a, ad, an, ag,
    m, mh, mb, mf, mx, mq, mg,
    q,
    r, rr, rz, rg,
    d, df, dg,
    p,
    c,
    u, ul, uv, ud, uj, uz, ug,
    o,
    e,
    t, tg, tq, tdq,
    s,
    f,
    b,
    y,
    z, zg,
    l,
    g,
    j, jn,
    x, xx, xu,
    k,
    h;

    private static EnumSet nounSet = EnumSet.of(n, nr, ns, nt, nz, ng);
    private static EnumSet verbSet = EnumSet.of(v, vd, vn, vi, vg);
    private static EnumSet adjectiveSet = EnumSet.of(a, ad, an, ag);
    private static EnumSet numeralSet = EnumSet.of(m, mh, mb, mf, mx, mq, mg);
    private static EnumSet pronounSet = EnumSet.of(r, rr, rz, rg);
    private static EnumSet timeSet = EnumSet.of(t, tg, tq, tdq);


    public static boolean isVerb(PartOfSpeech partOfSpeech){
        return verbSet.contains(partOfSpeech);
    }

    public static boolean isAdjective(PartOfSpeech partOfSpeech){
        return adjectiveSet.contains(partOfSpeech);
    }

    public static boolean isNumeral(PartOfSpeech partOfSpeech){
        return numeralSet.contains(partOfSpeech);
    }

    public static boolean isPronoun(PartOfSpeech partOfSpeech){
        return pronounSet.contains(partOfSpeech);
    }

    public static boolean isNoun(PartOfSpeech partOfSpeech){
        return nounSet.contains(partOfSpeech);
    }

    public static boolean isKeywords(PartOfSpeech partOfSpeech){
        return  isNoun(partOfSpeech) || isVerb(partOfSpeech) || isAdjective(partOfSpeech) || EnumSet.of(i, l, w, s, f).contains(partOfSpeech);
    }

    public static boolean isTimeType(PartOfSpeech partOfSpeech) {
        return timeSet.contains(partOfSpeech);
    }
}
