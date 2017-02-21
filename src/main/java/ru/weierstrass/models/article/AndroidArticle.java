package ru.weierstrass.models.article;

public class AndroidArticle extends Article {

    protected String caption;

    @Override
    public String getImage() {
        return this.image;
    }

    @Override
    public String getContentFull() {
        //TODO: вырезать лишнее через какой-нибудь HTML парсер
        return "modified";
    }

    public String getCaption() {
        return this.caption;
    }

    public AndroidArticle( Article article ) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.image = article.getImage();
        this.created = article.getCreated();
        this.contentShort = article.getContentShort();
        this.contentFull = article.getContentFull();
        this.caption = "https://e2e4online.ru/shop/info/events/?id=" + this.getId();
    }

}
