package lq.yiqian.service.impl;

import lombok.extern.log4j.Log4j2;
import lq.yiqian.mapper.BookListMapper;
import lq.yiqian.service.ISearchHistoryService;
import lq.yiqian.service.IUtilsService;
import lq.yiqian.utils.es.pojo.Book;
import lq.yiqian.utils.es.repository.BookRepository;
import lq.yiqian.utils.es.resultMapper.HighlightResultMapper;
import lq.yiqian.utils.threadPool.ThreadPoolUtils;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author LQ
 * @create 2021-05-06 21:05
 */
@Service
@Log4j2
public class UtilsService implements IUtilsService {

    @Resource
    private ElasticsearchTemplate elasticsearchTemplate;

    @Resource
    private BookRepository bookRepository;

    @Resource
    private BookListMapper bookListMapper;

    @Resource
    private ServletContext servletContext;

    @Resource
    private ISearchHistoryService searchHistoryService;

    @Override
    public void addAll() {

    }

    @Override
    public void createIndex() {
        try {
            elasticsearchTemplate.deleteIndex(Book.class);
            elasticsearchTemplate.createIndex(Book.class);
            elasticsearchTemplate.putMapping(Book.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("AlibabaLowerCamelCaseVariableNaming")
    @Override
    public void testES() {
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("小米手机7", "234"));
        books.add(new Book("坚果手机R1", "wer"));
        books.add(new Book("华为META10", "sdf"));
        books.add(new Book("小米Mix2S", "xcv"));
        bookRepository.saveAll(books);
    }


    /**
     * 根据书名查询
     */
    @Override
    public AggregatedPage<Book> findByBookName(String bookName, int page, int size) {
        SearchQuery queryBuilder = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchQuery("bookName", bookName))
                .withHighlightFields(new HighlightBuilder
                        .Field("bookName")
                        .preTags("<span style='color:#FF4500' >")
                        .postTags("</span>"))
                .withPageable(PageRequest.of(page - 1, size))
                .build();
        return this.elasticsearchTemplate.queryForPage(queryBuilder, Book.class, new HighlightResultMapper());
    }

    @Override
    public void save(String bookName, String path) {
        bookRepository.save(new Book(bookName, path));
    }

    @SuppressWarnings("AlibabaLowerCamelCaseVariableNaming")
    @Override
    public void dataTransferToES() {
        log.warn("dataTransferToES, 数据转移开始...");
        long start = System.currentTimeMillis();
        ThreadPoolUtils.execute(() -> {
            int page = 1;
            List<Book> all;
            do {
                all = bookListMapper.findAllForPage((page - 1) * 1000);
                page++;
                LinkedList<Book> books = new LinkedList<>();
                for (Book book : all) {
                    Book book1 = new Book();
                    BeanUtils.copyProperties(book, book1);
                    books.add(book1);
                }
                bookRepository.saveAll(books);
            } while (all.size() == 1000);
        });
        long end = System.currentTimeMillis();
        log.warn("dataTransferToES, 数据转移耗时: {} ms", (end - start));
    }

    @Override
    public void updateSearchNum() {
        // 查询搜索历史, 获取总搜索次数
        Integer searchTotal = searchHistoryService.getTotalCount();
        // 将总的搜索次数放到session中
        servletContext.setAttribute("searchTotal", searchTotal + "");
    }
}
