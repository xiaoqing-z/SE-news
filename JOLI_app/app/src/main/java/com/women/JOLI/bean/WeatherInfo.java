package com.women.JOLI.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherInfo {


    @JsonProperty("desc")
    public String desc;
    @JsonProperty("status")
    public int status;

    @JsonProperty("data")
    public DataEntity data;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DataEntity {

        @Override
        public String toString() {
            return "DataEntity{" +
                    "wendu='" + wendu + '\'' +
                    ", ganmao='" + ganmao + '\'' +
                    ", yesterday=" + yesterday +
                    ", aqi='" + aqi + '\'' +
                    ", city='" + city + '\'' +
                    ", forecast=" + forecast +
                    '}';
        }

        @JsonProperty("wendu")
        public String wendu;
        @JsonProperty("ganmao")
        public String ganmao;

        @JsonProperty("yesterday")
        public YesterdayEntity yesterday;
        @JsonProperty("aqi")
        public String aqi;
        @JsonProperty("city")
        public String city;

        @JsonProperty("forecast")
        public List<ForecastEntity> forecast;

        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class YesterdayEntity {

            @Override
            public String toString() {
                return "YesterdayEntity{" +
                        "fl='" + fl + '\'' +
                        ", fx='" + fx + '\'' +
                        ", high='" + high + '\'' +
                        ", type='" + type + '\'' +
                        ", low='" + low + '\'' +
                        ", date='" + date + '\'' +
                        '}';
            }

            @JsonProperty("fl")
            public String fl;
            @JsonProperty("fx")
            public String fx;
            @JsonProperty("high")
            public String high;
            @JsonProperty("type")
            public String type;
            @JsonProperty("low")
            public String low;
            @JsonProperty("date")
            public String date;
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class ForecastEntity {

            @Override
            public String toString() {
                return "ForecastEntity{" +
                        "fengxiang='" + fengxiang + '\'' +
                        ", fengli='" + fengli + '\'' +
                        ", high='" + high + '\'' +
                        ", type='" + type + '\'' +
                        ", low='" + low + '\'' +
                        ", date='" + date + '\'' +
                        '}';
            }

            @JsonProperty("fengxiang")
            public String fengxiang;
            @JsonProperty("fengli")
            public String fengli;
            @JsonProperty("high")
            public String high;
            @JsonProperty("type")
            public String type;
            @JsonProperty("low")
            public String low;
            @JsonProperty("date")
            public String date;
        }
    }
}
