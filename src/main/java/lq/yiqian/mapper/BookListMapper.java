package lq.yiqian.mapper;

import lq.yiqian.utils.es.pojo.Book;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 操作BookList表
 * @create 2020-06-24 15:57
 * @author yiqian
 */
public interface BookListMapper extends Mapper<Book> {
    /**
     * 查询所有
     */
    @Select("select id, bookName, path from bookList")
    public List<Book> findAll();

    /**
     * 根据书名查询
     */
    @Select("select id, bookName, path from bookList where bookName like #{bookName}")
    List<Book> findByBookName(String bookName);

    /**
     * 添加新书
     */
    @Insert("insert into bookList(bookName, path) values(#{bookName}, #{path})")
    void save(@Param("bookName") String bookName, @Param("path") String path);

    /**
     * 分页查询, 一页1000
     */
    @Select("select id, bookName, path from bookList limit #{page}, 1000")
    List<Book> findAllForPage(@Param("page") Integer page);
}
