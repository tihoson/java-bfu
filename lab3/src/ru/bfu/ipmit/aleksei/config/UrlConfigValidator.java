package ru.bfu.ipmit.aleksei.config;

import ru.bfu.ipmit.aleksei.InvalidUrlConfigException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlConfigValidator {

    private enum UrlConfigField {
        slash,
        colon,
        dot,
        questionMark,
        ampersand,
        equalSign,
        doubleQuestionMark,
        tripleQuestionMark,
    }

    public void validate(UrlConfig urlConfig) throws InvalidUrlConfigException {
        validateSeparatorsDistinction(urlConfig);
        validateSeparatorsSymbols(urlConfig);
    }

    private void validateSeparatorsDistinction(UrlConfig urlConfig) throws InvalidUrlConfigException {
        for (UrlConfigField firstField: UrlConfigField.values()) {
            for (UrlConfigField secondField: UrlConfigField.values()) {
                if (firstField.equals(secondField))
                    continue;

                String firstFieldValue = getUrlConfigFieldValue(urlConfig, firstField);
                String secondFieldValue = getUrlConfigFieldValue(urlConfig, secondField);

                if (firstFieldValue.equals(secondFieldValue)) {
                    throwEqualSeparatorsException(firstField, secondField);
                }
            }
        }
    }

    private void validateSeparatorsSymbols(UrlConfig urlConfig) throws InvalidUrlConfigException {
        Pattern validSeparatorPattern = Pattern.compile("[^a-zA-Z0-9]+");

        for (UrlConfigField field: UrlConfigField.values()) {
            if (!validSeparatorPattern.matcher(getUrlConfigFieldValue(urlConfig, field)).matches()) {
                throwNotValidSeparatorException(field);
            }
        }
    }

    private String getUrlConfigFieldValue(UrlConfig urlConfig, UrlConfigField urlConfigField) {
        return switch (urlConfigField) {
            case slash -> urlConfig.getSlash();
            case colon -> urlConfig.getColon();
            case dot -> urlConfig.getDot();
            case ampersand -> urlConfig.getAmpersand();
            case equalSign -> urlConfig.getEqualSign();
            case questionMark -> urlConfig.getQuestionMark();
            case doubleQuestionMark -> urlConfig.getDoubleQuestionMark();
            case tripleQuestionMark -> urlConfig.getTripleQuestionMark();
        };
    }

    private void throwEqualSeparatorsException(UrlConfigField firstField, UrlConfigField secondField) throws InvalidUrlConfigException {
        throw new InvalidUrlConfigException(
            "Separators " + firstField.name() + " and " + secondField.name() + " must be different"
        );
    }

    private void throwNotValidSeparatorException(UrlConfigField field) throws InvalidUrlConfigException {
        throw new InvalidUrlConfigException(
                "Separator " + field.name() + " cannot contain alphabetic symbols or numbers"
        );
    }
}
