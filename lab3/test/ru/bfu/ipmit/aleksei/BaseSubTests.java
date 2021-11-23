package ru.bfu.ipmit.aleksei;

import org.junit.jupiter.api.Test;
import ru.bfu.ipmit.aleksei.config.BrowserConfig;
import ru.bfu.ipmit.aleksei.config.UrlConfig;
import ru.bfu.ipmit.aleksei.converters.UrlConverter;
import ru.bfu.ipmit.aleksei.fragments.BaseExtendedUrlFragments;
import ru.bfu.ipmit.aleksei.fragments.BaseUrlFragments;
import ru.bfu.ipmit.aleksei.fragments.UrlFragments;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class BaseSubTests {

    @Test
    public void DefaultUrlTest() {
        UrlConfig urlConfig = new UrlConfig();
        BrowserConfig browserConfig = new BrowserConfig(Subscription.VIP);
        UrlConverter urlConverter = UrlConverter.fromConfig(browserConfig, urlConfig);

        // https://docs.oracle.com
        UrlFragments fragments = new BaseUrlFragments("https", new String[]{"docs", "oracle", "com"});
        assertEquals("https://docs.oracle.com", urlConverter.toString(fragments));

        fragments = urlConverter.parseUrl("https://docs.oracle.com");
        assertEquals("https://docs.oracle.com/", urlConverter.toString(fragments));
    }

    @Test
    public void DefaultUrlWithMethod() {
        UrlConfig urlConfig = new UrlConfig();
        BrowserConfig browserConfig = new BrowserConfig(Subscription.VIP);
        UrlConverter urlConverter = UrlConverter.fromConfig(browserConfig, urlConfig);

        // https://docs.oracle.com
        UrlFragments fragments = new BaseExtendedUrlFragments("https", new String[]{"docs", "oracle", "com"});
        assertEquals("(POST)https://docs.oracle.com", urlConverter.toString(fragments));
    }

    @Test
    public void DefaultUrlWithParams() {
        UrlConfig urlConfig = new UrlConfig();
        BrowserConfig browserConfig = new BrowserConfig(Subscription.VIP);
        UrlConverter urlConverter = UrlConverter.fromConfig(browserConfig, urlConfig);

        // https://vk.com/friends?section=online
        UrlFragments fragments = new BaseUrlFragments(
                "https",
                new String[]{"vk", "com"},
                new String[]{"friends"},
                new HashMap<>(){{
                    put("section", new String[]{"online"});
                }}
        );
        assertEquals("https://vk.com/friends?section=online", urlConverter.toString(fragments));
    }

    @Test
    public void UrlWithNewSeparators() {
        UrlConfig urlConfig = new UrlConfig(
                "#","-", "*", "!", "$", "@"
        );
        BrowserConfig browserConfig = new BrowserConfig(Subscription.VIP);
        UrlConverter urlConverter = UrlConverter.fromConfig(browserConfig, urlConfig);

        // https-##vk*com#friends!section@online
        UrlFragments fragments = new BaseUrlFragments(
                "https",
                new String[]{"vk", "com"},
                new String[]{"friends"},
                new HashMap<>(){{
                    put("section", new String[]{"online"});
                }}
        );
        assertEquals("https-##vk*com#friends!section@online", urlConverter.toString(fragments));
    }

    @Test
    public void ShouldBeExceptionWithDefaultConfigs() {
        UrlConfig urlConfig = new UrlConfig(
                "!","!", "-", "+", "$", "@"
        );
        BrowserConfig browserConfig = new BrowserConfig(Subscription.VIP);
        UrlConverter urlConverter = UrlConverter.fromConfig(browserConfig, urlConfig);

        // https-##vk*com#friends!section@online
        UrlFragments fragments = new BaseUrlFragments(
                "https",
                new String[]{"vk", "com"},
                new String[]{"friends"},
                new HashMap<>(){{
                    put("section", new String[]{"online"});
                }}
        );
        assertEquals("https://vk.com/friends?section=online", urlConverter.toString(fragments));
    }
}
