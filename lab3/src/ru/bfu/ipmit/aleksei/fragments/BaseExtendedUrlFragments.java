package ru.bfu.ipmit.aleksei.fragments;

import java.util.Map;

public class BaseExtendedUrlFragments extends BaseUrlFragments implements ExtendedUrlFragments {

    private final String method;

    public BaseExtendedUrlFragments(String method, String protocol, String[] hostFragments, String[] pathFragments, Map<String, String[]> queryParamValues, Map<String, String[]> bodyParamValues, Map<String, String[]> headerParamValues) {
        super(protocol, hostFragments, pathFragments, queryParamValues, bodyParamValues, headerParamValues);
        this.method = method;
    }

    public BaseExtendedUrlFragments(String method, String protocol, String[] hostFragments, String[] pathFragments, Map<String, String[]> queryParamValues, Map<String, String[]> bodyParamValues) {
        super(protocol, hostFragments, pathFragments, queryParamValues, bodyParamValues);
        this.method = method;
    }

    public BaseExtendedUrlFragments(String method, String protocol, String[] hostFragments, String[] pathFragments, Map<String, String[]> queryParamValues) {
        super(protocol, hostFragments, pathFragments, queryParamValues);
        this.method = method;
    }

    public BaseExtendedUrlFragments(String method, String protocol, String[] hostFragments, String[] pathFragments) {
        super(protocol, hostFragments, pathFragments);
        this.method = method;
    }

    public BaseExtendedUrlFragments(String method, String protocol, String[] hostFragments) {
        super(protocol, hostFragments);
        this.method = method;
    }

    public BaseExtendedUrlFragments(String protocol, String[] hostFragments) {
        this("POST", protocol, hostFragments);
    }

    @Override
    public String getHTTPMethod() {
        return this.method;
    }
}
