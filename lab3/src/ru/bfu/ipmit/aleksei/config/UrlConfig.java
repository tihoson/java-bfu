package ru.bfu.ipmit.aleksei.config;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UrlConfig {

    private String slash;
    private String colon;
    private String dot;
    private String questionMark;
    private String ampersand;
    private String equalSign;
    private String doubleQuestionMark;
    private String tripleQuestionMark;

    public UrlConfig(String slash, String colon, String dot, String questionMark, String ampersand, String equalSign) {
        this(slash, colon, dot, questionMark, ampersand, equalSign, "??", "???");
    }

    public UrlConfig() {
        this("/", ":", ".", "?", "&", "=", "??", "???");
    }

    public String getSlash() {
        return slash;
    }

    public String getColon() {
        return colon;
    }

    public String getDot() {
        return dot;
    }

    public String getQuestionMark() {
        return questionMark;
    }

    public String getAmpersand() {
        return ampersand;
    }

    public String getEqualSign() {
        return equalSign;
    }

    public String getDoubleQuestionMark() {
        return doubleQuestionMark;
    }

    public String getTripleQuestionMark() {
        return tripleQuestionMark;
    }
}
