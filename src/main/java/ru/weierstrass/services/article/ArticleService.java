package ru.weierstrass.services.article;

import org.springframework.stereotype.Service;
import ru.weierstrass.components.DbService;
import ru.weierstrass.models.article.AndroidArticle;
import ru.weierstrass.models.article.Article;

@Service
public class ArticleService extends DbService<Article> {

    public Article loadObject( int articleId ) throws Exception {
        return loadObject( Article.class, "SELECT * FROM public_api_v01.promo_get_article( ? )", articleId );
    }

    public AndroidArticle loadAndroidObject( int articleId ) throws Exception {
        return new AndroidArticle( loadObject( articleId ) );
    }

}
