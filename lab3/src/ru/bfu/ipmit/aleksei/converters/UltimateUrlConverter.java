package ru.bfu.ipmit.aleksei.converters;

import ru.bfu.ipmit.aleksei.fragments.BaseExtendedUrlFragments;
import ru.bfu.ipmit.aleksei.fragments.ExtendedUrlFragments;
import ru.bfu.ipmit.aleksei.fragments.UrlFragments;
import ru.bfu.ipmit.aleksei.config.UrlConfig;

import java.util.Map;
import java.util.logging.Logger;
import java.util.regex.Matcher;

public class UltimateUrlConverter extends PremiumUrlConverter{
    public UltimateUrlConverter(UrlConfig urlConfig) {
        super(urlConfig);
    }

    protected ExtendedUrlFragments parseUrlMatcher(Matcher urlMatcher) {
        ExtendedUrlFragments fragments = super.parseUrlMatcher(urlMatcher);
        Map<String, String[]> headerParamValues = this.buildParamValues(urlMatcher.group("header"));

        return new BaseExtendedUrlFragments(
                fragments.getHTTPMethod(),
                fragments.getProtocol(),
                fragments.getHostFragments(),
                fragments.getPathFragments(),
                fragments.getQuery(),
                fragments.getBody(),
                headerParamValues
        );
    }

    @Override
    public String toString(UrlFragments fragments) {
        try {
            return super.toString(fragments) +
                    this.urlConfig.getTripleQuestionMark() +
                    buildParamsString(fragments.getHeader());
        } catch (Exception exception) {
            Logger.getGlobal().severe(exception.getMessage());
            throw exception;
        }
    }
}
