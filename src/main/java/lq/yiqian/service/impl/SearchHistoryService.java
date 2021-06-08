package lq.yiqian.service.impl;

import com.github.pagehelper.PageHelper;
import lq.yiqian.mapper.SearchHistoryMapper;
import lq.yiqian.domain.SearchHistory;
import lq.yiqian.domain.TopSearch;
import lq.yiqian.service.ISearchHistoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author LQ
 * @create 2020-06-24 17:51
 */
@Service
public class SearchHistoryService implements ISearchHistoryService {
    @Resource
    private SearchHistoryMapper searchHistoryMapper;

    /**
     * 查询今天的搜索记录数
     */
    @Override
    public int findTodayTotal() {
        return searchHistoryMapper.findTodayTotal();
    }

    /**
     * 查询昨天的搜索记录数
     */
    @Override
    public int findYesterdayTotal() {
        return searchHistoryMapper.findYesterdayTotal();
    }

    /**
     * 使用分页查询所有
     */
    @Override
    public List<SearchHistory> findAllByPage(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        return searchHistoryMapper.findAll();
    }

    /**
     * 产生新的搜索记录
     */
    @Override
    public void save(SearchHistory searchHistory) {
        searchHistoryMapper.save(searchHistory);
    }

    /**
     * 查询书库热搜
     */
    @Override
    public List<TopSearch> findTopSearch(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        return searchHistoryMapper.findTopSearch();
    }

    @Override
    public Integer getTotalCount() {
        return searchHistoryMapper.getTotalCount();
    }
}
