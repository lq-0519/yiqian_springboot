package lq.yiqian.dao.query;

import java.io.Serializable;
import java.util.Date;

/**
 * 查询基类
 *
 * @author liqian477
 * @date 2021/10/24 14:33
 */
public class BaseQuery implements Serializable {

    private static final long serialVersionUID = -8481955341339313364L;

    /**
     * 开始条数
     */
    private int startRow;

    /**
     * 页码
     */
    private int page;

    /**
     * 页数
     */
    private int pageSize;

    /**
     * 排序规则
     */
    private String orderBy;

    /**
     * 数据的状态标识
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date created;

    /**
     * 最后修改时间
     */
    private Date modified;

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
        resetStartRow();
    }

    /**
     * 修正pageSize
     */
    public int getPageSize() {
        return pageSize <= 0 ? 10 : pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
        resetStartRow();
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    /**
     * 检查page、pageSize 重置 startRow
     */
    private void resetStartRow() {
        //noinspection ManualMinMaxCalculation
        page = page < 1 ? 1 : page;
        pageSize = pageSize <= 0 ? 10 : pageSize;
        this.startRow = (page - 1) * pageSize;
    }

    @Override
    public String toString() {
        return "BaseQuery{" +
                "startRow=" + startRow +
                ", page=" + page +
                ", pageSize=" + pageSize +
                ", orderBy='" + orderBy + '\'' +
                ", status=" + status +
                ", created=" + created +
                ", modified=" + modified +
                '}';
    }
}
