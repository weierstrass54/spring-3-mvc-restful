package ru.weierstrass.services.article;

import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.weierstrass.components.database.ORMDatabaseService;
import ru.weierstrass.models.article.AndroidArticle;
import ru.weierstrass.models.article.Article;

@Service
public class ArticleService extends ORMDatabaseService<Article> {

    @Autowired
    public ArticleService(DataSource db) {
        super(db);
    }

    public Article loadObject(int articleId)
        throws SQLException, InstantiationException, IllegalAccessException {
        return loadObject(Article.class, "SELECT * FROM public_api_v01.promo_get_article( ? )",
            articleId);
    }

    public AndroidArticle loadAndroidObject(int articleId)
        throws SQLException, InstantiationException, IllegalAccessException {
        return new AndroidArticle(loadObject(articleId));
    }

}
