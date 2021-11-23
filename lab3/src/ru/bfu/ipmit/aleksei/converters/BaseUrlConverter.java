package ru.bfu.ipmit.aleksei.converters;

import ru.bfu.ipmit.aleksei.*;
import ru.bfu.ipmit.aleksei.config.UrlConfig;
import ru.bfu.ipmit.aleksei.config.UrlConfigValidator;
import ru.bfu.ipmit.aleksei.fragments.BaseExtendedUrlFragments;
import ru.bfu.ipmit.aleksei.fragments.ExtendedUrlFragments;
import ru.bfu.ipmit.aleksei.fragments.UrlFragments;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BaseUrlConverter implements UrlConverter {

    protected final UrlConfig urlConfig;

    public BaseUrlConverter(UrlConfig urlConfig) {
        UrlConfigValidator configValidator = new UrlConfigValidator();
        try {
            configValidator.validate(urlConfig);
        } catch (InvalidUrlConfigException exception) {
            Logger.getGlobal().warning(exception.getMessage());
            Logger.getGlobal().warning("Reset url config to default");
            urlConfig = new UrlConfig();
        } catch (Exception exception) {
            Logger.getGlobal().severe(exception.getMessage());
            throw exception;
        }
        this.urlConfig = urlConfig;
    }

    @Override
    final public ExtendedUrlFragments parseUrl(String url) {
        try {
            RegexBuilder regexBuilder = new RegexBuilder(this.urlConfig);
            Pattern urlPattern = Pattern.compile(regexBuilder.toString());
            Matcher urlMatcher = urlPattern.matcher(url);
            return parseUrlMatcher(urlMatcher);
        } catch (Exception exception) {
            Logger.getGlobal().severe(exception.getMessage());
            throw exception;
        }
    }

    protected ExtendedUrlFragments parseUrlMatcher(Matcher urlMatcher) {
        if (!urlMatcher.matches()) {
            return null;
        }

        String method = Utils.getValueOrDefault(urlMatcher.group("method"), "GET");
        String protocol = urlMatcher.group("protocol");
        String[] hostFragments = this.buildHostFragments(urlMatcher.group("host"));
        String[] pathFragments = this.buildPathFragments(urlMatcher.group("path"));

        return new BaseExtendedUrlFragments(method, protocol, hostFragments, pathFragments);
    }

    private String[] buildHostFragments(String host) {
        host = Utils.getValueOrDefault(host, "");
        return host.split(Pattern.quote(this.urlConfig.getDot()));
    }

    private String[] buildPathFragments(String path) {
        path = Utils.getValueOrDefault(path, "");
        return path.split(Pattern.quote(this.urlConfig.getSlash()));
    }

    @Override
    public String toString(UrlFragments fragments) {
        try {
            return fragments.getProtocol() +
                    this.urlConfig.getColon() +
                    this.urlConfig.getSlash() +
                    this.urlConfig.getSlash() +
                    this.buildHostString(fragments) +
                    this.buildPathString(fragments);
        } catch (Exception exception) {
            Logger.getGlobal().severe(exception.getMessage());
            throw exception;
        }
    }

    private String buildHostString(UrlFragments fragments) {
        return String.join(this.urlConfig.getDot(), fragments.getHostFragments());
    }

    private String buildPathString(UrlFragments fragments) {
        if (fragments.getPathFragments().length == 0) {
            return "";
        }
        return this.urlConfig.getSlash() + String.join(this.urlConfig.getSlash(), fragments.getPathFragments());
    }
}
