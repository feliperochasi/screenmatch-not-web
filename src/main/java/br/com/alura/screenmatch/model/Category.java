package br.com.alura.screenmatch.model;

public enum Category {
    ACAO("Action", "Acao"),
    ROMANCE("Romance", "Romance"),
    COMEDIA("Comedy", "Comedia"),
    DRAMA("Drama", "Drama"),
    FICCAO("Fiction", "Ficcao"),
    CRIME("Crime", "Crime");

    private String categoryOmdb;
    private String portugueseCategory;

    Category(String categoryOmdb, String portugueseCategory) {
        this.categoryOmdb = categoryOmdb;
        this.portugueseCategory = portugueseCategory;
    }

    public static Category fromString(String text) {
        for (Category category : Category.values()) {
            if (category.categoryOmdb.equalsIgnoreCase(text)) {
                return category;
            } else if (category.portugueseCategory.equalsIgnoreCase(text)) {
                return category;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }


}
