package com.example.movieinfoservice.resources;

import java.util.List;

public class MovieSummary {
    public boolean adult;
    public String backdrop_path;
    public Object belongs_to_collection;
    public int budget;
    public List<Genre> genres;
    public String homepage;
    public int id;
    public String imdb_id;
    public String original_language;
    public String original_title;
    public String overview;
    public double popularity;
    public String poster_path;
    public List<ProductionCompany> production_companies;
    public List<ProductionCountry> production_countries;
    public String release_date;
    public int revenue;
    public int runtime;
    public List<SpokenLanguage> spoken_languages;
    public String status;
    public String tagline;
    public String title;
    public boolean video;
    public double vote_average;
    public int vote_count;

    public MovieSummary() {
    }

    public static class SpokenLanguage{
        public String english_name;
        public String iso_639_1;
        public String name;

        public SpokenLanguage() {
        }

        public SpokenLanguage(String english_name, String iso_639_1, String name) {
            this.english_name = english_name;
            this.iso_639_1 = iso_639_1;
            this.name = name;
        }
    }

    public static class Genre{
        public int id;
        public String name;

        public Genre() {
        }

        public Genre(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    public static class ProductionCompany{
        public int id;
        public String logo_path;
        public String name;
        public String origin_country;

        public ProductionCompany(int id, String logo_path, String name, String origin_country) {
            this.id = id;
            this.logo_path = logo_path;
            this.name = name;
            this.origin_country = origin_country;
        }

        public ProductionCompany() {
        }
    }

    public static class ProductionCountry{
        public String iso_3166_1;
        public String name;

        public ProductionCountry(String iso_3166_1, String name) {
            this.iso_3166_1 = iso_3166_1;
            this.name = name;
        }

        public ProductionCountry() {
        }
    }
}






