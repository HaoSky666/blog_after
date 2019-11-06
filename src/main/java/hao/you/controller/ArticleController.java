package hao.you.controller;

import hao.you.domain.Article;
import hao.you.service.ArticleService;
import hao.you.utils.ToolMethodUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("articles")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    //一页的文章条数
    private final Integer PAGE = 2;

    //查询所有文章
    @GetMapping
    public List<Article> getAllArticle() {
        List<Article> allArticle = articleService.getAllArticle();
        return allArticle;
    }

    //通过id查找文章
    @GetMapping("{id}")
    public Article getArticleById(@PathVariable("id") Integer id) {
        Article article = articleService.getArticleById(id);
        return article;
    }

    //查询文章总页数
    @GetMapping("pages")
    public Integer getPageArticle() {
        Integer allpage = 0;//总页数

        List<Article> allArticle = articleService.getAllArticle();
        //如果未查询到文章，直接返回空的文章页
        if (allArticle.size() != 0) {
            if (allArticle.size() % PAGE == 0) {
                allpage = allArticle.size() / PAGE;
            } else {
                allpage = allArticle.size() / PAGE + 1;
            }
        }
        return allpage;
    }

    //分页查询所有文章
    @GetMapping("pages/{id}")
    public List<Article> getPageArticle(@PathVariable("id") Integer id) {
        Integer page = 0;//当前页
        if (ToolMethodUtil.notNull(id)) page = id;
        List<Article> articles = articleService.getPageArticle(page * PAGE);
        return articles;
    }

    //分页查询某用户文章
    @GetMapping("{userId}/pages/{id}")
    public List<Article> getPageArticleByUser(@PathVariable("userId") String userId,@PathVariable("id") Integer id) {
        Integer page = 0;//当前页
        if (ToolMethodUtil.notNull(id)) page = id;
        List<Article> articles = articleService.getPageArticleByUser(userId, page * PAGE);
        return articles;
    }

    //删除文章
    @DeleteMapping("{id}")
    public void deleteArticle(@PathVariable("id") Integer id) {
        articleService.deleteArticleById(id);
    }

    //添加文章
    @PostMapping
    public Article addArticle(Article article) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        article.setArticle_time(sf.format(new Date()));
        articleService.addArticle(article);
        return article;
    }

    //修改文章
    @PutMapping("{id}")
    public Article update(@PathVariable("id") Integer id,Article article) {
        article.setArticle_id(id);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        article.setArticle_time(sf.format(new Date()));
        articleService.update(article);
        return article;
    }
}
