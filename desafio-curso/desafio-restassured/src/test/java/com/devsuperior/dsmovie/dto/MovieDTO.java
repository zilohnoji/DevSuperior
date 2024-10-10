package com.devsuperior.dsmovie.dto;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class MovieDTO {
    private final DecimalFormat df = new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.US));

    private Long id;
    private String title;
    private Double score;
    private Integer count;
    private String image;

    public MovieDTO(Long id, String title, Double score, Integer count, String image) {
        this.id = id;
        this.title = title;
        this.score = Double.valueOf(df.format(score));
        this.count = count;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Double getScore() {
        return score;
    }

    public Integer getCount() {
        return count;
    }

    public String getImage() {
        return image;
    }

    @Override
    public String toString() {
        return "MovieDTO [id=" + id + ", title=" + title + ", score=" + score + ", count=" + count + ", image=" + image
                + "]";
    }
}