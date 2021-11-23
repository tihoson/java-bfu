package ru.bfu.ipmit.aleksei.fragments;

import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class BaseUrlFragments implements UrlFragments {

    private final String protocol;
    private final String[] hostFragments;
    private final String[] pathFragments;
    private final Map<String, String[]> queryParamValues;
    private final Map<String, String[]> bodyParamValues;
    private final Map<String, String[]> headerParamValues;

    public BaseUrlFragments(String protocol, String[] hostFragments, String[] pathFragments, Map<String, String[]> queryParamValues, Map<String, String[]> bodyParamValues) {
        this(protocol, hostFragments, pathFragments, queryParamValues, bodyParamValues, new HashMap<>());
    }

    public BaseUrlFragments(String protocol, String[] hostFragments, String[] pathFragments, Map<String, String[]> queryParamValues) {
        this(protocol, hostFragments, pathFragments, queryParamValues, new HashMap<>());
    }

    public BaseUrlFragments(String protocol, String[] hostFragments, String[] pathFragments) {
        this(protocol, hostFragments, pathFragments, new HashMap<>());
    }

    public BaseUrlFragments(String protocol, String[] hostFragments) {
        this(protocol, hostFragments, new String[]{});
    }

    @Override
    public String getProtocol() {
        return this.protocol;
    }

    @Override
    public String[] getHostFragments() {
        return this.hostFragments;
    }

    @Override
    public String[] getPathFragments() {
        return this.pathFragments;
    }

    @Override
    public Map<String, String[]> getQuery() {
        return this.queryParamValues;
    }

    @Override
    public Map<String, String[]> getBody() {
        return this.bodyParamValues;
    }

    @Override
    public Map<String, String[]> getHeader() {
        return this.headerParamValues;
    }
}
