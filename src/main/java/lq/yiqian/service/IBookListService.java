package lq.yiqian.service;

import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.github.pagehelper.PageInfo;
import lq.yiqian.utils.es.pojo.Book;

import java.util.concurrent.TimeUnit;

/**
 * @author LQ
 * @create 2020-06-24 15:56
 */
public interface IBookListService {
    /**
     * 查书
     * @param bookName 书名
     */
    @Cached(expire = 2, timeUnit = TimeUnit.DAYS,name = "findByBookName", key = "#bookName + '_' + #page + '_' + #size",cacheType = CacheType.REMOTE)
    PageInfo<Book> findByBookName(String bookName, int page, int size);

    void save(String bookName, String path);
}
