package hao.you.service;

import hao.you.domain.Article;

import java.util.List;

public interface ArticleService {
    public Integer addArticle(Article article);
    public Integer deleteArticleById(int id);
    public Integer update(Article article);
    public Article getArticleById(Integer id);
    public List<Article> getAllArticle();
    public List<Article> getPageArticle(Integer page);
    public List<Article> getArticleByUser(String user);
    public List<Article> getPageArticleByUser(String user, Integer page);
}
