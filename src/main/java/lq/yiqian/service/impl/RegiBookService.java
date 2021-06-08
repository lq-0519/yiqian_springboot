package lq.yiqian.service.impl;

import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import lq.yiqian.mapper.RegiBookMapper;
import lq.yiqian.domain.RegiBook;
import lq.yiqian.service.IRegiBookService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 缺书登记
 * @author LQ
 * @create 2020-06-24 17:51
 */
@Service
@Slf4j
public class RegiBookService implements IRegiBookService {
    @Resource
    private RegiBookMapper regiBookMapper;

    /**
     * 缺书登记
     */
    @Override
    public void save(RegiBook regiBook) {
        regiBook.setIsFund(0);//初始化isFund, isFund=0代表还没处理
        regiBook.setRegiDate(new Date());//记录登记的时间
        regiBookMapper.save(regiBook);
    }

    /**
     * 根据是否被处理查询regiBook
     */
    @Override
    public List<RegiBook> findByIsFund(Integer fund, Integer page, Integer size) {
        PageHelper.startPage(page, size);
//        RegiBook regiBook = new RegiBook();
//        regiBook.setIsFund(fund);
        return regiBookMapper.findByIsFund(fund);
//        return regiBookMapper.select(regiBook);
    }

    /**
     * 根据id查询
     */
    @Override
    public RegiBook findById(Integer id) {
        return regiBookMapper.findById(id);
    }

    /**
     * 更新书名和作者
     */
    @Override
    public void updateById_bookName_author_remarks(RegiBook regiBook) {
        regiBookMapper.updateById_bookName_author_remarks(regiBook);
    }

    /**
     * 更新找书结果
     */
    @Override
    public void updateById_result_isFund(Integer id, String result) {
        regiBookMapper.updateById_result_isFund(id, result);
    }

    @Override
    public List<RegiBook> findAllByInvitationCode(String id, Integer page, int size) {
        PageHelper.startPage(page, size);
        return regiBookMapper.findAllByInvitationCode(id);
    }

    /**
     * 根据ID删除
     */
    @Override
    public void delById(String id) {
        regiBookMapper.delById(id);
    }
}
