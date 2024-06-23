/* Design Class generic for paging data (any Object). Demo the use.
Include: Object, size, pageNumber,... */

import java.util.*;

public class PagedData<T> {
    private List<T> items; // Items for current page
    private int size; // Size of the page
    private int pageNumber; // Current page number
    private long totalItems; // Total number of items in the full list
    private int totalPages; // Total number of pages

    public PagedData(List<T> items, int size, int pageNumber, long totalItems) {
        this.items = items;
        this.size = size;
        this.pageNumber = pageNumber;
        this.totalItems = totalItems;
        this.totalPages = (int) ((totalItems + size - 1) / size);
    }

    // Getter and setter
    public List<T> getItems() {
        return items;
    }

    public int getSize() {
        return size;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public long getTotalItems() {
        return totalItems;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setTotalItems(long totalItems) {
        this.totalItems = totalItems;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    @Override
    public String toString() {
        return "PagedData{" +
                "items=" + items +
                ", size=" + size +
                ", pageNumber=" + pageNumber +
                ", totalItems=" + totalItems +
                ", totalPages=" + totalPages +
                '}';
    }
}
