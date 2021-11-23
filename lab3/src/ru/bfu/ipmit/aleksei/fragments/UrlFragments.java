package ru.bfu.ipmit.aleksei.fragments;

import java.util.Map;

public interface UrlFragments {
    String getProtocol();
    String[] getHostFragments();
    String[] getPathFragments();
    Map<String, String[]> getQuery();
    Map<String, String[]> getBody();
    Map<String, String[]> getHeader();
}
