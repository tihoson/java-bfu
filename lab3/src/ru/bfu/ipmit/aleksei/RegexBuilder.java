package ru.bfu.ipmit.aleksei;

import lombok.AllArgsConstructor;
import ru.bfu.ipmit.aleksei.config.UrlConfig;

import java.util.regex.Pattern;

@AllArgsConstructor
public class RegexBuilder {
    private final UrlConfig urlConfig;

    private String method() {
        return "(?:\\((?<method>[A-Z]+)\\))?";
    }

    private String protocol() {
        return "(?<protocol>http|https)";
    }

    private String protocolWithHost() {
        String colon = Pattern.quote(this.urlConfig.getColon());
        String slash = Pattern.quote(this.urlConfig.getSlash());
        return colon + slash + slash;
    }

    private String host() {
        return "(?<host>[a-z0-9" + Pattern.quote(this.urlConfig.getDot()) + "]+)";
    }

    private String path() {
        String slash = Pattern.quote(this.urlConfig.getSlash());

        return "(?:" + slash + "(?<path>(?:[a-z0-9_]+" + slash + ")*[a-z0-9_]+" + slash + "?))?";
    }

    private String params() {
        String questionMark = Pattern.quote(this.urlConfig.getQuestionMark());
        String equalSign = Pattern.quote(this.urlConfig.getEqualSign());
        String ampersand = Pattern.quote(this.urlConfig.getAmpersand());
        String singleParam = "(?:[a-z0-9]+" + equalSign + "[a-z0-9]+)";

        return "(?:" + questionMark + "(?<params>(?:" + singleParam + ampersand + ")*" + singleParam + "?))?";
    }

    private String body() {
        String doubleQuestionMark = Pattern.quote(this.urlConfig.getDoubleQuestionMark());
        String equalSign = Pattern.quote(this.urlConfig.getEqualSign());
        String ampersand = Pattern.quote(this.urlConfig.getAmpersand());
        String singleParam = "(?:[a-z0-9]+" + equalSign + "[a-z0-9]+)";

        return "(?:" + doubleQuestionMark + "(?<body>(?:" + singleParam + ampersand + ")*" + singleParam + "?))?";
    }

    private String header() {
        String tripleQuestionMark = Pattern.quote(this.urlConfig.getTripleQuestionMark());
        String equalSign = Pattern.quote(this.urlConfig.getEqualSign());
        String ampersand = Pattern.quote(this.urlConfig.getAmpersand());
        String singleParam = "(?:[a-z0-9]+" + equalSign + "[a-z0-9]+)";

        return "(?:" + tripleQuestionMark + "(?<header>(?:" + singleParam + ampersand + ")*" + singleParam + "?))?";
    }

    @Override
    public String toString() {
        return this.method() +
                this.protocol() +
                this.protocolWithHost() +
                this.host() +
                this.path() +
                this.params() +
                this.body() +
                this.header();
    }
}
