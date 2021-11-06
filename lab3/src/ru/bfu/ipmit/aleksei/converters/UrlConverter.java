package ru.bfu.ipmit.aleksei.converters;

import ru.bfu.ipmit.aleksei.config.BrowserConfig;
import ru.bfu.ipmit.aleksei.config.UrlConfig;
import ru.bfu.ipmit.aleksei.fragments.UrlFragments;

public interface UrlConverter {
    UrlFragments parseUrl(String url);
    String toString(UrlFragments fragments);

    static UrlConverter fromConfig(BrowserConfig browserConfig, UrlConfig urlConfig) {
        return switch (browserConfig.getSubscription()) {
            case BASE -> new BaseUrlConverter(urlConfig);
            case VIP -> new VipUrlConverter(urlConfig);
            case PREMIUM -> new PremiumUrlConverter(urlConfig);
            case ULTIMATE -> new UltimateUrlConverter(urlConfig);
        };
    }
}