package tuannd.demo.data.model;

import java.util.List;

public class PaginableItemList<T> {
    private int pageSize;
    private int pageNumber;
    private long totalData;
    private int pageSizeNotComplete;
    private int pageNumberNotComplete;
    private long totalDataNotComplete;
    private List<T> listData;
    private List<T> listNotComplete;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public long getTotalData() {
        return totalData;
    }

    public void setTotalData(long totalData) {
        this.totalData = totalData;
    }

    public List<T> getListData() {
        return listData;
    }

    public void setListData(List<T> listData) {
        this.listData = listData;
    }

    public List<T> getListNotComplete() {
        return listNotComplete;
    }

    public void setListNotComplete(List<T> listNotComplete) {
        this.listNotComplete = listNotComplete;
    }

    public int getPageSizeNotComplete() {
        return pageSizeNotComplete;
    }

    public void setPageSizeNotComplete(int pageSizeNotComplete) {
        this.pageSizeNotComplete = pageSizeNotComplete;
    }

    public int getPageNumberNotComplete() {
        return pageNumberNotComplete;
    }

    public void setPageNumberNotComplete(int pageNumberNotComplete) {
        this.pageNumberNotComplete = pageNumberNotComplete;
    }

    public long getTotalDataNotComplete() {
        return totalDataNotComplete;
    }

    public void setTotalDataNotComplete(long totalDataNotComplete) {
        this.totalDataNotComplete = totalDataNotComplete;
    }
}