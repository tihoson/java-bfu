package ru.bfu.ipmit.aleksei.converters;

import ru.bfu.ipmit.aleksei.fragments.BaseExtendedUrlFragments;
import ru.bfu.ipmit.aleksei.fragments.ExtendedUrlFragments;
import ru.bfu.ipmit.aleksei.config.UrlConfig;
import ru.bfu.ipmit.aleksei.fragments.UrlFragments;

import java.util.*;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VipUrlConverter extends BaseUrlConverter{
    public VipUrlConverter(UrlConfig urlConfig) {
        super(urlConfig);
    }

    protected ExtendedUrlFragments parseUrlMatcher(Matcher urlMatcher) {
        ExtendedUrlFragments fragments = super.parseUrlMatcher(urlMatcher);
        Map<String, String[]> queryParamValues = this.buildParamValues(urlMatcher.group("params"));

        return new BaseExtendedUrlFragments(
            fragments.getHTTPMethod(),
            fragments.getProtocol(),
            fragments.getHostFragments(),
            fragments.getPathFragments(),
            queryParamValues
        );
    }

    protected Map<String, String[]> buildParamValues(String params) {
        if (params == null) {
            return new HashMap<>();
        }

        Map<String, List<String>> queryParamValues = new LinkedHashMap<>();

        for(String paramNameWithValue: params.split(Pattern.quote(this.urlConfig.getAmpersand()))) {
            String[] paramNameWithValueArray = paramNameWithValue.split(
                    Pattern.quote(this.urlConfig.getEqualSign())
            );
            if (paramNameWithValueArray.length != 2)
                throw new IllegalArgumentException("Length must be 2");

            // put new param if absent
            queryParamValues.putIfAbsent(paramNameWithValueArray[0], new ArrayList<>());
            queryParamValues.get(paramNameWithValueArray[0]).add(paramNameWithValueArray[1]);
        }

        Map<String, String[]> trueQueryParamValues = new LinkedHashMap<>();
        for (String paramName: queryParamValues.keySet()) {
            trueQueryParamValues.put(paramName, queryParamValues.get(paramName).toArray(String[]::new));
        }
        return trueQueryParamValues;
    }

    @Override
    public String toString(UrlFragments fragments) {
        try {
            StringBuilder builder = new StringBuilder();

            if (fragments instanceof ExtendedUrlFragments) {
                String method = ((ExtendedUrlFragments) fragments).getHTTPMethod();
                if (!method.equals("GET")) {
                    builder.append("(").append(method).append(")");
                }
            }
            builder.append(super.toString(fragments));
            String params = buildParamsString(fragments.getQuery());

            if (params.isEmpty())
                return builder.toString();

            return builder.append(this.urlConfig.getQuestionMark())
                    .append(buildParamsString(fragments.getQuery()))
                    .toString();
        } catch (Exception exception) {
            Logger.getGlobal().severe(exception.getMessage());
            throw exception;
        }
    }

    protected String buildParamsString(Map<String, String[]> paramsValues) {
        List<String> params = new ArrayList<>();

        for(String paramName: paramsValues.keySet()) {
            String[] paramValues = paramsValues.get(paramName);

            for (String paramValue: paramValues) {
                params.add(
                        String.join(
                            this.urlConfig.getEqualSign(),
                            new String[]{paramName, paramValue}
                        )
                );
            }
        }

        return String.join(this.urlConfig.getAmpersand(), params);
    }
}
