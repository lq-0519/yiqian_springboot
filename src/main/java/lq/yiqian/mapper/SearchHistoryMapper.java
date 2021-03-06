package lq.yiqian.mapper;

import lq.yiqian.domain.SearchHistory;
import lq.yiqian.domain.TopSearch;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author LQ
 * @create 2020-06-24 17:48
 */
public interface SearchHistoryMapper extends Mapper<SearchHistory> {
    /**
     * 查询所有
     * <p>
     * 按照时间倒序
     */
    @Select("select ip, searchTime, bookName, result, id from searchHistory order by searchTime desc")
    public List<SearchHistory> findAll();

    /**
     * 查询今天的搜索记录数
     */
    @Select("select count(id) from searchHistory where to_days(searchTime) = to_days(now())")
    int findTodayTotal();

    /**
     * 查询昨天的搜索记录数
     */
    @Select("SELECT count(id) FROM searchHistory where to_days(NOW()) - to_days(searchTime) = 1 ")
    int findYesterdayTotal();

    /**
     * 添加新的查询记录
     */
    @Insert("insert into searchHistory(ip, searchTime, bookName, result) " +
            " values(#{ip},#{searchTime},#{bookName},#{result})")
    void save(SearchHistory searchHistory);

    /**
     * 查询书库热搜
     * <p>
     * 根据书名分组, 然后在排序
     *
     */
    @Select("select bookName, count(*) numberOfSearches from searchHistory group by bookName ORDER BY count(*) DESC ")
    List<TopSearch> findTopSearch();

    @Select("select count(id) from searchHistory")
    Integer getTotalCount();
}
