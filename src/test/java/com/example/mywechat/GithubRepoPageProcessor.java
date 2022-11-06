package com.example.mywechat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class GithubRepoPageProcessor implements PageProcessor {

    private final Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    /**
     * 记录总分页列表url数
     */
    private static List<String> urlList = new ArrayList<>();

    /**
     * 文章详情信息
     */
    private static List<Csdn> articleDetailInfoList = new ArrayList<>();

    @Override
    public void process(Page page) {
        // 【部分二】：定义如何抽取页面信息，并保存下来
        Html html = page.getHtml();

        // 根据url判断该页面属于列表页还是文章详情页面
        String url = page.getUrl().toString();
        log.info("页面url地址：【{}】", url);
        if (url.contains("article/details")) {
            // 详情页面处理逻辑...
            // 文章id
            int articleId = Integer.parseInt(url.substring(url.lastIndexOf('/') + 1));
            // 文章标题
            String articleTitle = html.xpath("//h1[@class='title-article']//text()").toString();
            // 文章发布时间
            String articleTime = html.xpath("//div[@class='bar-content']//span[@class='time']//text()").toString();
            // 文章所属分类
            String articleCategory = html.xpath("//a[@class='tag-link']//text()").toString();
            // 文章内容
            String articleContent = html.xpath("//article[@class='baidu_pl']").toString();
            log.info("文章id：【{}】  文章标题：【{}】 文章所属分类：【{}】", articleId, articleTitle, articleCategory);
            Csdn csdn = new Csdn(articleId, articleTitle, articleTime, articleCategory, articleContent);
            if (articleDetailInfoList.contains(csdn)) {
                return;
            }
            articleDetailInfoList.add(csdn);
        } else if (url.contains("article/list")) {
            // 列表页面处理逻辑...
            if (urlList.contains(url)) {
                return;
            }
            urlList.add(url);
            List<Selectable> articleList = html
                    .xpath("//div[@class='article-list']//div[@class='article-item-box csdn-tracking-statistics']").nodes();
            if (CollectionUtils.isEmpty(articleList)) {
                // 这里移除最后一条错误元素
                urlList.remove(urlList.get(urlList.size() - 1));
                log.info("总列表数：【{}】  总文章数：【{}】", urlList, articleDetailInfoList);
                return;
            }
            // 开始解析每一篇文章数据【文章标题，发送时间，文章详情url地址】
            articleList.forEach(article -> {
                // 文章标题
                String articleTitle = article.$("a", "text").toString();
                // 文章详情url地址
                String articleUrl = article.links().toString();
                // 文章发布时间
                String articleTime =
                        article.xpath("//div[@class='info-box d-flex align-content-center']//span[@class='date']/text()")
                                .toString();

                log.info("文章标题：【{}】 文章地址：【{}】 文章发布时间：【{}】", articleTitle, articleUrl, articleTime);

                // 进入文章内部获取文章详情内容
                page.addTargetRequests(article.links().all());
            });

            // 【部分三】：从页面发现后续的url地址来抓取 (这里因csdn暂时拿不了页面尾部的分页数，因此手动模拟了一下数据...)
            int nextPage = Integer.parseInt(url.substring(url.lastIndexOf('/') + 1)) + 1;
            String newUrl = "https://blog.csdn.net/qq_38225558/article/list/" + nextPage;
            page.addTargetRequest(newUrl);
        } else {
            // Other ...
            log.error("该页面url【{}】无法解析...", url);
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new GithubRepoPageProcessor()).addUrl("https://blog.csdn.net/qq_38225558/article/list/1").thread(5).run();
    }
}

@Data
@AllArgsConstructor
class Csdn {

    /**
     * id
     */
    private int id;
    /**
     * 文章标题
     */
    private String title;
    /**
     * 文章发布时间
     */
    private String time;
    /**
     * 文章所属分类
     */
    private String category;
    /**
     * 文章内容
     */
    private String content;

}