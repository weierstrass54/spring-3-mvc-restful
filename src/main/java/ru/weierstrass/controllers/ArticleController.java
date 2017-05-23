package ru.weierstrass.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.weierstrass.components.request.PaginationRequestParams;
import ru.weierstrass.models.article.AndroidArticle;
import ru.weierstrass.models.article.Article;
import ru.weierstrass.models.article.Promo;
import ru.weierstrass.services.article.ArticleService;
import ru.weierstrass.services.article.PromoService;

@RestController
public class ArticleController {

    private PromoService _promoService;
    private ArticleService _articleService;

    @Autowired
    public ArticleController(PromoService promoService, ArticleService articleService) {
        _promoService = promoService;
        _articleService = articleService;
    }

    @RequestMapping(path = "/article/banner", method = RequestMethod.GET)
    public List<Promo> getBannerList() throws Exception {
        return _promoService.loadBannerList();
    }

    @RequestMapping(path = "/article/list", method = RequestMethod.GET)
    public List<Promo> getArticleList(
        @RequestParam(name = "chunk", required = true) int chunk,
        @RequestParam(name = "page", required = false, defaultValue = "1") int page
    ) throws Exception {
        PaginationRequestParams params = new PaginationRequestParams(chunk, page);
        if (!params.getErrors().isEmpty()) {
            throw new IllegalArgumentException(params.getErrorsString());
        }
        return _promoService.loadArticleList(params.getOffset(), params.getLimit());
    }

    @RequestMapping(path = "/article/article", method = RequestMethod.GET)
    public Article getArticle(@RequestParam(name = "id", required = true) int id) throws Exception {
        return _articleService.loadObject(id);
    }

    @RequestMapping(path = "/article/androidArticle", method = RequestMethod.GET)
    public AndroidArticle getAndroidArticle(@RequestParam(name = "id", required = true) int id)
        throws Exception {
        return _articleService.loadAndroidObject(id);
    }

}
