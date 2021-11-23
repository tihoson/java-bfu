package ru.bfu.ipmit.aleksei.converters;

import ru.bfu.ipmit.aleksei.fragments.BaseExtendedUrlFragments;
import ru.bfu.ipmit.aleksei.fragments.ExtendedUrlFragments;
import ru.bfu.ipmit.aleksei.config.UrlConfig;
import ru.bfu.ipmit.aleksei.fragments.UrlFragments;

import java.util.*;
import java.util.logging.Logger;
import java.util.regex.Matcher;

public class PremiumUrlConverter extends VipUrlConverter {
    public PremiumUrlConverter(UrlConfig urlConfig) {
        super(urlConfig);
    }

    protected ExtendedUrlFragments parseUrlMatcher(Matcher urlMatcher) {
        ExtendedUrlFragments fragments = super.parseUrlMatcher(urlMatcher);
        Map<String, String[]> bodyParamValues = this.buildParamValues(urlMatcher.group("body"));

        return new BaseExtendedUrlFragments(
            fragments.getHTTPMethod(),
            fragments.getProtocol(),
            fragments.getHostFragments(),
            fragments.getPathFragments(),
            fragments.getQuery(),
            bodyParamValues
        );
    }

    @Override
    public String toString(UrlFragments fragments) {
        try {
            return super.toString(fragments) +
                    this.urlConfig.getDoubleQuestionMark() +
                    buildParamsString(fragments.getBody());
        } catch (Exception exception) {
            Logger.getGlobal().severe(exception.getMessage());
            throw exception;
        }
    }
}
