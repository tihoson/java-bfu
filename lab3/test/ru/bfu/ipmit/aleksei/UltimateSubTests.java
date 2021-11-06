package ru.bfu.ipmit.aleksei;

import org.junit.jupiter.api.Test;
import ru.bfu.ipmit.aleksei.config.BrowserConfig;
import ru.bfu.ipmit.aleksei.config.UrlConfig;
import ru.bfu.ipmit.aleksei.converters.UrlConverter;
import ru.bfu.ipmit.aleksei.fragments.BaseExtendedUrlFragments;
import ru.bfu.ipmit.aleksei.fragments.UrlFragments;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UltimateSubTests {

    @Test
    public void UltimateUrlTest() {
        UrlConfig urlConfig = new UrlConfig();
        BrowserConfig browserConfig = new BrowserConfig(Subscription.ULTIMATE);
        UrlConverter urlConverter = UrlConverter.fromConfig(browserConfig, urlConfig);

        // (DELETE)https://site.com/test?query=q??body=b???header=h
        UrlFragments fragments = new BaseExtendedUrlFragments(
                "DELETE",
                "https",
                new String[]{"site", "com"},
                new String[]{"test"},
                new HashMap<>(){{
                    put("query", new String[]{"q"});
                }},
                new HashMap<>(){{
                    put("body", new String[]{"b"});
                }},
                new HashMap<>(){{
                    put("header", new String[]{"h"});
                }});
        assertEquals("(DELETE)https://site.com/test?query=q??body=b???header=h", urlConverter.toString(fragments));

        fragments = urlConverter.parseUrl("(DELETE)https://site.com/test?query=q??body=b???header=h");
        assertEquals("(DELETE)https://site.com/test?query=q??body=b???header=h", urlConverter.toString(fragments));
    }
}
