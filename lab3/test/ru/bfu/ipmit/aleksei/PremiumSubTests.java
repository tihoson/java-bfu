package ru.bfu.ipmit.aleksei;

import org.junit.jupiter.api.Test;
import ru.bfu.ipmit.aleksei.config.BrowserConfig;
import ru.bfu.ipmit.aleksei.config.UrlConfig;
import ru.bfu.ipmit.aleksei.converters.UrlConverter;
import ru.bfu.ipmit.aleksei.fragments.BaseExtendedUrlFragments;
import ru.bfu.ipmit.aleksei.fragments.UrlFragments;


import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class PremiumSubTests {

    @Test
    public void PremiumUrlTest() {
        UrlConfig urlConfig = new UrlConfig();
        BrowserConfig browserConfig = new BrowserConfig(Subscription.PREMIUM);
        UrlConverter urlConverter = UrlConverter.fromConfig(browserConfig, urlConfig);

        // (PUT)https://site.com/test?query=q??body=b
        UrlFragments fragments = new BaseExtendedUrlFragments(
                "PUT",
                "https",
                new String[]{"site", "com"},
                new String[]{"test"},
                new HashMap<>(){{
                    put("query", new String[]{"q"});
                }},
                new HashMap<>(){{
                    put("body", new String[]{"b"});
                }});
        assertEquals("(PUT)https://site.com/test?query=q??body=b", urlConverter.toString(fragments));

        fragments = urlConverter.parseUrl("(PUT)https://site.com/test?query=q??body=b");
        assertEquals("(PUT)https://site.com/test?query=q??body=b", urlConverter.toString(fragments));
    }
}
