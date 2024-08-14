package ru.caselab.vo.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum RequestState {
    @JsonProperty("success")
    SUCCESS("success"),
    @JsonProperty("error")
    ERROR("error"),
    @JsonProperty("unknown")
    UNKNOWN("unknown");
    private final String s;

    RequestState(String s) {
        this.s = s;
    }

    @JsonCreator
    public static RequestState getState(String json) {
        for (RequestState state : RequestState.values()) {
            if (state.s.equals(json)) {
                return state;
            }
        }
        return RequestState.UNKNOWN;
    }
}
