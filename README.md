结巴分词(java版) jieba-analysis
===============================

感谢jieba分词原作者[fxsjy](https://github.com/fxsjy)。
感谢jieba分词java版的作者[huaban](https://github.com/huaban/jieba-analysis)。


简介
====

-   源用[jieba分词的java版](https://github.com/huaban/jieba-analysis)支持search和index两种分词模式
-   增加词性标注功能


如何使用
========

-   Demo

``` {.java}

@Test
public void testDemo() {
    JiebaSegmenter segmenter = new JiebaSegmenter();
    String[] sentences =
        new String[] {"这是一个伸手不见五指的黑夜。我叫孙悟空，我爱北京，我爱Python和C++。", "我不喜欢日本和服。", "雷猴回归人间。",
                      "工信处女干事每月经过下属科室都要亲口交代24口交换机等技术性器件的安装工作", "结果婚的和尚未结过婚的"};
    for (String sentence : sentences) {
        System.out.println(segmenter.process(sentence, SegMode.INDEX, true).toString()); // 第三个参数如果是false则不标注词性
    }
}
```

算法(wiki补充...)
=================

-   [jieba分词的java版](https://github.com/huaban/jieba-analysis)
-   基于trie树,进行快速的词性标注

性能评估（待添加）
========

-   测试机配置

使用本库项目
============

-  基于 [jieba-analysis](https://github.com/huaban/jieba-analysis) 开发 @huaban

许可证
======

jieba(python版本)的许可证为MIT，jieba(java版本)的许可证为ApacheLicence
2.0

``` {.screen}
Copyright (C) 2013 Huaban Inc

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
```
